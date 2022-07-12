package br.com.aceleragep.biblioteca.dto.inputs;

import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroInput {

	@NotBlank(message = "O campo título é obrigatório")
	@Length(max = 200)
	private String titulo;

	@NotNull(message = "O campo ano de lançamento é obrigatório")
	@DecimalMax(value = "9999")
	private Integer anoLancamento;

	@NotEmpty(message = "O campo idsAutores é obrigatório")
	private List<Long> idsAutores;

}
