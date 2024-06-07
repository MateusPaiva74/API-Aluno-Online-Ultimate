package br.com.alunoonline.API.Service;

import br.com.alunoonline.API.Dtos.DisciplineStudentResponse;
import br.com.alunoonline.API.Dtos.HistoricoStudentResponse;
import br.com.alunoonline.API.Dtos.UpdatesGradesRequest;
import br.com.alunoonline.API.Enums.RegistrationStudentStatusEnum;
import br.com.alunoonline.API.Model.RegistrationStudent;
import br.com.alunoonline.API.Repository.RegistrationStudentRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationStudentService {

    public static final double GRADE_AVERAGE_TO_APPROVE = 7.0;
    @Autowired
    RegistrationStudentRepository registrationStudentRepository;

    public void create (RegistrationStudent registrationStudent){
        registrationStudent.setStatus(RegistrationStudentStatusEnum.MATRICULADO);
        registrationStudentRepository.save(registrationStudent);
    }
    public void updateGrades(Long registrationStudentId, UpdatesGradesRequest updatesGradesRequest){
        RegistrationStudent registrationStudent =
                registrationStudentRepository.findById(registrationStudentId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula nao encontrada"));
        updateStudentGrades(registrationStudent, updatesGradesRequest);
        updatesStudentStatus(registrationStudent);

        registrationStudentRepository.save(registrationStudent);
    }
    public void updateStudentGrades(RegistrationStudent registrationStudent, UpdatesGradesRequest updatesGradesRequest){

        if (updatesGradesRequest.getGrade1() != null){
            registrationStudent.setGrade1(updatesGradesRequest.getGrade1());
        }
        if (updatesGradesRequest.getGrade2() != null){
            registrationStudent.setGrade2(updatesGradesRequest.getGrade2());
        }
    }
    public void updatesStudentStatus(RegistrationStudent registrationStudent){

        Double grade1 = registrationStudent.getGrade1();
        Double grade2 = registrationStudent.getGrade2();

        if (grade1 !=null && grade2 !=null){
        double average = (grade1 + grade2) / 2;
        registrationStudent.setStatus(average >= GRADE_AVERAGE_TO_APPROVE ? RegistrationStudentStatusEnum.APROVADO: RegistrationStudentStatusEnum.REPROVADO);
    }
}

    public void updateStatusToBreak(Long registrationStudentId){
        RegistrationStudent registrationStudent = registrationStudentRepository.findById(registrationStudentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (! RegistrationStudentStatusEnum.MATRICULADO.equals(registrationStudent.getStatus())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        changeStatus(registrationStudent, RegistrationStudentStatusEnum.TRANCADO);
    }

    public void changeStatus(RegistrationStudent registrationStudent, RegistrationStudentStatusEnum registrationStudentStatusEnum){
        registrationStudent.setStatus(registrationStudentStatusEnum);
        registrationStudentRepository.save(registrationStudent);
    }

    public HistoricoStudentResponse getAcademicTranscript(Long studentId){
        List<RegistrationStudent> registrationOfStudents = registrationStudentRepository.findByStudentId(studentId);

        if (registrationOfStudents.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno nao possui matriculas");
        }

        HistoricoStudentResponse historico = new HistoricoStudentResponse();

        historico.setNameStudent(registrationOfStudents.get(0).getStudent().getName());
        historico.setEmailStudent(registrationOfStudents.get(0).getStudent().getEmail());

        List<DisciplineStudentResponse> disciplinesList = new ArrayList<>();

        for (RegistrationStudent registration : registrationOfStudents){
            DisciplineStudentResponse disciplineStudentResponse = new DisciplineStudentResponse();
            disciplineStudentResponse.setDisciplineName(registration.getDiscipline().getName());
            disciplineStudentResponse.setInstructorName(registration.getDiscipline().getInstructor().getName());
            disciplineStudentResponse.setGrade1(registration.getGrade1());
            disciplineStudentResponse.setGrade2(registration.getGrade2());

            if (registration.getGrade1() != null && registration.getGrade2() != null) {
                disciplineStudentResponse.setAverage((registration.getGrade1() + registration.getGrade2()) / 2.0);
            } else {
                disciplineStudentResponse.setAverage(null);
            }

            disciplineStudentResponse.setStatus(registration.getStatus());
            disciplinesList.add(disciplineStudentResponse);
        }
        historico.setDisciplineStudentResponseList(disciplinesList);

        return historico;
    }
}
