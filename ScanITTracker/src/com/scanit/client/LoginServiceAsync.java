package com.scanit.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void validateUser(String userName,String password,AsyncCallback<String> callback) throws IllegalArgumentException;
}
