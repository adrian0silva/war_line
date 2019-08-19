package br.com.esoft3.warline.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer round = 1;
	
	@OneToMany
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	@OneToMany
	private List<Jogada> jogadas = new ArrayList<>();
	
	public Historico(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	public void adicionarJogadasNoHistorico() {
				
		for(int i = 0;i < jogadores.size();i++) {
			 jogadas.addAll(jogadores.get(i).getJogadas());
		}
		
		round++;
	}

	public Integer getRound() {
		return round;
	}
	
	public List<Jogada> getJogadas() {
		return jogadas;
	}
}
