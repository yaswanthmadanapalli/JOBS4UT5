<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.DB.DBconnect"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Here</title>
<%@include file="all_components/all_css.jsp"%>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
%>
<style>
.card {
	/* 	border-radius: 50px 20px; */
	padding: 10px;
	/* border: 2px solid blue; */
}

.form-control {
	border-radius: 0px;
}

.form-control:hover {
	border-radius: 5px;
}

.bg-img {
	background-image: url('img/images (8).jpeg');
	height: 100vh;
	width: 100%;
	background-repeat: no-repeat;
	background-size: cover;
}

body {
	overflow: hidden;
}
</style>
</head>
<body>
	<%-- 	<%@include file="all_components/nav_bar.jsp"%> --%>

	<div class="row">
		<div class="col-md-5">
			<div class="container-fluid bg-img"></div>
		</div>
		<div class="col-md-7 mt-5">

			<div class="container-fluid">
				<c:if test="${not empty succMsg }">
					<h4 class="text-center text-success">${succMsg }</h4>
					<c:remove var="succMsg" />
				</c:if>
				<c:if test="${not empty succMsg1 }">
					<h4 class="text-center alert alert-danger" role="alert">${succMsg1 }</h4>
					<c:remove var="succMsg1" />
				</c:if>
				<div class="text-center">
					<!-- 							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i> -->
					<h1 style="color: #007bff">JOBS4U</h1>
				</div>
				<div class="row">
					<div class="col-md-6 offset-md-3">
						<!-- 				<center>	<img alt="login here" src="img/download (1).jpeg"> </center> -->
						<div class="card border-0">
							<div class="card-body">


								<form action="login" method="post">
									<div class="form-group">
										<label>Username</label> <input type="text" required="required"
											class="form-control border-left-0 border-top-0 border-right-0"
											id="exampleInputusr" aria-describedby="emailHelp"
											name="email" autofocus="on" onclick="myFunction1()">


									</div>

									<div class="form-group">

										<label for="exampleInput Password1">Password</label> <input
											required="required" type="password"
											class="form-control border-left-0 border-top-0 border-right-0"
											id="exampleInputpass" name="password" /> <br> <input
											type="checkbox" onclick="showpass()" /> &nbspShow Password
										<script>
											function showpass() {
												
												var x = document.getElementById("exampleInputpass");
												console.log("com");
												if (x.type === "password") {
													console.log("comi");
													document.getElementById("exampleInputpass").type = "text";
												} else {
													console.log("comin");
													document.getElementById("exampleInputpass").type = "password";
												}
											}
										</script>
									</div>

									<button type="submit" href="admin.jsp"
										class="btn btn-primary btn-block">
										<h4>&nbsp&nbsp&nbspLogin&nbsp&nbsp&nbsp</h4>
									</button>
									<br>
									<center>
										<a href="#">Forgotten password? </a>
									</center>

									<hr>
									

								</form>
								<center>
									<a href="Signup.jsp" class="btn btn-success ">
										<h4>&nbsp&nbspCreate New Account&nbsp&nbsp</h4>
									</a>

								</center>
							</div>

						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
