package br.com.warline.modelo;

public class Jogada {
	private Estado envia;
	private Estado destino;
	private Integer valor;
	public Jogada(Estado envia, Estado destino, Integer valor) {
		this.envia = envia;
		this.destino = destino;
		this.valor = valor;
	}
	
	
}
