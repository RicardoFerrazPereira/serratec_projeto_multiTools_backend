package br.com.backend.projetovoltz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.backend.projetovoltz.dto.UsuarioDto;
import br.com.backend.projetovoltz.entities.Usuario;
import br.com.backend.projetovoltz.exception.UsuarioException;
import br.com.backend.projetovoltz.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	public UsuarioDto paraDto(Usuario usuario, UsuarioDto usuarioDto) {
		usuarioDto.setIdUsuario(usuario.getIdUsuario());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setSenha(usuario.getSenha());
		usuarioDto.setIsAdmin(usuario.getIsAdmin());

		return usuarioDto;
	}

	public Usuario paraEntidade(UsuarioDto usuarioDto, Usuario usuario) {
		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setSenha(encoder.encode(usuarioDto.getSenha()));
		usuario.setIsAdmin(usuarioDto.getIsAdmin());

		return usuario;
	}

	public String salvar(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		Usuario usuarioSalvar = paraEntidade(usuarioDto, usuario);
		usuarioRepository.save(usuarioSalvar);

		return "Usuário foi criado com o id: " + usuario.getIdUsuario();
	}

	public String salvarVarios(List<UsuarioDto> listaUsuarioDto) {
		List<Usuario> listaDeUsuarios = new ArrayList<>();

		for (UsuarioDto usuarioDto : listaUsuarioDto) {
			Usuario usuario = new Usuario();
			paraEntidade(usuarioDto, usuario);
			listaDeUsuarios.add(usuario);
		}
		usuarioRepository.saveAll(listaDeUsuarios);
		return "Usuários criados com sucesso!";
	}

	public List<UsuarioDto> listarTodos() {
		List<Usuario> listaUsuario = usuarioRepository.findAll();
		List<UsuarioDto> listaUsuarioDto = new ArrayList<>();

		for (Usuario usuario : listaUsuario) {
			UsuarioDto usuarioDto = new UsuarioDto();
			paraDto(usuario, usuarioDto);
			listaUsuarioDto.add(usuarioDto);
		}
		return listaUsuarioDto;
	}

	public UsuarioDto buscarPorId(Integer idUsuario) throws UsuarioException {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		Usuario usuarioNoBanco = new Usuario();
		UsuarioDto usuarioDto = new UsuarioDto();

		if (usuario.isPresent()) {
			usuarioNoBanco = usuario.get();
			usuarioDto = paraDto(usuarioNoBanco, usuarioDto);
			return usuarioDto;
		}
		throw new UsuarioException("Usuario não encontrado com id informado!");
	}

	public String atualizar(Integer idUsuario, UsuarioDto usuarioDto) throws UsuarioException {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		Usuario usuarioNoBanco = new Usuario();

		if (usuario.isPresent()) {
			usuarioNoBanco = usuario.get();

			if (usuarioDto.getNome() != null) {
				usuarioNoBanco.setNome(usuarioDto.getNome());
			}
			if (usuarioDto.getEmail() != null) {
				usuarioNoBanco.setEmail(usuarioDto.getEmail());
			}
			if (usuarioDto.getSenha() != null) {
				usuarioNoBanco.setSenha(usuarioDto.getSenha());
			}
			usuarioRepository.save(usuarioNoBanco);
			return "O Usuário com o id " + usuarioNoBanco.getIdUsuario() + " foi atualizado";
		}
		throw new UsuarioException("Usuário não atualizado!");
	}

	public String deletar(Integer idUsuario) {
		usuarioRepository.deleteById(idUsuario);
		return "Usuário excluído com sucesso!";
	}

	public UsuarioDto confirmarUsuario(String email, String senha) {
		Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
		if (usuario != null) {
			UsuarioDto usuarioDto = new UsuarioDto();
			return paraDto(usuario, usuarioDto);
		}
		return null;
	}

}
