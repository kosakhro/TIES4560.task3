package com.task3.REST;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

@Path("/reservation")
@Produces(MediaType.APPLICATION_JSON)
public class ResesrvationResource {
	ReservationService reservationService=new ReservationService(); 
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> getReservation(@PathParam("customerId") long id){
	return reservationService.getReservationsByCustomerId(id);
	}
	
	@GET
	@Path("/customer")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation getReservation(@QueryParam("ReservationId")long rId,@QueryParam("CustomerId")long cId){
	return reservationService.getReservationById(rId,cId);
	}
		
	
	@POST
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Reservation addReservation (@PathParam("customerId") long id, Reservation newReservation, @Context UriInfo uriInfo){
	 
			return ReservationService.addReservation(newReservation,id, uriInfo);
	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Reservation> upDateReservation(@QueryParam("ReservationId")long rId,@QueryParam("CustomerId")long cId,Reservation updDatedReservation){
	
	return reservationService.upDateReservation(cId,rId,updDatedReservation);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Reservation> deleteAllReservations(@QueryParam("CustomerId")long cId){
	
	return ReservationService.deleteAllreservations(cId);
	}
	
	@DELETE
	@Path("/customer")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response   cancelReservation(@QueryParam("customerId")long cId,@QueryParam("reservationId")long rId){
	
	List <Reservation> list =reservationService.cancelReservation(cId,rId);
	
	return Response.status(Status.OK)
			.entity("Reservation deleted")
			.build(); 
	}

}
