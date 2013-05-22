<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User Signup</title>
</head>
<body bgcolor="#EBE6E5">
<div align="middle">
<input type="button" Style="border:1px;width:1200px;background-color:#EBE6E5">
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>

<br><br><br><br><br><div style='font-family: Helvetica;font-size: 20pt;background-color: #EBE6E5;color: blue;'><b>New User</b></div><br><br><br><br><br>
<form action="SignupServlet" method='post'>
		Ibm id <input style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' type="text" name="id" /><br><br>
		Name <input style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' type="text" name="nm" /><br><br>
		Password <input type="password" name="pass" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'/><br><br> <input
			type="submit" value="submit" Style="height:30px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;">
	</form>
	<br>
	<%String allowed="";
	if(session.getAttribute("isAllowed")!=null)
		allowed = (session.getAttribute("isAllowed")).toString(); 
	if(allowed.equals("FAIL"))
		out.println("<div style='font-family: Arial;font-size: 10pt;color: red;'>Enter valid details please!</div>");
		session.setAttribute("isAllowed", null);
		%>
		<%allowed="";
	if(session.getAttribute("isExists")!=null)
		allowed = (session.getAttribute("isExists")).toString(); 
	if(allowed.equals("FAIL"))
		out.println("<div style='font-family: Arial;font-size: 10pt;color: red;'>Sorry, ID already registered!</div>");
		session.setAttribute("isExists", null);
		%>
	</div>
</body>
</html>