package edu.mum.rentalHouse.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

	//@Query("Select distinct c from Contract c join c.tenant t where t.firstname=:firstName")
	@Query("Select distinct c from Contract c  where c.active='true'")
	public List<Contract> getContractTenant(String firstName);
	
	//@Query("Select distinct c from Contract c")
	//public List<Contract> getContractActive();

	//@Query("Select distinct c from Contract c join c.residence r  join r.address a where a.street=:street")
	@Query("Select distinct c from Contract c  where c.active='true'")
	public List<Contract> getContractResidenceStreet(String street);

	//@Query("Select distinct c from Contract c  where c.dateStart=: date")
	@Query("Select distinct c from Contract c")
	public List<Contract> getContractDate(LocalDate date);

	//public List<Contract> findByActive(true);

}
