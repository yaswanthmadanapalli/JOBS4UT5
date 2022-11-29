package com.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBconnect;
import com.dao.ApplyDao;
import com.dto.Apply;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadParameters;

@WebServlet("/Apply")
public class ApplyJob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			
		
			// To collect multiple types of requests from UI
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			HttpSession session=req.getSession(false);
			
			MultipartFormDataRequest nreq = new MultipartFormDataRequest(req);

			String firstName = nreq.getParameter("fname");
			String lastName = nreq.getParameter("lname");
			java.util.Date date = sdf1.parse(nreq.getParameter("dob"));
			java.sql.Date dateOfBirth = new java.sql.Date(date.getTime());  
			String yearOfGraduation = nreq.getParameter("yog");
			int experience = Integer.parseInt(nreq.getParameter("exp"));
			String technologiesKnown = nreq.getParameter("tech");
			long phoneNumber = Long.parseLong(nreq.getParameter("phone"));
			long alternatePhoneNumber = Long.parseLong(nreq.getParameter("altphone"));
			String email = nreq.getParameter("email");
			String role = nreq.getParameter("role");
			String address =nreq.getParameter("add");
			String collegeName = nreq.getParameter("cname");
			System.out.println("owner :: "+session.getAttribute("owner"));
//			String owner ="xyz";
			String owner = (String) session.getAttribute("owner");
			
			int count=0;
			System.out.println("====================="+dateOfBirth);
			UploadBean upb = new UploadBean();
			upb.setFolderstore("C:/store"); // folder location in the server machine file location.
			upb.setOverwrite(false);
			upb.setFilesizelimit(1024 * 1024);
			// complete file Upload process
			upb.store(nreq);

			
			  Vector<UploadParameters> vect=upb.getHistory();
			  ArrayList<String> filesList=new ArrayList();
			  for(UploadParameters up:vect)
			  {
			  filesList.add("C:/store/"+up.getFilename()); 
			  }
			  Apply a = new Apply();
			
			
			  
			  a.setFirstName(firstName);
			  a.setLastName(lastName);
			  a.setDateOfBirth(dateOfBirth);
			  a.setYearOfGraduation(yearOfGraduation);
			  a.setExperience(experience);
			  a.setTechnologiesKnown(technologiesKnown);
			  a.setPhoneNumber(phoneNumber);
			  a.setAlternatePhoneNumber(alternatePhoneNumber);
			  a.setEmail(email);
			  a.setCollegeName(collegeName);
			  a.setResume(filesList.get(0));
			  a.setPhoto(filesList.get(1));
			  a.setRole(role);
			  a.setAddress(address);
			  a.setOwner(owner);
			  a.setCount(count);
			 
			ApplyDao dao = new ApplyDao(DBconnect.getconn());
			boolean f = dao.ApplyJob(a);
			if (f) {
				session.setAttribute("succMsg", "Files are uploaded Successfully..Apply for more jobs");
				resp.sendRedirect("userFilterJobs.jsp");
			} else if (!f) {
				session.setAttribute("succMsg1", "Something went wrong..Pls try later");
				resp.sendRedirect("ApplyJob.jsp");
			} else {
				session.setAttribute("succMsg1", "Session timed out, pls login again");
				resp.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
		System.out.println(e);
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//	}

}
