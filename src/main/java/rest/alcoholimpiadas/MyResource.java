
package rest.alcoholimpiadas;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("myresource")
public class MyResource {
    
	SecurityManager sm = new SecurityManager();
	
    @GET 
    @Produces("application/xml")
    public ArrayList<User> getIt() throws Exception  {
    	System.out.print("llamando users");
        return sm.getAllUsersList() ;
    }
}
