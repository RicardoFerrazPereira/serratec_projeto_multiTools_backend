package br.com.backend.projetovoltz.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.backend.projetovoltz.entities.Usuario;
import br.com.backend.projetovoltz.repository.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.buscarPorEmail(username);

		if (usuario.isPresent()) {
			Usuario u = usuario.get();
			return new User(u.getEmail(), u.getSenha(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Usuário incorreto");
	}

}
