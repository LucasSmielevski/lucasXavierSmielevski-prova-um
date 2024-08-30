package jv.triersistemas.lucas_xavier_smielevski_prova1.dto;

import java.util.List;

import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ReservaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
	private Long id;
	private String nome;
	private String email;
	private List<ReservaEntity> reservas;

	
	public ClienteDto(ClienteEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.reservas = entity.getReservas();
	}
}
