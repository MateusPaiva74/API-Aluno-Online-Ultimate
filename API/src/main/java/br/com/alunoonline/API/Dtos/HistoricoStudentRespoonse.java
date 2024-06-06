package br.com.alunoonline.API.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoricoStudentRespoonse {

    private String nameStudent;
    private String emailStudent;
    private List<DisciplineStudentResponse> disciplineStudentResponseList;
}
