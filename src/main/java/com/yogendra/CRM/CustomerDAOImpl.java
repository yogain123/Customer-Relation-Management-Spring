package com.yogendra.CRM;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory factory;

	
	@org.springframework.transaction.annotation.Transactional
	public String addCustomer(String data) 
	{
	
		//-------------------------
		try
		{
			Session session = factory.getCurrentSession();
			Gson gson = new Gson();
			Customer c = gson.fromJson(data, Customer.class);
			session.save(c);
			//return "Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "Success";
		
	}
	
	@org.springframework.transaction.annotation.Transactional
	public String getAllCustomer() {
		List<Customer> result = new ArrayList<Customer>();
		
		Session session = factory.getCurrentSession();
		
		result = session.createQuery("from Customer").list();
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}

	@org.springframework.transaction.annotation.Transactional
	public void deletingCustomer(int id) {
		
		System.out.println("inside delete DAO");

		Session session = factory.getCurrentSession();
		Customer c = (Customer) session.get(Customer.class, id);
		System.out.println(c);
		
		session.delete(c);

	}

	@org.springframework.transaction.annotation.Transactional
	public void updatingCustomer(int id, String data) {

		System.out.println("inside Update Customer");
		Gson gson = new Gson();
		Session session = factory.getCurrentSession();
		Customer c = gson.fromJson(data, Customer.class);
		Customer databaseValue = (Customer) session.get(Customer.class, id);
		
		
		
		System.out.println("datavase value ******"+databaseValue);

		
		databaseValue.setFirstName(c.getFirstName());
		databaseValue.setLastName(c.getLastName());
		databaseValue.setEmail(c.getEmail());
	    List<Address> addresses = new ArrayList<Address>();
	    
	    Phone phone = databaseValue.getPhno();
	    
	    phone.setExtensionNumber(c.getPhno().getExtensionNumber());
	    phone.setMainNumber(c.getPhno().getMainNumber());
	    
	    databaseValue.setPhno(phone);
	    
	    addresses = databaseValue.getAddress();
	    List<Address> newAddressList = new ArrayList<Address>();
	    int count = 0 ;
	    for(Address a : addresses)
	    {
	    	System.out.println(a);
	    	a.setCity(c.getAddress().get(count).getCity());
	    	a.setCountry(c.getAddress().get(count).getCountry());
	    	
	    	newAddressList.add(a);
	    	

	    }
				
		//a.setCity(c.getAddress().getCity());
		//a.setCountry(c.getAddress().getCountry());
	    databaseValue.setAddress(newAddressList);
		
		session.update(databaseValue);
	
	}
	
}

