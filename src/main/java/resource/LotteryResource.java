package resource;

import model.LotteryReport;
import model.WinNumbers;
import model.Ticket;
import service.LotteryService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class LotteryResource {

    @Inject
    LotteryService service;

    @GET
    @Path("/win-numbers")
    public WinNumbers getWinNumbers() {
        return service.getWinningNumbers();
    }

    @GET
    @Path("/ticket")
    public Ticket getTicket(){
    return service.getCurrentTicket();
    }

    @GET
    @Path("/report")
    public LotteryReport report(){
        return service.getReport();
    }

    @POST
    @Path("/add")
    public Response postTicket(Ticket request){
        service.addTicket(request);
        return Response.ok().build();
    }
}