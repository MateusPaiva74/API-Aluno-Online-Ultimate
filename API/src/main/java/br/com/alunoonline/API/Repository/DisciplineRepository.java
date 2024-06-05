package br.com.alunoonline.API.Repository;

import br.com.alunoonline.API.Model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    List<Discipline> findByInstructorId(Long instructorId);
}
