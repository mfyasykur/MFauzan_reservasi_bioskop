package edu.Binar.challenge.CinemaTicketReservation.repository;

import edu.Binar.challenge.CinemaTicketReservation.model.Invoice;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Data
@RequiredArgsConstructor
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void isFoundByInvoiceNumber() {

        Invoice invoice = new Invoice(1L, LocalDateTime.now(), "MYCNM-001", "Kancil dan Buaya", LocalDate.now(), "14.30", "A", "24", "C", new BigDecimal(45000));
        invoiceRepository.save(invoice);

        Boolean actual = invoiceRepository.existsByInvoiceNumber("MYCNM-001");
        assertThat(actual).isTrue();
    }
}
