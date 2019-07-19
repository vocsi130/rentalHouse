package edu.mum.rentalHouse.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private double amount;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate payDate; 
	
	@ManyToOne
	@JoinColumn(name="tenant_id")
	   private Tenant tenant;

	
	public Payment() {
	
	}

	
	
	public Payment(double amount, LocalDate payDate) {
		this.amount = amount;
		this.payDate = payDate;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	
	
	
}
