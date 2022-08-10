package com.sourproject.multitools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourproject.multitools.DTO.ProjectDTO;
import com.sourproject.multitools.entities.Project;
import com.sourproject.multitools.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepo;

	public ProjectDTO toDto(Project project, ProjectDTO projectDTO) {
		projectDTO.setIdProject(project.getIdProject());
		projectDTO.setCreationDate(project.getCreationDate());
		projectDTO.setProjectName(project.getProjectName());

		return projectDTO;
	}

	public Project toEntity(ProjectDTO projectDTO, Project project) {
		project.setCreationDate(projectDTO.getCreationDate());
		project.setProjectName(projectDTO.getProjectName());

		return project;
	}

	public void create(ProjectDTO projectDTO) {
		Project project = new Project();
		toEntity(projectDTO, project);
		projectRepo.save(project);
	}

	public void update(Integer idProject, ProjectDTO projectDTO) {
		Optional<Project> project = projectRepo.findById(idProject);
		Project proj = new Project();

		if (project.isPresent()) {
			proj = project.get();

			if (projectDTO.getProjectName() != null) {
				proj.setProjectName(projectDTO.getProjectName());
			}
			if (projectDTO.getCreationDate() != null) {
				proj.setCreationDate(projectDTO.getCreationDate());
			}
			projectRepo.save(proj);

		}
	}

	public void delete(Integer idProject) {
		projectRepo.deleteById(idProject);
	}

	public List<ProjectDTO> listAll() {
		List<Project> listProject = projectRepo.findAll();
		List<ProjectDTO> listProjectDTO = new ArrayList<>();

		for (Project project : listProject) {
			ProjectDTO projectDTO = new ProjectDTO();
			toDto(project, projectDTO);
			listProjectDTO.add(projectDTO);
		}
		return listProjectDTO;

	}

}
