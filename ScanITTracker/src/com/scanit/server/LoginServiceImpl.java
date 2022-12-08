package com.scanit.server;

import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scanit.client.LoginService;
import com.scanit.server.dao.CustomerDao;
import com.scanit.server.dao.CustomerImpl;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public String validateUser(String userName, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub		
		CustomerDao customerDao=new CustomerImpl();
		int result=0;
		
		if((userName!=null)&&(password!=null)) {
			
			if((userName.length()>0)&&(password.length()>0)){
				
				try {
					result=customerDao.getCustomerByUserNamePassword(userName, password);
					if(result>0)
						  return "User Authenticated";
						else
							return "User Name or Password invalid";
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				
			}
			else
				return "User Name or Password invalid";
		}
		return "User Not Authenticated";
	}

}
