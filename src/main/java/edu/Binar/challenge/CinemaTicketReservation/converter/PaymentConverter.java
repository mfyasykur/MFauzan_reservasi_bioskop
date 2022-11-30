package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.PaymentDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Payment;
import org.modelmapper.ModelMapper;

public class PaymentConverter {

    private PaymentConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Payment convertDtoToEntity(PaymentDto paymentDto){
        return modelMapper.map(paymentDto, Payment.class);
    }

    public static PaymentDto convertEntityToDto(Payment payment){
        return modelMapper.map(payment, PaymentDto.class);
    }
}
