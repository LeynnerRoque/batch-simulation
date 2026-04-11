package org.com.example.demo.simulation.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaGrupo {
    private Integer id;
    private short invoiceGroup;
    private short installmentCode;
    private short status;
    private short conditions;
}