package edu.mum.rentalHouse.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import edu.mum.rentalHouse.model.Contract;

public interface ContractService {

	//public void createContract(Contract contract);
	public void updateContract(Contract contract, Long tenantId, Long residenceId);
	public void deleteContract(Long id);
	public Contract getContract(Long id);
	public List<Contract> getAll();
	public List<Contract> getContractTenant(String firstName);
	public List<Contract> getContractDate(LocalDate date);
	public List<Contract> getContractInactive();
	public List<Contract> getContractResidence(String  street);
	public void createContract(Contract contract, Long tenantId, Long residenceId);
	
	
}
