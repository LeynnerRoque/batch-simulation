package org.com.example.demo.simulation.writer;

import org.com.example.demo.simulation.model.Validation;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ValidationWriter {

    @Bean
    public JdbcBatchItemWriter<Validation> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Validation>()
                .dataSource(dataSource)
                .sql("""
                     INSERT INTO Validation (is_valid, name, reference, date_created)
                     VALUES (:valid, :name, :reference, :dateCreated)
                     """)
                .beanMapped()
                .build();
    }
}