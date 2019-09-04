package br.com.esoft3.warline.util;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.esoft3.warline.modelo.Jogador;
import br.com.esoft3.warline.modelo.Pais;
import br.com.esoft3.warline.repository.JogadorRepository;
import br.com.esoft3.warline.repository.PaisRepository;

@Component
public class PopulaBanco {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		Pais pr = new Pais("parana", 2);
		Pais sc = new Pais("santa catarina", 2);
		Pais sp = new Pais("sao paulo", 2);
		Pais rj = new Pais("rio de janeiro", 2);
		Pais mg = new Pais("minas gerais", 2);
		Pais rs = new Pais("rio grande do sul", 2);
		
		paisRepository.save(pr);
		paisRepository.save(sc);
		paisRepository.save(sp);
		paisRepository.save(rj);
		paisRepository.save(mg);
		paisRepository.save(rs);
		
		Jogador adriano = new Jogador("adriano", 5);
		Jogador computador = new Jogador("computador", 5);
		
		jogadorRepository.save(adriano);
		
		adriano.adicionarPais(pr);
		paisRepository.save(pr);
		jogadorRepository.save(adriano);
		
		jogadorRepository.save(computador);
		
	}


}
