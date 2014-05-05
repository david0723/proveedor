
package co.edu.uniandes.csw.proveedor.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.proveedor.logic.api.IProveedorLogicService;

@Default
@Stateless
@LocalBean
public class ProveedorLogicService extends _ProveedorLogicService implements IProveedorLogicService {

}