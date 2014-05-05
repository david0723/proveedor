
package co.edu.uniandes.csw.proveedor.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.proveedor.persistence.api.IProveedorPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ProveedorPersistence extends _ProveedorPersistence  implements IProveedorPersistence {

}