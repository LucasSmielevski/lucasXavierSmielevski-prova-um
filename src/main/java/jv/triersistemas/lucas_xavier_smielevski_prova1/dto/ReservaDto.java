package jv.triersistemas.lucas_xavier_smielevski_prova1.dto;

import java.time.LocalDate;

import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ReservaEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaDto {
	private Long id;
	private LocalDate dataReserva;
	private int numeroPessoas;
	private int numeroMesa;
	private StatusEnum status;
	
	private Long idCliente;
	
	public ReservaDto(ReservaEntity entity) {
		this.id = entity.getId();
		this.dataReserva = entity.getDataReserva();
		this.numeroPessoas = entity.getNumeroPessoas();
		this.numeroMesa = entity.getNumeroMesa();
		this.status = entity.getStatus();
		this.idCliente = entity.getCliente().getId();
	}
}
