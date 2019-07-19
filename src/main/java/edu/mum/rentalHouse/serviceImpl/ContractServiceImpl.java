package edu.mum.rentalHouse.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.rentalHouse.model.Contract;
import edu.mum.rentalHouse.model.Residence;
import edu.mum.rentalHouse.model.Tenant;
import edu.mum.rentalHouse.repository.ContractRepository;
import edu.mum.rentalHouse.repository.ResidenceRepository;
import edu.mum.rentalHouse.repository.TenantRepository;
import edu.mum.rentalHouse.service.ContractService;
@Service
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private ResidenceRepository residenceRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Override
	public void createContract(Contract contract, Long tenantId, Long residenceId) {
		// TODO Auto-generated method stub
         Tenant tenant=tenantRepository.findById(tenantId).get();
        Residence residence=residenceRepository.findById(residenceId).get();
        contract.setTenant(tenant);	
        contract.setResidence(residence);
		contractRepository.save(contract);	
	}

	@Override
	public void updateContract(Contract contract, Long tenantId, Long residenceId) {
		// TODO Auto-generated method stub
		   Tenant tenant=null;
	         Residence residence=null;
	        contract.setTenant(tenant);	
	        contract.setResidence(residence);
			contractRepository.save(contract);
	}

	@Override
	public void deleteContract(Long id) {
		// TODO Auto-generated method stub
		contractRepository.deleteById(id);
	}

	@Override
	public Contract getContract(Long id) {
		// TODO Auto-generated method stub
		return contractRepository.findById(id).get();
	}

	@Override
	public List<Contract> getAll() {
		// TODO Auto-generated method stub
		return contractRepository.findAll();
	}

	@Override
	public List<Contract> getContractTenant(String  firstName) {
		// TODO Auto-generated method stub
		return contractRepository.getContractTenant(firstName);
	}

	@Override
	public List<Contract> getContractDate(LocalDate date) {
		// TODO Auto-generated method stub
		return contractRepository.getContractDate(date);
	}

	@Override
	public List<Contract> getContractResidence(String  street) {
		// TODO Auto-generated method stub
		return contractRepository.getContractResidenceStreet(street);
	}

	@Override
	public List<Contract> getContractInactive() {
		// TODO Auto-generated method stub
		return contractRepository.findAll();
	}

}
