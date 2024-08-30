package jv.triersistemas.lucas_xavier_smielevski_prova1.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ReservaEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;
import jv.triersistemas.lucas_xavier_smielevski_prova1.repository.ReservaRepository;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	ReservaRepository repository;

	@Autowired
	ClienteServiceImpl clienteservice;

	@Override
	public ReservaDto adicionarReserva(ReservaDto novaReserva, ClienteDto clienteDto) {
		ClienteEntity idCliente = clienteservice.getClientePorId(novaReserva.getIdCliente()).map(ClienteEntity::new)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

		ReservaEntity reserva = new ReservaEntity(novaReserva);
		reserva.setCliente(idCliente);

		if (reserva.getDataReserva().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("A data de reserva não pode ser uma data no passado");
		}
		if (reserva.getNumeroPessoas() < 1 || novaReserva.getNumeroPessoas() > 10) {
			throw new IllegalArgumentException("O numero minimo de pessoas é 1 e o máximo de pessoas na mesa são 10");
		}
		if (reserva.getNumeroMesa() < 1 || novaReserva.getNumeroMesa() > 20) {
			throw new IllegalArgumentException("Só existe mesas do 1 ao 20");
		}
		if (reserva.getStatus() == null) {
			reserva.setStatus(StatusEnum.FEITA);
		}

		ReservaEntity reservaExistente = getReservaPorId(reserva.getNumeroMesa());

		if (reservaExistente == null) {
		} else {
			if (reserva.getDataReserva().equals(reservaExistente.getDataReserva())) {
				throw new IllegalArgumentException("Já existe uma reserva da mesa para está data");
			}
		}
		repository.save(reserva);
		return new ReservaDto(reserva);
	}

	@Override
	public List<ReservaDto> buscarReservasPorCliente(Long idCliente) {
		List<ReservaEntity> reservasCliente = repository.findByCliente_id(idCliente);
		return reservasCliente.stream().map(ReservaDto::new).toList();
	}

	@Override
	public ReservaDto atualizaReserva(Long id, ReservaDto reservaAtualizada) {
		Optional<ReservaEntity> reservaEntity = repository.findById(id);
		if (reservaEntity.isPresent()) {
			if (reservaAtualizada.getStatus() == StatusEnum.CONCLUIDA) {
				if (reservaAtualizada.getDataReserva().equals(LocalDate.now())
						|| reservaAtualizada.getDataReserva().isAfter(LocalDate.now())) {
					reservaEntity.get().atualizaReserva(reservaAtualizada);
				}
			} else {
				var entidadePersistida = repository.save(reservaEntity.get());
				return new ReservaDto(entidadePersistida);
			}
			var entidadePersistida = repository.save(reservaEntity.get());
			return new ReservaDto(entidadePersistida);
		}
		return null;

	}
	
	public void removerTarefa(Long id) {
		repository.deleteById(id);
	}

	public ReservaEntity getReservaPorId(int numMesa) {
		ReservaEntity reservaPorId = repository.findByNumeroMesa(numMesa);
		return reservaPorId;
	}

}
