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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "matriculas")
public class Matricula implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	
	//Relación entre Alumno - Matricula
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	
	//Relación entre Matricula - DetalleFactura
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="matricula_id", nullable = false)
	private List<DetalleMatricula> detalles;
	
	
	
	@PrePersist
	public void prePresist() {
		fecha_creacion = new Date();
	}
	
	public Matricula() {
		this.detalles =new ArrayList<DetalleMatricula>();
	}
	
	
	
	public List<DetalleMatricula> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleMatricula> detalles) {
		this.detalles = detalles;
	}

	public void addDetalleMatricula(DetalleMatricula detalles) {
		this.detalles.add(detalles);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for (int i=0; i<detalles.size();i++) {
			total += detalles.get(i).calcularCosto();
		}
		return total;
	}
	
	private static final long serialVersionUID = 1L;

}
