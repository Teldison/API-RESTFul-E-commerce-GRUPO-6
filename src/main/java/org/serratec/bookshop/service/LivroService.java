package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.LivroDto;
import org.serratec.bookshop.model.Livro;
import org.serratec.bookshop.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repositorio;
	
	public List<LivroDto> obterTodos() {
		return repositorio.findAll().stream().map(p->LivroDto.toDto(p)).toList();
	}
	
	public Optional<LivroDto> obterPorId(Long id) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		} return Optional.of(LivroDto.toDto(repositorio.findById(id).get()));
	}
	
    public List<LivroDto> obterPorNome(String nome) {
        List<Livro> livro = repositorio.findByNomeIgnoreCase(nome);
        return livro.stream()
            .map(LivroDto::toDto)
            .toList();
    }
	
	public LivroDto salvarLivro(LivroDto livro) {
		return LivroDto.toDto(repositorio.save(livro.toEntity()));
	}
	
	public boolean apagarLivro(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		} repositorio.deleteById(id);
		return true;
	}
	
	public Optional<LivroDto> alterarLivro(Long id, LivroDto dto) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		} Livro livroEntity = dto.toEntity();
		  livroEntity.setId(id);
		  repositorio.save(livroEntity);
		  return Optional.of(LivroDto.toDto(livroEntity));
	}

}
