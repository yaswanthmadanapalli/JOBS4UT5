package com.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/downloadurl")
public class Download extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String filePath=null;
	if(req.getParameter("Upload Resume")!=null)
	{
		filePath=req.getParameter("Upload Resume");
	}
	else {
		filePath=req.getParameter("photoId");
	}
		
		// get Length of the file and make it response content lengtht
		File file=new File(filePath);
		res.setContentLength((int) file.length());
		//get  file content type (MIME type) make it as response content type
		ServletContext sc=getServletContext();
		String mimeType=sc.getMimeType(filePath);
		mimeType=mimeType!=null?mimeType:"application/octet-stream";
		res.setContentType(mimeType);
		//Give instruction to browser to make the recived content as the downlodable file content
		res.setHeader("Content-Disposition","attachment;fileName="+file.getName());
		//create InputSTream  pointing to the file
		 InputStream is=new FileInputStream(filePath);
		 //create OuputStream pointing to response object
		 OutputStream os=res.getOutputStream();  // byte stream is taken to deal with both text, non-text content
		 //copy file content to response obj  (this compltes file donwloading
		 org.apache.commons.io.IOUtils.copy(is,os);
		 //close sreams
		 is.close();
		 os.close();

	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       doGet(req,res);
	}
	

}
