package org.serratec.bookshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.PedidoDto;
import org.serratec.bookshop.dto.PedidoItemDto;
import org.serratec.bookshop.dto.PedidoRegistroDto;
import org.serratec.bookshop.model.Cliente;
import org.serratec.bookshop.model.Livro;
import org.serratec.bookshop.model.Pedido;
import org.serratec.bookshop.model.PedidoItem;
import org.serratec.bookshop.repository.ClienteRepository;
import org.serratec.bookshop.repository.LivroRepository;
import org.serratec.bookshop.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
    private ClienteRepository clienteRepository; 
	
	@Autowired
	private LivroRepository livroRepository;
	
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
	
	 public PedidoDto salvarPedido(PedidoRegistroDto pedidoDto) {
		 	if (!clienteRepository.existsById(pedidoDto.getClienteId())) {
		 		throw new IllegalArgumentException("Cliente não encontrado");
		 	}
		 	
		 	Pedido pedido = new Pedido();
		 	pedido.setCliente(clienteRepository.findById(pedidoDto.getClienteId()).get());
		 	List<PedidoItem> itens = new ArrayList<>();
		 	
		 	for (PedidoItemDto p : pedidoDto.getItens())
		 	{
		 		if (!livroRepository.existsById(p.livroId())) {
		 			throw new IllegalArgumentException("Livro não encontrado");
		 		}
		 		
		 		Livro livro = livroRepository.findById(p.livroId()).get();
		 		PedidoItem pedidoItem = new PedidoItem();
		 		pedidoItem.setLivro(livro);
		 		pedidoItem.setPrecoVenda(livro.getValor_unitario());
		 		pedidoItem.setQuantidade(p.quantidade());
		 		pedidoItem.setPercentualDesconto(p.percentualDesconto());
		 		Double valorBruto = livro.getValor_unitario() * p.quantidade(); 
		 		pedidoItem.setValorBruto(valorBruto);
		 		pedidoItem.setValorLiquido(valorBruto - (valorBruto / 100 * p.percentualDesconto()));
		 		itens.add(pedidoItem);	
			}
		 	
	        pedido.setDataPedido(pedidoDto.getDataPedido());
	        pedido.setDataEnvio(pedidoDto.getDataPedido().plusDays(2));
	        pedido.setDataEntrega(pedidoDto.getDataEntrega());
	        pedido.setStatus("Pedido em andamento");
	        pedido.setPedidosItem(itens);
	        Double valorTotal = itens.stream()
	                .mapToDouble(PedidoItem::getValorLiquido) 
	                .sum();
	        pedido.setValorTotal(valorTotal);
	        
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
	            valorTotal += valorLiquido; 
	        }

	        pedido.setValorTotal(valorTotal); 
	    }
}
