  package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBconnect;
import com.dto.Jobs;
import com.dto.Registration;
//import com.neovisionaries.i18n.CountryCode;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private static String name = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			System.out.println("confirmation");
			String em = req.getParameter("email");
			String ps = req.getParameter("password");
			Registration r = new Registration();
			HttpSession session = req.getSession(true);
			
			PrintWriter pw = res.getWriter();

			Connection con = DBconnect.getconn();
			PreparedStatement pstmt = con.prepareStatement("select NAME,ROLE,QUALIFICATION,DESIGNATION from JOBPORTAL_REGISTRATIONDETAILS where email=? and password=?");

			pstmt.setString(1, em);
			pstmt.setString(2, ps);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				if (rs.getString(2).equals("Admin")) {
					session.setAttribute("userobj", r);
					r.setName(rs.getString(1));
					r.setRole(rs.getString(2));
					r.setQualification(rs.getString(3));
					r.setDesignation(rs.getString(4));
					
					//ServletContext servletcontext = getServletContext();
					//servletcontext.setAttribute("name", rs.getString(1));
					session.setAttribute("name", rs.getString(1));
					
					
					res.sendRedirect("admin.jsp");
//					CountryCode cc = CountryCode.getByCode("CHN");
//
//					System.out.println("Country name = " + cc.getName());                // "Japan"
//					System.out.println("ISO 3166-1 alpha-2 code = " + cc.getAlpha2());   // "JP"
//					System.out.println("ISO 3166-1 alpha-3 code = " + cc.getAlpha3());   // "JPN"
//					System.out.println("ISO 3166-1 numeric code = " + cc.getNumeric());  // 392
//					
//					
					 /*Map<String, String> countries = new HashMap<String, String>();
					
					    for (String iso : Locale.getISOCountries()) {
					    	 System.out.println(iso);
					        Locale l = new Locale("en", iso);
					        countries.put(l.getDisplayCountry().toUpperCase(), l.getISO3Country());
					    }
					    String country="inDia";
					   
					    System.out.println(countries.get(country.toUpperCase()));
				*/
				}

				else if(rs.getString(2).equals("User")) {
					session.setAttribute("userobj", r);
					r.setName(rs.getString(1));
					r.setRole(rs.getString(2));
					r.setQualification(rs.getString(3));
					r.setDesignation(rs.getString(4));
					//name = rs.getString(1);
					
					session.setAttribute("name", rs.getString(1));
					
					res.sendRedirect("user.jsp");
				}
			}
			
			
			else {
				session.setAttribute("succMsg1", "Invalid Username/Password");
				res.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public String getAdminName() {
	 * 
	 * return name;
	 * 
	 * }
	 */

}
