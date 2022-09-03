package br.com.backend.projetovoltz.dto;

import java.time.LocalDate;
import java.util.List;

public class ProjetoUsuarioDto {
	
	private String nome;
	private LocalDate dtInicio;
	private String email;
	private List<UsuarioDto> listaUsuarios;
	
	public ProjetoUsuarioDto() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UsuarioDto> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioDto> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}	

}
