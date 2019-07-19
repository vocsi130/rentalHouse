package edu.mum.rentalHouse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.Residence;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence,Long> {

	@Query("select distinct r from Residence r where r.floor is Null")
	List<Residence> getHouses();
	
	@Query("select distinct r from Residence r where r.lotSize is Null")
	List<Residence> getApartments();
	
}
