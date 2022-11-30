package edu.Binar.challenge.CinemaTicketReservation.service.impl;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;
import edu.Binar.challenge.CinemaTicketReservation.repository.PaymentRepository;
import edu.Binar.challenge.CinemaTicketReservation.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public Payment getPaymentById(Long paymentId) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + paymentId));

        return paymentRepository.save(payment);
    }

    @Override
    public Payment createPayment(Payment payment){
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long paymentId, Payment paymentDetails) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + paymentId));

        payment.setAmount(paymentDetails.getAmount());
        payment.setTimeStamp(paymentDetails.getTimeStamp());
        payment.setMethod(paymentDetails.getMethod());
        payment.setBooking(paymentDetails.getBooking());

        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long paymentId) throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + paymentId));

        paymentRepository.delete(payment);
    }
}
