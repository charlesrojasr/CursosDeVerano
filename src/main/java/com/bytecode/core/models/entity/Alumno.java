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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidos;
	private String email;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_creacion;
	
	private String nombres_apoderado;
	private String apellidos_apoderado;
	private String telefono_apoderado;
	
	@OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Matricula> matriculas;
	
	@PrePersist
	public void prePresist() {
		fecha_creacion = new Date();
	}
	
	public Alumno() {
		matriculas = new ArrayList<Matricula>();
	}
	
	public void addMatricula(Matricula matricula) {
		matriculas.add(matricula);
	}
	
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getNombres_apoderado() {
		return nombres_apoderado;
	}
	public void setNombres_apoderado(String nombres_apoderado) {
		this.nombres_apoderado = nombres_apoderado;
	}
	public String getApellidos_apoderado() {
		return apellidos_apoderado;
	}
	public void setApellidos_apoderado(String apellidos_apoderado) {
		this.apellidos_apoderado = apellidos_apoderado;
	}
	public String getTelefono_apoderado() {
		return telefono_apoderado;
	}
	public void setTelefono_apoderado(String telefono_apoderado) {
		this.telefono_apoderado = telefono_apoderado;
	}
	
	private static final long serialVersionUID = 1L;
}
