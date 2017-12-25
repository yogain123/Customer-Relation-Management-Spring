package com.yogendra.CRM.Controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yogendra.CRM.Services.CustomerDAOImpl;
import com.yogendra.CRM.Services.CustomerService;
import com.yogendra.CRM.Services.ServiceProvider;

@RestController
public class hola
{
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ServiceProvider service;
	
	
	@RequestMapping(value="/addingCustomer", method=RequestMethod.POST)
	public String addingCustomer(@RequestBody String data)
	{
		System.out.println("Inside AddingCustomer "+data);
		String res=customerService.addCustomer(data);
		
		String uri = "http://localhost:8086/CRM/gettingAllCustomer";
		String result = service.restCall(uri); // GEtting All Customer
		System.out.println("something");
		return result;
	}
	
	
	@RequestMapping(value="/gettingAllCustomer",method=RequestMethod.GET)
	public String gettingAllData()
	{
		String data = customerService.getAllCustomer();
		return data;
	}
	
	@RequestMapping(value="/deletingCustomer/{id}",method=RequestMethod.DELETE)
	public void deletingCustomer(@PathVariable int id)
	{
		
		System.out.println(" inside delete "+id);
		customerService.deletingCustomer(id);
	}
	
	@RequestMapping(value="/updatingCustomer/{id}",method=RequestMethod.PUT)
	public void updatingCustomer(@RequestBody String data, @PathVariable int id)
	{
		
		System.out.println(" inside update "+id);
		customerService.updatingCustomer(id, data);
	}
	
	@RequestMapping(value="/gettingSearchedCustomer/{id}",method=RequestMethod.GET)
	public String gettingSearchedCustomer(@PathVariable int id)
	{
		
		System.out.println(" inside gettingSearchedCustomer "+id);
		String result = customerService.gettingSearchedCustomer(id);
		
		return result;
	}
	
	@RequestMapping(value="/gettingSearchedCustomerWithName/{firstName}",method=RequestMethod.GET)
	public String gettingSearchedCustomerWithName(@PathVariable String firstName)
	{
		
		System.out.println(" inside gettingSearchedCustomerWithName "+firstName);
		String result = customerService.gettingSearchedCustomerWithName(firstName);
		
		return result;
	}
	
	
	@RequestMapping(value="/file",method=RequestMethod.POST)
	public String postingFile(@RequestBody String fileData)
	{
		//String ss = Base64.getEncoder().encodeToString(fileData.getBytes());
		byte[] b = fileData.getBytes(StandardCharsets.UTF_8); // Java 7+ only

		
		//System.out.println(" inside postingFile "+Arrays.toString(b) + "****");
		//String result = customerService.gettingSearchedCustomerWithName(firstName);
		CustomerDAOImpl cc = new CustomerDAOImpl();
		customerService.savingFile(b);
		
		return "success";
	}
	

	
}
