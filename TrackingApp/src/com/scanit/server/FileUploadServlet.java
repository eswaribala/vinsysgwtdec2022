package com.scanit.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

public class FileUploadServlet extends HttpServlet
{

private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      

	// Create a factory for disk-based file items
	   FileItemFactory factory = new DiskFileItemFactory();
	   // Create a new file upload handler
	   ServletFileUpload upload = new ServletFileUpload(factory);
	   try {
	      // Parse the request
	         List items = upload.parseRequest(request); 

	      // Process the uploaded items
	         Iterator iter = items.iterator();

	      while (iter.hasNext()) {
	         FileItem item = (FileItem) iter.next();
	      
	         //handling a normal form-field
	         if(item.isFormField()) {
	            //GWT.log("Got a form field");
	            String name = item.getFieldName();
	            String value = item.getString();
	            System.out.print("Name:"+name+",Value:"+value);				
	         
	         } else { 
	            
	            //handling file loads
	            System.out.println("Not form field");
	            String fieldName = item.getFieldName();
	            String fileName = item.getName();
	            if (fileName != null) {
	               fileName = FilenameUtils.getName(fileName);
	            }
	            
	            String contentType = item.getContentType();
	            boolean isInMemory = item.isInMemory();
	            long sizeInBytes = item.getSize();
	           // Window.alert("Field Name:"+fieldName +",File Name:"+fileName);
	            //Window.alert("Content Type:"+contentType
	            //   +",Is In Memory:"+isInMemory+",Size:"+sizeInBytes);			 
	            
	            byte[] data = item.get();
	            fileName = getServletContext()
	               .getRealPath( "/" + fileName);
	           // Window.alert("File name:" +fileName);			
	            FileOutputStream fileOutSt = new FileOutputStream(fileName);
	            fileOutSt.write(data);
	            fileOutSt.close();
	            response.getWriter().write("File Uploaded Successfully!");
	         }	
	      }
	   } catch(Exception e){
		   response.getWriter().write("File Uploading Failed!" + e.getMessage());
	   } 
}
}