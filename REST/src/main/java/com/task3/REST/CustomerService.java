package com.task3.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


public class CustomerService {

	private static Map<Long, Customer> customers = (Map<Long, Customer>) DBclass.getCustomers();
	static Long customersCounter = DBclass.getCustomersC();
	/*
	public CustomerService() {
		CustomerService.customersCounter=(long) 0;// This could guarantee that each customer has uniqe id
	}*/

	public static Customer addCustomer(Customer newCustomer, @Context UriInfo uriInfo) {
		   newCustomer.setCustomer_id(customersCounter);
		   customersCounter=customersCounter+1;
		   customers.put(newCustomer.getCustomer_id(), newCustomer);
		   
			String uri = uriInfo.getBaseUriBuilder()
					.path(CustomerResource.class)
					.path(Long.toString(newCustomer.getCustomer_id()))
					.build()
					.toString();
				
			newCustomer.addLink(uri, "self");
		   
		return newCustomer;
	}
	
	
	
	
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(customers.values());
	}
	
   public static Customer getCustomerById(long id) {
	   
	   Customer foundedCustomer=customers.get(id);
	   ErrorMessage errorMessage = new ErrorMessage("Customer #"+id+"  not found.", 404, "http://myDocs.org");
	   Response response = Response.status(Status.NOT_FOUND)
	   .entity(errorMessage)
	   .build();
		if (foundedCustomer == null ) {
			throw new WebApplicationException(response);
			}	
	      
	return foundedCustomer;
	   
   }
   
   public Customer upDateCutomer(Customer modifyCustomer, long id) {
	   ErrorMessage errorMessage = new ErrorMessage("Customer #"+id+"  not found.", 404, "http://myDocs.org");
	   Response response = Response.status(Status.NOT_FOUND)
	   .entity(errorMessage)
	   .build();
		if (modifyCustomer == null ) {
			throw new WebApplicationException(response);
			}
	   
	   
	   modifyCustomer.setCustomer_id(id);


	   
	   customers.put(modifyCustomer.getCustomer_id(), modifyCustomer);
	   return  modifyCustomer;
	   
	   
   }
   
   public Customer deleteCustomer(long id) {
	    ReservationService.deleteAllreservations(id);
	   return customers.remove(id);
	   
   }

}
