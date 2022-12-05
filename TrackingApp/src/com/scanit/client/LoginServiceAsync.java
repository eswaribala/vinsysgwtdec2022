package com.scanit.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void showMessage(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
