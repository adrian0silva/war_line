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
public class Atribuicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Jogador jogador;
	
	@OneToOne
	private Pais pais;
	private Integer quantidade;

	public Atribuicao(Pais pais, Integer quantidade) {
		jogador = pais.getJogador();
		this.pais = pais;
		this.quantidade = quantidade;
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
}
