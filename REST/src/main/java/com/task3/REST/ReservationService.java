package com.task3.REST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public class ReservationService {
	private static Map<Customer, List<Reservation>> reservations = (Map<Customer, List<Reservation>>) DBclass
			.getReservations();
	static Long reservationCounter = DBclass.getReservationC();

	/*
	 * public ReservationService() { ReservationService.reservationCounter=(long)
	 * 0;// This could guarantee that each customer has uniqe id }
	 */

	public List<Reservation> getReservationsByCustomerId(long customer_id) {
		Customer customer = CustomerService.getCustomerById(customer_id);
		return reservations.get(customer);
	}

	public static Reservation addReservation(Reservation newReservation, long customer_id, @Context UriInfo uriInfo) {
		Customer customer = CustomerService.getCustomerById(customer_id);
		newReservation.setReservation_id(reservationCounter);
		reservationCounter = reservationCounter + 1;
		if (reservations.get(customer) == null) {
			List<Reservation> listOfReservation = new ArrayList<Reservation>();
			listOfReservation.add(newReservation);
			reservations.put(customer, listOfReservation);
		} else {

			List<Reservation> ListOfReservation = reservations.get(customer);
			ListOfReservation.add(newReservation);
			reservations.put(customer, ListOfReservation);//// ????? I still need add the new reservation
		}
		
		String uri = uriInfo.getBaseUriBuilder()
				.path(ResesrvationResource.class)
				.path(Long.toString(newReservation.getReservation_id()))
				.build()
				.toString();
			
		newReservation.addLink(uri, "self");

		return newReservation;
	}

	public Reservation getReservationById(long reservation_id, long customerId) {
		Customer customer = CustomerService.getCustomerById(customerId);
		List<Reservation> customer_reservations = reservations.get(customer);

		for (int i = 0; i < customer_reservations.size(); i++) {
			Reservation reserv = customer_reservations.get(i);
			long reserv_id = reserv.getReservation_id();
			if (reserv_id == reservation_id) {
				return reserv;
			}
		}

		return null;
	}

	public List<Reservation> upDateReservation(long customerId, long reservationId, Reservation modifyreservation) {
		Customer customer = CustomerService.getCustomerById(customerId);
		List<Reservation> reservationsList = reservations.get(customer);
		modifyreservation.setReservation_id(reservationId);

		for (int i = 0; i < reservationsList.size(); i++) {
			if (reservationId == (reservationsList.get(i).getReservation_id())) {
				reservationsList.set(i, modifyreservation);
			}

		}
		reservations.put(customer, reservationsList);

		return reservations.get(customer);
	}
	
	public static List<Reservation> deleteAllreservations(long customerId){
		Customer customer = CustomerService.getCustomerById(customerId);
		return reservations.remove(customer);
		
	}
	
	public List<Reservation>  cancelReservation(long cId, long rId) {
		Customer customer=CustomerService.getCustomerById(cId);
		List<Reservation> reservationsList = reservations.get(customer);
		
        int index = -1;
		for (int i = 0; i < reservationsList.size(); i++) {
			if (rId == (reservationsList.get(i).getReservation_id())){
				index =i;
			}
		}
		if (index!=-1) {
		reservationsList.remove(index);
		}
		reservations.put(customer, reservationsList);
		return reservations.get(customer);
	}

}
