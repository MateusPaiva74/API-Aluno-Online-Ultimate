package br.com.alunoonline.API.Controller;

import br.com.alunoonline.API.Model.Instructor;
import br.com.alunoonline.API.Model.Student;
import br.com.alunoonline.API.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Instructor instructor){
        instructorService.create(instructor);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Instructor> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public Optional<Instructor> findById(@PathVariable Long id){
        return instructorService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void update (@RequestBody Instructor instructor, @PathVariable Long id){
        instructorService.update(id, instructor);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteById(@PathVariable Long id){
        instructorService.deleteById(id);
    }
}
