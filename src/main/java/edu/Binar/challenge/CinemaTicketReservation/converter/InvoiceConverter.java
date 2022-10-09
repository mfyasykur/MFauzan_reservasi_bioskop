package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.InvoiceDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import org.modelmapper.ModelMapper;

public class InvoiceConverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Invoice convertDtoToEntity(InvoiceDto invoiceDto){
        return modelMapper.map(invoiceDto, Invoice.class);
    }

    public static InvoiceDto convertEntityToDto(Invoice invoice){
        return modelMapper.map(invoice, InvoiceDto.class);
    }
}
