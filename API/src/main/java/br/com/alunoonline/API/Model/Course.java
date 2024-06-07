package br.com.alunoonline.API.Model;

import br.com.alunoonline.API.Enums.CourseTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monthlyCost;

    private String name;

    @Enumerated(EnumType.STRING)
    private CourseTypeEnum type;

}
