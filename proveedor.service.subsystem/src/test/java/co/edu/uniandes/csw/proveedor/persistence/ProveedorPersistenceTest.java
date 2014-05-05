
package co.edu.uniandes.csw.proveedor.persistence;

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
import co.edu.uniandes.csw.proveedor.persistence.api.IProveedorPersistence;
import co.edu.uniandes.csw.proveedor.persistence.entity.ProveedorEntity;

@RunWith(Arquillian.class)
public class ProveedorPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ProveedorPersistence.class.getPackage())
				.addPackage(ProveedorEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IProveedorPersistence proveedorPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from ProveedorEntity").executeUpdate();
	}

	private List<ProveedorEntity> data=new ArrayList<ProveedorEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ProveedorEntity entity=new ProveedorEntity();
			entity.setName(generateRandom(String.class));
			entity.setEmail(generateRandom(String.class));
			entity.setTelefono(generateRandom(String.class));
			entity.setDireccion(generateRandom(String.class));
			entity.setObservaciones(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createProveedorTest(){
		ProveedorDTO dto=new ProveedorDTO();
		dto.setName(generateRandom(String.class));
		dto.setEmail(generateRandom(String.class));
		dto.setTelefono(generateRandom(String.class));
		dto.setDireccion(generateRandom(String.class));
		dto.setObservaciones(generateRandom(String.class));
		
		
		ProveedorDTO result=proveedorPersistence.createProveedor(dto);
		
		Assert.assertNotNull(result);
		
		ProveedorEntity entity=em.find(ProveedorEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getEmail(), entity.getEmail());	
		Assert.assertEquals(dto.getTelefono(), entity.getTelefono());	
		Assert.assertEquals(dto.getDireccion(), entity.getDireccion());	
		Assert.assertEquals(dto.getObservaciones(), entity.getObservaciones());	
	}
	
	@Test
	public void getProveedorsTest(){
		List<ProveedorDTO> list=proveedorPersistence.getProveedors();
		Assert.assertEquals(list.size(), data.size());
        for(ProveedorDTO dto:list){
        	boolean found=false;
            for(ProveedorEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getProveedorTest(){
		ProveedorEntity entity=data.get(0);
		ProveedorDTO dto=proveedorPersistence.getProveedor(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getEmail(), dto.getEmail());
		Assert.assertEquals(entity.getTelefono(), dto.getTelefono());
		Assert.assertEquals(entity.getDireccion(), dto.getDireccion());
		Assert.assertEquals(entity.getObservaciones(), dto.getObservaciones());
        
	}
	
	@Test
	public void deleteProveedorTest(){
		ProveedorEntity entity=data.get(0);
		proveedorPersistence.deleteProveedor(entity.getId());
        ProveedorEntity deleted=em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateProveedorTest(){
		ProveedorEntity entity=data.get(0);
		
		ProveedorDTO dto=new ProveedorDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setEmail(generateRandom(String.class));
		dto.setTelefono(generateRandom(String.class));
		dto.setDireccion(generateRandom(String.class));
		dto.setObservaciones(generateRandom(String.class));
		
		
		proveedorPersistence.updateProveedor(dto);
		
		
		ProveedorEntity resp=em.find(ProveedorEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getEmail(), resp.getEmail());	
		Assert.assertEquals(dto.getTelefono(), resp.getTelefono());	
		Assert.assertEquals(dto.getDireccion(), resp.getDireccion());	
		Assert.assertEquals(dto.getObservaciones(), resp.getObservaciones());	
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