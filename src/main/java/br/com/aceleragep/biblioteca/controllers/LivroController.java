package br.com.aceleragep.biblioteca.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	@ResponseStatus(value = HttpStatus.CREATED)
	public LivroOutput cria(@RequestBody @Valid LivroInput input) {
		LivroEntity livroConvertido = livroConvert.inputToEntity(input);
		LivroEntity livroCriado = livroService.cria(livroConvertido, input);
		return livroConvert.entityToOtput(livroCriado);
	}

	@PutMapping("/{id}")
	public LivroOutput altera(@PathVariable Long id, @RequestBody @Valid LivroInput input) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		livroConvert.copyInputToEntity(input, livroEncontrado);
		LivroEntity livroAlterado = livroService.altera(input, livroEncontrado);
		return livroConvert.entityToOtput(livroAlterado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleta(@PathVariable Long id) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		livroService.deleta(livroEncontrado);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public Page<LivroOutput> listaTodos(
			@PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable paginacao) {
		Page<LivroEntity> livros = livroService.listaTodos(paginacao);
		return livroConvert.ListEntityToPageOutput(livros);
	}

	@GetMapping("/{id}")
	public LivroOutput buscaPorId(@PathVariable Long id) {
		LivroEntity livroEncontrado = livroService.buscaPorId(id);
		return livroConvert.entityToOtput(livroEncontrado);
	}

}
