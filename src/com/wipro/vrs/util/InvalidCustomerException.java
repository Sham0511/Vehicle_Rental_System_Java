
package com.wipro.vrs.util;

public class InvalidCustomerException extends Exception{
public InvalidCustomerException(String message) {
	super(message);
	
}
@Override
public String toString() {
	return "InvalidCustomerException:Invalid customer ID " 
			+ "Customrer is null, empty or does not exist";
}
}
