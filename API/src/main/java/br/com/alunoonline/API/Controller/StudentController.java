package br.com.alunoonline.API.Controller;

import br.com.alunoonline.API.Dtos.CreateStudentRequest;
import br.com.alunoonline.API.Model.Student;
import br.com.alunoonline.API.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateStudentRequest createStudentRequest){
        studentService.create(createStudentRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Student> findAll(){
        return studentService.findAll(); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<Student> findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Student student, @PathVariable Long id){
        studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        studentService.deleteById(id);
    }
}
