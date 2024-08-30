package jv.triersistemas.lucas_xavier_smielevski_prova1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.repository.ClienteRepository;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ClienteService;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	ClienteRepository repository;
	
	@Override
	public List<ClienteDto> getTodosClientes() {
		List<ClienteEntity> clientesAll = repository.findAll();
		return clientesAll.stream().map(ClienteDto::new).toList();
	}

	@Override
	public Optional<ClienteDto> getClientePorId(Long id) {
		Optional<ClienteEntity> clientePorId = repository.findById(id);
		return clientePorId.map(ClienteDto::new);
	}

	@Override
	public ClienteDto adicionarCliente(ClienteDto novaTarefa) {
		ClienteEntity novoCliente = repository.save(new ClienteEntity(novaTarefa));
		return new ClienteDto(novoCliente);
	}

	@Override
	public ClienteDto atualizarCliente(Long id, ClienteDto clienteAtualizado) {
		Optional<ClienteEntity> clientePorId = repository.findById(id);
		if(clientePorId.isPresent()) {
			clientePorId.get().atualizaCliente(clienteAtualizado);
			var entidadePersistida = repository.save(clientePorId.get());
			return new ClienteDto(entidadePersistida);
		}
		return null;
	}

	@Override
	public void removerCliente(Long id) {
		repository.deleteById(id);
		
	}

}
