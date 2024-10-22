package org.serratec.bookshop.service;


import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.ClienteDto;
import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Cliente;
import org.serratec.bookshop.model.Endereco;
import org.serratec.bookshop.model.ViaCep;
import org.serratec.bookshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

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
	
	public ClienteDto aplicarApiCep(ClienteDto dto) {
		String json = ViaCepService.(dto.toEntity().getEnderecos().getCep());
        ViaCep ViaCep = new Gson().fromJson(json, ViaCep.class);

        Endereco endereco = new Endereco();
        endereco.setCep(dto.toEntity().getEnderecos().getCep());
        endereco.setRua(ViaCep.rua());
        endereco.setBairro(ViaCep.bairro());
        endereco.setCidade(ViaCep.cidade());
        endereco.setUf(ViaCep.uf());
        endereco.setNumero(dto.toEntity().getEnderecos().getNumero());
        endereco.setComplemento(dto.toEntity().getEnderecos().getComplemento());

        Cliente cliente = dto.toEntity();
        cliente.setEnderecos(endereco);
        return ClienteDto.toDto(cliente);
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
	
	public ClienteDto buscarPorIdPedido(Long id) {
        Cliente cliente = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Id do Pedido não encontrado"));
        return ClienteDto.toDto(cliente);
    }
}
