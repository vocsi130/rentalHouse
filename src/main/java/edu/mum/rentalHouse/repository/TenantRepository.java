package edu.mum.rentalHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.rentalHouse.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
