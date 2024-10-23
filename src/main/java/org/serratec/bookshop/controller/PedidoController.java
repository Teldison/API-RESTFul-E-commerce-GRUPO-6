package org.serratec.bookshop.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.PedidoDto;
import org.serratec.bookshop.dto.PedidoRegistroDto;
import org.serratec.bookshop.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired 
	private PedidoService pedidoService;
	
	@GetMapping
	public List<PedidoDto> todosPedidos() {
		return pedidoService.obterTodos();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna um pedido por id",
	description = "Dado um determinado número de id, será retornado o pedido contendo as informações "
			+ "de cliente, nome do pruduto e valor")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Pedido pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Pedido localizado")
    })
	public ResponseEntity<PedidoDto> obterPorId(@PathVariable Long id) {
		Optional<PedidoDto> dto = pedidoService.obterPorId(id);
		if(!dto.isPresent()){
			return ResponseEntity.notFound().build();
		} return ResponseEntity.ok(dto.get());
	}
	
	@PostMapping
	@Operation(summary = "Registra um pedido",
	description = "Cria um pedido com detalhes do cliente e do produto")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi registrado o Pedido pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Pedido registrado")
    })
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDto criarPedido(@RequestBody PedidoRegistroDto pedido) {
		return pedidoService.salvarPedido(pedido);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar um pedido por id",
	description = "apaga o registro de um pedido")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi deletado o Pedido pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Pedido deletado")
    })
	public ResponseEntity<Void> apagarPedido(@PathVariable Long id) {
		if(!pedidoService.apagarPedido(id)) {
			return ResponseEntity.notFound().build();
		} return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Altera o pedido por id",
	description = "altera o registro de um pedido")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Não foi alterado o Pedido pelo id informado. Verifique!"),
            @ApiResponse(responseCode = "200", description = "Pedido alterado")
    })
	public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto dto) {
		Optional<PedidoDto> pedidoAlterado = pedidoService.alterarPedido(id, dto);
		if(!pedidoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		} return ResponseEntity.notFound().build();
	}
}
