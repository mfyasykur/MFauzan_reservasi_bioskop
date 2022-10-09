package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.converter.InvoiceConverter;
import edu.Binar.challenge.CinemaTicketReservation.dto.InvoiceDto;
import edu.Binar.challenge.CinemaTicketReservation.exception.ResourceNotFoundException;
import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import edu.Binar.challenge.CinemaTicketReservation.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.exception.DataException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private JasperPrint getJasperPrint(List<Invoice> invoiceList, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoiceList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "MyCinema");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }

    private @NotNull Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-invoices");
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        if (fileFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, uploadPath+fileName);
        }

        return uploadPath;
    }

    private String getPdfFileLink(String uploadPath){
        return uploadPath+"/"+"invoice.pdf";
    }

    @Override
    public String generateInvoice(String invoiceNumber, String fileFormat) throws JRException, IOException{
        List<Invoice> invoiceList = invoiceRepository.findByInvoiceNumberOrderByCreatedAtDesc(invoiceNumber);

        //load the file and compile it
        String resourceLocation = "classpath:invoice.jrxml";
        JasperPrint jasperPrint = getJasperPrint(invoiceList, resourceLocation);

        //create a folder to store the invoice
        String fileName = "/"+"invoice.pdf";
        Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);

        //create a private method that returns the link to the specific pdf file
        return getPdfFileLink(uploadPath.toString());
    }

    @Override
    public List<Invoice> getInvoiceByInvoiceNumber(String invoiceNumber) {
            return invoiceRepository.findByInvoiceNumberOrderByCreatedAtDesc(invoiceNumber);
    }

    @Override
    public List<Invoice> getInvoice(){
        return invoiceRepository.findAll();
    }
}
