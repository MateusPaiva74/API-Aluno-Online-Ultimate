package br.com.alunoonline.API.Dtos;

import lombok.Data;

@Data
public class CreateStudentRequest {

    private String name;

    private String email;

    private Long courseId;

    private Double discount;

    private Integer dueDate;
}
