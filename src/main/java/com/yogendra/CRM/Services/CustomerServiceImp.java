package com.yogendra.CRM.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;
	public String addCustomer(String data) 
	{
	    String res =  customerDAO.addCustomer(data);	
	    return res;
	}
	public String getAllCustomer() {
		String res = customerDAO.getAllCustomer();
		return res;
	}
	public void deletingCustomer(int id) {
			customerDAO.deletingCustomer(id);
	}
	
	public void updatingCustomer(int id, String data) {
		customerDAO.updatingCustomer(id, data);
	}
	public String gettingSearchedCustomer(int id) {
		String result = customerDAO.gettingSearchedCustomer(id);
		return result;
	}
	public String gettingSearchedCustomerWithName(String firstName) {

		String result = customerDAO.gettingSearchedCustomerWithName(firstName);
		return result;
	}
	public void savingFile(byte[] file) {
		// TODO Auto-generated method stub
		
		customerDAO.savingFile(file);
	}


}
