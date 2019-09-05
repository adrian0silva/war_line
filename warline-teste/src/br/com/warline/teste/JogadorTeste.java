package br.com.warline.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ParsingContextSnapshot;

import br.com.warline.modelo.Estado;
import br.com.warline.modelo.Historico;
import br.com.warline.modelo.Jogador;

@TestMethodOrder(OrderAnnotation.class)
public class JogadorTeste {
	
	
	private static Historico historico;
	private static Estado rs;
	private static Jogador adriano;
	private static Jogador computador;
	private static Estado sc;

	@BeforeAll
	public static void criarJogo() { 
		historico = new Historico();
	}
	
	@Test
	@Order(1)
	public void deveCriarJogadores() {
		adriano = new Jogador("ADRIANO");
		computador = new Jogador("COMPUTADOR");
		
		historico.adicionarJogadores(Arrays.asList(adriano,computador));
		
		assertEquals(2, historico.obterJogadores().size());
	}
	
	@Test
	@Order(2)
	public void deveCriarEstado() {
		rs = new Estado("RIO GRANDE DO SUL", 2);
		rs.adicionarJogador(adriano);	
		assertEquals(adriano, rs.obterJogador());
		assertEquals(1, adriano.obterEstados().size());
		
		sc = new Estado("SANTA CATARINA", 2);
		sc.adicionarJogador(computador);	
		assertEquals(computador, sc.obterJogador());
		assertEquals(1, computador.obterEstados().size());
	
		historico.adicionarEstados(Arrays.asList(rs,sc));
		
		assertEquals(2, historico.getEstados().size());
	}
	
	@Test
	@Order(3)
	public void deveAdicionarNaListaDeAtribuicaoDoJogador() {
		adriano.atribuirPontos(rs,3);
		assertEquals(1, adriano.getAtribuicoes().size());
	}
	
	@Test
	@Order(4)
	public void deveAdicionarNaListaDeJogadas() { 
		adriano.enviarPontos(rs, sc, 2);
		assertEquals(1, adriano.getJogadas().size());
	}
	
	@Test
	@Order(5)
	public void deveJogar() {
		historico.jogar();
		
		assertEquals(1, historico.getRound());
		assertEquals(0, sc.getValor());
		
	}
}
