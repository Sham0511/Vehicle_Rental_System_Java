package com.wipro.vrs.entity;

public class Customer {
	private String customerID;
	private String name;
	private String licenseNumber;
	private String customertype;
	private int activeRentals;
	
	public Customer(String customerID,String name ,String licenseNumber,String customertype,int activerentals) {
		this.customerID=customerID;
		this.name=name;
		this.licenseNumber=licenseNumber;
		this.customertype=customertype;
		this.activeRentals=activeRentals;
}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public int getActiveRentals() {
		return activeRentals;
	}

	public void setActiveRentals(int activeRentals) {
		this.activeRentals = activeRentals;
	}
	
}