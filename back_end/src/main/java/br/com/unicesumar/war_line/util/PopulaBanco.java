package br.com.unicesumar.war_line.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.unicesumar.war_line.controller.JogadaController;
import br.com.unicesumar.war_line.controller.JogadorController;
import br.com.unicesumar.war_line.modelo.Estado;
import br.com.unicesumar.war_line.modelo.Jogador;
import br.com.unicesumar.war_line.repository.EstadoRepository;
import br.com.unicesumar.war_line.repository.JogadorRepository;

@Component
public class PopulaBanco {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
		
	@EventListener
	public void appReady(ApplicationReadyEvent event) {		
		
		Estado ac = new Estado("AC","Acre", 2);
		Estado al = new Estado("AL","Alagoas", 2);
		Estado ap = new Estado("AP","Amapa", 2);
		Estado am = new Estado("AM","Amazonas", 2);
		Estado ba = new Estado("BA","Bahia", 2);
		Estado ce = new Estado("CE","Ceara", 2);
		Estado df = new Estado("DF","Distrito Federal", 2);
		Estado es = new Estado("ES","Espirito Santo", 2);
		Estado go = new Estado("GO","Goias", 2);
		Estado ma = new Estado("MA","Maranhao", 2);
		Estado mt = new Estado("MT","Mato Grosso", 2);
		Estado ms = new Estado("MS","Mato Grosso do Sul", 2);
		Estado mg = new Estado("MG","Minas Gerais", 2);
		Estado pa = new Estado("PA","Para", 2);
		Estado pb = new Estado("PB","Paraiba", 2);
		Estado pr = new Estado("PR","Parana", 2);
		Estado pe = new Estado("PE","Pernambuco", 2);
		Estado pi = new Estado("PI","Piaui", 2);
		Estado rj = new Estado("RJ","Rio de Janeiro", 2);
		Estado rn = new Estado("RN","Rio Grande do Norte", 2);
		Estado rs = new Estado("RS","Rio Grande do Sul", 2);
		Estado ro = new Estado("RO","Rondonia", 2);
		Estado rr = new Estado("RR","Roraima", 2);
		Estado sc = new Estado("SC","Santa Catarina", 2);
		Estado sp = new Estado("SP","Sao Paulo", 2);
		Estado se = new Estado("SE","Sergipe", 2);
		Estado to = new Estado("TO","Tocantins", 2);
		
		estadoRepository.saveAll(Arrays.asList(ac,al,ap,am,ba,ce,df,es,go,ma,mt,ms,mg,pa,pb,pr,pe,pi,rj,rn,rs,ro,rr,sc,sp,se,to));
		
		Jogador player = new Jogador("player");
		Jogador computador = new Jogador("computador");
		
		jogadorRepository.save(player);
		jogadorRepository.save(computador);
		
		player.adicionarEstado(pr);
		estadoRepository.save(pr);
		jogadorRepository.save(player);
		
		player.adicionarEstado(sc);
		estadoRepository.save(sc);
		jogadorRepository.save(player);
		
		computador.adicionarEstado(rj);
		estadoRepository.save(rj);
		jogadorRepository.save(computador);		
		
		
		
		for(int linha = 1;linha < 28;linha++) {
			for(int coluna = 1;coluna < 28;coluna++) {
				boolean temAdjacencia = new JogadorController().matrizDeAdjacencia(Long.valueOf(linha), Long.valueOf(coluna));
				
				System.out.println(estadoRepository.findById(Long.valueOf(linha)).get().getNome() + " tem adjacencia com " + estadoRepository.findById(Long.valueOf(coluna)).get().getNome() + " ? " +  temAdjacencia);	
			}
		}
	}

}
