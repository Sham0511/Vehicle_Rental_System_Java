package com.wipro.vrs.util;

public class RentalOperationException extends Exception{
	public RentalOperationException(String message) {
		super(message);
	}
@Override
public String toString() {
	return "RentalOperationException:operation cannot be completed due to invalid rental details, duplicate return attempts, or mismatched customer/vehicle information";
}
}
