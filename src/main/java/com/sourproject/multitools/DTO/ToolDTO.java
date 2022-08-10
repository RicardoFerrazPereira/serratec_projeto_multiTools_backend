package com.sourproject.multitools.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class ToolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idTool;
	private String name;
	private String type;
	private String description;
	private BigDecimal monthlyCost;
	private String tags;

	public ToolDTO() {

	}

	public Integer getIdTool() {
		return idTool;
	}

	public void setIdTool(Integer idTool) {
		this.idTool = idTool;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(BigDecimal monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
