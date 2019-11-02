package br.com.unicesumar.war_line.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Atribuicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Jogador jogador;
	
	@OneToOne
	private Estado estado;
	
	private Integer pontos;
	
	public Atribuicao() {
	}

	public Atribuicao(Jogador jogador,Estado estado,Integer pontos) {
		this.jogador = jogador;
		this.estado = estado;
		this.pontos = pontos;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	public Long getId() {
		return id;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
}
