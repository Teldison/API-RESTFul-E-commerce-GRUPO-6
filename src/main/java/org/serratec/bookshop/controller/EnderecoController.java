package org.serratec.bookshop.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.bookshop.dto.EnderecoDto;
import org.serratec.bookshop.model.Endereco;
import org.serratec.bookshop.service.EnderecoService;
import org.serratec.bookshop.service.ViaCepService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping
    public List<EnderecoDto> getAllEnderecos() {
        return enderecoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> obterPorId(@PathVariable Long id) {
        Optional<EnderecoDto> endereco = enderecoService.obterPorId(id);
        if(!endereco.isPresent()) {
        	return ResponseEntity.notFound().build();
        } return ResponseEntity.ok(endereco.get());
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscarEnderecoPorCep(@PathVariable String cep) {
        try {
            Endereco endereco = viaCepService.buscarEnderecoPorCep(cep);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public EnderecoDto criarEndereco(@RequestBody Endereco endereco) {
        return enderecoService.salvarEndereco(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> alterarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
		EnderecoDto enderecoDto = EnderecoDto.toDto(endereco);
    	Optional<EnderecoDto> enderecoAlterado = viaCepService.alterarEndereco(id, enderecoDto);
		if(!enderecoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		} return ResponseEntity.notFound().build();
	}  

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
		if(!viaCepService.apagarEndereco(id)) {
			return ResponseEntity.notFound().build();
		} return ResponseEntity.noContent().build();
	}
}
