package org.com.example.demo.simulation.processor;

import org.com.example.demo.simulation.model.Validation;
import org.com.example.demo.simulation.model.dto.FaturaUnificadaDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationProcessor implements ItemProcessor<FaturaUnificadaDTO, Validation> {

    @Override
    public Validation process(FaturaUnificadaDTO item) {
        boolean isValid = checkRules(item);

        if(isValid){
            return null;
        }

        return Validation.builder()
                .valid(isValid)
                .name(item.getName())
                .reference(item.getReference())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    private boolean checkRules(FaturaUnificadaDTO item) {
        short code = item.getCodeFatura();
        short status = item.getStatus();
        short group = item.getInvoiceGroup();

        return switch (code) {
            case 1 -> (status == 1 || status == 2);
            
            case 2 -> (group == 2);
            
            case 3 -> (status == 2 || status == 3);
            
            case 4 -> (group == 4 && status == 5);
            
            // Se vier um codeFatura fora de 1-4, definimos como inválido por padrão
            default -> false;
        };
    }
}