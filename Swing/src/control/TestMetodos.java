package control;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class TestMetodos {
	static Metodos method = new Metodos ();
	@Test
	public void checkUsername() {
		boolean check=method.checkUsername("jon", "123");
		
        assertEquals(true, check);
        
	}
	
	@Test
	public void checkId() {
		boolean check=method.checkId("jon", "123");
		//System.out.println(pepe.getCanti());
        assertEquals(true, check);
        
	}
	@Test
	public void deleteUser() {
		method.crearUsuario("Test", 1, "PassTest");
		int idTest=method.getId("Test", "PassTest");
		method.deleteUser(idTest);
		boolean check=method.checkUsername("Test", "PassTest");
		//System.out.println(pepe.getCanti());
        assertEquals(false, check);
        
	}
	@Test
	public void updateUser() {
		
		
		method.crearUsuario("Test", 1, "PassTest");
		int idTest=method.getId("Test", "PassTest");
		User u = new User(idTest, "Testupdate",1,"PassTest12345");
		method.updateUser(u, idTest);
		boolean check=method.checkUsername("Testupdate", "PassTest12345");
	
		
		//System.out.println(pepe.getCanti());
        assertEquals(true, check);
        
	}
	

}
