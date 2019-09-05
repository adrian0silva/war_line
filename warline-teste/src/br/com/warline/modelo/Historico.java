package br.com.warline.modelo;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

public class Historico {
	
	private List<Jogador> jogadores = new ArrayList<>();
	private List<Estado> estados = new ArrayList<>();
	
	private List<List<Jogada>> listaDaListaDeJogadas = new ArrayList<List<Jogada>>();
	
	private Integer round = 0;
	
	public Historico() {
	}


	public void jogar() {
		for(int a = 0;a < this.jogadores.size();a++) {			
			this.listaDaListaDeJogadas.add(this.jogadores.get(a).getJogadas());
		}
		
		round++;
	}
	
	public List<Jogada> getListaDaListaDeJogadas() {
		List<Jogada> jogadas = new ArrayList<Jogada>();
		this.listaDaListaDeJogadas.forEach(e -> jogadas.addAll(e));
		return jogadas;
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
