
package co.edu.uniandes.csw.proveedor.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.proveedor.logic.dto.ProveedorDTO;
import co.edu.uniandes.csw.proveedor.logic.api.IProveedorLogicService;
import co.edu.uniandes.csw.proveedor.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.proveedor.persistence.api.IProveedorPersistence;
import co.edu.uniandes.csw.proveedor.persistence.entity.ProveedorEntity;

@RunWith(Arquillian.class)
public class ProveedorLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProveedorLogicService.class.getPackage())
				.addPackage(ProveedorPersistence.class.getPackage())
				.addPackage(ProveedorEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProveedorLogicService proveedorLogicService;
	
	@Inject
	private IProveedorPersistence proveedorPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<ProveedorDTO> dtos=proveedorPersistence.getProveedors();
		for(ProveedorDTO dto:dtos){
			proveedorPersistence.deleteProveedor(dto.getId());
		}
	}

	private List<ProveedorDTO> data=new ArrayList<ProveedorDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProveedorDTO pdto=new ProveedorDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setEmail(generateRandom(String.class));
			pdto.setTelefono(generateRandom(String.class));
			pdto.setDireccion(generateRandom(String.class));
			pdto.setObservaciones(generateRandom(String.class));
			pdto=proveedorPersistence.createProveedor(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createProveedorTest(){
		ProveedorDTO ldto=new ProveedorDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setEmail(generateRandom(String.class));
		ldto.setTelefono(generateRandom(String.class));
		ldto.setDireccion(generateRandom(String.class));
		ldto.setObservaciones(generateRandom(String.class));
		
		
		ProveedorDTO result=proveedorLogicService.createProveedor(ldto);
		
		Assert.assertNotNull(result);
		
		ProveedorDTO pdto=proveedorPersistence.getProveedor(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getEmail(), pdto.getEmail());	
		Assert.assertEquals(ldto.getTelefono(), pdto.getTelefono());	
		Assert.assertEquals(ldto.getDireccion(), pdto.getDireccion());	
		Assert.assertEquals(ldto.getObservaciones(), pdto.getObservaciones());	
	}
	
	@Test
	public void getProveedorsTest(){
		List<ProveedorDTO> list=proveedorLogicService.getProveedors();
		Assert.assertEquals(list.size(), data.size());
        for(ProveedorDTO ldto:list){
        	boolean found=false;
            for(ProveedorDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProveedorTest(){
		ProveedorDTO pdto=data.get(0);
		ProveedorDTO ldto=proveedorLogicService.getProveedor(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getEmail(), ldto.getEmail());
		Assert.assertEquals(pdto.getTelefono(), ldto.getTelefono());
		Assert.assertEquals(pdto.getDireccion(), ldto.getDireccion());
		Assert.assertEquals(pdto.getObservaciones(), ldto.getObservaciones());
        
	}
	
	@Test
	public void deleteProveedorTest(){
		ProveedorDTO pdto=data.get(0);
		proveedorLogicService.deleteProveedor(pdto.getId());
        ProveedorDTO deleted=proveedorPersistence.getProveedor(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProveedorTest(){
		ProveedorDTO pdto=data.get(0);
		
		ProveedorDTO ldto=new ProveedorDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setEmail(generateRandom(String.class));
		ldto.setTelefono(generateRandom(String.class));
		ldto.setDireccion(generateRandom(String.class));
		ldto.setObservaciones(generateRandom(String.class));
		
		
		proveedorLogicService.updateProveedor(ldto);
		
		
		ProveedorDTO resp=proveedorPersistence.getProveedor(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getEmail(), resp.getEmail());	
		Assert.assertEquals(ldto.getTelefono(), resp.getTelefono());	
		Assert.assertEquals(ldto.getDireccion(), resp.getDireccion());	
		Assert.assertEquals(ldto.getObservaciones(), resp.getObservaciones());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}