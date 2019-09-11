package br.com.esoft3.warline.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.esoft3.warline.modelo.Estado;
import br.com.esoft3.warline.modelo.Jogador;
import br.com.esoft3.warline.repository.EstadoRepository;
import br.com.esoft3.warline.repository.JogadorRepository;

@Component
public class PopulaBanco {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		
		Estado pr = new Estado("PR","parana", 2);
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
		jogadorRepository.save(adriano);
	}


}
