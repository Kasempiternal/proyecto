package demo.rest;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserResource {
	
	UserRepository repo = new UserRepository();

	
	@GET
	@Path("getalluser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		
		System.out.println("getuser llamado..");
		System.out.println(repo.getUsers());
		
		
		
		return repo.getUsers();
	}
	@POST
	@Path("user")
	public User createUser(User us) {
		System.out.println("user llamado..");
		System.out.println(us);
		
		repo.createUser(us);
		
		return us;
	}
	
	@GET
	@Path("users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@javax.ws.rs.PathParam("id")int id) {
		
		System.out.println("getuser txiripiti..");
		System.out.println("getuser ..");
		
		
		
		return repo.getUser(id);
	}
	
	

}

