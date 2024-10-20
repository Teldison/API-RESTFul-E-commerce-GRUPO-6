package org.serratec.bookshop.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Endereco;
import org.serratec.bookshop.viacep.viaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
	
	private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    @Autowired
    private RestTemplate restTemplate;

    public Endereco buscarEnderecoPorCep(String cep) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("cep", cep);

        try {
            viaCepResponse response = restTemplate.getForObject(VIA_CEP_URL, viaCepResponse.class, uriVariables);
            if (response != null && response.getCep() != null) {
                return converterParaEndereco(response);
            } else {
                throw new RuntimeException("CEP não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar CEP: " + e.getMessage());
        }
    }

    private Endereco converterParaEndereco(viaCepResponse response) {
        Endereco endereco = new Endereco();
        endereco.setCep(response.getCep());
        endereco.setRua(response.getLogradouro());
        endereco.setBairro(response.getBairro());
        endereco.setCidade(response.getLocalidade());
        endereco.setUf(response.getUf());
        endereco.setComplemento(response.getComplemento());
        return endereco;
    }

	public Optional<EnderecoDto> alterarEndereco(Long id, EnderecoDto dto) {
		return null;
	}

	public boolean apagarEndereco(Long id) {
		return false;
	}
}

