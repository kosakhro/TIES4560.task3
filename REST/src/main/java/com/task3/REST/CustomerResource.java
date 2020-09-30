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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;


@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
	CustomerService customerService = new CustomerService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomers(){
	return customerService.getAllCustomers();
	}
	
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") long  id){
	Customer required_customer = CustomerService.getCustomerById(id);

	return required_customer;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer addCustomer (Customer customer, @Context UriInfo uriInfo){
	return CustomerService.addCustomer(customer, uriInfo);
	}
	
	@PUT
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	/*Todo 
	 * This put method should check if the id in path
	 * same as in the Jeson body and rejected if not.
	 * */
	public Customer updateCustomer(@PathParam("customerId") long id, Customer outDatedCustomer){
	Customer upDatedCustomer=customerService.upDateCutomer(outDatedCustomer, id);

	return upDatedCustomer;
	}
	

    @DELETE
	@Path("/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	/*Todo 
	 * This put method should check if the id in path
	 * same as in the Jeson body and rejected if not.
	 * */
	public Customer removeCustomer(@PathParam("customerId") long id){
	Customer deletedCustomer=customerService.deleteCustomer(id);
	return deletedCustomer;
	}
}	
	

