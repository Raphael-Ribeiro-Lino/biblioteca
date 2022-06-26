package br.com.aceleragep.biblioteca.converts;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.dto.outputs.LivroOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.services.AutorService;

@Component
public class LivroConvert {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AutorService autorService;

	public LivroEntity inputToEntity(LivroInput input) {
		LivroEntity livro = modelMapper.map(input, LivroEntity.class);
		converteIdsAutorParaAutores(input, livro);
		return livro;
	}

	private void converteIdsAutorParaAutores(LivroInput input, LivroEntity livroEntity) {
		List<AutorEntity> autores = new ArrayList<>();
		for (Long idAutor : input.getIdsAutores()) {
			AutorEntity autor = autorService.buscaPorId(idAutor);
			autores.add(autor);
		}
		livroEntity.setAutores(autores);
	}

	public void copyInputToEntity(LivroInput input, LivroEntity livroEncontrado) {
		modelMapper.map(input, livroEncontrado);
		converteIdsAutorParaAutores(input, livroEncontrado);
	}

	public LivroOutput entityToOtput(LivroEntity livroEntity) {
		return modelMapper.map(livroEntity, LivroOutput.class);
	}

	public Page<LivroOutput> ListEntityToPageOutput(Page<LivroEntity> livros) {
		return livros.map(this::entityToOtput);
	}

}
