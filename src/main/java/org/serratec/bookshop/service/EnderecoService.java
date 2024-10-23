package org.serratec.bookshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Endereco;
import org.serratec.bookshop.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;

	@Autowired
	private ViaCepService viaCepService;

	public List<Endereco> obterTodos() {
		return  repositorio.findAll();
	}

	public Endereco obterPorId(Long id) {
		return  repositorio.findById(id).orElseThrow(
				() -> new EntityNotFoundException
						("Não foi possível encontrar um endereço com o id " + id)
		);
	}
	

	public List<Endereco> obterPorCep(String cep) {
		
		List<Endereco> enderecos =  repositorio.findAll();
		List<Endereco> enderecosCep = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			if (endereco.getCep().equals(cep)) {
				enderecosCep.add(endereco);
			}
		}
		return enderecosCep;
	}

	public EnderecoDto salvarEndereco(String cep, EnderecoDto endereco) {
		EnderecoDto enderecoViaCep = viaCepService.getCepInfo(cep);
		return EnderecoDto.toDto(repositorio.save(enderecoViaCep.toEntity()));
		}
	
	public boolean apagarEndereco(Long id) {
		
		if(!repositorio.existsById(id)) {
			return false;
		} repositorio.deleteById(id);
		return true;
	}
	
	public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto dto) {
		if (!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Endereco enderecoEntity = dto.toEntity();
		enderecoEntity.setId(id);
		repositorio.save(enderecoEntity);
		return Optional.of(EnderecoDto.toDto(enderecoEntity));

	}
	
	
}
