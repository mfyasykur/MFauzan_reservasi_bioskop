package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.converter.InvoiceConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.InvoiceDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import edu.Binar.challenge.CinemaTicketReservation.service.InvoiceService;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/find")
    public List<Invoice> getInvoicesByInvoiceNumber(@RequestParam(value = "invoiceNumber") String invoiceNumber) throws ResourceNotFoundException {
        if (invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).isEmpty()){
            throw new ResourceNotFoundException("Invoice not found for this invoice number: " + invoiceNumber);
        }

        return invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        Invoice invoiceRequest = InvoiceConverter.convertDtoToEntity(invoiceDto);
        Invoice invoice = invoiceService.createInvoice(invoiceRequest);

        InvoiceDto invoiceResponse = InvoiceConverter.convertEntityToDto(invoice);

        return new ResponseEntity<InvoiceDto>(invoiceResponse, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String showInvoice(Model model){
        model.addAttribute("invoice", invoiceService.getInvoice());

        return "invoice";
    }

    @PostMapping("/generate")
    public String generateInvoice(@RequestParam("invoiceNumber") String invoiceNumber, @RequestParam("fileFormat") String fileFormat) throws JRException, IOException {
        String fileLink = invoiceService.generateInvoice(invoiceNumber, fileFormat);

        return "redirect:/"+fileLink;
    }
}
