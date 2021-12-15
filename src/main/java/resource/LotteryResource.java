package resource;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/draw")
@Singleton
public class LotteryResource {

    @GET
    public Response connectionTest() {
        return Response.ok("All good").build();
    }
}
