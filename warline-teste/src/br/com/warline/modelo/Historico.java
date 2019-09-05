package br.com.warline.modelo;

import java.util.ArrayList;
import java.util.List;

public class Historico {
	
	private List<Jogador> jogadores = new ArrayList<>();
	private List<Estado> estados = new ArrayList<>();
	
	private Integer round = 0;
	
	public Historico() {
	}


	public void jogar() {
		
		
		round++;
	}
	
	public Integer getRound() {
		return round;
	}
	
	public void adicionarJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	public List<Jogador> obterJogadores() {
		return jogadores;
	}
	
	public void adicionarEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}
}
