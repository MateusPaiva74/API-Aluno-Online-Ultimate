package br.com.alunoonline.API.Model;

import br.com.alunoonline.API.Enums.FinancialStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class FinancialStudent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double discount;

    private Integer dueDate;

    @Enumerated(EnumType.STRING)
    private FinancialStatusEnum status;
}
