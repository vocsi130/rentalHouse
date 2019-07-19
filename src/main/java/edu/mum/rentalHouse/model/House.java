package edu.mum.rentalHouse.model;


import javax.persistence.Entity;

@Entity
public class House extends Residence {
	private int lotSize;
	
	public int getLotSize() {
		return lotSize;
	}

	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}
	
	
}
