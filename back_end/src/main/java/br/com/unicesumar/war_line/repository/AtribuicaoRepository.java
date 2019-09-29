package br.com.unicesumar.war_line.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unicesumar.war_line.modelo.Atribuicao;

public interface AtribuicaoRepository extends JpaRepository<Atribuicao, Long>{
	
	@Query("select a from Atribuicao a where ESTADO_ID = :estadoId")
	List<Atribuicao> buscarAtribuicao(Long estadoId);
	
	@Query("select a from Atribuicao a where ESTADO_ID = :estadoId")
	Atribuicao buscar(Long estadoId);
}
