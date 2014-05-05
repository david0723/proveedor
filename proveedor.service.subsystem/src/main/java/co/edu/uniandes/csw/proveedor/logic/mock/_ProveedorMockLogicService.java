
package co.edu.uniandes.csw.proveedor.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;
import co.edu.uniandes.csw.proveedor.logic.api._IProveedorLogicService;

public abstract class _ProveedorMockLogicService implements _IProveedorLogicService {

	private Long id= new Long(1);
	protected List<ProveedorDTO> data=new ArrayList<ProveedorDTO>();

	public ProveedorDTO createProveedor(ProveedorDTO proveedor){
		id++;
		proveedor.setId(id);
		return proveedor;
    }

	public List<ProveedorDTO> getProveedors(){
		return data; 
	}

	public ProveedorDTO getProveedor(Long id){
		for(ProveedorDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteProveedor(Long id){
	    ProveedorDTO delete=null;
		for(ProveedorDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateProveedor(ProveedorDTO proveedor){
	    ProveedorDTO delete=null;
		for(ProveedorDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(proveedor);
		} 
	}	
}