package br.com.alunoonline.API.Service;

import br.com.alunoonline.API.Model.FinancialStudent;
import br.com.alunoonline.API.Model.Invoice;
import br.com.alunoonline.API.Repository.FinancialStudentRepository;
import br.com.alunoonline.API.Repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class FinancialService {
    private static final Integer QUANTITY_OF_DAYS_BEFORE_GENERATE = 10;

    private static final Logger logger = LoggerFactory.getLogger(FinancialService.class);

    @Autowired
    FinancialStudentRepository financialStudentRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void invoiceGeneration(){
        logger.info("Starting invoice generation...");

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime generationDeadline = currentDate.plusDays(QUANTITY_OF_DAYS_BEFORE_GENERATE);

    List<FinancialStudent> financialStudents = financialStudentRepository.findAll();

     for (FinancialStudent financialStudent : financialStudents) {
         Integer dueDay = financialStudent.getDueDate();

         if (dueDay != null) {
             LocalDate dueDateCurrentMonth = calculateDueDate(dueDay, currentDate.getYear(), currentDate.getMonthValue());

             if (dueDateCurrentMonth.isBefore(currentDate.toLocalDate())) {
                 dueDateCurrentMonth = calculateDueDate(dueDay, currentDate.getYear(), currentDate.getMonthValue() + 1);
             }

             if (dueDateCurrentMonth != null && (dueDateCurrentMonth.isBefore(generationDeadline.toLocalDate()) || dueDateCurrentMonth.isEqual(generationDeadline.toLocalDate()))) {

                 if (invoiceRepository.existsByStudentFinancialAndDueDate(financialStudent, dueDateCurrentMonth.atTime(LocalTime.MIDNIGHT))) {
                     continue;
                 }

                 logger.info("Generate invoice for student: {}", financialStudent.getId());

                 Invoice invoice = new Invoice();
                 invoice.setFinancialStudent(financialStudent);
                 invoice.setDueDate(dueDateCurrentMonth.atTime(LocalTime.MIDNIGHT));
                 invoice.setCreatedAt(currentDate);

                 invoiceRepository.save(invoice);

                 logger.info("Invoice generated for the student: {} with expiration date: {}", financialStudent.getId(), dueDateCurrentMonth);

             }
         }
     }
        logger.info("Generated of invoices suscefull.");
    }
    private LocalDate calculateDueDate(Integer dueDay, int year, int month){
        YearMonth yearMonth = YearMonth.of(year,month);

        int dayOfMonth = Math.min(dueDay, yearMonth.lengthOfMonth());

        return LocalDate.of(year,month,dayOfMonth);
    }
}
