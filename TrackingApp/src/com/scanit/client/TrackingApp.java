package com.scanit.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.scanit.server.LoginServiceImpl;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TrackingApp implements EntryPoint,ClickHandler {
	
	private LoginServiceAsync loginServiceAsync=GWT.create(LoginServiceImpl.class);
	private Button sendButton;
	private TextBox nameField;
	
	public void onModuleLoad() {
		sendButton = new Button("Send");
		TextBox nameField = new TextBox();
		nameField.setText("Scan IT  User");		

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		sendButton.addClickHandler(this);
		
		/* client side call
		sendButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Welcome "+nameField.getText()+"!!!");
				 
			}
			
		});
       */
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
	
		
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		loginServiceAsync.showMessage(nameField.getText(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Error occurred "+caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Window.alert("Welcome "+nameField.getText()+"!!!");
			}
			
		});
	}
}

