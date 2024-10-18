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
	public ResponseEntity<LivroDto> obterPorId(@PathVariable Long id) {
		Optional<LivroDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@GetMapping("/nome/{nome}")
	public List<LivroDto> obterPorNome(@PathVariable String nome) {
		return servico.obterPorNome(nome);
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LivroDto cadastrarLivro(@RequestBody LivroDto dto) {
		return servico.salvarLivro(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaLivro(@PathVariable Long id){
		if(!servico.apagarLivro(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroDto> alterarLivro(@PathVariable Long id, @RequestBody LivroDto dto){
		Optional<LivroDto> livroAlterado = servico.alterarLivro(id, dto);
		if (!livroAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(livroAlterado.get());
	}
}
