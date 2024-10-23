package org.serratec.bookshop.service;

import java.util.List;
import java.util.Optional;


import org.serratec.bookshop.dto.PedidoDto;
import org.serratec.bookshop.dto.RegistroPedidoDto;
import org.serratec.bookshop.model.Cliente;
import org.serratec.bookshop.model.Pedido;
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
	
	public RegistroPedidoDto calcularPedido(RegistroPedidoDto dto) {
		
		double valorTotal = 0;
		double valorBruto = 0;
		double valorLiquido = 0;
		Pedido pedido = dto.toEntity(livroRepository);
				
		int i = 0;
		for (RegistroPedidoDto rp : pedido.getLivro().stream().map(RegistroPedidoDto::toDto).toList()) {
		    pedidoService.obterPorId(rp.getProdutoId()).ifPresent(livro -> {
		        double valorBruto = livro.getValorUnitario() * rp.getQuantidade();
		        rp.setValorBruto(valorBruto);

		        double percentualDesconto = ip.getPercentualDesconto() / 100.0;
		        double valorDesconto = valorBruto * percentualDesconto;

		        double valorLiquido = valorBruto - valorDesconto;
		        ip.setValorLiquido(valorLiquido);

		        valorTotal += valorLiquido;

		        ItemPedidoDtoCadastroPedido itemDto = dto.getItensPedido().get(i);
		        itemDto.setValorBruto(valorBruto);
		        itemDto.setValorLiquido(valorLiquido);
		        itemDto.setPrecoVenda(produto.getValorUnitario());

		        i++;
		    }dto.setValorTotal(valorTotal);
			pedido.setValorTotal(valorTotal);
			return dto;
		}
		
	}
	
	public PedidoDto salvarPedido(PedidoDto pedidoDto) {
	    Pedido pedido = pedidoDto.toEntity();
	    if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
	        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
	                          .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
	        pedido.setCliente(cliente); 
	    }
	    pedido =  pedidoRepository.save(pedido);
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
	
	public class ResourceNotFoundException extends RuntimeException {
	    public ResourceNotFoundException(String message) {
	        super(message);
	    }
	}
	
	
}
