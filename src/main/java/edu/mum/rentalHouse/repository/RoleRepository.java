package edu.mum.rentalHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.Role;


@Repository
public interface RoleRepository  extends JpaRepository<Role, String>{

//	@Query("from role r where r.name=")
	public Role findByName(String name);

}
