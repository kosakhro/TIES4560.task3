package com.task3.REST;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

	private Long reservation_id;
	private String Country;
	private List<Link> links = new ArrayList<>();

	
	public Reservation() {
		this.Country="";
	}
	
		
	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public Long getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
	
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
	
	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(List<Link> links) {
		this.links = links;
	}


	

	
}
