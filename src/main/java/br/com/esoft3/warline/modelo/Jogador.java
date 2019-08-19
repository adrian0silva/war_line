package br.com.esoft3.warline.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jogador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	private Integer pontos;
	
	@OneToMany
	private List<Pais> paises = new ArrayList<>();
	
	@OneToMany
	private List<Jogada> jogadas = new ArrayList<Jogada>();
	
	@OneToMany
	private List<Atribuicao> atribuicoes = new ArrayList<>();
	
	public Jogador() {}
	
	public Jogador(String nome, Integer pontos) {
		this.nome = nome;
		this.pontos = pontos;
	}
	
	public void adicionarPais(Pais pais) {
		pais.setJogador(this);
		paises.add(pais);
	}
	
	public List<Atribuicao> getAtribuicoes() {
		return atribuicoes;
	}
	
	public List<Pais> getPaises() {
		return paises;
	}
	
	public void adicionarPontos(Pais pais) {
		if(pais.getJogador() == this && pontos > 0) {
			this.pontos--;
			if(!atribuicoes.contains(pais)) {
				pais.setValor(pais.getValor() + 1);
				atribuicoes.add(new Atribuicao(pais,1));
			} else {
				pais.setValor(pais.getValor() + 1);
				Atribuicao atribuicao = atribuicoes.stream().filter(c -> c.getPais() == pais).findFirst().get();
				atribuicao.setQuantidade(atribuicao.getQuantidade() + 1);
			}
		}
	}
	
	public Integer getPontos() {
		return pontos;
	}

	public void enviar(Pais paisEnvia,Pais paisRecebe,Integer valor) {
		boolean estaPresente = jogadas.stream().filter(c -> c.getPaisEnvia() == paisEnvia && c.getPaisRecebe() == paisRecebe).findFirst().isPresent();
				
		if(estaPresente) {
			Jogada jogada = jogadas.stream().filter(c -> c.getPaisEnvia() == paisEnvia && c.getPaisRecebe() == paisRecebe).findFirst().get();
			int posicao = 0;
			for(int i = 0;i < jogadas.size();i++) {
				 if(jogadas.get(i).equals(jogada)) {
					 posicao = i;
					 break;
				 }
			}
			jogadas.get(posicao).setValor(jogadas.get(posicao).getValor() + valor);
		} else {
			jogadas.add(new Jogada(paisEnvia, paisRecebe, valor));
		}
	}
	
	public List<Jogada> getJogadas() {
		return jogadas;
	}
	
	public void jogar(Historico historico) {
		historico.adicionarJogadasNoHistorico();
	}
	
	@Override
	public String toString() {
		return nome + " " + pontos + " " + paises;
	}
}
