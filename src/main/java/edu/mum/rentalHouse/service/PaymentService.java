package edu.mum.rentalHouse.service;

import java.util.List;
import edu.mum.rentalHouse.model.*;
public interface PaymentService {

    public void addPayment(Payment p);
    public List<Payment > findAllPayment();
       public Payment get(Long id) ;
    public List<Payment> incorrectPayment();

}
