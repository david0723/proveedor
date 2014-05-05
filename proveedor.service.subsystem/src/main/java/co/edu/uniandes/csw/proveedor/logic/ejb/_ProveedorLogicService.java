
package co.edu.uniandes.csw.proveedor.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;
import co.edu.uniandes.csw.proveedor.logic.api._IProveedorLogicService;
import co.edu.uniandes.csw.proveedor.persistence.api.IProveedorPersistence;

public abstract class _ProveedorLogicService implements _IProveedorLogicService {

	@Inject
	protected IProveedorPersistence persistance;

	public ProveedorDTO createProveedor(ProveedorDTO proveedor){
		return persistance.createProveedor( proveedor); 
    }

	public List<ProveedorDTO> getProveedors(){
		return persistance.getProveedors(); 
	}

	public ProveedorDTO getProveedor(Long id){
		return persistance.getProveedor(id); 
	}

	public void deleteProveedor(Long id){
	    persistance.deleteProveedor(id); 
	}

	public void updateProveedor(ProveedorDTO proveedor){
	    persistance.updateProveedor(proveedor); 
	}	
}