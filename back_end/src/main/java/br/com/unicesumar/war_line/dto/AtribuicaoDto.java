package br.com.unicesumar.war_line.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unicesumar.war_line.modelo.Atribuicao;
import br.com.unicesumar.war_line.modelo.Jogador;

public class AtribuicaoDto {
	
	private String nomeJogador;
	private String nomeEstado;
	private Integer pontos;
	
	public AtribuicaoDto(Atribuicao atribuicao) {
		this.nomeJogador = atribuicao.getJogador().getNome();
		this.nomeEstado = atribuicao.getEstado().getNome();
		this.pontos = atribuicao.getPontos();
	}
	public String getNomeEstado() {
		return nomeEstado;
	}
	public String getNomeJogador() {
		return nomeJogador;
	}
	public Integer getPontos() {
		return pontos;
	}
	
	public static List<AtribuicaoDto> converter(List<Atribuicao> atribuicao){
		return atribuicao.stream().map(AtribuicaoDto::new).collect(Collectors.toList());
	}
}
