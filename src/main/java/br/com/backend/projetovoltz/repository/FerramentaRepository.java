package br.com.backend.projetovoltz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.backend.projetovoltz.entities.Ferramenta;
import br.com.backend.projetovoltz.entities.Projeto;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, Integer> {

	Optional<Ferramenta> findByProjetos(Projeto projeto);

}
