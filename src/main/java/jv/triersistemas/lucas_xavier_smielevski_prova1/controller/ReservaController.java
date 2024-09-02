package jv.triersistemas.lucas_xavier_smielevski_prova1.controller;

import java.time.LocalDate;
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

import jv.triersistemas.lucas_xavier_smielevski_prova1.dto.ReservaDto;
import jv.triersistemas.lucas_xavier_smielevski_prova1.enums.StatusEnum;
import jv.triersistemas.lucas_xavier_smielevski_prova1.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@PostMapping
	public ReservaDto adicionarReserva(@RequestBody ReservaDto novaReserva) {
		return reservaService.adicionarReserva(novaReserva);
	}

	@GetMapping("/cliente/{clienteId}")
	public List<ReservaDto> buscarReservasPorCliente(@PathVariable Long clienteId) {
		List<ReservaDto> tarefas = reservaService.buscarReservasPorCliente(clienteId);
		return tarefas;
	}

	@GetMapping
	public String buscarReservaNaMesaPorData(@RequestParam LocalDate data, @RequestParam Integer numeroMesa) {
		String resultado = reservaService.buscarSeTemReservaNaMesaPorData(data, numeroMesa);
		return resultado;
	}

	@PutMapping()
	public ReservaDto atualizarStatusReserva(@RequestParam Long id, @RequestParam StatusEnum status) {
		return reservaService.atualizaReserva(id, status);
	}

}
