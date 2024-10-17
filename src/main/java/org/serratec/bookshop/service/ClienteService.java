package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.ClienteDto;
import org.serratec.bookshop.model.Cliente;
import org.serratec.bookshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class ClienteService {
	@Autowired
	private ClienteRepository repositorio;
	
	public List<ClienteDto> obterTodos() {
		return repositorio.findAll().stream().map(c -> ClienteDto.toDto(c)).toList();
	}
	
	public Optional<ClienteDto> obterPorId(Long id) {
		if(!repositorio.existsById(id)) { 
			return Optional.empty();
		}
		return Optional.of(ClienteDto.toDto(repositorio.findById(id).get()));
	}
	
	public ClienteDto salvarCliente(ClienteDto dto) {
		Cliente clienteEntity = repositorio.save(dto.toEntity());
		return ClienteDto.toDto(clienteEntity);
	}	
		
	public boolean apagarCliente(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public Optional<ClienteDto> alterarCliente(Long id, ClienteDto dto) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Cliente clienteEntity = dto.toEntity();
		clienteEntity.setId(id);
		repositorio.save(clienteEntity);
		return Optional.of(ClienteDto.toDto(clienteEntity));
	}
	
}
