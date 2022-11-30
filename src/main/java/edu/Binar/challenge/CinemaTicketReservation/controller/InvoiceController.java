package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.InvoiceConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.InvoiceDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import edu.Binar.challenge.CinemaTicketReservation.service.InvoiceService;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        Invoice invoiceRequest = InvoiceConverter.convertDtoToEntity(invoiceDto);
        Invoice invoice = invoiceService.createInvoice(invoiceRequest);

        InvoiceDto invoiceResponse = InvoiceConverter.convertEntityToDto(invoice);

        return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<InvoiceDto> getAllInvoices(){
        return invoiceService
                .getAllInvoices()
                .stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).toList();
    }

    @GetMapping("/find")
    public List<Invoice> getInvoicesByInvoiceNumber(@RequestParam(value = "invoiceNumber") String invoiceNumber) throws ResourceNotFoundException {
        if (invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).isEmpty()){
            throw new ResourceNotFoundException("Invoice not found for this invoice number: " + invoiceNumber);
        }

        return invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
    }

    @GetMapping("/generate")
    public String generateInvoice(@RequestParam("invoiceNumber") String invoiceNumber, @RequestParam("fileFormat") String fileFormat) throws IOException, JRException {
        return invoiceService.generateInvoice(invoiceNumber, fileFormat);
    }
}