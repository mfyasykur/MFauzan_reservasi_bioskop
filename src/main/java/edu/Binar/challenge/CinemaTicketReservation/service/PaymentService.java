package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();
    Payment getPaymentById(Long paymentId) throws ResourceNotFoundException;
    Payment createPayment(Payment payment);
    Payment updatePayment(Long paymentId, Payment payment) throws ResourceNotFoundException;
    void deletePayment(Long paymentId) throws ResourceNotFoundException;
}
