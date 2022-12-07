package com.scanit.client;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScanITTracker implements EntryPoint,ClickHandler {
	
	private TextBox userNameTextBox,emailTextBox,mobileNoTextBox;
	private PasswordTextBox passwordTextBox;
	private Button loginButton,regButton;
	private DialogBox dialogBox;
	private VerticalPanel dialogVPanel;
	private VerticalPanel loginPanel,regPanel;
	private Button closeButton;
	private Label resultLabel,userNameLbl,passwordLbl,advLbl,emailLbl,mobileNoLbl,dobLbl,genderLbl;
	private Image image;
	private Grid loginGrid,regGrid;
	private DatePicker datePicker;
	private RadioButton maleButton,femaleButton;
	private FormPanel regFormPanel;
	private static List<String> messages=new ArrayList<String>();
	
	static {
		messages.add("Scan IT is India's Top 10 Company");
		messages.add("GWT Training is on");
		messages.add("Freshers getting trained on GWT");
		messages.add("Happy to Learn");
	}
	
	
	private String getMessage() {
		return messages.get(new Random().nextInt(messages.size()-1));
	}
	
	
	private final LoginServiceAsync loginServiceAsync=GWT.create(LoginService.class);
 	
	public void onModuleLoad() {
		userNameLbl=new Label();
		userNameLbl.setText("User Name");
		passwordLbl=new Label();
		passwordLbl.setText("Password");
		
		userNameTextBox=new TextBox();
		userNameTextBox.setPixelSize(150, 20);
		passwordTextBox=new PasswordTextBox();
		passwordTextBox.setPixelSize(150, 20);
		loginButton=new Button();
		loginButton.setText("Login");
		loginButton.setStyleName("sendButton");
		loginButton.addClickHandler(this);
		
		regButton=new Button();
		regButton.setText("Signup");
		regButton.setStyleName("regButton");
		regButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				loginPanel.clear();	
				
				
				RootPanel.get("loginContainer").add(createRegPanel());
				
			}
			
		});
		
		
		image=new Image();
		String path="/images/logo.jpeg";
		image.setUrl(path);
		image.setPixelSize(100, 100);
		
		loginGrid=new Grid(3,2);
		loginGrid.setWidget(0, 0, userNameLbl);
		loginGrid.setWidget(0, 1, userNameTextBox);
		loginGrid.setWidget(1, 0, passwordLbl);
		loginGrid.setWidget(1, 1, passwordTextBox);
		loginGrid.setWidget(2, 1, loginButton);
		loginGrid.setWidget(2, 0, regButton);
		
		
		loginPanel=new VerticalPanel();
		loginPanel.add(loginGrid);
       
        advLbl=new Label();
        advLbl.setText("We are ScanIT");
        advLbl.setStyleName("adv");
		Timer timer=new Timer() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				advLbl.setText(getMessage());
				}
			
		};
		
		timer.scheduleRepeating(2000);	
		
		
		RootPanel.get("logoContainer").add(image);
		RootPanel.get("advContainer").add(advLbl);
        RootPanel.get("loginContainer").add(loginPanel);
       
        
		
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		loginServiceAsync.validateUser(userNameTextBox.getText(), passwordTextBox.getText(), 
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert(caught.getMessage());
						
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
				
						//Window.alert(result);
						
						dialogBox=new DialogBox();
						dialogBox.setAnimationEnabled(true);
						dialogBox.setText("Login");
						closeButton=new Button();
						closeButton.setText("OK");
						closeButton.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								dialogBox.hide();
							}
							
						});						
						
						resultLabel=new Label();
						resultLabel.setText(result);
						dialogVPanel=new VerticalPanel();
						dialogVPanel.add(resultLabel);
						dialogVPanel.add(closeButton);
						dialogBox.setWidget(dialogVPanel);
						dialogBox.setPopupPosition(500, 50);
						dialogBox.show();
						
						
					}
			
		});
	}
	
	
	public FormPanel createRegPanel() {
		
		userNameTextBox=new TextBox();
		userNameTextBox.setName("userName");
		userNameTextBox.setPixelSize(175, 20);
		passwordTextBox=new PasswordTextBox();
		passwordTextBox.setName("password");
		passwordTextBox.setPixelSize(175, 20);
		emailTextBox=new TextBox();
		emailTextBox.setName("email");
		emailTextBox.setPixelSize(175, 20);
		mobileNoTextBox=new TextBox();
		mobileNoTextBox.setName("mobileNo");
		mobileNoTextBox.setPixelSize(175, 20);
		datePicker=new DatePicker();
		TextBox datePickerLbl=new TextBox();
		datePickerLbl.setName("datePickerLbl");
		datePickerLbl.setVisible(false);
		datePickerLbl.setReadOnly(true);
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				//Window.alert(event.getValue().toString());
				datePicker.setValue(event.getValue(),true);
			    datePickerLbl.setText(event.getValue().getDate()
			    		+"/"+(1+event.getValue().getMonth())+"/"+(1900+event.getValue().getYear()));
			}
			
		});
		
		maleButton=new RadioButton("genderGroup","Male");
		
		femaleButton=new RadioButton("genderGroup","Female");
		TextBox rbLbl=new TextBox();
		rbLbl.setName("rbLbl");
		rbLbl.setVisible(false);
		rbLbl.setReadOnly(true);
		
		maleButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				rbLbl.setText("MALE");
			}
			
		});
		femaleButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				rbLbl.setText("FEMALE");
			}
			
		});
		
		Label genderLbl=new Label();
		
		
		regButton=new Button();
		regButton.setText("Submit");
		regButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				regFormPanel.submit();
			}
			
		});
		
		userNameLbl=new Label();
		userNameLbl.setText("User Name");
		
		passwordLbl=new Label();
		passwordLbl.setText("Password");
		
		emailLbl=new Label();
		emailLbl.setText("Email");
		
		mobileNoLbl=new Label();
		mobileNoLbl.setText("Mobile No");
		
		genderLbl=new Label();
		genderLbl.setText("Gender");
		
		dobLbl=new Label();
		dobLbl.setText("DOB");
		
		regGrid=new Grid(8,2);
		regGrid.setWidget(0, 0, userNameLbl);
		regGrid.setWidget(0, 1, userNameTextBox);
		regGrid.setWidget(1, 0, passwordLbl);
		regGrid.setWidget(1, 1, passwordTextBox);
		regGrid.setWidget(2, 0, emailLbl);
		regGrid.setWidget(2, 1, emailTextBox);
		regGrid.setWidget(3, 0, mobileNoLbl);
		regGrid.setWidget(3, 1, mobileNoTextBox);
		regGrid.setWidget(4, 0, dobLbl);
		regGrid.setWidget(4, 1, datePicker);
		regGrid.setWidget(5, 0, datePickerLbl);
		regGrid.setWidget(6, 0, genderLbl);
		HorizontalPanel genderPanel=new HorizontalPanel();
		genderPanel.add(maleButton);
		genderPanel.add(femaleButton);
		regGrid.setWidget(6, 1, genderPanel);
		regGrid.setWidget(7, 0, rbLbl);
		regGrid.setWidget(7, 1, regButton);
		
		regPanel=new VerticalPanel();
		regPanel.add(regGrid);
		
		regFormPanel=new FormPanel();
		regFormPanel.setAction(GWT.getModuleBaseURL()+"register");
		regFormPanel.setMethod(FormPanel.METHOD_POST);
		
		regFormPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// TODO Auto-generated method stub
				Window.alert(event.getResults().toString());
			}
			
		});
		
		
		regFormPanel.add(regPanel);
		
		
		return regFormPanel;
		
	}
	
	
	
}
