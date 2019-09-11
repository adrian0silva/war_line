package br.com.esoft3.warline.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Jogador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Integer pontos = 5; 
	
	@OneToMany
	private List<Estado> estados = new ArrayList<>();
	
	
	public Jogador() {
	}
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	public void adicionarEstado(Estado estado) {
		this.estados.add(estado);
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Integer getPontos() {
		return pontos;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
}
