package com.example.demo.openapi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.dto.inputs.LivroInput;
import com.example.demo.dto.outputs.LivroOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Livro")
public interface LivroOpenApi {

	@Operation(summary = "Cadastra livro", description = "Cadastra um novo livro no sistema")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public LivroOutput criaLivro(@Parameter(description = "Representação de um livro") @Valid @RequestBody LivroInput livro);
	
	@Operation(summary = "Altera livro", description = "Altera os dados do livro selecionado")
	@PutMapping("/{id}")
	public LivroOutput alteraLivro(@Parameter(description = "Id do livro", example = "1") @PathVariable Long id, @Parameter(description = "Representação de um livro") @Valid @RequestBody LivroInput livroInput);
	
	@Operation(summary = "Busca livro", description = "Busca o livro pelo seu id")
	@GetMapping("/{id}")
	public LivroOutput buscaLivroPorId(@Parameter(description = "Id do livro", example = "1") @PathVariable Long id);
	
	@Operation(summary = "Lista livros", description = "Lista todos os livros cadastrados no sistema")
	@GetMapping
	public List<LivroOutput> listaLivros();
	
	@Operation(summary = "Exclui livro", description = "Exclui o livro selecionado do sistema")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeLivro(@Parameter(description = "Id do livro", example = "1") @PathVariable Long id);
}
