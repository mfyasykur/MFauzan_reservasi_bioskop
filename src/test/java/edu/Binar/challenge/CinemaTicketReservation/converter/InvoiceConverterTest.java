package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.InvoiceDto;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class InvoiceConverterTest {

    @Test
    public void convertInvoiceDtoToEntity(){
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceId(4L);
        invoiceDto.setInvoiceNumber("TDR3000");
        invoiceDto.setDate(LocalDate.now());
        invoiceDto.setTime("09.30");
        invoiceDto.setRow("A");
        invoiceDto.setSeatNumber("10");
        invoiceDto.setHall("B");
        invoiceDto.setPrice(BigDecimal.valueOf(45000));
        invoiceDto.setCreatedAt(LocalDateTime.now());

        Invoice invoice = InvoiceConverter.convertDtoToEntity(invoiceDto);

        assertEquals(invoice.getInvoiceId(), invoiceDto.getInvoiceId());
        assertEquals(invoice.getInvoiceNumber(), invoiceDto.getInvoiceNumber());
        assertEquals(invoice.getDate(), invoiceDto.getDate());
        assertEquals(invoice.getTime(), invoiceDto.getTime());
        assertEquals(invoice.getRow(), invoiceDto.getRow());
        assertEquals(invoice.getSeatNumber(), invoiceDto.getSeatNumber());
        assertEquals(invoice.getHall(), invoiceDto.getHall());
        assertEquals(invoice.getPrice(), invoiceDto.getPrice());
        assertEquals(invoice.getCreatedAt(), invoiceDto.getCreatedAt());
    }

    @Test
    public void convertInvoiceToDto(){
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(4L);
        invoice.setInvoiceNumber("TDR3000");
        invoice.setDate(LocalDate.now());
        invoice.setTime("09.30");
        invoice.setRow("A");
        invoice.setSeatNumber("10");
        invoice.setHall("B");
        invoice.setPrice(BigDecimal.valueOf(45000));
        invoice.setCreatedAt(LocalDateTime.now());

        InvoiceDto invoiceDto = InvoiceConverter.convertEntityToDto(invoice);

        assertEquals(invoice.getInvoiceId(), invoiceDto.getInvoiceId());
        assertEquals(invoice.getInvoiceNumber(), invoiceDto.getInvoiceNumber());
        assertEquals(invoice.getDate(), invoiceDto.getDate());
        assertEquals(invoice.getTime(), invoiceDto.getTime());
        assertEquals(invoice.getRow(), invoiceDto.getRow());
        assertEquals(invoice.getSeatNumber(), invoiceDto.getSeatNumber());
        assertEquals(invoice.getHall(), invoiceDto.getHall());
        assertEquals(invoice.getPrice(), invoiceDto.getPrice());
        assertEquals(invoice.getCreatedAt(), invoiceDto.getCreatedAt());
    }
}
