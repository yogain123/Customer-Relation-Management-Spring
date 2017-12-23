package com.yogendra.CRM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hola
{
	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value="/addingCustomer", method=RequestMethod.POST)
	public String addingCustomer(@RequestBody String data)
	{
		System.out.println("Inside AddingCustomer "+data);
		String res=customerService.addCustomer(data);
		return res;
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
	
}
