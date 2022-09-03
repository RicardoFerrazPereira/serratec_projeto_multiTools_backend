package br.com.backend.projetovoltz.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ferramenta")
public class Ferramenta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ferramenta")
	private Integer idFerramenta;
	
	@Column(name = "nome")
	@NotNull(message = "Campo nome não pode ficar vazio")
	private String nome;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "custo_minimo")
	private BigDecimal custoMinUsuarioMes;
	
	@Column(name = "tags")
	private String tags;
	
	@ManyToMany
	@JoinTable(
		name= "ferramenta_projeto",
		joinColumns = @JoinColumn(name="id_ferramenta"),
		inverseJoinColumns = @JoinColumn (name="id_projeto"))
	
	private List<Projeto> projetos;	
	
	
	public Ferramenta() {		
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


	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(custoMinUsuarioMes, descricao, idFerramenta, nome, projetos, tags, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ferramenta other = (Ferramenta) obj;
		return Objects.equals(custoMinUsuarioMes, other.custoMinUsuarioMes)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(idFerramenta, other.idFerramenta)
				&& Objects.equals(nome, other.nome) && Objects.equals(projetos, other.projetos)
				&& Objects.equals(tags, other.tags) && Objects.equals(tipo, other.tipo);
	}	
	
		
	

}
