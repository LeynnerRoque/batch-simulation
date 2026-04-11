package org.com.example.demo.simulation.model.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaUnificadaDTO {
    // Dados para o Join e Processamento
    private short codeFatura;
    private short numberFatura;
    private String contractCode;
    private short installmentCode;
    private short invoiceGroup;
    private short status;
    private short conditions;
    private String name;
    private short codeProduct;
    private String reference;
}