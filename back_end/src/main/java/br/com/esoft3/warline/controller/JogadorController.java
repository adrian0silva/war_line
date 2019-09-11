package br.com.esoft3.warline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esoft3.warline.modelo.Estado;
import br.com.esoft3.warline.modelo.Jogador;
import br.com.esoft3.warline.repository.EstadoRepository;
import br.com.esoft3.warline.repository.JogadorRepository;

@RequestMapping("/api/jogador")
@RestController
public class JogadorController {

	@Autowired
	JogadorRepository jogadorRepository;
	
	@GetMapping
	public List<Jogador> getAll() {
		return jogadorRepository.findAll();
	}
}
