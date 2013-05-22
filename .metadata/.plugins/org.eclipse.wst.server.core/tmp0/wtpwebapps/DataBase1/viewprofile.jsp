<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page language="java" import="login.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body bgcolor="#EBE6E5">
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:1300px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>

<div align="center" Style='font-family:Helvetica'>
<h1 align = 'center'>User profile</h1>


<%
	User user = (User) session.getAttribute("user");
	int access = user.getAccess();
	String acc="Read Only";
	if(access==1)
		acc = "Read/Write";
	try {
		out.println("Name : " + user.getNickname() + "<br/>" + "ID : "
				+ user.getIbmId() + "<br/>" + "Access : "+ acc);
	} catch (Exception e) {
		out.println("Not found");
		e.printStackTrace();
	}

	if (session.getAttribute("currentSessionUser") != null) {
		
			User currentUser = (User) (session
					.getAttribute("currentSessionUser"));
			if (!user.getIbmId().trim().equals(currentUser.getIbmId())) {
				out.println("<div id = 'Edit' style='display:none;'>");
			}
		}
	else {
		out.println("<div id = 'Edit' style='display:none;'>");

	}
%>

<br><br><br><br>
<div align="center" Style='font-family:Helvetica'>
<h2 align = 'center'>Edit Profile</h2>
<form action = 'SaveProfileChanges' method='post'>
IBM Id : <%=user.getIbmId()%><br/><br/>
Nickname : <%=user.getNickname()%><br/><br/>
Password : <input type = 'password' name = 'pass' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'/><br/><br/>
<input type='submit' value='Save Changes' Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;"/>
</form>
</div>
</div>
</body>
</html>