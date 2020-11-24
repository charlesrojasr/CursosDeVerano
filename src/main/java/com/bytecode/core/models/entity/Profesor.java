package com.bytecode.core.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "profesores")
public class Profesor implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidos;
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	
	//Relación Cursos - Profesor
	@OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Curso> cursos;
	
	@PrePersist
	public void prePresist() {
		fecha_creacion = new Date();
	}
	
	public Profesor () {
		cursos = new ArrayList<Curso>();
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void addCursos(Curso curso) {
		cursos.add(curso);
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}



	private static final long serialVersionUID = 1L;
}
