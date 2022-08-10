package com.sourproject.multitools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourproject.multitools.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
