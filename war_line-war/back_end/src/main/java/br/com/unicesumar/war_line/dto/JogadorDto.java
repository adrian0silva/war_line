package br.com.unicesumar.war_line.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unicesumar.war_line.modelo.Jogador;

public class JogadorDto {
	
	private String nome;
	private Integer pontos;
	
	public JogadorDto(Jogador jogador){
		this.nome = jogador.getNome();
		this.pontos = jogador.getPontos();
	}
	
	public String getNome() {
		return nome;
	}
	public Integer getPontos() {
		return pontos;
	}
	
	public static List<JogadorDto> converter(List<Jogador> jogador) {
		return jogador.stream().map(JogadorDto::new).collect(Collectors.toList());
	}
}
