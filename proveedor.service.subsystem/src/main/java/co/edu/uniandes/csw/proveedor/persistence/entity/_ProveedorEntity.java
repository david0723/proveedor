
package co.edu.uniandes.csw.proveedor.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ProveedorEntity {

	@Id
	@GeneratedValue(generator = "Proveedor")
	private Long id;
	private String name;
	private String email;
	private String telefono;
	private String direccion;
	private String observaciones;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	public String getTelefono(){
		return telefono;
	}
	
	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	public String getDireccion(){
		return direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public String getObservaciones(){
		return observaciones;
	}
	
	public void setObservaciones(String observaciones){
		this.observaciones = observaciones;
	}
}