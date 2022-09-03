package br.com.backend.projetovoltz.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projeto")
	private Integer idProjeto;

	@Column(name = "nome")	
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_inicio")
	private LocalDate dtInicio;
	
	
	@Column(name = "email")
	private String email;
	
	@ManyToMany(mappedBy = "projetos", cascade = CascadeType.REMOVE)
	private List<Ferramenta> ferramentas;	
	
	@ManyToMany(mappedBy = "projetosUsuario", cascade = CascadeType.REMOVE)
	private List<Usuario> usuarios;
	
	public Projeto() {
		
	}	

	public Projeto(Integer idProjeto, String nome, LocalDate dtInicio, String email, List<Ferramenta> ferramentas, List<Usuario> usuarios) {
		super();
		this.idProjeto = idProjeto;
		this.nome = nome;
		this.dtInicio = dtInicio;
		this.email = email;
		this.ferramentas = ferramentas;
		
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

	public List<Ferramenta> getFerramentas() {
		return ferramentas;
	}

	public void setFerramentas(List<Ferramenta> ferramentas) {
		this.ferramentas = ferramentas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtInicio, email, ferramentas, idProjeto, nome, usuarios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(dtInicio, other.dtInicio) && Objects.equals(email, other.email)
				&& Objects.equals(ferramentas, other.ferramentas) && Objects.equals(idProjeto, other.idProjeto)
				&& Objects.equals(nome, other.nome) && Objects.equals(usuarios, other.usuarios);
	}
	
}
