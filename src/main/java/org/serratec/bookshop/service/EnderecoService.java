package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;


import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Endereco;
import org.serratec.bookshop.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repositorio;
	
	public List<EnderecoDto> obterTodos() {
		return repositorio.findAll().stream().map(c -> EnderecoDto.toDto(c)).toList();
	}
	
	public Optional<EnderecoDto> obterPorId(Long id) {
		if(!repositorio.existsById(id)) { 
			return Optional.empty();
		}
		return Optional.of(EnderecoDto.toDto(repositorio.findById(id).get()));
	}
	
	public EnderecoDto salvarEndereco(Endereco endereco) {
		Endereco enderecoEntity = repositorio.save(endereco);
		return EnderecoDto.toDto(enderecoEntity);
	}	
	
	public boolean apagarEndereco(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto dto) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Endereco enderecoEntity = dto.toEntity();
		enderecoEntity.setId(id);
		repositorio.save(enderecoEntity);
		return Optional.of(EnderecoDto.toDto(enderecoEntity));
	}

	

	

	
}

