package com.github.raphaelfontoura.migracaodadosjob.reader;

import java.util.Date;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import com.github.raphaelfontoura.migracaodadosjob.dominio.Pessoa;

@Configuration
public class ArquivoPessoaReaderConfig {

  @Bean
  public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
    return new FlatFileItemReaderBuilder<Pessoa>()
        .name("arquivoPessoaReader")
        .resource(new FileSystemResource("./files/pessoas.csv"))
        .delimited()
        .names("nome", "email", "dataNascimento", "idade", "id")
        .addComment("--")
        .fieldSetMapper(pessoaFieldSetMapper())
        .build();
  }

  private FieldSetMapper<Pessoa> pessoaFieldSetMapper() {
    return new FieldSetMapper<Pessoa>() {

      @Override
      public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(fieldSet.readString("nome"));
        pessoa.setEmail(fieldSet.readString("email"));
        pessoa.setDataNascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));
        pessoa.setIdade(fieldSet.readInt("idade"));
        pessoa.setId(fieldSet.readInt("id"));
        return pessoa;
      }
      
    };
  }

}
