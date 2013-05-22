<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Login Page</title>
</head>
<body bgcolor="#EBE6E5">
<input type="button" Style="border:1px;width:1200px;background-color:#EBE6E5">
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>
<div align = "middle">
<br><br><br><br><br><br><div style='font-family: Helvetica;font-size: 20pt;background-color: #EBE6E5;color:blue;'><b>Login</b></div>
<br><br><br><br>
	
	
	<form action="LoginServlet" method='post'>
		IBM Id <input width="150" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' type="text" name="un" /><br>
		<br>Password <input width="150" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' type="password" name="pw" /><br> 
		<br><input type="submit" value="Submit"  Style="height:30px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;">
	</form>
	<%String allowed="";
	if(session.getAttribute("isValid")!=null)
		allowed = (session.getAttribute("isValid")).toString(); 
	if(allowed.equals("FAIL"))
		out.println("<br><div style='font-family: Arial;font-size: 10pt;color: red;'>Invalid Login</div>");
		session.setAttribute("isValid", null);%>
	<br><br><br><br>
	New User?
	<a href="signup.jsp">Sign Up!</a>
	<br>
		
	</div>
</body>
</html>
