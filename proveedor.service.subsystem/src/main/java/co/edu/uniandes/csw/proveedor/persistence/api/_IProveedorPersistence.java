
package co.edu.uniandes.csw.proveedor.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;

public interface _IProveedorPersistence {

	public ProveedorDTO createProveedor(ProveedorDTO detail);
	public List<ProveedorDTO> getProveedors();
	public ProveedorDTO getProveedor(Long id);
	public void deleteProveedor(Long id);
	public void updateProveedor(ProveedorDTO detail);
	
}