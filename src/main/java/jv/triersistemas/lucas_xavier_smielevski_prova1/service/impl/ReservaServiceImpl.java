package jv.triersistemas.lucas_xavier_smielevski_prova1.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ReservaEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;
import jv.triersistemas.lucas_xavier_smielevski_prova1.repository.ReservaRepository;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ClienteService;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private ClienteService clienteservice;

	@Override
	public ReservaDto adicionarReserva(ReservaDto novaReserva) {
		ClienteEntity cliente = clienteservice.getClientePorId(novaReserva.getIdCliente()).map(ClienteEntity::new)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

		validaDataReserva(novaReserva);
		validaNumMesa(novaReserva);
		validaNumPessoas(novaReserva);
		validaReservaNaMesmaData(novaReserva);

		ReservaEntity reserva = new ReservaEntity(novaReserva);
		reserva.setCliente(cliente);

		return new ReservaDto(repository.save(reserva));
	}

	@Override
	public List<ReservaDto> buscarReservasPorCliente(Long idCliente) {
		List<ReservaEntity> reservasCliente = repository.findByClienteId(idCliente);
		return reservasCliente.stream().map(ReservaDto::new).toList();
	}

	@Override
	public ReservaDto atualizaReserva(Long id, StatusEnum status) {
		Optional<ReservaEntity> reservaEntity = repository.findById(id);
		if (reservaEntity.isPresent()) {
			validaReservaConcluida(status, reservaEntity.get().getDataReserva());
			validaReservaCancelada(status, reservaEntity.get().getDataReserva());
			reservaEntity.get().atualizarStatus(status);
			var entidadePersistida = repository.save(reservaEntity.get());
			return new ReservaDto(entidadePersistida);
		}
		return null;

	}

	@Override
	public String buscarSeTemReservaNaMesaPorData(LocalDate dataReserva, Integer numeroMesa) {
		List<ReservaEntity> reservasExistentes = getReservaPorNumeroMesa(numeroMesa, dataReserva);
		if (!reservasExistentes.isEmpty()) {
			return "Existe uma reserva para está data na mesa " + numeroMesa;
		}
		return "Não existe uma reserva para está data na mesa" + numeroMesa;
	}

	public void removerReserva(Long id) {
		repository.deleteById(id);
	}

	public void validaDataReserva(ReservaDto reserva) {
		if (reserva.getDataReserva().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("A data de reserva não pode ser uma data no passado");
		}
	}

	public void validaNumPessoas(ReservaDto reserva) {
		if (reserva.getNumeroPessoas() < 1 || reserva.getNumeroPessoas() > 10) {
			throw new IllegalArgumentException("O numero minimo de pessoas é 1 e o máximo de pessoas na mesa são 10");
		}
	}

	public void validaNumMesa(ReservaDto reserva) {
		if (reserva.getNumeroMesa() < 1 || reserva.getNumeroMesa() > 20) {
			throw new IllegalArgumentException("Só existe mesas do 1 ao 20");
		}
	}

	public void validaReservaNaMesmaData(ReservaDto reserva) {
		List<ReservaEntity> reservasExistentes = getReservaPorNumeroMesa(reserva.getNumeroMesa(),
				reserva.getDataReserva());
		boolean reservasFeitas = reservasExistentes.stream()
				.anyMatch(reservaExistente -> reservaExistente.getStatus().equals(StatusEnum.FEITA));
		if (Objects.nonNull(reservasExistentes) && reservasFeitas) {
			if (!reservasExistentes.isEmpty()) {
				throw new IllegalArgumentException("Já existe uma reserva da mesa para está data");
			}
		}
	}

	public void validaReservaConcluida(StatusEnum status, LocalDate dataReserva) {
		if (status == StatusEnum.CONCLUIDA
				&& (dataReserva.isBefore(LocalDate.now()))) {
			throw new IllegalArgumentException(
					"A reserva só pode ser alterada para concluida caso a data seja igual a da reserva ou após");
		}

	}

	public void validaReservaCancelada(StatusEnum status, LocalDate dataReserva) {
		if (status == StatusEnum.CANCELADA
				&& dataReserva.equals(LocalDate.now())
				|| dataReserva.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("A reserva só pode ser cancelada com no máximo 1 dia de antecedência");
		}

	}

	public List<ReservaEntity> getReservaPorNumeroMesa(int numMesa, LocalDate dataReserva) {
		List<ReservaEntity> reservaPorId = repository.findByNumeroMesaAndDataReserva(numMesa, dataReserva);
		return reservaPorId;
	}

}
