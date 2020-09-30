package com.task3.REST;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;




//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

@XmlRootElement
public class Customer  {
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)*/
	private Long customer_id;
    
    public String name;
	public String email;
	private List<Link> links = new ArrayList<>();
	
	public Customer() {
		this.name="";
		this.email="";
	}
	
	public void setCustomer_id(Long  customer_id) {
		this.customer_id = customer_id;
	}
	public Customer(String full_Name, String e_mail) {
		this.name=full_Name;
		this.email=e_mail;
	}
	
	public Long getCustomer_id() {
		return customer_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		return true;
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
