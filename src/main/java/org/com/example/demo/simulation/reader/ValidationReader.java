package org.com.example.demo.simulation.reader;

import org.com.example.demo.simulation.model.dto.FaturaUnificadaDTO;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class ValidationReader {

    @Bean
    public JdbcCursorItemReader<FaturaUnificadaDTO> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<FaturaUnificadaDTO>()
                .name("validationReader")
                .dataSource(dataSource)
                .sql(buildJoinQuery())
                .rowMapper(new BeanPropertyRowMapper<>(FaturaUnificadaDTO.class))
                .build();
    }

    private String buildJoinQuery() {
        return """
               SELECT 
                   f.code_fatura AS codeFatura, 
                   f.number_fatura AS numberFatura, 
                   f.contract_code AS contractCode, 
                   f.installment_code AS installmentCode,
                   fg.invoice_group AS invoiceGroup, 
                   fg.status, 
                   fg.conditions,
                   c.name, 
                   c.code_product AS codeProduct, 
                   c.reference
               FROM Fatura f
               INNER JOIN FaturaGrupo fg ON f.installment_code = fg.installment_code
               INNER JOIN Comercial c ON f.installment_code = c.installment_code
               """;
    }
}