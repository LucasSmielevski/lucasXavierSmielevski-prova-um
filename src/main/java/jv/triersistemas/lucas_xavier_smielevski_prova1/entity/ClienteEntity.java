package jv.triersistemas.lucas_xavier_smielevski_prova1.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.primeiro_projeto.dto.CategoriaDto;
import jv.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cliente")
public class ClienteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.DETACH)
	private List<ReservaEntity> reservas;
	
	public ClienteEntity(ClienteDto dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.reservas = dto.getReservas();
	}
	
    public ClienteEntity atualizaCliente(ClienteDto dto) {
    	this.nome = dto.getNome();
    	this.email = dto.getEmail();
    	return this;
    }
}
