package br.com.esoft3.warline.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	
	private Integer valor;
	
	@OneToOne
	private Jogador jogador;
	
	public Pais() {}
	
	public Pais(String nome,Integer valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public Integer getValor() {
		return valor;
	}
	@Override
	public String toString() {
		return nome + "  " + valor;
	}

	public Jogador getJogador() {
		return jogador;
	}
	
}
