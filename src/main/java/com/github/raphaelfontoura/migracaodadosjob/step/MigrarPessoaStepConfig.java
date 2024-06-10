package com.github.raphaelfontoura.migracaodadosjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.raphaelfontoura.migracaodadosjob.dominio.Pessoa;

@Configuration
public class MigrarPessoaStepConfig {

  @Bean
  public Step migrarPessoaStep(
    JobRepository repository,
    PlatformTransactionManager transactionManager,
    ItemReader<Pessoa> migrarPessoaReader,
    ItemWriter<Pessoa> migrarPessoaWriter
  ) {
    return new StepBuilder("migrarPessoaStep", repository)
    .<Pessoa, Pessoa>chunk(1, transactionManager)
    .reader(migrarPessoaReader)
    .writer(migrarPessoaWriter)
    .build();
  }

}
