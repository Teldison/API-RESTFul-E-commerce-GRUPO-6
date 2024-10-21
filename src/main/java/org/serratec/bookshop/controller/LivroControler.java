package org.serratec.bookshop.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.LivroDto;
import org.serratec.bookshop.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/livro")
public class LivroControler {
	@Autowired
	private LivroService servico;

	@GetMapping
	public List<LivroDto> obterTodos() {
		return servico.obterTodos();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retorna um livro por id",
	description = "Dado um número de id retorna um livro")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Livro pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Livro localizado")
    })
	public ResponseEntity<LivroDto> obterPorId(@PathVariable Long id) {
		Optional<LivroDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Retorna um livro por nome",
	description = "Dado um nome retorna um livro")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Livro pelo nome informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Livro localizado")
    })
	public List<LivroDto> obterPorNome(@PathVariable String nome) {
		return servico.obterPorNome(nome);
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Registra um livro",
	description = "Dado um determinado número de id, será retornado o livro contendo as informações")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi registrado o Livro pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Livro registrado")
    })
	public LivroDto cadastrarLivro(@RequestBody LivroDto dto) {
		return servico.salvarLivro(dto);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar um livro por id",
	description = "apaga o registro de um livro")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi deletado o Livro pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Livro deletado")
    })
	public ResponseEntity<Void> deletaLivro(@PathVariable Long id){
		if(!servico.apagarLivro(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera o livro por id",
	description = "altera o registro de um livro")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi alterado o Livro pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Livro alterado")
    })
	public ResponseEntity<LivroDto> alterarLivro(@PathVariable Long id, @RequestBody LivroDto dto){
		Optional<LivroDto> livroAlterado = servico.alterarLivro(id, dto);
		if (!livroAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(livroAlterado.get());
	}
}
