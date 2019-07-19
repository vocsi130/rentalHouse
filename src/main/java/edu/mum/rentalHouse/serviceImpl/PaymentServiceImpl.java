package edu.mum.rentalHouse.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.rentalHouse.model.Payment;
import edu.mum.rentalHouse.model.Tenant;
import edu.mum.rentalHouse.repository.PaymentRepository;
import edu.mum.rentalHouse.repository.TenantRepository;
import edu.mum.rentalHouse.service.PaymentService;

@Service
@Transactional
public class PaymentServiceImpl  implements PaymentService{
		 
	@Autowired
	     private PaymentRepository paymentRepository;

	@Autowired
    private TenantRepository tenantpository;

	@Override
	public void addPayment(Payment p) {
		// TODO Auto-generated method stub
	      Tenant te=tenantpository.findById(p.getTenant().getId()).get();
	        p.setTenant(te);
	        paymentRepository.save(p);
	  
	}

	@Override
	public List<Payment> findAllPayment() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}

	@Override
	public Payment get(Long id) {
		// TODO Auto-generated method stub
		return paymentRepository.findById(id).get();
	}

	

	@Override
	public List<Payment> incorrectPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		paymentRepository.deleteById(id);
	}

	

	}