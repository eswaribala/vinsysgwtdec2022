package com.scanit.server.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scanit.server.helper.MySQLHelper;
import com.scanit.server.models.Customer;

public class CustomerImpl implements CustomerDao{

	private static Connection conn;
	private PreparedStatement pre;
	private ResultSet rs;
	
	@Override
	public Customer addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		conn=MySQLHelper.getConnection();
		pre=conn.prepareStatement("insert into customer(UserName,Password,"
				+ "Email,DOB,MobileNo,Gender)values(?,?,?,?,?,?) ");
		
		pre.setString(1, customer.getUserName());
		pre.setString(2, customer.getPassword());
		pre.setString(3,  customer.getEmail());
		pre.setDate(4, new Date(customer.getDob().getYear(),
				customer.getDob().getMonth(),customer.getDob().getDate()));
		pre.setLong(5, customer.getMobileNo());
		pre.setString(6, customer.getGender().toString());
		int row=pre.executeUpdate();	
		if(row>0)
			return customer;
		else				
		    return null;
	}

	@Override
	public Customer getCustomerById(long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCustomerByUserNamePassword(String userName, String password) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		conn=MySQLHelper.getConnection();
		pre=conn.prepareStatement("select count(*) as rowcount from customer where UserName=? and Password = ?");
		pre.setString(1, userName);
        pre.setString(2, password);
        ResultSet rs= pre.executeQuery();
		rs.next();
		return rs.getInt("rowcount");
	
	}

}
