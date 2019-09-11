package br.com.esoft3.warline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esoft3.warline.modelo.Estado;
import br.com.esoft3.warline.repository.EstadoRepository;

@RequestMapping("/api/estado")
@RestController
public class EstadoController {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> getAll() {
		return estadoRepository.findAll();
	}
	
}
