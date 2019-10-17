package br.com.unicesumar.war_line.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicesumar.war_line.modelo.Jogada;

public interface JogadaRepository extends JpaRepository<Jogada, Long> {

}
