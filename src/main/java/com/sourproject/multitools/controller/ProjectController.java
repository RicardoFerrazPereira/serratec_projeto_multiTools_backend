package com.sourproject.multitools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	

}
