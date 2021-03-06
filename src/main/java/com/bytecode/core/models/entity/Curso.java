package com.bytecode.core.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Double precio;
//	private int cantidad;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_creacion;
	
	//Relación Curso - Profesor
	@OneToOne
	@JoinColumn(name = "profesor_id")
	private Profesor profesor;
	
	//Relación Curso - Categoria
	@OneToOne
	@JoinColumn(name = "categoria_id")
	private CategoriaCursos categoria;
	
	
	@PrePersist
	public void prePresist() {
		fecha_creacion = new Date();
	}		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public CategoriaCursos getCategoria() {
		return categoria;
	}



	public void setCategoria(CategoriaCursos categoria) {
		this.categoria = categoria;
	}


	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ","
				+ " profesor=" 
				+ ", categorias=" + "]";
	}





	private static final long serialVersionUID = 1L;
	
}
