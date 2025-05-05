package com.psoft.healthcareinsuance.Batch;

import com.psoft.healthcareinsuance.Entity.PatientEntity;
import com.psoft.healthcareinsuance.Repository.PatientRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<PatientEntity> reader() {
        FlatFileItemReader<PatientEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("C:/Users/Md Abdul Hafeez/Desktop/patients.csv"));
        reader.setLineMapper(new DefaultLineMapper<PatientEntity>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "age", "city", "medicalHistory");  // These should match the CSV header
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<PatientEntity>() {{
                setTargetType(PatientEntity.class);  // Map to PatientEntity class
            }});
        }});
        reader.setLinesToSkip(1); // Skip the header row
        return reader;
    }

    @Bean
    public LineMapper<PatientEntity> lineMapper() {
        DefaultLineMapper<PatientEntity> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");  // default is comma
        tokenizer.setStrict(false);
        tokenizer.setNames("name", "age", "city", "medicalHistory"); // must match your CSV column order

        BeanWrapperFieldSetMapper<PatientEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PatientEntity.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }


    @Bean
    public PatientItemProcessor processor() {
        return new PatientItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<PatientEntity> writer(PatientRepository repository) {
        RepositoryItemWriter<PatientEntity> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job importPatientJob(JobRepository jobRepository,
                                JobCompletionNotificationListener listener,
                                Step step1) {
        return new JobBuilder("importPatientJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<PatientEntity> reader,
                      PatientWriter patientWriter) { // ✅ Use custom writer

        return new StepBuilder("step1", jobRepository)
                .<PatientEntity, PatientEntity>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(patientWriter) // ✅ Use your custom writer
                .build();
    }

}
