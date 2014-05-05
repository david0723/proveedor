package co.edu.uniandes.csw.proveedor.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.proveedor.logic.api.IProveedorLogicService;
import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;


public abstract class _ProveedorService {

	@Inject
	protected IProveedorLogicService proveedorLogicService;
	
	@POST
	public ProveedorDTO createProveedor(ProveedorDTO proveedor){
		return proveedorLogicService.createProveedor(proveedor);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteProveedor(@PathParam("id") Long id){
		proveedorLogicService.deleteProveedor(id);
	}
	
	@GET
	public List<ProveedorDTO> getProveedors(){
		return proveedorLogicService.getProveedors();
	}
	
	@GET
	@Path("{id}")
	public ProveedorDTO getProveedor(@PathParam("id") Long id){
		return proveedorLogicService.getProveedor(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateProveedor(@PathParam("id") Long id, ProveedorDTO proveedor){
		proveedorLogicService.updateProveedor(proveedor);
	}
	
}