
package co.edu.uniandes.csw.proveedor.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;
import co.edu.uniandes.csw.proveedor.persistence.entity.ProveedorEntity;

public abstract class _ProveedorConverter {


	public static ProveedorDTO entity2PersistenceDTO(ProveedorEntity entity){
		if (entity != null) {
			ProveedorDTO dto = new ProveedorDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setEmail(entity.getEmail());
				dto.setTelefono(entity.getTelefono());
				dto.setDireccion(entity.getDireccion());
				dto.setObservaciones(entity.getObservaciones());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ProveedorEntity persistenceDTO2Entity(ProveedorDTO dto){
		if(dto!=null){
			ProveedorEntity entity=new ProveedorEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setTelefono(dto.getTelefono());
			entity.setDireccion(dto.getDireccion());
			entity.setObservaciones(dto.getObservaciones());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ProveedorDTO> entity2PersistenceDTOList(List<ProveedorEntity> entities){
		List<ProveedorDTO> dtos=new ArrayList<ProveedorDTO>();
		for(ProveedorEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ProveedorEntity> persistenceDTO2EntityList(List<ProveedorDTO> dtos){
		List<ProveedorEntity> entities=new ArrayList<ProveedorEntity>();
		for(ProveedorDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}