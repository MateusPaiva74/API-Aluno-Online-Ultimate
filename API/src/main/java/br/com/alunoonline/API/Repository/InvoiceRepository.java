package br.com.alunoonline.API.Repository;

import br.com.alunoonline.API.Model.FinancialStudent;
import br.com.alunoonline.API.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    boolean existsByStudentFinancialAndDueDate(FinancialStudent financialStudent, LocalDateTime dueDate);
}
