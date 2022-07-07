package com.api.rest.automovil.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "automovil")
public class Automovil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String nombre;
	
	//datos para completar practica
	/* ============================*/
	private String pais_origen;
	private String calificacion;
	private String descripcion;
	/* =============================*/

	@OneToMany(mappedBy = "automovil", cascade = CascadeType.ALL)
	private Set<Modelo> modelo = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Modelo> getModelo() {
		return modelo;
	}
	
	/* ======================================================================*/
	public String getPais_origen() {
		return pais_origen;
	}

	public void setPais_origen(String pais_origen) {
		this.pais_origen = pais_origen;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/* ==================================================================*/


	public void setModelo(Set<Modelo> modelo) {
		this.modelo = modelo;
		for(Modelo modelos : modelo) {
			modelos.setAutomovil(this);
		}
	}

	
	
	

}
