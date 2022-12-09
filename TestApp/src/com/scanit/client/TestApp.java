package com.scanit.client;



import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestApp implements EntryPoint {
	PhotoFactory factory = GWT.create(PhotoFactory.class);
	public void onModuleLoad() {
		
		
		
	    final VerticalPanel photoViewMainPanel = new VerticalPanel();
	    final ScrollPanel scrollPanel  = new ScrollPanel(); 
	    scrollPanel.setSize("1200px", "600px");
	       
		
		//REST call
		 String url = "https://jsonplaceholder.typicode.com/photos";
		 RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

		 try {
		  Request request = builder.sendRequest(null, new RequestCallback() {
		   public void onError(Request request, Throwable exception) {
		     // Couldn't connect to server (could be timeout, SOP violation, etc.)
		   }

		   public void onResponseReceived(Request request, Response response) {
		    if (200 == response.getStatusCode()) {
		      // Process the response in response.getText()	    	
		    	//Window.alert(response.getText());       
		    
		    	try {
		    		  // parse the response text into JSON
		    		  JSONValue jsonValue = JSONParser.parse(response.getText());
		    		  JSONArray jsonArray = jsonValue.isArray();
		    		  //Window.alert(String.valueOf(jsonArray.size()));
		    		  List<Photo> photos=new ArrayList<Photo>();
		    		  for(int i=0;i<jsonArray.size();i++)
		    		  {
		    		  if (jsonArray != null) {
		    		    
		    			  JSONValue item = jsonArray.get(i);
		    			  AutoBean<Photo> bean = AutoBeanCodex
	                              .decode(factory, Photo.class, item.toString());
		    			 // Window.alert(bean.as().getThumbnailUrl());
		    			  photos.add(bean.as());	    			  
		    		    
		    		  } 
		    		  
		    		  }
		    		  
		    		 
		    		
		    	    	CellTable<Photo> cellTable= createCellTable();
		    	    	cellTable.setRowCount(photos.size());
		    	    	cellTable.setRowData(photos);
		    	    	 ListDataProvider<Photo> dataProvider = new ListDataProvider<Photo>();
		    	    	    dataProvider.addDataDisplay(cellTable);
		    	    	    dataProvider.setList(photos);   	    	
		    	    	
			    	
		    	    	SimplePager pager = new SimplePager();
		    	        pager.setDisplay(cellTable);
		    	        pager.setPageSize(15); // 20 rows will be shown at a time
		    	    	photoViewMainPanel.add(cellTable);	    	    	
		    	    	photoViewMainPanel.add(pager);
		    	    	scrollPanel.add(photoViewMainPanel);
		    	    	RootPanel.get().add(scrollPanel);
		    	    	
		    	}
		    	catch(Exception ex)
		    	{
		    		
		    	}
		
}
		  	
		  	else {
	     // Handle the error.  Can get the status text from response.getStatusText()
	    	Window.alert(response.getText());
	    }
	   }
	  });
	 } catch (RequestException e) {
	  // Couldn't connect to server
	 }		
	
		
		
		
		
		
		
		
		
		
		
		
		/*
		  final FlexTable flexTable = new FlexTable();
		  
		  flexTable.setBorderWidth(3);
		  //Set table headers
		        flexTable.setText(0, 0, "N/A");
		 
		  flexTable.setText(0, 1, "Column 1");
		        flexTable.setText(0, 2, "Column 2");
		 
		        //Button for adding new row
		        Button AddRowButton = new Button("Add Row", new ClickHandler() {
		            @Override
		            public void onClick(ClickEvent event) {
		                addRow(flexTable);
		            }
		        });
		 
		        //Button for deleting row
		        Button DeleteRowButton = new Button("Delete Row", new ClickHandler() {
		            @Override
		            public void onClick(ClickEvent event) {
		                deleteRow(flexTable);
		            }
		        });
		         
		        VerticalPanel buttonsPanel = new VerticalPanel();
		        buttonsPanel.add(AddRowButton);
		        buttonsPanel.add(DeleteRowButton);
		         
		        HorizontalPanel hp = new HorizontalPanel();
		        hp.add(flexTable);
		        hp.add(buttonsPanel);
		         
		        RootPanel.get().add(hp);
		    }
		 
		    // Add row method
		    private void addRow(FlexTable flexTable) {
		        int numRows = flexTable.getRowCount();
		        flexTable.setText(numRows, 0, "Row "+ numRows);
		        flexTable.setText(numRows, 1, "Item["+ numRows +", 1]");
		        flexTable.setText(numRows, 2, "Item["+ numRows +", 2]");
		    }
		 
		    // Delete row method
		    private void deleteRow(FlexTable flexTable) {
		        int numRows = flexTable.getRowCount();
		        if (numRows > 1) {
		            flexTable.removeRow(numRows - 1);
		        }
		        }
*/
		
		
	}
	
	
	private static CellTable<Photo> createCellTable()
	{
		CellTable<Photo> cellTable=new CellTable<Photo>();
		TextColumn<Photo> albumIdCol=new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				// TODO Auto-generated method stub
				return String.valueOf(object.getAlbumId());
			}
			
		};
		cellTable.addColumn(albumIdCol,"Album Id");
		
		TextColumn<Photo> idCol=new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				// TODO Auto-generated method stub
				return String.valueOf(object.getId());
			}
			
		};
		cellTable.addColumn(idCol,"Id");
		
		TextColumn<Photo> thumbnailCol=new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				// TODO Auto-generated method stub
				return object.getThumbnailUrl();
			}
			
		};
		cellTable.addColumn( thumbnailCol,"ThumbnailUrl");
		TextColumn<Photo> urlCol=new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				// TODO Auto-generated method stub
				return object.getUrl();
			}
			
		};
		cellTable.addColumn( urlCol,"Url");
		TextColumn<Photo> titleCol=new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				// TODO Auto-generated method stub
				return object.getTitle();
			}
			
		};
		cellTable.addColumn(titleCol,"Title");
		return cellTable;
	}

}
