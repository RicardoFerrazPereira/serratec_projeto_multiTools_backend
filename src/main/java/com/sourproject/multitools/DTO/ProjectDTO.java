package com.sourproject.multitools.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class ProjectDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProject;
	private String projectName;
	private LocalDate creationDate;

	public ProjectDTO() {

	}

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
