package edu.mum.rentalHouse.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.mum.rentalHouse.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//@Query("from Payment p where p.payDate>?")
//public List<Payment> latePayments(LocalDate date);

}
