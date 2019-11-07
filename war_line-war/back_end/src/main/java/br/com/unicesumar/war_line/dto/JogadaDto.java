package br.com.unicesumar.war_line.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unicesumar.war_line.modelo.Jogada;

public class JogadaDto {
	
	private String estadoEnviaNome;
	private String estadoRecebeNome;
	private Integer valor;
	
	public JogadaDto(Jogada jogada) {
		estadoEnviaNome = jogada.getEstadoEnvia().getNome();
		estadoRecebeNome = jogada.getEstadoRecebe().getNome();
		valor = jogada.getValor();
	}
	
	public String getEstadoEnviaNome() {
		return estadoEnviaNome;
	}
	public String getEstadoRecebeNome() {
		return estadoRecebeNome;
	}
	public Integer getValor() {
		return valor;
	}
	public static List<JogadaDto> converter(List<Jogada> jogadas) {
		return jogadas.stream().map(JogadaDto::new).collect(Collectors.toList());
	}
}
