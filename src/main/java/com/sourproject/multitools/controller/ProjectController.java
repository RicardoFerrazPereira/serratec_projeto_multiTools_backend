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

import com.sourproject.multitools.DTO.ProjectDTO;
import com.sourproject.multitools.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@PostMapping
	public ResponseEntity<Void> projectCreate(@RequestBody ProjectDTO projectDTO) {
		projectService.create(projectDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProjectDTO>> projectList() {
		return ResponseEntity.ok(projectService.listAll());
	}

	@DeleteMapping("/{idProject}")
	public ResponseEntity<Void> projectDelete(@PathVariable Integer idProject) {
		projectService.delete(idProject);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{idProject}")
	public ResponseEntity<Void> projectUpdate(@PathVariable Integer idProject, @RequestBody ProjectDTO projectDTO) {
		projectService.update(idProject, projectDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
