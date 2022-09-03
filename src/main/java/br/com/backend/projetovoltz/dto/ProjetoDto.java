package br.com.backend.projetovoltz.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class ProjetoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProjeto;
	private String nome;
	private LocalDate dtInicio;
	private String email;
	private List<FerramentaDto> ferramentasDto;
	private List<UsuarioDto> listaUsuariosDto;
	private UsuarioDto usuarioDto;

	public ProjetoDto() {
		super();
	}

	public Integer getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
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

	public List<FerramentaDto> getFerramentas() {
		return ferramentasDto;
	}

	public void setFerramentasDto(List<FerramentaDto> ferramentasDto) {
		this.ferramentasDto = ferramentasDto;
	}

	public List<UsuarioDto> getListaUsuariosDto() {
		return listaUsuariosDto;
	}

	public void setListaUsuariosDto(List<UsuarioDto> listaUsuariosDto) {
		this.listaUsuariosDto = listaUsuariosDto;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

}
