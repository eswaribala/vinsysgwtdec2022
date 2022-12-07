package com.scanit.server.dao;

import java.sql.SQLException;

import com.scanit.server.models.Customer;

public interface CustomerDao {
	
	Customer addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	
	Customer getCustomerById(long mobileNo);
	
	Customer getCustomerByUserNamePassword(String userName,String password);

}
