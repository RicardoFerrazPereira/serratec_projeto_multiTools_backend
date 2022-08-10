package com.sourproject.multitools.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourproject.multitools.DTO.ToolDTO;
import com.sourproject.multitools.entities.Tool;
import com.sourproject.multitools.repository.ToolRepository;

@Service
public class ToolService {

	@Autowired
	ToolRepository toolRepo;

	public ToolDTO toDto(Tool tool, ToolDTO toolDTO) {
		toolDTO.setIdTool(tool.getIdTool());
		toolDTO.setName(tool.getName());
		toolDTO.setDescription(tool.getDescription());
		toolDTO.setMonthlyCost(tool.getMonthlyCost());
		toolDTO.setTags(tool.getTags());
		toolDTO.setType(tool.getType());

		return toolDTO;
	}

	public Tool toEntity(ToolDTO toolDTO, Tool tool) {
		tool.setName(toolDTO.getName());
		tool.setDescription(toolDTO.getDescription());
		tool.setMonthlyCost(toolDTO.getMonthlyCost());
		tool.setTags(toolDTO.getTags());
		tool.setType(toolDTO.getType());

		return tool;
	}

	public void create(ToolDTO toolDTO) {
		Tool tool = new Tool();
		toEntity(toolDTO, tool);
		toolRepo.save(tool);
	}

	public void update(Integer idTool, ToolDTO toolDTO) {
		Optional<Tool> tool = toolRepo.findById(idTool);
		Tool newTool = new Tool();

		if (tool.isPresent()) {
			newTool = tool.get();

			if (toolDTO.getName() != null) {
				newTool.setName(toolDTO.getName());
			}
			if (toolDTO.getDescription() != null) {
				newTool.setDescription(toolDTO.getDescription());
			}
			if (toolDTO.getMonthlyCost() != null) {
				newTool.setMonthlyCost(toolDTO.getMonthlyCost());
			}
			if (toolDTO.getTags() != null) {
				newTool.setTags(toolDTO.getTags());
			}
			if (toolDTO.getType() != null) {
				newTool.setType(toolDTO.getType());
			}
			toolRepo.save(newTool);
		}
	}
	
	public void delete(Integer idTool) {
		toolRepo.deleteById(idTool);
	}
	
	public List<ToolDTO> listAll() {
		List<Tool> listTool = toolRepo.findAll();
		List<ToolDTO> listToolDTO = new ArrayList<>();
		
		for (Tool tool : listTool) {
			ToolDTO toolDTO = new ToolDTO();
			toDto(tool, toolDTO);
			listToolDTO.add(toolDTO);
		}
		return listToolDTO;
	}

}
