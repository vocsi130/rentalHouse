package edu.mum.rentalHouse.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mum.rentalHouse.model.Apartment;
import edu.mum.rentalHouse.model.House;
import edu.mum.rentalHouse.model.Residence;

public interface ResidenceService {

	public void saveResidence(Residence residence);
	public void updateResidence(Residence residence);
	public void deleteResidence(Long id);
	public Residence get(Long id);
	public List<Residence> getAll();
	public List<Residence> getHouses();
	public List<Residence> getApartments();
	boolean createPDFHouse(List<House> tenants, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createPDFApartment(List<Apartment> tenants, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
