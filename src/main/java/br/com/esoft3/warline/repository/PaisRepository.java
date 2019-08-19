package br.com.esoft3.warline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esoft3.warline.modelo.Jogador;
import br.com.esoft3.warline.modelo.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer>{

}
