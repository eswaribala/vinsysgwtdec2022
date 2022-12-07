package com.scanit.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

	String validateUser(String userName,String password) throws IllegalArgumentException;
}
