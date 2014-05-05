
package co.edu.uniandes.csw.proveedor.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ProveedorDTO {

	private Long id;
	private String name;
	private String email;
	private String telefono;
	private String direccion;
	private String observaciones;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
 
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
 
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getObservaciones() {
		return observaciones;
	}
 
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}