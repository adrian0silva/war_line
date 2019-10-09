package br.com.unicesumar.war_line.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.dto.JogadorDto;
import br.com.unicesumar.war_line.modelo.Atribuicao;
import br.com.unicesumar.war_line.modelo.Estado;
import br.com.unicesumar.war_line.modelo.Jogador;
import br.com.unicesumar.war_line.repository.AtribuicaoRepository;
import br.com.unicesumar.war_line.repository.EstadoRepository;
import br.com.unicesumar.war_line.repository.JogadorRepository;

@RequestMapping("/api/jogador")
@RestController
public class JogadorController {

	@Autowired
	JogadorRepository jogadorRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	AtribuicaoRepository atribuicaoRepository;
	
	@GetMapping
	@CrossOrigin
	public List<JogadorDto> getAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		return JogadorDto.converter(jogador);
	}

	@PutMapping("/{nome}")
	@CrossOrigin
	public void adicionarPontos(@PathVariable("nome") String uf) {
		Estado estado = estadoRepository.findByUf(uf);
		Jogador jogador = estado.getJogador();
		

		if(jogador.getPontos() == 0) {
			throw new RuntimeException("Jogador nao possui pontos para atribuir!");
		}
		
		
		jogador.setPontos(jogador.getPontos() - 1);
		
		List<Atribuicao> listaAtribuicao = atribuicaoRepository.buscarAtribuicao(estado.getId());
		
		System.out.println(listaAtribuicao.size());
		
		if(listaAtribuicao.size() == 0) {
			Atribuicao atribuicao = jogador.adicionarPontos(estado, 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		} else {
			Atribuicao atribuicao = atribuicaoRepository.buscar(estado.getId());
			atribuicao.setPontos(atribuicao.getPontos() + 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		}
		
		System.out.println(estado.getNome());
		
	}
	
}
