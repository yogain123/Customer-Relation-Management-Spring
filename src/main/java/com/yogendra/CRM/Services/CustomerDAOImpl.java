package com.yogendra.CRM.Services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.yogendra.CRM.POJO.Address;
import com.yogendra.CRM.POJO.Customer;
import com.yogendra.CRM.POJO.LocationFinder;
import com.yogendra.CRM.POJO.Phone;
import com.yogendra.CRM.POJO.Student;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory factory;
	
	@Autowired 
	ServiceProvider service;

	
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
			
			//------------------------------------------------------------
			//Just for Checking External API call from JAVA---------------
			String uri = "http://ip-api.com/json/208.80.152.201";
			String LocationJson = service.restCall(uri);  // Gettong All API Details
			LocationFinder location = gson.fromJson(LocationJson, LocationFinder.class);
			session.save(location);
			//------------------------------------------------------------
			
			
			//------------------------------------------------------------
			//Just for Checking @ManyToMany RelationShip
			String student_course_json = "{\"firstName\" : \"yogendra\", \"lastName\" : \"Saxena\", \"course\" : [{\"courseName\":\"Java\",\"courseAuthorName\":\"Chad\"},{\"courseName\":\"NodeJS\",\"courseAuthorName\":\"Bucky\"}]}";
			Student s = gson.fromJson(student_course_json, Student.class);
			session.save(s);
			
			//------------------------------------------------------------

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
	    System.out.println("Nulling");
	    System.out.println(phone.getMainNumber());

	    
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

	@org.springframework.transaction.annotation.Transactional
	public String gettingSearchedCustomer(int id) {
		System.out.println("Inside gettingSearchedCustomer");
		String result="";
		Session session = factory.getCurrentSession();
		
		Gson gson = new Gson();

		Customer customer = (Customer) session.get(Customer.class, id);
		result = gson.toJson(customer);
		System.out.println(customer);
		return result;
	}
	
	
	@org.springframework.transaction.annotation.Transactional
	public String gettingSearchedCustomerWithName(String firstName) {
		
		System.out.println("Inside gettingSearchedCustomerWithName");
		Session session = factory.getCurrentSession();
		Gson gson = new Gson();
		List<Customer> customersList = new ArrayList<Customer>();
		
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		customersList = (List<Customer>) criteria.add(Restrictions.eq("firstName", firstName)).list();
		
		String result = gson.toJson(customersList);
		System.out.println(result);
		return result;
	}

	
}

