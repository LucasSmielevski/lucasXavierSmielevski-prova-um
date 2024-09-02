package jv.triersistemas.lucas_xavier_smielevski_prova1.service;

import java.time.LocalDate;
import java.util.List;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;

public interface ReservaService {
	ReservaDto adicionarReserva(ReservaDto novaReserva);

	List<ReservaDto> buscarReservasPorCliente(Long idCliente);

	String buscarSeTemReservaNaMesaPorData(LocalDate dataReserva, Integer numeroMesa);

	ReservaDto atualizaReserva(Long id, StatusEnum status);

	public void removerReserva(Long id);

}
