package org.serratec.bookshop.dto;

import java.time.LocalDate;

import org.serratec.bookshop.model.Cliente;

public record ClienteDto(
		Long id,
		String email,
		String nome_completo,
		String cpf,
		String telefone,
		LocalDate anoNascimento
		) {
	
	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setId(this.id);
		cliente.setEmail(this.email);
		cliente.setNome_completo(this.nome_completo);
		cliente.setCpf(this.cpf);
		cliente.setTelefone(this.telefone);
		cliente.setAnoNascimento(this.anoNascimento);
		return cliente;
	}
	
	public static ClienteDto toDto (Cliente cliente) {
		return new ClienteDto(cliente.getId(),cliente.getEmail(),
				cliente.getNome_completo(),cliente.getCpf(),
				cliente.getTelefone(),cliente.getAnoNascimento());
		
	}
}