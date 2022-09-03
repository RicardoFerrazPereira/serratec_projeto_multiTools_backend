package br.com.backend.projetovoltz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.backend.projetovoltz.dto.ProjetoDto;

import br.com.backend.projetovoltz.exception.ProjetoException;
import br.com.backend.projetovoltz.repository.FerramentaRepository;
import br.com.backend.projetovoltz.repository.ProjetoRepository;
import br.com.backend.projetovoltz.service.FerramentaService;
import br.com.backend.projetovoltz.service.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	FerramentaRepository ferramentaRepository;

	@Autowired
	ProjetoRepository projetoRepository;

	@Autowired
	ProjetoService projetoService;

	@Autowired
	FerramentaService ferramentaService;

	@PostMapping("/salvar")
	public ResponseEntity<String> salvarProjeto(@RequestBody ProjetoDto projetoDto) {
		return ResponseEntity.ok(projetoService.salvar(projetoDto));
	}

	@PostMapping("/salvar-varios")
	public ResponseEntity<Void> salvarLista(@RequestBody List<ProjetoDto> listaProjetoDto) {
		projetoService.salvarVarios(listaProjetoDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/atualizar/{idProjeto}")
	public ResponseEntity<String> atualizarProjeto(@PathVariable Integer idProjeto, @RequestBody ProjetoDto projetoDto)
			throws ProjetoException {
		return ResponseEntity.ok(projetoService.atualizar(idProjeto, projetoDto));
	}

	@GetMapping("/lista")
	public ResponseEntity<List<ProjetoDto>> listarProjetos() {
		return ResponseEntity.ok(projetoService.listarTodos());
	}

	@GetMapping("/buscar/{idProjeto}")
	public ResponseEntity<ProjetoDto> buscaPorId(@PathVariable Integer idProjeto) throws ProjetoException {
		return ResponseEntity.ok(projetoService.buscarPorId(idProjeto));
	}

	@DeleteMapping("/{idProjeto}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idProjeto) throws ProjetoException {
		projetoService.deletar(idProjeto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@PostMapping("/{idProjeto}/{idFerramenta}")
	public ResponseEntity<ProjetoDto> addFerramenta(@PathVariable Integer idProjeto,
			@PathVariable Integer idFerramenta) {
		ProjetoDto projetoDto = projetoService.addFerramenta(idProjeto, idFerramenta);

		return new ResponseEntity<>(projetoDto, HttpStatus.OK);

	}

	@PostMapping("/adicionar-usuario/{idProjeto}/{idUsuario}")
	public ResponseEntity<ProjetoDto> adicionarUsuario(@PathVariable Integer idProjeto,
			@PathVariable Integer idUsuario) {
		ProjetoDto projetoDto = projetoService.addUsuario(idProjeto, idUsuario);

		return new ResponseEntity<>(projetoDto, HttpStatus.OK);

	}
	
	@GetMapping("/projeto-usuario/{emailUsuario}")
	public ResponseEntity<List<ProjetoDto>> listarProjetosPorUsuario(@PathVariable String emailUsuario) {
		return ResponseEntity.ok(projetoService.listarProjetosPorUsuario(emailUsuario));
	}

}
