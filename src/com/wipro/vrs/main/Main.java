package com.wipro.vrs.main;

import java.time.LocalDate;
import java.util.ArrayList;
import com.wipro.vrs.entity.Vehicle;
import com.wipro.vrs.entity.Customer;
import com.wipro.vrs.entity.Rental;
import com.wipro.vrs.service.VehicleRentalService;
import com.wipro.vrs.util.InvalidCustomerException;
import com.wipro.vrs.util.VehicleNotAvailableException;
import com.wipro.vrs.util.RentalOperationException;

public class Main {
	public static void main(String[] args) {

		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle("V001", "Swift", "CAR", true, 1500.0));
		vehicles.add(new Vehicle("V002", "Activa", "BIKE", true, 500.0));
		vehicles.add(new Vehicle("V003", "Innova", "SUV", false, 2500.0)); // currently unavailable
		// Initialize customers
		ArrayList<Customer> customers = new ArrayList<>();
		customers.add(new Customer("C001", "Rahul Sharma", "DL12345", "REGULAR", 0));
		customers.add(new Customer("C002", "ABC Corp", "NA_CORP", "CORPORATE", 0));
		// Initialize rentals list (initially empty)
		ArrayList<Rental> rentals = new ArrayList<>();
		VehicleRentalService service = new VehicleRentalService(vehicles, customers, rentals);
		try {

			service.validateCustomer("C001");
			service.checkVehicleAvailability("V001");

			Rental rental = service.startRental("C001", "V001", 3);
			System.out.println("Rental Started Successfully! Rental ID: " + rental.getRentalId());

			LocalDate returnDate = LocalDate.now().plusDays(4);
			double amount = service.endRental(rental.getRentalId(), returnDate);
			System.out.println("Vehicle Returned. Total Charge: Rs. " + amount);
		} catch (InvalidCustomerException | VehicleNotAvailableException | RentalOperationException ex) {
			System.out.println(ex.toString());
		} catch (Exception ex) {

			System.out.println("Unexpected error: " + ex.toString());
		}
	}
}