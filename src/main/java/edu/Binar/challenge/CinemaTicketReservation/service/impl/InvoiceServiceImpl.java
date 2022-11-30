package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import edu.Binar.challenge.CinemaTicketReservation.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getInvoiceByInvoiceNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumberOrderByCreatedAtDesc(invoiceNumber);
    }

    @Override
    public String generateInvoice(@NotNull String invoiceNumber, @NotNull String fileFormat) throws FileNotFoundException, JRException {
        String path = "./generated-invoices";
        List<Invoice> invoices = invoiceRepository.findByInvoiceNumberOrderByCreatedAtDesc(invoiceNumber);

        File file = ResourceUtils.getFile("classpath:invoice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoices);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "MyCinema");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if (fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/invoice-" + invoiceNumber + ".pdf");
        }

        return "invoice-" + invoiceNumber + ".pdf generated in : " + path;
    }

}
