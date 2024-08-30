package jv.triersistemas.lucas_xavier_smielevski_prova1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ClienteService;
import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public List<ClienteDto> getTodasCategorias(){
		return clienteService.getTodosClientes();
	}
	
	@GetMapping("/{id}")
	public ClienteDto buscarClienteId(@PathVariable Long id) {
		Optional<ClienteDto> cliente =  clienteService.getClientePorId(id);
		return cliente.orElse(null);
	}
	
	@PostMapping
	public ClienteDto adicionarCliente(@RequestBody ClienteDto clienteDto) {
		return clienteService.adicionarCliente(clienteDto);
	}
	
	@PutMapping("/{id}")
	public ClienteDto atualizarCliente(@PathVariable Long id , @RequestBody ClienteDto clienteAtualizado) {
		return clienteService.atualizarCliente(id, clienteAtualizado);
	}
	
	@DeleteMapping
	public void removerCliente(@RequestParam Long id) {
		clienteService.removerCliente(id);
	}
}
