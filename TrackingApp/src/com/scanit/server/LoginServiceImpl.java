package com.scanit.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scanit.client.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public String showMessage(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return "Welcome"+name+"!!!";
	}

}
