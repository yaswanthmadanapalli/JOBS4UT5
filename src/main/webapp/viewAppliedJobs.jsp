<%@page
	import="org.apache.commons.collections.bag.SynchronizedSortedBag"%>
<%@page import="com.dto.Apply"%>
<%@page import="com.dao.ApplyDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DB.DBconnect"%>
<%@page import="java.util.List"%>

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
%>
</head>
<body style="background-color: #f0f1f2;">
	<%@include file="all_components/nav_bar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h5 class="text-center text-primary">All Jobs</h5>
				<c:if test="${not empty succMsg }">
					<div class="alert alert-success" role="alert">${ succMsg}</div>
					<c:remove var="succMsg" />
				</c:if>
				<c:if test="${not empty succMsg1 }">
					<div class="alert alert-danger" role="alert">${ succMsg1}</div>
					<c:remove var="succMsg1" />
				</c:if>


				<%
				ApplyDao dao = new ApplyDao(DBconnect.getconn());
				// 				HttpSession session = request.getSession(false);
				String name = (String) session.getAttribute("name");
				System.out.println(name);

				if (name == null) {
					session.setAttribute("succMsg1", "Session timed out, pls login again");
					response.sendRedirect("login.jsp");
				}
				List<Apply> list = dao.getAllAppliedJobs(name);
				for (Apply j : list) {
					System.out.println("fghj");
				%>
				<div class="card mt-2">
					<div class="card-body">
						<div class="text-center text-primary">
							<i class="far fa-clipboard fa-2x"></i>
						</div>
						<div class="form-row">

						<%-- 					<h6><%=j.getTitle()%></h6> --%>
						
						<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Name:<%=j.getFirstName()%>" readonly>

							</div>
							</div>
						<br>
						<div class="form-row">
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Year Of Graduation:<%=j.getYearOfGraduation()%>"
									readonly>
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Experience:<%=j.getExperience()%>" readonly>
							</div>
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Technologies:<%=j.getTechnologiesKnown()%>" readonly>

							</div>
						
						</div>
						<div class="form-row">
						<div class="form-group col-md-9">
								<input type="text" class="form-control form-control-sm"
									value="Projects:<%=j.getAddress()%>" readonly>

							</div>
							</div>
						<div class="text-center ">
							<a href="ViewMoreDetails.jsp?id=<%=j.getId()%>"
								class="btn btn-sm bg-success text-white">ViewMore...</a>


						</div>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
</body>
</html>