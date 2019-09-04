package br.com.warline.modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ParsingContextSnapshot;

import br.com.warline.teste.Estado;

public class Jogador {
	private String nome;
	private Integer pontos = 5; 
	private List<Estado> estados = new ArrayList<Estado>();
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
	public void adicionarEstado(Estado estado) {
		this.estados.add(estado);
	}
	
	public List<Estado> obterEstados() {
		return estados; 
	}
	
	public void atribuirPontos(Estado estado,Integer pontos) {
		estado.setValor(estado.getValor() + pontos);
		this.pontos = this.pontos - pontos;
	}

	public void enviarPontos(Estado envia,Estado destino,int valor) {
		if(envia.obterJogador() == this) {
			if(destino.obterJogador() == this) {
				destino.setValor(destino.getValor() + valor);
				envia.setValor(envia.getValor() - valor);
			} else {
				destino.setValor(destino.getValor() - valor);
				envia.setValor(envia.getValor() - valor);
			}
			
		} else {
			throw new RuntimeException("num potchi");
		}
	}
}
