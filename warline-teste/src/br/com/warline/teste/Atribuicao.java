package br.com.warline.teste;

import br.com.warline.modelo.Estado;
import br.com.warline.modelo.Jogador;

public class Atribuicao {
	
	private Jogador jogador;
	private Estado estado;
	private Integer pontos;

	public Atribuicao(Jogador jogador,Estado estado, Integer pontos) {
		this.estado = estado;
		this.pontos = pontos;
		this.jogador = jogador;
	}
	
}
