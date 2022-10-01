package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;
import edu.Binar.challenge.CinemaTicketReservation.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema-v1")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments/")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "paymentId") Long paymentId)
            throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

        return ResponseEntity.ok().body(payment);
    }

    @PostMapping("/payments")
    public Payment createPayment(@Valid @RequestBody Payment payment){
        return paymentRepository.save(payment);
    }

    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable(value = "paymentId") Long paymentId, @Valid @RequestBody Payment paymentDetails)
            throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

        payment.setAmount(paymentDetails.getAmount());
        payment.setTimeStamp(paymentDetails.getTimeStamp());
        payment.setDiscountCouponId(paymentDetails.getDiscountCouponId());
        payment.setRemoteTransactionId(paymentDetails.getRemoteTransactionId());
        payment.setMethod(paymentDetails.getMethod());
        payment.setBooking(paymentDetails.getBooking());
        final Payment updatedPayment = paymentRepository.save(payment);

        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/payments/{paymentId}")
    public Map<String, Boolean> deletePayment(@PathVariable(value = "paymentId") Long paymentId)
            throws ResourceNotFoundException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

        paymentRepository.delete(payment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
