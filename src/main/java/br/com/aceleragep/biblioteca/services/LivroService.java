package br.com.aceleragep.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.exceptions.NotFoundBusinessException;
import br.com.aceleragep.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public LivroEntity cria(LivroEntity livroConvertido, LivroInput input) {
		return livroRepository.save(livroConvertido);
	}

	public LivroEntity buscaPorId(Long id) {
		return livroRepository.findById(id).orElseThrow(() -> new NotFoundBusinessException("Livro não encontrado"));
	}

	public void altera(LivroEntity livroEncontrado) {
		livroRepository.save(livroEncontrado);
	}

	public void deleta(LivroEntity livroEncontrado) {
		livroRepository.delete(livroEncontrado);
	}

	public Page<LivroEntity> listaTodos(Pageable paginacao) {
		return livroRepository.findAll(paginacao);
	}

	public Page<LivroEntity> buscaPorIdAutor(Long id, Pageable paginacao) {
		return livroRepository.findAllByAutoresId(id, paginacao);
	}

}
