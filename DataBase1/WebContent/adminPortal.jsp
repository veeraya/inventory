<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="login.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Portal</title>
</head>
<body bgcolor="#EBE6E5">

<div id = 'Edit' style='display:none;'>
<%
if (session.getAttribute("currentSessionUser") != null) {
	try {
		User currentUser = (User) (session
				.getAttribute("currentSessionUser"));
		if (currentUser.getIbmId().trim().equals("admin")) {
			out.println("</div>");
		}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
}
%>
<br>
<br>
<br>
<div align ="center">
<h3>User Access</h3>
<br>
</div>
<div align="center">
<form action ='SaveAccessChanges' method = 'post'>
<table cellpadding="5" style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid;'>
<tr>
<th>Delete</th>
<th>User Id</th>
<th>User Name</th>
<th>Write Access?</th>
</tr>
<%
ArrayList<User> list = new ArrayList<User>();
list = UserStorage.getUsers();
for(int i=0;i<list.size();i++)
{
	out.println("<tr>");
	out.println("<td align='middle'>");
	out.println("<input type='checkbox' name = 'delete"+i+"' />");
	out.println("</td>");
	out.println("<td>");
	out.println(list.get(i).getIbmId());
	out.println("</td>");
	out.println("<td>");
	out.println(list.get(i).getNickname());
	out.println("</td>");
	out.println("<td align='middle'>");
	if(list.get(i).getAccess()==1)	
		out.println("<input type='checkbox' checked='checked' name = 'access"+i+"' />");
	else
		out.println("<input type='checkbox' name = 'access"+i+"' />");
	out.println("</td>");
	out.println("</tr>");
		
	
}
%>
</table>
<input type = 'submit' Style='height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;' value='Save'/>

</form>
</div>
</body>
</html>