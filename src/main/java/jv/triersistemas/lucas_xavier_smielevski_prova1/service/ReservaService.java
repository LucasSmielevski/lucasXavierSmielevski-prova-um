package jv.triersistemas.lucas_xavier_smielevski_prova1.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;


public interface ReservaService {
	ReservaDto adicionarReserva(@RequestBody ReservaDto novaReserva, ClienteDto clienteDto);
	
	List<ReservaDto> buscarReservasPorCliente (Long idCliente);
	
	ReservaDto atualizaReserva (Long id , ReservaDto reservaAtualizada);
	
	public void removerTarefa(Long id);
		
}
