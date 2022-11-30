package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.PaymentConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.PaymentDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;
import edu.Binar.challenge.CinemaTicketReservation.service.PaymentService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments/")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments().stream().map(payment -> new ModelMapper().map(payment, PaymentDto.class))
                .toList();
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable(value = "paymentId") Long paymentId)
            throws ResourceNotFoundException {

        Payment payment = paymentService.getPaymentById(paymentId);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return ResponseEntity.ok().body(paymentResponse);
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto){

        Payment paymentRequest = PaymentConverter.convertDtoToEntity(paymentDto);
        Payment payment = paymentService.createPayment(paymentRequest);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable(value = "paymentId") Long paymentId, @Valid @RequestBody PaymentDto paymentDto) throws ResourceNotFoundException {

        Payment paymentRequest = PaymentConverter.convertDtoToEntity(paymentDto);
        Payment payment = paymentService.updatePayment(paymentId, paymentRequest);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable(value = "paymentId") Long paymentId) throws ResourceNotFoundException {
        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok().body("Payment with ID(" + paymentId + ") deleted successfully");
    }
}
