
package co.edu.uniandes.csw.proveedor.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.proveedor.logic.api.IProveedorLogicService;

@Alternative
@Singleton
public class ProveedorMockLogicService extends _ProveedorMockLogicService implements IProveedorLogicService {
	
}