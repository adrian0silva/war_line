package br.com.unicesumar.war_line.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
		
		Estado pr = new Estado("PR","parana", 5456465);
		Estado sc = new Estado("SC","santa catarina", 2);
		Estado sp = new Estado("SP","sao paulo", 2);
		Estado rj = new Estado("RJ","rio de janeiro", 2);
		Estado mg = new Estado("MG","minas gerais", 2);
		Estado rs = new Estado("RS","rio grande do sul", 2);
		
		estadoRepository.save(pr);
		estadoRepository.save(sc);
		estadoRepository.save(sp);
		estadoRepository.save(rj);
		estadoRepository.save(mg);
		estadoRepository.save(rs);
		
		Jogador adriano = new Jogador("adriano");
		Jogador computador = new Jogador("computador");
		
		jogadorRepository.save(adriano);
		jogadorRepository.save(computador);
		
		adriano.adicionarEstado(pr);
		estadoRepository.save(pr);
		jogadorRepository.save(adriano);
		
		computador.adicionarEstado(rj);
		estadoRepository.save(rj);
		jogadorRepository.save(computador);
		
		
	}


}
