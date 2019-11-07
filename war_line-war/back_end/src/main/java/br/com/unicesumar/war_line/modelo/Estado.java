package br.com.unicesumar.war_line.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uf;
	private String nome;
	private Integer valor;

	@ManyToOne
	private Jogador jogador;

	public Estado() {
	}
	
	public Estado(String UF, String nome, Integer valor) {
		this.uf = UF;
		this.nome = nome;
		this.valor = valor;
	}

	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public Long getId() {
		return id;
	}

	public String getUf() {
		return uf;
	}

	public String getNome() {
		return nome;
	}

	public Integer getValor() {
		return valor;
	}

	public void adicionarValorAleatorio(Integer valor) {
		this.valor = valor;
	}
	
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public List<String> verificaAdjacencia(String uf) {
		
		List<String> adjacentes = new ArrayList<>();
		
		switch (uf) {
		case "AC":
			adjacentes.add("AM");
			adjacentes.add("RO");
			break;
			
		case "AL":
			adjacentes.add("PE");
			adjacentes.add("SE");
			adjacentes.add("BA");
			break;
		
		case "AP":
			adjacentes.add("PA");
			break;	
			
		case "AM":
			adjacentes.add("PA");
			adjacentes.add("MT");
			adjacentes.add("RO");
			adjacentes.add("RR");
			adjacentes.add("AC");
			break;	
		
		case "BA":
			adjacentes.add("MG");
			adjacentes.add("ES");
			adjacentes.add("GO");
			adjacentes.add("TO");
			adjacentes.add("PI");
			adjacentes.add("PE");
			adjacentes.add("AL");
			adjacentes.add("SE");
			break;	
			
		case "CE":
			adjacentes.add("RN");
			adjacentes.add("PB");
			adjacentes.add("PE");
			adjacentes.add("PI");
			break;	
			
		case "DF":
			adjacentes.add("GO");
			adjacentes.add("MG");
			break;	
		
		case "ES":
			adjacentes.add("BA");
			adjacentes.add("MG");
			adjacentes.add("RJ");
			break;	
		
		case "GO":
			adjacentes.add("MS");
			adjacentes.add("MT");
			adjacentes.add("TO");
			adjacentes.add("BA");
			adjacentes.add("MG");
			adjacentes.add("DF");
			break;	
		
		case "MA":
			adjacentes.add("PI");
			adjacentes.add("TO");
			adjacentes.add("PA");
			break;
			
		case "MT":
			adjacentes.add("AM");
			adjacentes.add("PA");
			adjacentes.add("TO");
			adjacentes.add("GO");
			adjacentes.add("MS");
			adjacentes.add("RO");
			break;	
		
		case "MS":
			adjacentes.add("MT");
			adjacentes.add("GO");
			adjacentes.add("MG");
			adjacentes.add("SP");
			adjacentes.add("PR");
			break;	
		
		case "MG":
			adjacentes.add("SP");
			adjacentes.add("RJ");
			adjacentes.add("ES");
			adjacentes.add("GO");
			adjacentes.add("BA");
			adjacentes.add("DF");
			adjacentes.add("MS");
			break;	
		
		case "PA":
			adjacentes.add("AP");
			adjacentes.add("RR");
			adjacentes.add("AM");
			adjacentes.add("MS");
			adjacentes.add("TO");
			adjacentes.add("MA");
			break;	
		
		case "PB":
			adjacentes.add("RN");
			adjacentes.add("PE");
			adjacentes.add("CE");
			break;	
		
		case "PR":
			adjacentes.add("MS");
			adjacentes.add("SC");
			adjacentes.add("SP");
			break;	
		
		case "PE":
			adjacentes.add("PB");
			adjacentes.add("CE");
			adjacentes.add("AL");
			adjacentes.add("BA");
			adjacentes.add("PI");
			break;
			
		case "PI":
			adjacentes.add("PE");
			adjacentes.add("CE");
			adjacentes.add("TO");
			adjacentes.add("BA");
			adjacentes.add("MA");
			break;	
		
		case "RJ":
			adjacentes.add("MG");
			adjacentes.add("ES");
			adjacentes.add("SP");	
			break;	
		
		case "RN":
			adjacentes.add("PB");
			adjacentes.add("CE");			
			break;	
		
		case "RS":
			adjacentes.add("SC");			
			break;	
		
		case "RO":
			adjacentes.add("MT");
			adjacentes.add("AM");
			adjacentes.add("AC");
			break;	
		
		case "RR":
			adjacentes.add("PA");
			adjacentes.add("AM");
			break;	
		
		case "SC":
			adjacentes.add("PR");
			adjacentes.add("RS");
			break;	
		
		case "SP":
			adjacentes.add("MG");
			adjacentes.add("PR");
			adjacentes.add("RJ");
			adjacentes.add("MS");
			break;	
		
		case "SE":
			adjacentes.add("BA");
			adjacentes.add("AL");
			break;	
		
		case "TO":
			adjacentes.add("GO");
			adjacentes.add("MT");
			adjacentes.add("PA");
			adjacentes.add("MA");
			adjacentes.add("PI");
			adjacentes.add("BA");
			break;	
			
		default:
			System.out.println("Estado invalido!");
			break;
		}
		
		return adjacentes;
	}
}
