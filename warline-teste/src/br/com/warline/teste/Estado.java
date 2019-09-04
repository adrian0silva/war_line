package br.com.warline.teste;

import br.com.warline.modelo.Jogador;

public class Estado {
	private String nome;
	
	private Integer valor;
	
	private Jogador jogador;

	public Estado(String nome, Integer valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public void adicionarJogador(Jogador jogador) {
		this.jogador = jogador;
		jogador.adicionarEstado(this);
	}
	
	public Jogador obterJogador() {
		return jogador;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
}
