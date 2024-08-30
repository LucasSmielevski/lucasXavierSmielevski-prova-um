package jv.triersistemas.lucas_xavier_smielevski_prova1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ClienteEntity;
import jv.triersistemas.lucas_xavier_smielevski_prova1.entity.ReservaEntity;
import jv.triersistemas.primeiro_projeto.entity.TarefaEntity;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{
    List<ReservaEntity> findByCliente_id(Long clienteId);
    
    ReservaEntity findByNumeroMesa(int numeroMesa);


}
