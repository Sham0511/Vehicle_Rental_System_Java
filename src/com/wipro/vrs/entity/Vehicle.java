package com.wipro.vrs.entity;

public class Vehicle {
	private String vehicleId;
	private String model;
	private String vehicleType;
	private boolean available;
	private double baseDailyRate;
	
	public Vehicle(String vehicleId,String model,String vehicleType,boolean available,double baseDailyRate) {
		this.vehicleId=vehicleId;
		this.model=model;
		this.vehicleType=vehicleType;
		this.available=available;
		this.baseDailyRate=baseDailyRate;
}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getBaseDailyRate() {
		return baseDailyRate;
	}

	public void setBaseDailyRate(double baseDailyRate) {
		this.baseDailyRate = baseDailyRate;
	}
	
}