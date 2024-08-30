package jv.triersistemas.lucas_xavier_smielevski_prova1.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "reserva")
public class ReservaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private LocalDate dataReserva;
	@Column(nullable = false)
	private int numeroPessoas;
	@Column(nullable = false)
	private int numeroMesa;
	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnore
	private ClienteEntity cliente;

	public ReservaEntity(ReservaDto dto) {
		this.id = dto.getId();
		this.dataReserva = dto.getDataReserva();
		this.numeroPessoas = dto.getNumeroPessoas();
		this.numeroMesa = dto.getNumeroMesa();
		this.status = dto.getStatus();
	}

	public ReservaEntity atualizaReserva(ReservaDto dto) {
		this.dataReserva = dto.getDataReserva();
		this.numeroPessoas = dto.getNumeroPessoas();
		this.numeroMesa = dto.getNumeroMesa();
		this.status = dto.getStatus();
		return this;
	}
}
