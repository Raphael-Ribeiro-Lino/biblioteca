package br.com.aceleragep.biblioteca.dto.inputs;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
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
	private Integer anoLancamento;

	@NotNull(message = "O campo idsAutores é obrigatório")
	private List<Long> idsAutores;

	@AssertTrue(message = "É preciso no mínimo um autor para cadastrar o livro")
	public boolean isIdsAutores() {
		if (this.idsAutores == null || this.idsAutores.isEmpty()) {
			return false;
		}

		return true;
	}

	@AssertTrue(message = "O ano de lançamento pode ter no máximo 4 dígitos")
	public boolean isAnoLancamento() {
		if (this.anoLancamento > 9999) {
			return false;
		}

		return true;
	}

}
