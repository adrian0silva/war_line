package br.com.unicesumar.war_line.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.modelo.Jogador;
import br.com.unicesumar.war_line.repository.JogadorRepository;

@RequestMapping("/api/jogador")
@RestController
public class JogadorController {

	@Autowired
	JogadorRepository jogadorRepository;
	
	@GetMapping
	@CrossOrigin
	public List<Jogador> getAll() {
		return jogadorRepository.findAll();
	}
}
