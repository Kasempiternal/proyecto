package rest.alcoholimpiadas;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserManager {

	UserArray repo = new UserArray();

	@GET
	@Produces(MediaType.APPLICATION_XML)
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
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@javax.ws.rs.PathParam("id") int id) {

		System.out.println("getuser ..");

		return repo.getUser(id);
	}

}
