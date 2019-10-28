package br.com.unicesumar.war_line.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Jogada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Estado estadoEnvia;
	@OneToOne
	private Estado estadoRecebe;
	private int valor;

	public Jogada() {
	}
	
	public Jogada(Estado estadoEnvia, Estado estadoRecebe, int valor) {
		this.estadoEnvia = estadoEnvia;
		this.estadoRecebe = estadoRecebe;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public Estado getEstadoEnvia() {
		return estadoEnvia;
	}

	public Estado getEstadoRecebe() {
		return estadoRecebe;
	}

	public int getValor() {
		return valor;
	}

	public void setEstadoEnvia(Estado estadoEnvia) {
		this.estadoEnvia = estadoEnvia;
	}

	public void setEstadoRecebe(Estado estadoRecebe) {
		this.estadoRecebe = estadoRecebe;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}