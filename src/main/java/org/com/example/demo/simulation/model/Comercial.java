package org.com.example.demo.simulation.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comercial {
    private Integer id;
    private String name;
    private short codeProduct;
    private short installmentCode;
    private String reference;
}