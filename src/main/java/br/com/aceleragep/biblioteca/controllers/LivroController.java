package br.com.aceleragep.biblioteca.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aceleragep.biblioteca.configs.ControllerConfig;
import br.com.aceleragep.biblioteca.converts.LivroConvert;
import br.com.aceleragep.biblioteca.dto.inputs.LivroInput;
import br.com.aceleragep.biblioteca.dto.outputs.LivroOutput;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.services.LivroService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/livros")
public class LivroController {

	@Autowired
	private LivroConvert livroConvert;

	@Autowired
	private LivroService livroService;

	@PostMapping
	public ResponseEntity<?> cria(@RequestBody @Valid LivroInput input, UriComponentsBuilder uriBuilder) {
		LivroEntity livroConvertido = livroConvert.inputToEntity(input);
		LivroEntity livroCriado = livroService.cria(livroConvertido, input);

		URI uri = uriBuilder.path(ControllerConfig.PRE_URL + "/livros/{id}").buildAndExpand(livroCriado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(livroCriado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> altera(@PathVariable Long id, @RequestBody @Valid LivroInput input) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		livroConvert.copyInputToEntity(input, livroEncontrado);
		livroService.altera(livroEncontrado);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleta(@PathVariable Long id) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		livroService.deleta(livroEncontrado);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public Page<LivroOutput> listaTodos(Pageable paginacao) {
		Page<LivroEntity> livros = livroService.listaTodos(paginacao);
		return livroConvert.ListEntityToPageOutput(livros);
	}

	@GetMapping("/{id}")
	public LivroOutput buscaPorId(@PathVariable Long id) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		return livroConvert.entityToOtput(livroEncontrado);
	}

	@GetMapping("/autor/{id}")
	public Page<LivroOutput> buscaPorIdAutor(@PathVariable Long id, Pageable paginacao) {
		Page<LivroEntity> livrosEncontrados = livroService.buscaPorIdAutor(id, paginacao);
		return livroConvert.ListEntityToPageOutput(livrosEncontrados);
	}
}
