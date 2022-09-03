package br.com.backend.projetovoltz.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class FerramentaDto implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	private Integer idFerramenta;
	private String nome;
	private String tipo;
	private String descricao;
	private BigDecimal custoMinUsuarioMes;
	private String tags;
	
	public FerramentaDto() {
	}

	public Integer getIdFerramenta() {
		return idFerramenta;
	}

	public void setIdFerramenta(Integer idFerramenta) {
		this.idFerramenta = idFerramenta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getCustoMinUsuarioMes() {
		return custoMinUsuarioMes;
	}

	public void setCustoMinUsuarioMes(BigDecimal custoMinUsuarioMes) {
		this.custoMinUsuarioMes = custoMinUsuarioMes;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	

}
