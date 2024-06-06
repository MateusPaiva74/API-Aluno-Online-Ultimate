package br.com.alunoonline.API.Repository;

import br.com.alunoonline.API.Model.RegistrationStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationStudentRepository extends JpaRepository<RegistrationStudent, Long> {
    @Query("SELECT * FROM Registration * WHERE * studentId")
    List<RegistrationStudent> findByStudentId (Long studentId);
}