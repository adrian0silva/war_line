package br.com.unicesumar.war_line.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.unicesumar.war_line.modelo.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	Estado findByNome(String nomeEstado);

	Estado findByUf(String uf);

	@Query(value = "SELECT TOP 1 * FROM ESTADO WHERE JOGADOR_ID = 2 ORDER BY RAND()",nativeQuery = true)
	Estado buscarEstadoDoComputadorAleatorio();
	
	@Query(value = "SELECT TOP 1 * FROM ESTADO WHERE JOGADOR_ID = 1 ORDER BY RAND()",nativeQuery = true)
	Estado buscarEstadoDoJogadorAleatorio();
}
