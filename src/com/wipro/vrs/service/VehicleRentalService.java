package com.wipro.vrs.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.wipro.vrs.entity.Customer;
import com.wipro.vrs.entity.Vehicle;
import com.wipro.vrs.entity.Rental;
import com.wipro.vrs.util.InvalidCustomerException;
import com.wipro.vrs.util.VehicleNotAvailableException;
import com.wipro.vrs.util.RentalOperationException;

public class VehicleRentalService {

	private ArrayList<Vehicle> vehicles;
	private ArrayList<Customer> customers;
	private ArrayList<Rental> rentals;

	public VehicleRentalService(ArrayList<Vehicle> vehicles, ArrayList<Customer> customers, ArrayList<Rental> rentals) {
		this.vehicles = vehicles;
		this.customers = customers;
		this.rentals = rentals;
	}

	public boolean validateCustomer(String customerId) throws InvalidCustomerException {

		if (customerId == null || customerId.trim().isEmpty()) {
			throw new InvalidCustomerException("Invalid customer");
		}

		for (Customer c : customers) {
			if (c.getCustomerID().equals(customerId)) {
				return true;
			}
		}

		throw new InvalidCustomerException("Customer not found");
	}

	public boolean checkVehicleAvailability(String vehicleId) throws VehicleNotAvailableException {

		for (Vehicle v : vehicles) {
			if (v.getVehicleId().equals(vehicleId)) {
				if (v.isAvailable()) {
					return true;
				} else {
					throw new VehicleNotAvailableException("Vehicle not available");
				}
			}
		}

		throw new VehicleNotAvailableException("Vehicle not found");
	}

	public Rental startRental(String customerId, String vehicleId, int days)
			throws InvalidCustomerException, VehicleNotAvailableException, RentalOperationException {

		if (days <= 0) {
			throw new RentalOperationException("Invalid days");
		}

		validateCustomer(customerId);
		checkVehicleAvailability(vehicleId);

		Customer customer = null;
		Vehicle vehicle = null;

		for (Customer c : customers) {
			if (c.getCustomerID().equals(customerId)) {
				customer = c;
				break;
			}
		}

		for (Vehicle v : vehicles) {
			if (v.getVehicleId().equals(vehicleId)) {
				vehicle = v;
				break;
			}
		}

		if (customer == null || vehicle == null) {
			throw new RentalOperationException("Rental failed");
		}

		String rentalId = "R" + (rentals.size() + 1);
		LocalDate startDate = LocalDate.now();
		LocalDate expectedDate = startDate.plusDays(days);

		Rental rental = new Rental(rentalId, customerId, vehicleId, startDate, null, expectedDate);

		rental.setClosed(false);
		rentals.add(rental);

		vehicle.setAvailable(false);
		customer.setActiveRentals(customer.getActiveRentals() + 1);

		return rental;
	}

	public double endRental(String rentalId, LocalDate actualEndDate) throws RentalOperationException {

		Rental rental = null;

		for (Rental r : rentals) {
			if (r.getRentalId().equals(rentalId)) {
				rental = r;
				break;
			}
		}

		if (rental == null || rental.isClosed()) {
			throw new RentalOperationException("Return failed");
		}

		rental.setActualEndDate(actualEndDate);
		rental.setClosed(true);

		Vehicle vehicle = null;
		Customer customer = null;

		for (Vehicle v : vehicles) {
			if (v.getVehicleId().equals(rental.getVehicleId())) {
				vehicle = v;
				break;
			}
		}

		for (Customer c : customers) {
			if (c.getCustomerID().equals(rental.getCustomerId())) {
				customer = c;
				break;
			}
		}

		if (vehicle == null || customer == null) {
			throw new RentalOperationException("Invalid return");
		}

		vehicle.setAvailable(true);
		customer.setActiveRentals(customer.getActiveRentals() - 1);

		long daysUsed = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getActualEndDate());

		if (daysUsed <= 0) {
			daysUsed = 1;
		}

		double amount = daysUsed * vehicle.getBaseDailyRate();

		if (rental.getActualEndDate().isAfter(rental.getExpectedDate())) {
			long lateDays = ChronoUnit.DAYS.between(rental.getExpectedDate(), rental.getActualEndDate());
			amount = amount + (lateDays * 500);
		}

		if (customer.getCustomertype().equalsIgnoreCase("CORPORATE")) {
			amount = amount * 0.9;
		}

		rental.setTotalCharge(amount);
		return amount;
	}
}
