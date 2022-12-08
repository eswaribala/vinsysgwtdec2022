package com.scanit.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.core.client.GWT;
import com.ibm.icu.text.SimpleDateFormat;
import com.scanit.server.dao.CustomerDao;
import com.scanit.server.dao.CustomerImpl;
import com.scanit.server.models.Customer;
import com.scanit.server.models.Gender;

public class RegistrationServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		
		Enumeration<String> enumeratedData=req.getParameterNames();
		
		
	   Customer customer=new Customer();
		
       customer.setUserName(req.getParameter("userName"));
		
	   customer.setPassword(req.getParameter("password"));    
			
	   customer.setEmail(req.getParameter("email"));      
			
	   customer.setMobileNo(Long.parseLong(req.getParameter
						   ("mobileNo")));  
		      
		customer.setGender(Enum.valueOf(Gender.class, req.getParameter("rbLbl")));
		SimpleDateFormat sf =new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			customer.setDob(sf.parse(req.getParameter("datePickerLbl")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/Html");
		String message=null;		
		
		CustomerDao customerDao=new CustomerImpl();
		try {
			customerDao.addCustomer(customer);
			message="Customer Created";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			message=e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			message=e.getMessage();
		}	
					
		out.println(message);
	
	}
	
	

}
