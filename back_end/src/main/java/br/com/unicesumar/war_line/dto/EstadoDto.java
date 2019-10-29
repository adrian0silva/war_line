package br.com.unicesumar.war_line.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.unicesumar.war_line.modelo.Estado;

public class EstadoDto {
	
	private String id;
	private String UF;
	private String nome;
	private Integer valor;
	
	private String jogador;	
	
	public EstadoDto(Estado estado) {
		this.id = estado.getId().toString();
		UF = estado.getUf();
		this.nome = estado.getNome();
		this.valor = estado.getValor();
		
		if(estado.getJogador() != null) {
			this.jogador = estado.getJogador().getNome();			
		}
	}

	public String getId() {
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

	public String getJogador() {
		return jogador;
	}
	
	public static List<EstadoDto> converter(List<Estado> estado) {
		return estado.stream().map(EstadoDto::new).collect(Collectors.toList());
	}
	
}
