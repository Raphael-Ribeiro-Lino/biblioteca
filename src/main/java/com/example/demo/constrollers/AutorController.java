package com.example.demo.constrollers;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converts.AutorConvert;
import com.example.demo.converts.LivroConvert;
import com.example.demo.dto.inputs.AutorInput;
import com.example.demo.dto.outputs.AutorOutput;
import com.example.demo.dto.outputs.LivroOutput;
import com.example.demo.entities.AutorEntity;
import com.example.demo.entities.LivroEntity;
import com.example.demo.services.AutorService;
import com.example.demo.services.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autor")
@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorConvert autorConvert;

	@Autowired
	private LivroConvert livroConvert;

	@Operation(summary = "Cadastra autor", description = "Cadastra um novo autor no sistema")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutorOutput criaAutor(@Parameter(description = "Representação de um usuário") @RequestBody @Valid AutorInput autor) throws URISyntaxException {
		AutorEntity autorEntity = autorConvert.inputToEntity(autor);
		AutorEntity autorCriado = autorService.cria(autorEntity);
		return autorConvert.entityToOutput(autorCriado);
	}

	@Operation(summary = "Altera autor", description = "Altera os dados do autor selecionado")
	@PutMapping("/{id}")
	public AutorOutput alteraAutor(@Parameter(description = "Id do autor", example = "1") @PathVariable Long id, @Parameter(description = "Representação de um usuário") @Valid @RequestBody AutorInput autorInput) {
		AutorEntity autorEntity = autorConvert.inputToEntity(autorInput);
		autorEntity.setId(id);
		AutorEntity autorAlterado = autorService.alterar(autorEntity);
		return autorConvert.entityToOutput(autorAlterado);
	}

	
	@Operation(summary = "Busca autor", description = "Busca um autor pelo seu id")
	@GetMapping("/{id}")
	public AutorOutput buscaAutorPorId(@Parameter(description = "Id do autor", example = "1") @PathVariable Long id) {
		AutorEntity autorEntity = autorService.buscaPeloId(id);
		return autorConvert.entityToOutput(autorEntity);
	}

	@Operation(summary = "Lista autores", description = "Lista todos os autores cadastrados no sistema")
	@GetMapping
	public List<AutorOutput> listaAutores() {
		List<AutorEntity> listaTodos = autorService.listaTodos();
		return autorConvert.entityToOutput(listaTodos);
	}

	@Operation(summary = "Lista livros do autor", description = "Lista todos os livros cadastrados pelo autor selecionado")
	@GetMapping("/{idAutor}/livros")
	public List<LivroOutput> listaLivros(@Parameter(description = "Id do autor", example = "1") @PathVariable Long idAutor) {
		List<LivroEntity> listaTodos = livroService.listaLivrosPeloAutor(idAutor);
		return livroConvert.entityToOutput(listaTodos);
	}

}
