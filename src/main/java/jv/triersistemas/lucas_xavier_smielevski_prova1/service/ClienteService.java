package jv.triersistemas.lucas_xavier_smielevski_prova1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.dto.TarefaDto;

public interface ClienteService {
	List<ClienteDto> getTodosClientes();

	Optional<ClienteDto> getClientePorId(Long id);

	ClienteDto adicionarCliente(ClienteDto novaCliente);

	ClienteDto atualizarCliente(Long id, ClienteDto clienteAtualizado);

	void removerCliente(Long id);
}
