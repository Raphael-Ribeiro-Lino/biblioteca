package br.com.aceleragep.biblioteca.converts;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.dto.outputs.LivroAutorOutput;
import br.com.aceleragep.biblioteca.dto.outputs.LivroOutput;
import br.com.aceleragep.biblioteca.entities.LivroEntity;

@Component
public class LivroConvert {

	@Autowired
	private ModelMapper modelMapper;

	public LivroEntity inputToEntity(LivroInput input) {
		return modelMapper.map(input, LivroEntity.class);
	}

	public void copyInputToEntity(LivroInput input, LivroEntity livroEncontrado) {
		modelMapper.map(input, livroEncontrado);
	}

	public LivroOutput entityToOtput(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroOutput.class);
	}

	public LivroAutorOutput entityToOtputCopy(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroAutorOutput.class);
	}

	public Page<LivroOutput> ListEntityToPageOutput(Page<LivroEntity> livros) {
		return livros.map(this::entityToOtput);
	}

	public Page<LivroAutorOutput> listEntityToListOutputCopy(Page<LivroEntity> livrosEncontrados) {
		return livrosEncontrados.map(this::entityToOtputCopy);
	}

}
