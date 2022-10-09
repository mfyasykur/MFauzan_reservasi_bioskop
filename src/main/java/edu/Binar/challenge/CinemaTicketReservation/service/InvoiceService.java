package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface InvoiceService {

    @Autowired
    Invoice createInvoice(Invoice invoice);

    @Autowired
    String generateInvoice(String invoiceNumber, String fileFormat) throws JRException, IOException;

    @Autowired
    List<Invoice> getInvoiceByInvoiceNumber(String invoiceNumber) throws ResourceNotFoundException;

    @Autowired
    List<Invoice> getAllInvoices();
}
