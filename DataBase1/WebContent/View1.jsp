 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reload Page</title>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@ page language="java" import="login.*"%>
<script type="text/javascript">
var time = 1800;




var tottime=0;

function onloaded()
{
setInterval(redirect,1000);

};

function redirect()
{
	tottime++;

	
	if(tottime==1800)
		{
		window.location='View1.jsp';
		}
}

</script>
</head>




<body onload="onloaded()" style="text-align: center; vertical-align: center"  bgcolor="#EBE6E5">
<div id = 'time'></div>
<a href="ViewMain.jsp" target="_blank"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:1250px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>


<% 

User currentUser = new User();




	out.println("<h3>");
	out.println("<a href=\"ViewMain.jsp\" target=\"_blank\">Home</a>");
	out.println("<br><br><br><br><br><br>");
	out.println("</h3>");
	database2.startExec();
	table Table1 = database1.startExec();
	table Cancelled = cancelled.startfilter();
	shipped.saveShipped();


	database2.getNewOrders(session.getAttribute("time").toString());
	database2.getNewOrdersmes(session.getAttribute("time").toString());
%>
<div align="center">
<input type="SUBMIT" Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;" value="Reload Data" onclick="window.location='View1.jsp';"> 
</div>
<br><BR><BR><br>
<a href="View1Only.jsp" Style="text-decoration: none;height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;" target="_blank">Main Table</a>
<%

%>
</body>
</html>