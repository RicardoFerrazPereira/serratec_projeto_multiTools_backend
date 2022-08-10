package com.sourproject.multitools.controller;

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

import com.sourproject.multitools.DTO.ToolDTO;
import com.sourproject.multitools.service.ToolService;

@RestController
@RequestMapping("/tool")
public class ToolController {
	
	@Autowired
	ToolService toolService;
	
	@PostMapping
	public ResponseEntity<Void> toolCreate(@RequestBody ToolDTO toolDTO) {
		toolService.create(toolDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ToolDTO>> toolList() {
		return ResponseEntity.ok(toolService.listAll());		
	}
	
	@DeleteMapping("/{idTool}")
	public ResponseEntity<Void> toolDelete(@PathVariable Integer idTool) {
		toolService.delete(idTool);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{idPTool}")
	public ResponseEntity<Void> toolUpdate(@PathVariable Integer idTool, @RequestBody ToolDTO toolDTO) {
		toolService.update(idTool, toolDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
