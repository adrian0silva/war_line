package br.com.unicesumar.war_line.modelo;

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
	@OneToMany
	private List<Jogada> jogadas = new ArrayList<>();
	private String nome;
	private Integer pontos = 5;

	@OneToMany(mappedBy = "jogador")
	private List<Estado> estados = new ArrayList<>();

	@OneToMany
	private List<Atribuicao> atribuicoes = new ArrayList<>();

	public Jogador() {
	}

	public Jogador(String nome) {
		this.nome = nome;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public void adicionarEstado(Estado estado) {
		this.estados.add(estado);
		estado.setJogador(this);
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

	public Atribuicao adicionarPontos(Estado estado, Integer pontos) {
		Atribuicao atribuicao = new Atribuicao(this, estado, pontos);
		this.atribuicoes.add(atribuicao);
		return atribuicao;
	}

	public List<Atribuicao> getAtribuicoes() {
		return atribuicoes;
	}
	public void adicionarJogada(Jogada jogada) {
		this.jogadas.add(jogada);
	}
}
