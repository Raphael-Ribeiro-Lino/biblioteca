package com.example.demo.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.dto.inputs.AutorInput;
import com.example.demo.dto.outputs.AutorOutput;
import com.example.demo.dto.outputs.LivroOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autor")
public interface AutorOpenApi {

	
	@Operation(summary = "Cadastra autor", description = "Cadastra um novo autor no sistema")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutorOutput criaAutor(@Parameter(description = "Representação de um autor") @RequestBody @Valid AutorInput autor);
	
	@Operation(summary = "Altera autor", description = "Altera os dados do autor selecionado")
	@PutMapping("/{id}")
	public AutorOutput alteraAutor(@Parameter(description = "Id do autor", example = "1") @PathVariable Long id, @Parameter(description = "Representação de um usuário") @Valid @RequestBody AutorInput autorInput);
	
	@Operation(summary = "Busca autor", description = "Busca um autor pelo seu id")
	@GetMapping("/{id}")
	public AutorOutput buscaAutorPorId(@Parameter(description = "Id do autor", example = "1") @PathVariable Long id);
	
	@Operation(summary = "Lista autores", description = "Lista todos os autores cadastrados no sistema")
	@GetMapping
	public List<AutorOutput> listaAutores();
	
	@Operation(summary = "Lista livros do autor", description = "Lista todos os livros cadastrados pelo autor selecionado")
	@GetMapping("/{idAutor}/livros")
	public List<LivroOutput> listaLivros(@Parameter(description = "Id do autor", example = "1") @PathVariable Long idAutor);
}
