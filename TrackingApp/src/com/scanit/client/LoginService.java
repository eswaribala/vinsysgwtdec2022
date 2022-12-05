package com.scanit.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("welcome")
public interface LoginService extends RemoteService{

	String showMessage(String name)throws IllegalArgumentException;
}
