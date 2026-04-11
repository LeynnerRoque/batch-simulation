package org.com.example.demo.simulation.configuration;

import org.com.example.demo.simulation.model.Validation;
import org.com.example.demo.simulation.model.dto.FaturaUnificadaDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

    @Bean
    public Step validationStep(JobRepository jobRepository,
                               PlatformTransactionManager transactionManager,
                               JdbcCursorItemReader<FaturaUnificadaDTO> reader,
                               ItemProcessor<FaturaUnificadaDTO, Validation> processor,
                               JdbcBatchItemWriter<Validation> writer) {

        // No Spring Batch 5, passamos o nome e o jobRepository no construtor
        return new StepBuilder("validationStep", jobRepository)
                .<FaturaUnificadaDTO, Validation>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job validationJob(JobRepository jobRepository, Step validationStep) {
        // No Spring Batch 5, passamos o nome e o jobRepository no construtor
        return new JobBuilder("validationJob", jobRepository)
                .start(validationStep)
                .build();
    }
}