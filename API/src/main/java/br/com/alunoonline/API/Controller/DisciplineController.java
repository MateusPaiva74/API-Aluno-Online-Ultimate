package br.com.alunoonline.API.Controller;

import br.com.alunoonline.API.Model.Discipline;
import br.com.alunoonline.API.Repository.DisciplineRepository;
import br.com.alunoonline.API.Service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    DisciplineService disciplineService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Discipline discipline){
        disciplineService.create(discipline);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Discipline> findAll(){
        return disciplineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<Discipline> findById(@PathVariable Long id){
        return disciplineService.findById(id);
    }

    @GetMapping("/instructor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Discipline> findByInstructorId(@PathVariable Long id){
        return disciplineService.findByInstructorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Discipline discipline, @PathVariable Long id){
        disciplineService.update(id, discipline.getInstructor());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        disciplineService.deleteById(id);
    }
}
