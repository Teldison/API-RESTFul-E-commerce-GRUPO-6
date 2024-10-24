package org.serratec.bookshop.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public EnderecoDto getCepInfo(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject(VIACEP_URL, Endereco.class, cep);
        System.out.println("Dados recebidos do ViaCep: " + endereco);
        EnderecoDto enderecoDto = new EnderecoDto( 
        		endereco.getId(), endereco.getCep(),
        		endereco.getRua(), endereco.getBairro(),
        	    endereco.getCidade(), endereco.getNumero(),
        	    endereco.getComplemento(), endereco.getUf());
        
        return enderecoDto;
    }
    
    public static String pegarEndereco(String cep) throws IOException, InterruptedException {
    	URI uri = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
    	
    	HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		         .uri(uri)
		         .build();
		HttpResponse<String> response = client
				 .send(request, BodyHandlers.ofString());   
		String json = response.body();
		return json;
    }
}