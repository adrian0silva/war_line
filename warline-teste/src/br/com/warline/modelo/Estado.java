package br.com.warline.modelo;

public class Estado {
	private String nome;
	
	private Integer valor;
	
	private Jogador jogador;

	public Estado(String nome, Integer valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
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

	@Override
	public String toString() {
		return "Estado [nome=" + nome + ", valor=" + valor + ", jogador=" + jogador + "]";
	}
	
	
}
