package edu.mum.rentalHouse.model;

import javax.persistence.Entity;

@Entity
public class Apartment extends Residence {
	private int floor;

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

}
