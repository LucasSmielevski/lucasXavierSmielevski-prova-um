package jv.triersistemas.lucas_xavier_smielevski_prova1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ClienteDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	ReservaService reservaService;

	@PostMapping
	public ReservaDto adicionarReserva(@RequestBody ReservaDto novaReserva, ClienteDto clienteDto) {
		return reservaService.adicionarReserva(novaReserva, clienteDto);
	}

	@GetMapping("/cliente/{clienteId}")
	public List<ReservaDto> buscarReservasPorCliente(@PathVariable Long clienteId) {
		List<ReservaDto> tarefas = reservaService.buscarReservasPorCliente(clienteId);
		return tarefas;
	}

	@PutMapping("{id}")
	public ReservaDto atualizarStatusReserva(@PathVariable Long id, @RequestBody ReservaDto reservaAtualizada) {
		return reservaService.atualizaReserva(id, reservaAtualizada);
	}
	
	@DeleteMapping
	public void removerTarefa(@RequestParam Long id) {
		reservaService.removerTarefa(id);
	}
}
