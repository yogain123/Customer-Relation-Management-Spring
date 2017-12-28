package com.yogendra.CRM.Controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public void postingFile(@RequestBody  String fileData)
	{
		//System.out.println(fileData);
		//System.out.println(fileData.getBytes());
		//String encodedFile = Base64.getEncoder().encodeToString(fileData.getBytes("UTF-8"));
		//System.out.println("HOLAAA1 "+encodedFile);
		//byte[] decodedStr = Base64.getDecoder().decode( encodedFile );
		//System.out.println( new String( decodedStr, "utf-8" ) );
		
		//customerService.savingFile(fileData);
		System.out.println(fileData);
		customerService.savingFile(fileData);
	}
	
	@RequestMapping(value="/searchImageWithName/{imageName}",method=RequestMethod.GET)
	public String searchImageWithName(@PathVariable  String imageName)
	{
		System.out.println(imageName);
		imageName = imageName+".jpg";
		String result = customerService.searchImageWithName(imageName);
		System.out.println("hola "+result);
		return result;
	}
	

	
}
