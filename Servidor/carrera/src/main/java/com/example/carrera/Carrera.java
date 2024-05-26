package com.example.carrera;

import java.util.Objects;

public class Carrera {
	private Long id;
	private String nombre;
	private String rama;
	private String duracion;
	private String precio;
	
	
	public Carrera() {
	}

	public Carrera(Long id, String nombre, String rama, String duracion, String precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rama = rama;
		this.duracion = duracion;
		this.precio = precio;
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

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, id, nombre, precio, rama);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(duracion, other.duracion) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio)
				&& Objects.equals(rama, other.rama);
	}
	
	
	
	
}
