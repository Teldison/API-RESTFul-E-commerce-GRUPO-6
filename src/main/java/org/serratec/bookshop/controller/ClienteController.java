package org.serratec.bookshop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.ClienteDto;
import org.serratec.bookshop.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService servico;
	
	@GetMapping
	public List<ClienteDto> obterTodos() {
		return servico.obterTodos();
	}
	
	@GetMapping("/pedido/{id}")
	@Operation(summary = "Retorna um cliente por id",
	description = "Dado um determinado número de id do pedido, será retornado o cliente")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Cliente pelo id do pedido informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Cliente localizado")
    })
    public ResponseEntity<ClienteDto> buscarClientePorIdPedido(@PathVariable Long id) {
        ClienteDto cliente = servico.buscarPorIdPedido(id);
		return ResponseEntity.ok(cliente); 
    }
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um cliente por id",
	description = "Dado um número de id retorna um cliente")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Cliente pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Cliente localizado")
    })
	public ResponseEntity<ClienteDto> obterPorId (@PathVariable Long id) {
		Optional<ClienteDto> dto = servico.obterPorId(id);
		if (!dto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping 
	@Operation(summary = "Registra um cliente",
	description = "Dado um determinado número de id, será retornado o cliente contendo as informações")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi registrado o Cliente desejado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Cliente registrado")
    })
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto cadastrarPedido(@RequestBody ClienteDto dto) throws IOException, InterruptedException {
		return servico.salvarCliente(dto);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar um cliente por id",
	description = "apaga o registro de um cliente")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi deletado o Cliente desejado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Cliente deletado")
    })
	public ResponseEntity<Void> deletaCliente(@PathVariable Long id){
		if(!servico.apagarCliente(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Altera o cliente por id",
	description = "altera o registro de um cliente")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi alterado o Cliente desejado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Cliente alterado")
    })
	public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id, @RequestBody ClienteDto dto) throws IOException, InterruptedException{
		Optional<ClienteDto> clienteAlterado = servico.alterarCliente(id, dto);
		if (!clienteAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteAlterado.get());
	}
}
