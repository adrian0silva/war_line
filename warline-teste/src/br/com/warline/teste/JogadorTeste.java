package br.com.warline.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ParsingContextSnapshot;

import br.com.warline.modelo.Jogador;

public class JogadorTeste {
	
	
	
	@Test
	public void deveCriarJogadores() {
		Historico historico = new Historico();

		Jogador adriano = new Jogador("ADRIANO");
		Jogador computador = new Jogador("COMPUTADOR");
		
		historico.adicionarJogadores(Arrays.asList(adriano,computador));
		
		assertEquals(2, historico.obterJogadores().size());
	}
	
	@Test
	public void deveCriarEstado() {
		Jogador adriano = new Jogador("ADRIANO");
		Estado rs = new Estado("RIO GRANDE DO SUL", 2);
		rs.adicionarJogador(adriano);	
		assertEquals(adriano, rs.obterJogador());
		assertEquals(1, adriano.obterEstados().size());
		
		Jogador computador = new Jogador("COMPUTADOR");
		Estado sc = new Estado("SANTA CATARINA", 2);
		sc.adicionarJogador(computador);	
		assertEquals(computador, sc.obterJogador());
		assertEquals(1, computador.obterEstados().size());
	
		// troca o jogador
//		
//		rs.adicionarJogador(computador);
//		
//		assertEquals(computador, rs.obterJogador());
	}
	
	@Test
	public void deveAdicionarPontosAoPais() {
		Jogador adriano = new Jogador("ADRIANO");
		Estado rs = new Estado("RIO GRANDE DO SUL", 2);
		
		rs.adicionarJogador(adriano);	

		adriano.atribuirPontos(rs,3);
		
		assertEquals(5, rs.getValor().intValue());
		assertEquals(2, adriano.getPontos().intValue());
	}
	
	@Test
	public void deveAtacarUmEstado() {
		Jogador adriano = new Jogador("ADRIANO");
		Estado rs = new Estado("RIO GRANDE DO SUL", 2);
		rs.adicionarJogador(adriano);
		Estado sc = new Estado("SANTA CATARINA", 2);
		
		adriano.enviarPontos(rs,sc,3);
	}
}
