package br.com.aceleragep.biblioteca.dto.inputs;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorInput {

	@NotBlank(message = "O campo nome é obrigatório")
	@Length(max = 100, message = "O tamanho máximo do nome é de 100 caracteres")
	private String nome;

	@NotBlank(message = "O campo biografia é obrigatório")
	@Length(max = 1000, message = "O tamanho da biografia é de 1000 caracteres")
	private String biografia;
}
