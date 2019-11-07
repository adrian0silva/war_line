package br.com.unicesumar.war_line.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.dto.JogadaDto;
import br.com.unicesumar.war_line.dto.JogadorDto;
import br.com.unicesumar.war_line.modelo.Atribuicao;
import br.com.unicesumar.war_line.modelo.Estado;
import br.com.unicesumar.war_line.modelo.Jogada;
import br.com.unicesumar.war_line.modelo.Jogador;
import br.com.unicesumar.war_line.repository.AtribuicaoRepository;
import br.com.unicesumar.war_line.repository.EstadoRepository;
import br.com.unicesumar.war_line.repository.JogadaRepository;
import br.com.unicesumar.war_line.repository.JogadorRepository;

@RequestMapping("/api/jogador")
@RestController
@CrossOrigin("*")
public class JogadorController {

	@Autowired
	JogadorRepository jogadorRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	AtribuicaoRepository atribuicaoRepository;
	
	@Autowired
	JogadaRepository jogadaRepository;
	
	@GetMapping
	public List<JogadorDto> getAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		return JogadorDto.converter(jogador);
	}

	@PutMapping("/{nome}")
	@CrossOrigin
	public void adicionarPontos(@PathVariable("nome") String uf) {
		Estado estado = estadoRepository.findByUf(uf);
		
		if(estado.getJogador() == null || estado.getJogador().getId() == 2) {
			throw new RuntimeException("Não potchi, este estado não lha pertence!");
		}
		
		Jogador jogador = estado.getJogador();
		
		if(jogador.getPontos() == 0) {
			throw new RuntimeException("Jogador nao possui pontos para atribuir!");
		}		
		
		jogador.setPontos(jogador.getPontos() - 1);
		
		List<Atribuicao> listaAtribuicao = atribuicaoRepository.buscarAtribuicao(estado.getId());
		
		System.out.println(listaAtribuicao.size());
				
		if(listaAtribuicao.size() == 0) {
			Atribuicao atribuicao = jogador.adicionarPontos(estado, 1);
			estado.setValor(estado.getValor() + 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		} 
		else {
			Atribuicao atribuicao = atribuicaoRepository.buscar(estado.getId());
			atribuicao.setPontos(atribuicao.getPontos() + 1);
			estado.setValor(estado.getValor() + 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		}
		
		System.out.println(estado.getNome());		
	}
	
	@PostMapping("/{id}/jogadas")
	@CrossOrigin
	public void adicionarJogada(@PathVariable("id") Long id, @RequestBody Jogada jogada) {
		Jogador jogador = jogadorRepository.findById(id).get();
		
		Estado estadoEnvia = estadoRepository.findById(jogada.getEstadoEnvia().getId()).get();
		
		Estado estadoRecebe = estadoRepository.findById(jogada.getEstadoRecebe().getId()).get();
		
		
		System.out.println("Valor da jogada!: " + jogada.getValor());
		
		System.out.println("Valor do estado: " + estadoEnvia.getValor());
		
		if(estadoEnvia.getId() == estadoRecebe.getId()) {
			throw new RuntimeException("Voce nao pode mandar para o mesmo estado");
		}
		
		if(estadoEnvia.getJogador().getId() != jogador.getId()) {
			throw new RuntimeException("Este estado não lhe pertence!");
		}
		
		if(jogada.getValor() <= 0  || jogada.getValor() > estadoEnvia.getValor()) {
			throw new RuntimeException("Valor não pode ser 0,negativo ou maior que os pontos do estado que envia");
		}	
		
		List<Jogada> jogadasIguais = jogadaRepository.buscarCombinacao(estadoEnvia.getId(), estadoRecebe.getId());
		
		if(jogadasIguais.size() > 0) {
			
			Jogada jogadaEncontrada = jogadasIguais.get(0);
			jogadaEncontrada.setValor(jogadaEncontrada.getValor() + jogada.getValor());
			estadoEnvia.setValor(estadoEnvia.getValor() - jogada.getValor());
			
			estadoRepository.save(estadoEnvia);
			estadoRepository.save(estadoRecebe);
			jogadaRepository.save(jogadaEncontrada);
			jogadorRepository.save(jogador);
			return;
		}
		
		jogada = new Jogada(estadoEnvia, estadoRecebe, jogada.getValor());
		
		estadoEnvia.setValor(estadoEnvia.getValor() - jogada.getValor());
		
		estadoRepository.save(estadoEnvia);
		estadoRepository.save(estadoRecebe);
		jogadaRepository.save(jogada);
		jogador.adicionarJogada(jogada);
		jogadorRepository.save(jogador);
		
	}

	@GetMapping("/{id}/jogadas")
	public List<JogadaDto> listarJogadas() {
		List<Jogada> jogadas = jogadaRepository.findAll();
		return JogadaDto.converter(jogadas);
	}
	
	@PostMapping
	public void criarJogadaDoComputador() {
		
		Jogador computador = jogadorRepository.findById(Long.valueOf(2)).get();
		
		// buscar estado que é do computador
		Estado estadoAleatorio = estadoRepository.buscarEstadoDoComputadorAleatorio();
		
		// adicionar pontos
		estadoAleatorio.adicionarValorAleatorio(estadoAleatorio.getValor() + new Random().nextInt(computador.getPontos()));

		estadoRepository.save(estadoAleatorio);
		
		Estado estadoEnvia = estadoRepository.buscarEstadoDoComputadorAleatorio();
		Estado estadoRecebe;
		
		if(new Random().nextInt() / 2 == 0) {
			estadoRecebe = estadoRepository.buscarEstadoDoComputadorAleatorio();	
		} else {
			estadoRecebe = estadoRepository.buscarEstadoDoJogadorAleatorio();
		}
		
		int valor = new Random().nextInt(estadoEnvia.getValor());
		
		Jogada jogadaDoComputador = new Jogada(estadoEnvia, estadoRecebe, valor);
		// realizar uma jogada para o copmutador
		
		jogadaRepository.save(jogadaDoComputador);
		
		computador.adicionarJogada(jogadaDoComputador);
		
		jogadorRepository.save(computador);		
		
		// simular partida
		// pegar jogadas do jogador e computador e juntar em uma
		
		List<Jogada> todasJogadas = jogadaRepository.findAll();
		
		for(int i = 0;i < todasJogadas.size();i++) {
			if(todasJogadas.get(i).getEstadoEnvia().getJogador() == todasJogadas.get(i).getEstadoRecebe().getJogador()) {
				todasJogadas.get(i).getEstadoRecebe().setValor(todasJogadas.get(i).getEstadoRecebe().getValor() + todasJogadas.get(i).getValor());
			}
			if(todasJogadas.get(i).getEstadoEnvia().getJogador() != todasJogadas.get(i).getEstadoRecebe().getJogador()) {
				todasJogadas.get(i).getEstadoRecebe().setValor(todasJogadas.get(i).getEstadoRecebe().getValor() - todasJogadas.get(i).getValor());
			}
			todasJogadas.get(i).getEstadoEnvia().setValor(todasJogadas.get(i).getEstadoEnvia().getValor() - todasJogadas.get(i).getValor());
			estadoRepository.save(todasJogadas.get(i).getEstadoEnvia());
			estadoRepository.save(todasJogadas.get(i).getEstadoRecebe());
		}		
	}
}