package com.sourproject.multitools.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourproject.multitools.entities.Tool;

public interface ToolRepository extends JpaRepository<Tool, Integer>{

}
