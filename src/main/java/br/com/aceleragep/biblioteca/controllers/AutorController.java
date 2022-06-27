package br.com.aceleragep.biblioteca.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aceleragep.biblioteca.configs.ControllerConfig;
import br.com.aceleragep.biblioteca.converts.AutorConvert;
import br.com.aceleragep.biblioteca.dto.inputs.AutorInput;
import br.com.aceleragep.biblioteca.dto.outputs.AutorOutput;
import br.com.aceleragep.biblioteca.entities.AutorEntity;
import br.com.aceleragep.biblioteca.services.AutorService;

@RestController
@RequestMapping(ControllerConfig.PRE_URL + "/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private AutorConvert autorConvert;

	@PostMapping
	public ResponseEntity<?> cria(@RequestBody @Valid AutorInput input, UriComponentsBuilder uriBuilder) {
		AutorEntity autorConvertido = autorConvert.inputToNewEntity(input);
		AutorEntity autorCriado = autorService.cria(autorConvertido);

		URI uri = uriBuilder.path(ControllerConfig.PRE_URL + "/autores/{id}").buildAndExpand(autorCriado.getId())
				.toUri();
		return ResponseEntity.created(uri).body(autorCriado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> altera(@RequestBody @Valid AutorInput input, @PathVariable Long id) {
		AutorEntity autorEncontrado = autorService.buscaPorId(id);
		autorConvert.copyInputToEntity(input, autorEncontrado);
		autorService.altera(autorEncontrado);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public Page<AutorOutput> listaTodos(
			@PageableDefault(size = 5, sort = "id", direction = Direction.ASC) Pageable paginacao) {
		Page<AutorEntity> autores = autorService.listaTodos(paginacao);
		return autorConvert.ListEntityToPageOutput(autores);
	}

	@GetMapping("/{id}")
	public AutorOutput buscaPorId(@PathVariable Long id) {
		AutorEntity autoEncontrado = autorService.buscaPorId(id);
		return autorConvert.entityToOutput(autoEncontrado);
	}
}
