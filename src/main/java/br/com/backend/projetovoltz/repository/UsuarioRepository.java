package br.com.backend.projetovoltz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.backend.projetovoltz.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByEmail(String email);

	public Usuario findByEmailAndSenha(String email, String senha);
	
	@Query(value="FROM Usuario u WHERE u.email = ?1")
	Optional<Usuario> buscarPorEmail(String email);

}
