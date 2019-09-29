package br.com.unicesumar.war_line.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicesumar.war_line.modelo.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	Estado findByNome(String nomeEstado);

	Estado findByUf(String uf);

}
