package com.yogendra.CRM.Services;

public interface CustomerDAO {
	
	public String addCustomer(String data);
	
	public String getAllCustomer();
	
	public void deletingCustomer(int id);
	
	public void updatingCustomer(int id, String data);
	
	public String gettingSearchedCustomer(int id);
	
	public String gettingSearchedCustomerWithName(String firstName);
	
	public void savingFile(String s);
	
	public String searchImageWithName(String imageName);


}
