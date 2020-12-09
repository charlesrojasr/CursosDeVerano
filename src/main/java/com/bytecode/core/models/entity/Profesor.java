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
	private int id;
	private String nombres;
	private String apellidos;
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	/*
	//Relación Cursos - Profesor
	@OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Curso> cursos;
	*/
	@PrePersist
	public void prePresist() {
		fecha_creacion = new Date();
	}
	/*
	public Profesor () {
		cursos = new ArrayList<Curso>();
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}
*/
	
/*
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void addCursos(Curso curso) {
		cursos.add(curso);
	}
*/
	public int getId() {
		return id;
	}


	public void setId(int id) {
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((fecha_creacion == null) ? 0 : fecha_creacion.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (fecha_creacion == null) {
			if (other.fecha_creacion != null)
				return false;
		} else if (!fecha_creacion.equals(other.fecha_creacion))
			return false;
		if (id != other.id)
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		return true;
	}

	public Profesor() {
		super();
	}
	
	


	@Override
	public String toString() {
		return "Profesor [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fecha_creacion="
				+ fecha_creacion + "]";
	}




	private static final long serialVersionUID = 1L;
}
