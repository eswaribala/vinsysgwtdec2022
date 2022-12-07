package com.scanit.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scanit.client.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public String validateUser(String userName, String password) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if((userName!=null)&&(password!=null)) {
			
			if((userName.length()>0)&&(password.length()>0)){
				
				return "User Authenticated";
			}
		}
		return "User Not Authenticated";
	}

}
