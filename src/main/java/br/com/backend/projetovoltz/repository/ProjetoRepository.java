package br.com.backend.projetovoltz.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.backend.projetovoltz.entities.Projeto;
import br.com.backend.projetovoltz.entities.Usuario;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
	
	@Query("SELECT p FROM Projeto p INNER JOIN p.usuarios u WHERE u IN (?1)")
	List<Projeto> findByInUsuarios(Collection<Usuario> usuarios);
	
}
