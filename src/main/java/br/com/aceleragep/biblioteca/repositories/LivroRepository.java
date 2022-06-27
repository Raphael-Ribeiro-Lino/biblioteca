package br.com.aceleragep.biblioteca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aceleragep.biblioteca.entities.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

	Page<LivroEntity> findAllByAutoresId(Long id, Pageable paginacao);

}
