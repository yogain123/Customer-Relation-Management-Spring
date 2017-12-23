package com.yogendra.CRM;

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


}
