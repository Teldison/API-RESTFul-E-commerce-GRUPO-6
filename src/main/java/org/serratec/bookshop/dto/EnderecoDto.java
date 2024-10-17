package org.serratec.bookshop.dto;

import org.serratec.bookshop.model.Endereco;


public record EnderecoDto(
		Long id,
		String cep,
		String rua,
		String bairro,
		String cidade,
		Double numero,
		String complemento,
		String uf
		) {
	
		public Endereco toEntity() {
			Endereco endereco = new Endereco();
			endereco.setId(this.id);
			endereco.setCep(cep);
			endereco.setRua(rua);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setNumero(numero);
			endereco.setComplemento(complemento);
			endereco.setUf(uf);
			return endereco;
		}
		
		public static EnderecoDto toDto(Endereco endereco) {
			return new EnderecoDto(endereco.getId(), endereco.getCep(), 
					endereco.getRua(), endereco.getBairro(), endereco.getCidade(),
					endereco.getNumero(), endereco.getComplemento(), endereco.getUf());
		}

}
