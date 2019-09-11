package br.com.esoft3.warline.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String UF;
	private String nome;

	private Integer valor;


	public Estado() {
	}
	
	public Estado(String UF, String nome, Integer valor) {
		this.UF = UF;
		this.nome = nome;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getUF() {
		return UF;
	}

	public String getNome() {
		return nome;
	}

	public Integer getValor() {
		return valor;
	}


}
