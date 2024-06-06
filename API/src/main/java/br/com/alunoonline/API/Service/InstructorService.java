package br.com.alunoonline.API.Service;

import br.com.alunoonline.API.Model.Instructor;
import br.com.alunoonline.API.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    public void create( Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    public void update (Long id, Instructor instructor) {
            Optional<Instructor> instructorFromDb = findById(id);

            if (instructorFromDb.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);


            Instructor instructorUpdate = instructorFromDb.get();

            instructorUpdate.setName(instructor.getName());
            instructorUpdate.setEmail(instructor.getEmail());

            instructorRepository.save(instructorUpdate);
    }

    public void deleteById (Long id){
        instructorRepository.deleteById(id);
    }
}