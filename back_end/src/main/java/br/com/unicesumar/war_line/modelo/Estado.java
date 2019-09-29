package br.com.unicesumar.war_line.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uf;
	private String nome;

	private Integer valor;

	@ManyToOne
	private Jogador jogador;

	public Estado() {
	}
	
	public Estado(String UF, String nome, Integer valor) {
		this.uf = UF;
		this.nome = nome;
		this.valor = valor;
	}

	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public Long getId() {
		return id;
	}

	public String getUf() {
		return uf;
	}

	public String getNome() {
		return nome;
	}

	public Integer getValor() {
		return valor;
	}


}
