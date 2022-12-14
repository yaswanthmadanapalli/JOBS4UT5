<%@page import="com.dto.Jobs"%>
<%@page import="com.dao.JobDao"%>
<%@page import="com.dao.UserDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DB.DBconnect"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Jobs</title>
<%@include file="all_components/all_css.jsp"%>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
String name = (String) session.getAttribute("name");

if (name == null) {

	session.setAttribute("succMsg1", "Session timed out, pls login again");
	response.sendRedirect("login.jsp");
}
%>
<style>
#bt{
margin-top:8px;
}
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_components/nav_bar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<h5 class="text-center text-primary">All Jobs</h5>
				<c:if test="${not empty succMsg }">
					<div class="alert alert-success" role="alert">${ succMsg}</div>
					<c:remove var="succMsg" />
				</c:if>

				<div class="card mt-2 mx-auto">
					<div class="card-body">
						<div class="text-primary">
							<form action="filter" method="post" ><!-- target="f2" -->
								<div class="form-row">
									<div class="form-group col-md-4 mr-8">
										<label>Location</label> <select name="location"
											class="custom-select " id="inlineFormCustomSelect Pref">
											<option selected>Choose</option>
											<option value="Hydrabad">Hydrabad</option>
											<option value="Odisha">Odisha</option>
											<option value="Jharkhand">Jharkhand</option>
											<option value="Gujurat">Gujurat</option>
											<option value="Bhubaneswar">Bhubaneswar</option>
											<option value="Delhi">Delhi</option>
											<option value="Bang Lore">Banglore</option>
											<option value="Chennai">Chennai</option>
										</select>
									</div>
									<div class="form-group col-md-4 mr-8">
										<label>Category</label> <select class="custom-select "
											id="inlineFormCustomSelect Pref" name="category">
											<option selected>Choose</option>
											<option value="IT">IT</option>
											<option value="Devloper">Developer</option>
											<option value="Banking">Banking</option>
											<option value="Engineer">Engineer</option>
											<option value="Teacher">Teacher</option>
										</select>

									</div>
								
								<div id="bt" class="form-group col-md-2">
									<br>
									<button type="submit" class="btn btn-primary ">Search</button>
								
								</div>
								</div>
							</form>
						</div>
					</div>
				</div>

<%
				JobDao dao = new JobDao(DBconnect.getconn());
// 				HttpSession session = request.getSession(false);
				
				
				
				List<Jobs> list = dao.getAllJobs();
				for (Jobs j : list) {

				%>
				<div class="card mt-2">
					<div class="card-body">
						<div class="text-center text-primary">
							<i class="far fa-clipboard fa-2x"></i>
						</div>

						<h6><%=j.getTitle()%></h6>
						<p><%=j.getDescription()%></p>
						<br>
						<div class="form-row">
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Location:<%=j.getLocation()%>" readonly>
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Category:<%=j.getCategory()%>" readonly>
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Status:<%=j.getStatus()%>" readonly>

							</div>
						</div>
						<%SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
							String date=formatter.format(j.getPdate());
							%>
						 <h6>
							Publish Date : 
							
							<%=date%></h6> 
						<div class="text-center ">
							<a href="ApplyJob.jsp"						
								class="btn btn-sm bg-success text-white">Apply</a> 

						</div>
					</div>
				</div>
				<%}
						%>
			</div>
		</div>
	</div>
</body>
</html>