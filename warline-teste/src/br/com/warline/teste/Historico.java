package br.com.warline.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.warline.modelo.Jogador;

public class Historico {
	
	private List<Jogador> jogadores = new ArrayList<>();
	private Integer round = 0;
	
	public Historico() {
	}

	public void adicionarJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	public List<Jogador> obterJogadores() {
		return jogadores;
	}
}
