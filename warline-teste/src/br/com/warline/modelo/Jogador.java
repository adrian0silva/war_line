package br.com.warline.modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ParsingContextSnapshot;

public class Jogador {
	private String nome;
	private Integer pontos = 5; 
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Atribuicao> atribuicoes = new ArrayList<Atribuicao>();
	private List<Jogada> jogadas = new ArrayList<>();
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
	public void adicionarEstado(Estado estado) {
		this.estados.add(estado);
	}
	
	public List<Estado> obterEstados() {
		return estados; 
	}
	
	public void atribuirPontos(Estado estado,Integer pontos) {
		this.atribuicoes.add(new Atribuicao(this, estado, pontos));
	}

	public List<Atribuicao> getAtribuicoes() {
		return atribuicoes;
	}
	
	public List<Jogada> getJogadas() {
		return jogadas;
	}
	public void enviarPontos(Estado envia,Estado destino,int valor) {
		this.jogadas.add(new Jogada(envia, destino, valor));
	}

	
	@Override
	public String toString() {
		return "Jogador [nome=" + nome + "]";
	}

	
}
