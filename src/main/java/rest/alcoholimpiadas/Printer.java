
package rest.alcoholimpiadas;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("users")
public class Printer {

	@GET
	@Produces("application/xml")
	public ArrayList<User> getIt() throws Exception {
		System.out.print("llamando users");
		return UserManager.getAllUsersList();
	}
}
