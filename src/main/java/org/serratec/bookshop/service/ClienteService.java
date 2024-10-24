package org.serratec.bookshop.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.ClienteDto;
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
	
	public ClienteDto utilizarCepApi(ClienteDto dto) throws IOException, InterruptedException {
		String cep = dto.toEntity().getCep();
		String json = ViaCepService.pegarEndereco(cep);
        ViaCep viaCep = new Gson().fromJson(json.toString(), ViaCep.class);

        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep());
        endereco.setRua(viaCep.rua());
        endereco.setBairro(viaCep.bairro());
        endereco.setCidade(viaCep.cidade());
        endereco.setUf(viaCep.uf());
        endereco.setNumero(dto.endereco().numero());
        endereco.setComplemento(dto.endereco().complemento());

        Cliente cliente = dto.toEntity();
        cliente.setEndereco(endereco);
        return ClienteDto.toDto(cliente);
	}
	
	public ClienteDto salvarCliente(ClienteDto dto) throws IOException, InterruptedException {
		ClienteDto cliente = utilizarCepApi(dto);
		Cliente clienteEntity = repositorio.save(cliente.toEntity());
		return ClienteDto.toDto(clienteEntity);
	}	
		
	public boolean apagarCliente(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		}
		repositorio.deleteById(id);
		return true;
	}
	
	public Optional<ClienteDto> alterarCliente(Long id, ClienteDto dto) throws IOException, InterruptedException {
		ClienteDto cliente = utilizarCepApi(dto);
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
		Cliente clienteEntity = repositorio.findById(id).orElseThrow();
		clienteEntity.setNome_completo(cliente.nome_completo());
		clienteEntity.setCpf(cliente.cpf());
		clienteEntity.setAnoNascimento(cliente.anoNascimento());
		clienteEntity.setEndereco(cliente.endereco().toEntity());
		Cliente clienteSalvo = repositorio.save(clienteEntity);
		repositorio.save(clienteEntity);
		return Optional.of(ClienteDto.toDto(clienteEntity));
	}
	
	public ClienteDto buscarPorIdPedido(Long id) {
        Cliente cliente = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Id do Pedido não encontrado"));
        return ClienteDto.toDto(cliente);
    }
}
