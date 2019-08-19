package br.com.esoft3.warline.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Jogada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Jogador jogador;
	
	@OneToOne
	private Pais paisEnvia;
	
	@OneToOne
	private Pais paisRecebe;
	private Integer valor;

	
	public Jogada(Pais paisEnvia, Pais paisRecebe, Integer valor) {
		jogador = paisEnvia.getJogador();
		this.paisEnvia = paisEnvia;
		this.paisRecebe = paisRecebe;
		this.valor = valor;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public Pais getPaisEnvia() {
		return paisEnvia;
	}
	public Pais getPaisRecebe() {
		return paisRecebe;
	}
	
	public Integer getValor() {
		return valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return valor + " armies from " +  paisEnvia.getNome() +" will attack/transfer  " + paisRecebe.getNome() + " \n";
	}
}
