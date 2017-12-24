package com.yogendra.CRM.Services;

public interface CustomerService {
	
	public String addCustomer(String data);
	
	public String getAllCustomer();
	
	public void deletingCustomer(int id);

	public void updatingCustomer(int id, String data);

	


}
