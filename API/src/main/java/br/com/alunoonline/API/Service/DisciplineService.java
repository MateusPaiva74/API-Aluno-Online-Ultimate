package br.com.alunoonline.API.Service;

import br.com.alunoonline.API.Model.Discipline;
import br.com.alunoonline.API.Model.Instructor;
import br.com.alunoonline.API.Repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    @Autowired
    DisciplineRepository disciplineRepository;

    public void create(Discipline discipline){
        disciplineRepository.save(discipline);
    }

    public List<Discipline> findAll(){
        return disciplineRepository.findAll();
    }

    public List<Discipline> findByInstructorId(Long instructorId){
        return disciplineRepository.findByInstructorId(instructorId);
    }

    public Optional<Discipline> findById(Long id){
        return disciplineRepository.findById(id);
    }

    public void update(Long id, Instructor instructor){
        Optional<Discipline> disciplineFromDb = findById(id);

        if (disciplineFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Discipline disciplineUpdate = disciplineFromDb.get();
        disciplineUpdate.setName(disciplineUpdate.getName());
        disciplineUpdate.setInstructor(disciplineUpdate.getInstructor());

        disciplineRepository.save(disciplineUpdate);
    }

    public void deleteById(Long id){
        disciplineRepository.deleteById(id);
    }
}
