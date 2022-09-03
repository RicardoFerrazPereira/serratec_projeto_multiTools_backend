package br.com.backend.projetovoltz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.backend.projetovoltz.dto.UsuarioDto;
import br.com.backend.projetovoltz.exception.UsuarioException;
import br.com.backend.projetovoltz.security.JwtUtil;
import br.com.backend.projetovoltz.security.UsuarioAuthenticationRequest;
import br.com.backend.projetovoltz.security.UsuarioAuthenticationResponse;
import br.com.backend.projetovoltz.security.UsuarioDetalheService;
import br.com.backend.projetovoltz.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioDetalheService usuarioDetalheService;
	
	@Autowired
	private JwtUtil jwtUtil;	
	
		
	@PostMapping("/salvar")
	public ResponseEntity<String> salvarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
		return ResponseEntity.ok(usuarioService.salvar(usuarioDto));	
	}
	
	@PostMapping("/salvar-varios")
	public ResponseEntity<Void> salvarLista(@RequestBody List<UsuarioDto> listaUsuarioDto) {
		usuarioService.salvarVarios(listaUsuarioDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar/{idUsuario}")
	public ResponseEntity<String> atualizarUsuario(@PathVariable Integer idUsuario, @RequestBody UsuarioDto usuarioDto) throws UsuarioException {
		return ResponseEntity.ok(usuarioService.atualizar(idUsuario, usuarioDto));
	}	
	
	@GetMapping("/lista")
	public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}
	
	@GetMapping("/buscar/{idUsuario}")
	public ResponseEntity<UsuarioDto> buscaPorId(@PathVariable Integer idUsuario) throws UsuarioException {
		return ResponseEntity.ok(usuarioService.buscarPorId(idUsuario));
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idUsuario) {
		usuarioService.deletar(idUsuario);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDto> auteUsuario(@RequestBody UsuarioDto usuarioDto) {
		UsuarioDto uExiste = usuarioService.confirmarUsuario(usuarioDto.getEmail(), usuarioDto.getSenha());
		if(null != uExiste) 
			return new ResponseEntity<>(uExiste, HttpStatus.OK);
		else 
			return new ResponseEntity<>(uExiste, HttpStatus.NOT_FOUND);				
	}
			
	@PostMapping("/authenticate")
	public ResponseEntity<?> criarAutenticacao(@RequestBody UsuarioAuthenticationRequest usuario) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
		}catch(Exception e){
			throw new Exception("Senha incorreta",e);
		}
		UserDetails usuarioDetalhe = usuarioDetalheService.loadUserByUsername(usuario.getUsername());
		String token = jwtUtil.generateToken(usuarioDetalhe);
		return ResponseEntity.ok(new UsuarioAuthenticationResponse(token));
	}
	
	
}
