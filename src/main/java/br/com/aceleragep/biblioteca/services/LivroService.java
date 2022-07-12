package br.com.aceleragep.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.exceptions.NotFoundBusinessException;
import br.com.aceleragep.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorService autorService;

	public LivroEntity cria(LivroEntity livroConvertido, LivroInput input) {
		converteIdsAutorParaAutores(input, livroConvertido);
		return livroRepository.save(livroConvertido);
	}

	public LivroEntity buscaPorId(Long id) {
		return livroRepository.findById(id)
				.orElseThrow(() -> new NotFoundBusinessException("Livro " + id + " não encontrado"));
	}

	public LivroEntity altera(LivroInput input, LivroEntity livroEncontrado) {
		converteIdsAutorParaAutores(input, livroEncontrado);
		return livroRepository.save(livroEncontrado);
	}

	public void deleta(LivroEntity livroEncontrado) {
		livroRepository.delete(livroEncontrado);
	}

	public Page<LivroEntity> listaTodos(Pageable paginacao) {
		return livroRepository.findAll(paginacao);
	}

	public Page<LivroEntity> buscaPeloAutor(AutorEntity autorEncontrado, Pageable paginacao) {
		return livroRepository.findAllByAutores(autorEncontrado, paginacao);
	}

	private void converteIdsAutorParaAutores(LivroInput input, LivroEntity livroEntity) {
		List<AutorEntity> autores = new ArrayList<>();
		for (Long idAutor : input.getIdsAutores()) {
			AutorEntity autor = autorService.buscaPorId(idAutor);
			autores.add(autor);
		}
		livroEntity.setAutores(autores);
	}
}
