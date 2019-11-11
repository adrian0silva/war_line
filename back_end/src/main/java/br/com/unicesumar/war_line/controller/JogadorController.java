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
import br.com.unicesumar.war_line.modelo.Jogo;
import br.com.unicesumar.war_line.repository.AtribuicaoRepository;
import br.com.unicesumar.war_line.repository.EstadoRepository;
import br.com.unicesumar.war_line.repository.JogadaRepository;
import br.com.unicesumar.war_line.repository.JogadorRepository;
import br.com.unicesumar.war_line.repository.JogoRepository;

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

	@Autowired
	JogoRepository jogoRepository;
	
	
	
	@GetMapping("/pontos")
	public Integer retornaPontosDoJogador() {
		Jogador jogador = jogadorRepository.findById(Long.valueOf(1)).get();
		return jogador.getPontos();
	}

	@GetMapping
	public List<JogadorDto> getAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		return JogadorDto.converter(jogador);
	}

	@PutMapping("/{nome}")
	@CrossOrigin
	public void adicionarPontos(@PathVariable("nome") String uf) {
		Estado estado = estadoRepository.findByUf(uf);

		if (estado.getJogador() == null || estado.getJogador().getId() == 2) {
			throw new RuntimeException("Não potchi, este estado não lha pertence!");
		}

		Jogador jogador = estado.getJogador();

		if (jogador.getPontos() == 0) {
			throw new RuntimeException("Jogador nao possui pontos para atribuir!");
		}

		jogador.setPontos(jogador.getPontos() - 1);

		List<Atribuicao> listaAtribuicao = atribuicaoRepository.buscarAtribuicao(estado.getId());


		if (listaAtribuicao.size() == 0) {
			Atribuicao atribuicao = jogador.adicionarPontos(estado, 1);
			estado.setValor(estado.getValor() + 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		} else {
			Atribuicao atribuicao = atribuicaoRepository.buscar(estado.getId());
			atribuicao.setPontos(atribuicao.getPontos() + 1);
			estado.setValor(estado.getValor() + 1);
			atribuicaoRepository.save(atribuicao);
			jogadorRepository.save(jogador);
			estadoRepository.save(estado);
		}

	}

	@PostMapping("/{id}/jogadas")
	@CrossOrigin
	public void adicionarJogada(@PathVariable("id") Long id, @RequestBody Jogada jogada) {
		Jogador jogador = jogadorRepository.findById(id).get();

		Estado estadoEnvia = estadoRepository.findById(jogada.getEstadoEnvia().getId()).get();

		Estado estadoRecebe = estadoRepository.findById(jogada.getEstadoRecebe().getId()).get();


		if (estadoEnvia.getId() == estadoRecebe.getId()) {
			throw new RuntimeException("Voce nao pode mandar para o mesmo estado");
		}

		if (estadoEnvia.getJogador().getId() != jogador.getId()) {
			throw new RuntimeException("Este estado não lhe pertence!");
		}

		if (jogada.getValor() <= 0 || jogada.getValor() > estadoEnvia.getValor()) {
			throw new RuntimeException("Valor não pode ser 0,negativo ou maior que os pontos do estado que envia");
		}

		List<Jogada> jogadasIguais = jogadaRepository.buscarCombinacao(estadoEnvia.getId(), estadoRecebe.getId());

		if (jogadasIguais.size() > 0) {

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

	public void criarAtribuicoesParaOComputador() {
		Jogador computador;
		do {

			computador = jogadorRepository.findById(Long.valueOf(2)).get();

			Estado estadoAleatorio = estadoRepository.buscarEstadoDoComputadorAleatorio();

			int valorAleatorioParaAdicionar = new Random().nextInt(computador.getPontos() + 1);
			estadoAleatorio.adicionarValorAleatorio(estadoAleatorio.getValor() + valorAleatorioParaAdicionar);

			computador.setPontos(computador.getPontos() - valorAleatorioParaAdicionar);


			jogadorRepository.save(computador);
			estadoRepository.save(estadoAleatorio);

		} while (computador.getPontos() > 0);
	}

	public void criarJogadasParaOComputador() {
		Jogador computador = jogadorRepository.findById(Long.valueOf(2)).get();
		do {

			Estado estadoEnvia = estadoRepository.buscarEstadoDoComputadorAleatorio();
			Estado estadoRecebe;

			boolean temAdjacencia;
			int idEscolhido;
			do {
				idEscolhido = new Random().nextInt(28);
				temAdjacencia = matrizDeAdjacencia(estadoEnvia.getId(), (long) idEscolhido);
			} while (!temAdjacencia);

			estadoRecebe = estadoRepository.findById(Long.valueOf(idEscolhido)).get();


			int valor = new Random().nextInt(estadoEnvia.getValor());

			estadoEnvia.setValor(estadoEnvia.getValor() - valor);

			Jogada jogadaDoComputador;
			List<Jogada> jogadasEncontradas = jogadaRepository.buscarCombinacao(estadoEnvia.getId(),
					estadoRecebe.getId());
			if (jogadasEncontradas.size() == 0) {
				jogadaDoComputador = new Jogada(estadoEnvia, estadoRecebe, valor);
				estadoRepository.save(estadoEnvia);
				estadoRepository.save(estadoRecebe);
				jogadaRepository.save(jogadaDoComputador);
				//computador.adicionarJogada(jogadaDoComputador);
				jogadorRepository.save(computador);
			} else {
				jogadaDoComputador = jogadasEncontradas.get(0);

				jogadaDoComputador.setValor(jogadaDoComputador.getValor() + valor);

				estadoRepository.save(estadoEnvia);
				estadoRepository.save(estadoRecebe);
				jogadaRepository.save(jogadaDoComputador);
				//computador.adicionarJogada(jogadaDoComputador);
				jogadorRepository.save(computador);
			}
		} while (new Random().nextInt(computador.getEstados().size() + 1) == 1);
		
		
	}

	@PostMapping
	public List<JogadaDto> simularJogadas() {

		criarAtribuicoesParaOComputador();

		criarJogadasParaOComputador();

		List<Jogada> todasJogadas = jogadaRepository.findAll();

		rodarJogadas(todasJogadas);

		return JogadaDto.converter(todasJogadas);
		/*
		 * Jogador computador = jogadorRepository.findById(Long.valueOf(2)).get();
		 * 
		 * // buscar estado que é do computador Estado estadoAleatorio =
		 * estadoRepository.buscarEstadoDoComputadorAleatorio();
		 * 
		 * // adicionar pontos
		 * estadoAleatorio.adicionarValorAleatorio(estadoAleatorio.getValor() + new
		 * Random().nextInt(computador.getPontos()));
		 * 
		 * estadoRepository.save(estadoAleatorio);
		 * 
		 * Estado estadoEnvia = estadoRepository.buscarEstadoDoComputadorAleatorio();
		 * Estado estadoRecebe;
		 * 
		 * if(new Random().nextInt() / 2 == 0) { estadoRecebe =
		 * estadoRepository.buscarEstadoDoComputadorAleatorio(); } else { estadoRecebe =
		 * estadoRepository.buscarEstadoDoJogadorAleatorio(); }
		 * 
		 * int valor = new Random().nextInt(estadoEnvia.getValor());
		 * 
		 * Jogada jogadaDoComputador = new Jogada(estadoEnvia, estadoRecebe, valor); //
		 * realizar uma jogada para o copmutador
		 * 
		 * jogadaRepository.save(jogadaDoComputador);
		 * 
		 * computador.adicionarJogada(jogadaDoComputador);
		 * 
		 * jogadorRepository.save(computador);
		 * 
		 * // simular partida // pegar jogadas do jogador e computador e juntar em uma
		 * 
		 * List<Jogada> todasJogadas = jogadaRepository.findAll();
		 * 
		 * for(int i = 0;i < todasJogadas.size();i++) {
		 * if(todasJogadas.get(i).getEstadoEnvia().getJogador() ==
		 * todasJogadas.get(i).getEstadoRecebe().getJogador()) {
		 * todasJogadas.get(i).getEstadoRecebe().setValor(todasJogadas.get(i).
		 * getEstadoRecebe().getValor() + todasJogadas.get(i).getValor()); }
		 * if(todasJogadas.get(i).getEstadoEnvia().getJogador() !=
		 * todasJogadas.get(i).getEstadoRecebe().getJogador()) {
		 * todasJogadas.get(i).getEstadoRecebe().setValor(todasJogadas.get(i).
		 * getEstadoRecebe().getValor() - todasJogadas.get(i).getValor()); }
		 * todasJogadas.get(i).getEstadoEnvia().setValor(todasJogadas.get(i).
		 * getEstadoEnvia().getValor() - todasJogadas.get(i).getValor());
		 * estadoRepository.save(todasJogadas.get(i).getEstadoEnvia());
		 * estadoRepository.save(todasJogadas.get(i).getEstadoRecebe()); }
		 */

	}

	public void rodarJogadas(List<Jogada> jogadas) {

		for (int a = 0; a < jogadas.size(); a++) {
			
			Jogada jogadaDestaPosicao = jogadas.get(a);
			if (jogadaDestaPosicao.getEstadoEnvia().getJogador() == jogadaDestaPosicao.getEstadoRecebe().getJogador()) {
				jogadaDestaPosicao.getEstadoRecebe()
						.setValor(jogadaDestaPosicao.getEstadoRecebe().getValor() + jogadaDestaPosicao.getValor());
			} else {
				jogadaDestaPosicao.getEstadoRecebe()
						.setValor(jogadaDestaPosicao.getEstadoRecebe().getValor() - jogadaDestaPosicao.getValor());
			}
			if(jogadaDestaPosicao.getEstadoRecebe().getValor() < 0) {
				excluirProximasJogadas(jogadas,jogadaDestaPosicao.getEstadoRecebe(),a);
				
				System.out.println("RODA OU NAO RODA?");
				jogadaDestaPosicao.getEstadoRecebe().setJogador(jogadaDestaPosicao.getEstadoEnvia().getJogador());
				System.out.println(jogadaDestaPosicao.getEstadoEnvia().getJogador());
				jogadaDestaPosicao.getEstadoRecebe().setValor(jogadaDestaPosicao.getEstadoRecebe().getValor() * -1);
			}
			estadoRepository.save(jogadaDestaPosicao.getEstadoEnvia());
			
			estadoRepository.save(jogadaDestaPosicao.getEstadoRecebe());
			
		}
		
		Jogador jogador = jogadorRepository.findById(Long.valueOf(1)).get();
		jogador.removerJogadas();
		jogadorRepository.save(jogador);
		Jogador computador = jogadorRepository.findById(Long.valueOf(2)).get();
		computador.removerJogadas();
		jogadorRepository.save(computador);
		
		jogadaRepository.deleteAll();
		Jogo jogo = jogoRepository.findById(1).get();
		
		jogo.avancarRodada();
		jogoRepository.save(jogo);
	
		comecarNovaRodada();
	}

	private void excluirProximasJogadas(List<Jogada> jogadas, Estado estadoDominado, int posicao) {
		for(int a = posicao;a < jogadas.size();a++ ) {
			if(jogadas.get(a).getEstadoEnvia().getId() == estadoDominado.getId()) {
				jogadaRepository.delete(jogadas.get(a));
			}
		}
	}

	public void comecarNovaRodada() {
		Jogador jogador = jogadorRepository.findById(Long.valueOf(1)).get();
		jogador.setPontos(5);
		jogadorRepository.save(jogador);
		Jogador computador = jogadorRepository.findById(Long.valueOf(2)).get();
		computador.setPontos(5);
		jogadorRepository.save(computador);
	}
	
	public boolean matrizDeAdjacencia(Long estadoEnviaId, Long estadoRecebeId) {
		boolean matriz[][] = new boolean[28][28];

		matriz[1][4] = true;
		matriz[1][22] = true;
		matriz[2][17] = true;
		matriz[2][26] = true;
		matriz[2][5] = true;

		// fazendo a do RJ primeiro
		matriz[19][13] = true;
		matriz[19][8] = true;
		matriz[19][25] = true;

		return matriz[estadoEnviaId.intValue()][estadoRecebeId.intValue()];

	}

}
