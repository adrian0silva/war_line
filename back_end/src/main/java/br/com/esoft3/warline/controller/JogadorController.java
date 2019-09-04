package br.com.esoft3.warline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esoft3.warline.modelo.Jogador;
import br.com.esoft3.warline.repository.JogadorRepository;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	@CrossOrigin
	@GetMapping
	public List<Jogador> lista() {
		return jogadorRepository.findAll();
	}
}
