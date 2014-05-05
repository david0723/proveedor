
package co.edu.uniandes.csw.proveedor.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;
import co.edu.uniandes.csw.proveedor.persistence.api._IProveedorPersistence;
import co.edu.uniandes.csw.proveedor.persistence.converter.ProveedorConverter;
import co.edu.uniandes.csw.proveedor.persistence.entity.ProveedorEntity;

public abstract class _ProveedorPersistence implements _IProveedorPersistence {

	@PersistenceContext(unitName="ProveedorPU")
	protected EntityManager entityManager;
	
	public ProveedorDTO createProveedor(ProveedorDTO proveedor) {
		ProveedorEntity entity=ProveedorConverter.persistenceDTO2Entity(proveedor);
		entityManager.persist(entity);
		return ProveedorConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ProveedorDTO> getProveedors() {
		Query q = entityManager.createQuery("select u from ProveedorEntity u");
		return ProveedorConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ProveedorDTO getProveedor(Long id) {
		return ProveedorConverter.entity2PersistenceDTO(entityManager.find(ProveedorEntity.class, id));
	}

	public void deleteProveedor(Long id) {
		ProveedorEntity entity=entityManager.find(ProveedorEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateProveedor(ProveedorDTO detail) {
		ProveedorEntity entity=entityManager.merge(ProveedorConverter.persistenceDTO2Entity(detail));
		ProveedorConverter.entity2PersistenceDTO(entity);
	}

}