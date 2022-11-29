<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup Here</title>
<%@include file="all_components/all_css.jsp"%>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
%>
<style>
.card {
	border-radius: 50px 50px;
	border: 0px;
}

.btn {
	padding: 10px 120px 10px 120px;
}
/* body{
overflow: hidden;
} */
.form-control {
	border-style: none none solid none;
	border-radius: 0px;
}

.fa {
	color: #007bff
}

.form-control:hover {
	border-radius: 5px;
}

#alpup, #alplow, #digit, #symbol, #passlen {
	color: red;
}

body {
	overflow-x: hidden;
}
</style>
</head>
<body>
	<%-- 	<%@include file="all_components/nav_bar.jsp"%> --%>
	<div class="continer-fluid bg-img">
		<c:if test="${not empty succMsg1 }">
			<h4 class="text-center text-danger">${succMsg1 }</h4>
			<c:remove var="succMsg1" />
		</c:if>
		<div class="row">
			<div class="col-md-5 offset-md-3">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h1 style="color: #007bff">Create a New Account</h1>
						</div>


						<form action="signup" method="post" id="frm">
							<div class="form-group">
								<label>Full Name</label> <input type="text" class="form-control"
									id="username" aria-describedby="emailHelp" name="name"
									onblur="msg()" onfocus="clr()"> <span
									id="usrnamenotnull"> </span>
							</div>

							<div class="form-group">
								<label>Qualification</label> <input type="text"
									class="form-control" id="qual" aria-describedby="emailHelp"
									name="qualification" onblur="msg1()" onfocus="clr1()"> <span
									id="qualnotnull"> </span>
							</div>

							<div class="form-group">
								<label>Email</label> <input type="text" class="form-control"
									id="email" aria-describedby="emailHelp" name="email"
									 onblur="msg2()" onfocus="clr2()"> <span
									id="emailnotnull"> </span>
							</div>

							<div class="form-group">

								<label for="password">Password</label>
								<div class="card rounded-0">
									<div class="input-group " id="show_hide_password">
										<input required="required" type="password"
											class="form-control" id="password" name="password"
											onblur="msg3()" onfocus="clr3()">&nbsp;&nbsp;
										<div class="input-group-addon align-bottom">

											<a href=" "><i class="fa fa-eye-slash mt-2"
												id="togglePassword" aria-hidden="true"></i></a>&nbsp;&nbsp;

										</div>

									</div>
								</div>
								<div>
								<span
											id="passwordnotnull"> </span><br>
									<span style="color: green;">Password must contain
										Atleast</span> <span id="alpup"> one Upper case, </span> <span
										id="alplow">one Lower case,</span> <span id="digit">
										one Digit,<br>
									</span> <span id="symbol">one special Character</span><span
										id="passlen"> minimum 8 and maximum of 14 Characters</span> <br>
								</div>
							</div>

							<div class="form-group">
								<label>Designation</label> <input type="text"
									required="required" class="form-control"
									id="designation" aria-describedby="emailHelp"
									name="designation" onblur="msg4()" onfocus="clr4()"><span
									id="designnotnull"> </span>

							</div>

							<div class="form-group">
								<label>Select Role</label><br /> <input type="radio"
									value="Admin" required="required" aria-describedby="emailHelp"
									name="role">Admin &nbsp; &nbsp; <input type="radio"
									value="User" required="required" aria-describedby="emailHelp"
									name="role">User
							</div>
							<center>
								<button type="submit" class="btn btn-primary badge-pill ">Register</button>
							</center>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		
		document.getElementById("password").addEventListener("keyup", fun2);
		//document.getElementById("un").addEventListener("keypress", fun3);

		function msg() {
			let usr=document.getElementById("username").value;
			if (usr.trim() == "" || usr==null) {
				document.getElementById("usrnamenotnull").style.color="red";
                document.getElementById("usrnamenotnull").innerHTML = ` Username can not be null`;
			}  }
		function msg1() {
			let quali=document.getElementById("qual").value;
			if (quali==null || quali.trim() == "" ) {
				document.getElementById("qualnotnull").style.color="red";
                document.getElementById("qualnotnull").innerHTML = ` Qualification can not be null`;
			}} 
		function msg2() {
			let mail=document.getElementById("email").value;
			if (mail.trim() == "" || mail==null) {
				document.getElementById("emailnotnull").style.color="red";
                document.getElementById("emailnotnull").innerHTML = ` Email can not be null`;
			} }
		function msg3() { 
			let pass=document.getElementById("password").value;
			if (pass.trim() == "" || pass==null) {
				document.getElementById("passwordnotnull").style.color="red";
                document.getElementById("passwordnotnull").innerHTML = ` Password can not be null`;
			} }
		function msg4() {
			let deisg=document.getElementById("designation").value;
			if (deisg.trim() == "" || deisg==null) {
				document.getElementById("designnotnull").style.color="red";
                document.getElementById("designnotnull").innerHTML = ` Designation can not be null`;
			}}
		
function clr(){

		document.getElementById("usrnamenotnull").innerHTML = "";
}

function clr1(){
	document.getElementById("qualnotnull").innerHTML = "";
}
function clr2(){
	document.getElementById("emailnotnull").innerHTML = "";
}
function clr3(){
	document.getElementById("passwordnotnull").innerHTML = "";
}
function clr4(){
	document.getElementById("designnotnull").innerHTML = "";

	
}
		function fun2(ps) {

			var psd = document.getElementById("password").value;
			console.log(psd)
			let alphaup = false;
			let digit = false;
			let alphaloe = false;
			let symbol = false;
			var reg_exp = /[A-Z]/;
			var reg_exp1 = /[a-z]/;
			var reg_exp2 = /[0-9]/;
			var reg_exp3 = /[@!#$%^&*`~]/;

			if (reg_exp.test(psd) == true) {
				document.getElementById("alpup").style.color = "green";
			} else {
				document.getElementById("alpup").style.color = "red";
			}
			if (reg_exp1.test(psd) == true) {
				document.getElementById("alplow").style.color = "green";
			} else {
				document.getElementById("alplow").style.color = "red";
			}
			if (reg_exp2.test(psd) == true) {
				document.getElementById("digit").style.color = "green";
			} else {
				document.getElementById("digit").style.color = "red";
			}
			if (reg_exp3.test(psd) == true) {
				document.getElementById("symbol").style.color = "green";
			} else {
				document.getElementById("symbol").style.color = "red";
			}
			if (psd.length >= 8 && psd.length <= 14) {
				document.getElementById("passlen").style.color = "green";
			} else {
				document.getElementById("passlen").style.color = "red";
			}

		}
	</script>

	<script>
		$(document)
				.ready(
						function() {
							$("#show_hide_password a")
									.on(
											'click',
											function(event) {
												event.preventDefault();
												if ($(
														'#show_hide_password input')
														.attr("type") == "text") {
													$(
															'#show_hide_password input')
															.attr('type',
																	'password');
													$('#show_hide_password i')
															.addClass(
																	"fa-eye-slash");
													$('#show_hide_password i')
															.removeClass(
																	"fa-eye");
												} else if ($(
														'#show_hide_password input')
														.attr("type") == "password") {
													$(
															'#show_hide_password input')
															.attr('type',
																	'text');
													$('#show_hide_password i')
															.removeClass(
																	"fa-eye-slash");
													$('#show_hide_password i')
															.addClass("fa-eye");
												}
											});
						});
	</script>
</body>
</html>