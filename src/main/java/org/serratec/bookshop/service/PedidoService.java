package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.PedidoDto;
import org.serratec.bookshop.model.Pedido;
import org.serratec.bookshop.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private EmailService emailService;
	
	public List<PedidoDto> obterTodos() {
		return repositorio.findAll().stream().map(p->PedidoDto.toDto(p)).toList();
	}
	
	public Optional<PedidoDto> obterPorId(Long id) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		} return Optional.of(PedidoDto.toDto(repositorio.findById(id).get()));
	}
	
	public PedidoDto salvarPedido(PedidoDto pedido) {
		emailService.enviarEmail("caiojunqueirapacheco@gmail.com", "Novo pedido", pedido.toString());
		return PedidoDto.toDto(repositorio.save(pedido.toEntity()));
	}
	
	public boolean apagarPedido(Long id) {
		if(!repositorio.existsById(id)) {
			return false;
		} repositorio.deleteById(id);
		return true;
	}
	
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto dto) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		} Pedido pedidoEntity = dto.toEntity();
		  pedidoEntity.setId(id);
		  repositorio.save(pedidoEntity);
		  return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
}
