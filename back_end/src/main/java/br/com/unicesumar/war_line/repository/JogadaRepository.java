package br.com.unicesumar.war_line.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unicesumar.war_line.modelo.Atribuicao;
import br.com.unicesumar.war_line.modelo.Jogada;

public interface JogadaRepository extends JpaRepository<Jogada, Long> {


//	@Query("select j from Jogada j where ESTADO_ENVIA_ID = :estadoId")
//	List<Jogada> buscarJogada(Long estadoId);
//	
//	@Query("select j from Jogada j where ESTADO_ENVIA_ID = :estadoId")
//	Jogada buscar(Long estadoId);
	
	@Query(value = "select j from Jogada j where ESTADO_ENVIA_ID = :IdEstadoEnvia and ESTADO_RECEBE_ID = :IdEstadoRecebe")
	List<Jogada> buscarCombinacao(Long IdEstadoEnvia,Long IdEstadoRecebe);
}
