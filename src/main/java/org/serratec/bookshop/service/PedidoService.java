package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.PedidoDto;
import org.serratec.bookshop.model.Cliente;
import org.serratec.bookshop.model.Pedido;
import org.serratec.bookshop.model.PedidoItem;
import org.serratec.bookshop.repository.ClienteRepository;
import org.serratec.bookshop.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
    private ClienteRepository clienteRepository; 
	
	@Autowired
	private EmailService emailService;
	
	public List<PedidoDto> obterTodos() {
		return  pedidoRepository.findAll().stream().map(p->PedidoDto.toDto(p)).toList();
	}
	
	public Optional<PedidoDto> obterPorId(Long id) {
		if(! pedidoRepository.existsById(id)) {
			return Optional.empty();
		} return Optional.of(PedidoDto.toDto( pedidoRepository.findById(id).get()));
	}
	
	 public PedidoDto salvarPedido(PedidoDto pedidoDto) {
	        Pedido pedido = pedidoDto.toEntity();
	        
	        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
	            Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
	                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
	            pedido.setCliente(cliente); 
	        }
	        
	        calcularValoresPedido(pedido); // Chama o método para calcular os valores do pedido

	        pedido = pedidoRepository.save(pedido);
	        emailService.enviarEmail("caiojunqueirapacheco@gmail.com", "Novo pedido", pedido.toString());
	        return PedidoDto.toDto(pedido);
	    }

	public boolean apagarPedido(Long id) {
		if(! pedidoRepository.existsById(id)) {
			return false;
		} pedidoRepository.deleteById(id);
		return true;
	}
	
	public Optional<PedidoDto> alterarPedido(Long id, PedidoDto dto) {
		if(! pedidoRepository.existsById(id)) {
			return Optional.empty();
		} Pedido pedidoEntity = dto.toEntity();
		  pedidoEntity.setId(id);
		  pedidoRepository.save(pedidoEntity);
		  return Optional.of(PedidoDto.toDto(pedidoEntity));
	}
	
	 private void calcularValoresPedido(Pedido pedido) {
	        double valorTotal = 0.0;

	        for (PedidoItem item : pedido.getPedidosItem()) {
	            double valorBruto = item.getPrecoVenda() * item.getQuantidade();
	            double valorLiquido;

	            if (item.getPercentualDesconto() != null && item.getPercentualDesconto() > 0) {
	                double desconto = valorBruto * (item.getPercentualDesconto() / 100);
	                valorLiquido = valorBruto - desconto;
	            } else {
	                valorLiquido = valorBruto; 
	            }

	            item.setValorBruto(valorBruto);
	            item.setValorLiquido(valorLiquido);
	            valorTotal += valorLiquido; // Soma os valores líquidos para o total do pedido
	        }

	        pedido.setValorTotal(valorTotal); // Armazena o valor total do pedido
	    }
}
