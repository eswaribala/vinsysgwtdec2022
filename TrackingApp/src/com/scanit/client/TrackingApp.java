package com.scanit.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.scanit.server.LoginServiceImpl;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TrackingApp implements EntryPoint {
	private static final String UPLOAD_ACTION_URL = GWT.getModuleBaseURL() + "upload";

	private FormPanel form;
	private Label info;
	private FileUpload fileupload;
	private Button uploadFileBtn;

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		init();

	    uploadFileBtn.addClickHandler(new ClickHandler() {
	        @Override
	        public void onClick(ClickEvent event) {
	            String filename = fileupload.getFilename();

	            if(filename.length() == 0) {
	                Window.alert("File Upload failed");
	            } else if(filename.endsWith(".pdf")) {

	                form.submit();          

	            } else {
	                Window.alert("File is not a pdf file");
	            }
	        }
	    });


	    form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
	        @Override
	        public void onSubmitComplete(SubmitCompleteEvent event) {

	            if(event.getResults().length() == 0) {
	                Window.alert("Something went wrong - Try again");
	            } else {
	            	 Window.alert(event.getResults());               
	            }
	        }
	    });


	    VerticalPanel vp = new VerticalPanel();
	    vp.add(info);
	    vp.add(fileupload);
	    vp.add(new HTML("<br>"));
	    vp.add(uploadFileBtn);
	   

	    form.add(vp);
	    RootPanel rp = RootPanel.get();
	    rp.add(form);
	}

	private void init() {
	    form = new FormPanel();
	    form.setAction(UPLOAD_ACTION_URL);
	    form.setEncoding(FormPanel.ENCODING_MULTIPART);
	    form.setMethod(FormPanel.METHOD_POST);

	    info = new Label("Choose a file");

	    fileupload = new FileUpload();

	    //Here I added a name to the fileuploader
	    fileupload.setName("uploader");

	    uploadFileBtn = new Button("Show Status");

	    
	}
	
	
	
	
}

