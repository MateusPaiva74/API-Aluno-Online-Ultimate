package br.com.alunoonline.API.Repository;

import br.com.alunoonline.API.Model.FinancialStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialStudentRepository extends JpaRepository<FinancialStudent, Long> {
}
