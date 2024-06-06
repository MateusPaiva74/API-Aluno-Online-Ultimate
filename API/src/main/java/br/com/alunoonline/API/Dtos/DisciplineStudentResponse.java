package br.com.alunoonline.API.Dtos;

import br.com.alunoonline.API.Enums.RegistrationStudentStatusEnum;
import lombok.Data;

@Data
public class DisciplineStudentResponse {

    private String disciplineName;
    private Double grade1;
    private Double grade2;
    private RegistrationStudentStatusEnum status;
}
