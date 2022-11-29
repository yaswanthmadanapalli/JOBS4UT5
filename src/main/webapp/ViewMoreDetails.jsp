<%@page import="com.dto.Apply"%>
<%@page import="com.dao.ApplyDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DB.DBconnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply Job</title>
<%@include file="all_components/all_css.jsp"%>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
%>
<style>
.card {
	border-radius: 20px 20px;
	padding: 10px;
	border: 2px solid blue;
}

/* .bg-img{ */
/* background-image: url('img/login.jpg'); */

/* 	height: 88vh; */
/* 	width: auto; */
/* 	background-repeat: no-repeat;  */
/* 	background-size: cover;	 */
/* } */
</style>

</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_components/nav_bar.jsp"%>
	
		<c:if test="${not empty succMsg }">
			<h4 class="text-center text-success">${succMsg }</h4>
			<c:remove var="succMsg" />
		</c:if>
		<c:if test="${not empty succMsg1 }">
			<h4 class="text-center alert alert-danger" role="alert">${succMsg1 }</h4>
			<c:remove var="succMsg1" />
		</c:if>
		<%
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		ApplyDao dao = new ApplyDao(DBconnect.getconn());
		Apply j = dao.getMoreDetails(id);
		%>
		<br>
		<h1 class="text-center">Applicant details are here for you </h1>
		<div class="row">
			<div class="col-md-6 offset-md-3">
			
				<div class="card">
				
					<div class="card-body">

						<div class="form-row">
							<div class="form-group col-md-6">

								<label class="form-control form-control-sm">
								FirstName : <%=j.getFirstName()%></label>
							</div>

							<div class="form-group col-md-6">
									<label class="form-control form-control-sm">
								Last Name : <%=j.getLastName()%></label>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Date Of Birth:<%=j.getDateOfBirth()%></label>
									
							</div>


							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Year Of Graduation:<%=j.getYearOfGraduation()%></label>
							</div>

							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Experience:<%=j.getExperience()%></label>
							</div>
						</div>


						<div class="form-row">
							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Role:<%=j.getRole()%></label>
							</div>

							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>College Name:<%=j.getCollegeName()%></label>
							</div>

							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Technologies:<%=j.getTechnologiesKnown()%></label>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Phone Number : <%=j.getPhoneNumber()%></label>
							</div>

							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Alt Phone Number : <%=j.getAlternatePhoneNumber()%></label>
								
							</div>

							<div class="form-group col-md-4">
								<label   class="form-control form-control-sm"
									>Enter Email : <%=j.getEmail()%></label>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<label   class="form-control form-control-sm"
									><a href='downloadurl?Upload Resume=<%=j.getResume()%>'> download resume</a></label>
									
							</div>

							<div class="form-group col-md-6">
								<label   class="form-control form-control-sm"
									><a href='downloadurl?photoId=<%=j.getPhoto()%>'> download photo</a></label>
									
							</div>
						</div>



						<div class="form-group">

							<label   class="form-control form-control-sm"
								>Projects:<%=j.getAddress()%></label>
						</div>
					</div>
				</div>
			</div>
		</div>
	




</body>
</html>