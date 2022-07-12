package br.com.aceleragep.biblioteca.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.biblioteca.configs.ControllerConfig;
import br.com.aceleragep.biblioteca.converts.AutorConvert;
import br.com.aceleragep.biblioteca.converts.LivroConvert;
import br.com.aceleragep.biblioteca.dto.inputs.AutorInput;
import br.com.aceleragep.biblioteca.dto.outputs.AutorOutput;
import br.com.aceleragep.biblioteca.dto.outputs.LivroAutorOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.entities.LivroEntity;
import br.com.aceleragep.biblioteca.services.AutorService;
import br.com.aceleragep.biblioteca.services.LivroService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private AutorConvert autorConvert;

	@Autowired
	private LivroService livroService;

	@Autowired
	private LivroConvert livroConvert;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AutorOutput cria(@RequestBody @Valid AutorInput input) {
		AutorEntity autorConvertido = autorConvert.inputToNewEntity(input);
		AutorEntity autorCriado = autorService.cria(autorConvertido);
		return autorConvert.entityToOutput(autorCriado);
	}

	@PutMapping("/{id}")
	public AutorOutput altera(@RequestBody @Valid AutorInput input, @PathVariable Long id) {
		AutorEntity autorEncontrado = autorService.buscaPorId(id);
		autorConvert.copyInputToEntity(input, autorEncontrado);
		AutorEntity autorAlterado = autorService.altera(autorEncontrado);
		return autorConvert.entityToOutput(autorAlterado);
	}

	@GetMapping
	public Page<AutorOutput> listaTodos(
			@PageableDefault(page = 0, size = 5, sort = "nome", direction = Direction.ASC) Pageable paginacao) {
		Page<AutorEntity> autores = autorService.listaTodos(paginacao);
		return autorConvert.ListEntityToPageOutput(autores);
	}

	@GetMapping("/{id}")
	public AutorOutput buscaPorId(@PathVariable Long id) {
		AutorEntity autoEncontrado = autorService.buscaPorId(id);
		return autorConvert.entityToOutput(autoEncontrado);
	}

	@GetMapping("/{id}/livros")
	public Page<LivroAutorOutput> buscaLivrosDoAutor(@PathVariable Long id,
			@PageableDefault(page = 0, size = 5, sort = "titulo", direction = Direction.ASC) Pageable paginacao) {
		AutorEntity autorEncontrado = autorService.buscaPorId(id);
		Page<LivroEntity> livrosEncontrados = livroService.buscaPeloAutor(autorEncontrado, paginacao);
		return livroConvert.listEntityToListOutputCopy(livrosEncontrados);
	}
}
