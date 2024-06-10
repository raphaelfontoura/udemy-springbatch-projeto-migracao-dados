package com.github.raphaelfontoura.migracaodadosjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.raphaelfontoura.migracaodadosjob.dominio.DadosBancarios;

@Configuration
public class MigrarDadosBancariosStepConfig {

  @Bean
  public Step migrarDadosBancariosStep(
    JobRepository repository,
    PlatformTransactionManager transactionManager,
    ItemReader<DadosBancarios> migrarDadosBancariosReader,
    ItemWriter<DadosBancarios> migrarDadosBancariosWriter
  ) {
    return new StepBuilder("migrarDadosBancariosStep", repository)
    .<DadosBancarios, DadosBancarios>chunk(1, transactionManager)
    .reader(migrarDadosBancariosReader)
    .writer(migrarDadosBancariosWriter)
    .build();
  }
}
