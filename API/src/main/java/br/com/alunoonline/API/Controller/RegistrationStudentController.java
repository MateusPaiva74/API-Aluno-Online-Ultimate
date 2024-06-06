package br.com.alunoonline.API.Controller;

import br.com.alunoonline.API.Dtos.HistoricoStudentResponse;
import br.com.alunoonline.API.Dtos.UpdatesGradesRequest;
import br.com.alunoonline.API.Model.RegistrationStudent;
import br.com.alunoonline.API.Service.RegistrationStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration-student")
public class RegistrationStudentController {

    @Autowired
    RegistrationStudentService registrationStudentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody RegistrationStudent registrationStudent){
        registrationStudentService.create(registrationStudent);
    }

    @PatchMapping("/updates-grades/{registrationStudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void updateGrades(@RequestBody UpdatesGradesRequest updatesGradesRequest, @PathVariable Long registrationStudentId){
        registrationStudentService.updateGrades(registrationStudentId, updatesGradesRequest);
    }

    @PatchMapping("/update-status-to-break{registrationStudentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void updateStatusToBreak (@PathVariable Long registrationStudentId){
        registrationStudentService.updateStatusToBreak(registrationStudentId);
    }

    @GetMapping("/academic-trasncript/{student_id}")
    @ResponseStatus(HttpStatus.OK)

    public HistoricoStudentResponse getAcademicTranscript(@PathVariable Long studentId){
        return registrationStudentService.getAcademicTranscript(studentId);
    }
}
