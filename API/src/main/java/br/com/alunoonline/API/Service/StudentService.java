package br.com.alunoonline.API.Service;

import br.com.alunoonline.API.Dtos.CreateStudentRequest;
import br.com.alunoonline.API.Enums.FinancialStatusEnum;
import br.com.alunoonline.API.Model.Course;
import br.com.alunoonline.API.Model.FinancialStudent;
import br.com.alunoonline.API.Model.Student;
import br.com.alunoonline.API.Repository.CourseRepository;
import br.com.alunoonline.API.Repository.FinancialStudentRepository;
import br.com.alunoonline.API.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FinancialStudentRepository financialStudentRepository;

    @Autowired
    CourseRepository courseRepository;

    public void create(CreateStudentRequest createStudentRequest) {
        Course course = courseRepository.findById(createStudentRequest.getCourseId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        Student studentSaved = studentRepository.save(
                new Student(
                        null,
                        createStudentRequest.getName(),
                        createStudentRequest.getEmail(),
                        course
               )
        );
        createFinancialInformation(studentSaved, createStudentRequest);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }

    public void update (Long id,Student student){
        Optional<Student> studentFromDb = findById(id);

        if (studentFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Student studentUpdate = studentFromDb.get();

        studentUpdate.setName(student.getName());
        studentUpdate.setEmail(student.getEmail());

        studentRepository.save(studentUpdate);
    }

    public void deleteById (Long id){
        studentRepository.deleteById(id);
    }

    public void createFinancialInformation(Student student, CreateStudentRequest createStudentRequest){
        FinancialStudent financialStudent = new FinancialStudent(
                null,
                student,
                createStudentRequest.getDiscount(),
                createStudentRequest.getDueDate(),
                  FinancialStatusEnum.EM_DIA
        );
        financialStudentRepository.save(financialStudent);
    }
}
