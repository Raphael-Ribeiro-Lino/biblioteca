package br.com.aceleragep.biblioteca.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.exceptions.NotFoundBusinessException;
import br.com.aceleragep.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	@Transactional
	public AutorEntity cria(AutorEntity autorConvertido) {
		return autorRepository.save(autorConvertido);
	}

	public AutorEntity buscaPorId(Long id) {
		return autorRepository.findById(id)
				.orElseThrow(() -> new NotFoundBusinessException("Autor " + id + " não encontrado"));
	}

	@Transactional
	public AutorEntity altera(AutorEntity autorConvertido) {
		return autorRepository.save(autorConvertido);
	}

	public Page<AutorEntity> listaTodos(Pageable paginacao) {
		return autorRepository.findAll(paginacao);
	}

}
