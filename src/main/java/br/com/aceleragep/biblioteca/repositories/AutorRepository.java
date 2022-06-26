package br.com.aceleragep.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aceleragep.biblioteca.entities.AutorEntity;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

}
