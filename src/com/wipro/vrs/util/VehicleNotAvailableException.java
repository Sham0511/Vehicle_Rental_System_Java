package com.wipro.vrs.util;

public class VehicleNotAvailableException extends Exception {
	public VehicleNotAvailableException (String message) {
		super(message);
	}
@Override
public String toString() {
	return "VehicleNotAvailableException:vehicle either does not exist in the fleet or is currently marked as unavailable (already rented or out of service)";
}
}