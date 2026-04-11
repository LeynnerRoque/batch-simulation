package org.com.example.demo.simulation.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fatura {
    private Integer id;
    private short codeFatura;
    private short numberFatura;
    private String contractCode;
    private short installmentCode;
}