package edu.mum.rentalHouse.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DiscriminatorOptions;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorOptions(force = true) 
public class Residence {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate builtDate;
	
	private int squareFt;
	  
	@OneToMany(mappedBy="residence",cascade = CascadeType.ALL)
	private List<Contract> contracts=new ArrayList<Contract>();
	
	@Embedded
	private Address address;

	
	
	public Residence() {
	
	}
	
	public Residence(LocalDate builtDate, int squareFt) {
		this.builtDate = builtDate;
		this.squareFt = squareFt;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBuiltDate() {
		return builtDate;
	}

	public void setBuiltDate(LocalDate builtDate) {
		this.builtDate = builtDate;
	}

	public int getSquareFt() {
		return squareFt;
	}

	public void setSquareFt(int squareFt) {
		this.squareFt = squareFt;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public void addContract(Contract contract) {
		contracts.add(contract);
		   contract.setResidence(this);
		}
	
}
