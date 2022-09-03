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

import br.com.backend.projetovoltz.dto.FerramentaDto;



import br.com.backend.projetovoltz.exception.FerramentaException;

import br.com.backend.projetovoltz.repository.FerramentaRepository;
import br.com.backend.projetovoltz.service.FerramentaService;
import br.com.backend.projetovoltz.service.ProjetoService;

@RestController
@RequestMapping("/ferramenta")
public class FerramentaController {
	
	@Autowired
	FerramentaService ferramentaService;
	
	@Autowired
	FerramentaRepository ferramentaRepository;
	
	@Autowired
	ProjetoService projetoService;
	
	
	@PostMapping("/salvar")
	public ResponseEntity<String> salvarFerramenta(@RequestBody FerramentaDto ferramentaDto) {
		return ResponseEntity.ok(ferramentaService.salvar(ferramentaDto));	
	}
	
	@PostMapping("/salvar-varios")
	public ResponseEntity<Void> salvarLista(@RequestBody List<FerramentaDto> listaFerramentaDto) {
		ferramentaService.salvarVarios(listaFerramentaDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar/{idFerramenta}")
	public ResponseEntity<String> atualizarFerramenta(@PathVariable Integer idFerramenta, @RequestBody FerramentaDto ferramentaDto) throws FerramentaException {
		return ResponseEntity.ok(ferramentaService.atualizar(idFerramenta, ferramentaDto));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<FerramentaDto>> listarFerramentas() {
		return ResponseEntity.ok(ferramentaService.listarTodos());
	}
	
	@GetMapping("/buscar/{idFerramenta}")
	public ResponseEntity<FerramentaDto> buscaPorId(@PathVariable Integer idFerramenta) throws FerramentaException {
		return ResponseEntity.ok(ferramentaService.buscarPorId(idFerramenta));
	}	
	
	@DeleteMapping("/{idFerramenta}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idFerramenta) throws FerramentaException {
		ferramentaService.deletar(idFerramenta);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/buscar-por-projeto/{idProjeto}")
    public ResponseEntity<FerramentaDto> buscaPorIdProjeto(@PathVariable Integer idProjeto) throws FerramentaException {
        return ResponseEntity.ok(ferramentaService.buscaPorIdProjeto(idProjeto));
    }
	
		
}
