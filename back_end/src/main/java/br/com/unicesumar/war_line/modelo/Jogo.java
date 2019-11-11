package br.com.unicesumar.war_line.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer rodada = 1;
	
	public Integer getRodada() {
		return rodada;
	}
	
	public void avancarRodada() {
		this.rodada++;
	}
}
