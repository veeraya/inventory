 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-s//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<title>Home Page</title>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="login.*" %>
</head>
<script type="text/javascript">
window.onload=function()
{
	toggle();

}

var isVisible=true;
var isVisiblec=true;
function toggle() {
	var ele = document.getElementById("maintab");
	
	if(ele.hidden==true) {
		isVisble=true;
    		ele.hidden=false;

	}
	else if(ele.hidden==false)
		{
		
		isVisible=false;
		ele.hidden=true;

		}
} 



function savesession()
{
	<%
	   String mannum = request.getParameter( "mannum" );
	   session.setAttribute( "ManNums", mannum );
	   
	%>
}
</script>
<body bgcolor="#EBE6E5">
<%
session.setAttribute("currentURLMain","View1OnlyBox.jsp"); 
  String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	session.setAttribute("isMes", 1);
	session.setAttribute("isMesd", 1);

%>
<% 
if(session.getAttribute("currentSessionUser")==null)
out.println("<INPUT  Style='color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Login' onClick=\"window.location='login.jsp' \" TYPE='SUBMIT'>");
else
{User currentUser = (User)(session.getAttribute("currentSessionUser"));
out.println("<div class='font-family: Helvetica;font-size: 60pt;'>");
out.println("<b>Hello, <a href = 'Userprofile?name="+currentUser.getNickname()+"'>"); 
out.println(currentUser.getNickname()+"</a>!</b>" ); 
out.println("</div>");
}
%>
<% 
if(session.getAttribute("currentSessionUser")!=null)
{
	out.println("<input type='button' Style='border:1px;width:1200px;background-color:#EBE6E5'>");
	out.println("<INPUT  Style='color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Logout' onClick=\"window.location='logout' \" TYPE='SUBMIT'>");
}
%>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div align="middle" style='font-family: Arial;font-size: 30pt;'><b>Manufacturing Operations Dashboard</b></div>
<br>

<%
if (session.getAttribute("currentSessionUser") != null) {
	try {
		User currentUser = (User) (session.getAttribute("currentSessionUser"));
		if (currentUser.getIbmId().trim().equals("admin")) {
			out.println("<table border=0 cellpadding = 15 style='border-radius: 10px;-moz-border-radius:10px;  background-color: #EBE6E5; margin-left:auto; margin-right:auto;' >");
			out.println("<tr>");
			


			out.println("<td style='border-radius: 10px;-moz-border-radius:10px;padding:10; background-color: #EBE6E5;'><input type=\"button\" Style=\"height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;\" value=\"Admin Portal\" onClick=\"window.location='adminPortal.jsp';\"></td>");
			out.println("<td style='border-radius: 10px;-moz-border-radius:10px; background-color: #EBE6E5;'><P><INPUT TYPE='SUBMIT' Style=\"height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;\" value=\"Reload Data\" onclick=\"toggle()\"></P></td>");
			out.println("</tr>");

			out.println("</table>");
			
			out.println("			<FORM METHOD=POST ACTION=\"/DataBase1/database2\">");
			out.println(" <div align=\"center\" id = \"maintab\"> ");
			out.println(" <table  id = \"fullTable\"  style='border: 0px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; padding:10; background-color: #000000; margin-left:auto; margin-right:auto;' >");
			out.println(" <tr style='background-color: #C3BEBD;border-radius: 10px;-moz-border-radius:10px'><td><input type = \"radio\" name = \"dataCat\" value=\"isMan\"/>Manufacturing Numbers:</td>");
			out.println("  <td><textarea   name=\"mannum\" rows='1' cols='100' style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'>For Eg. 1AJGJK5,0TC6668..etc</textarea><br></td></tr>");
			out.println("  <tr style='background-color: #C3BEBD;border-radius: 10px;-moz-border-radius:10px'><td colspan=\"2\" ><input type =\"radio\" name=\"dataCat\" value=\"isTime\" checked='checked'/>Time Frame</td></tr>");
			out.println("<tr style='background-color: #C3BEBD;border-radius: 10px;-moz-border-radius:10px'><td colspan=\"2\" align= \"middle\" >Time Cut-off <input type='text' name='time' value='08'> hrs</td></tr>");
			out.println("  <tr style='background-color: #C3BEBD'><td align=\"center\" colspan=\"2\" ><input  type=\"submit\" Style=\"height:30px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;\" value=\"Submit\"></td></tr>");
			out.println("</table>");

			out.println("</div>");
			out.println("</form>");

		}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
}
		%>	
		

<table border=0 cellpadding = 15 style='border-radius: 10px;-moz-border-radius:10px;  background-color: #EBE6E5; margin-left:auto; margin-right:auto;' >

<tr>
<td style='border-radius: 10px;-moz-border-radius:10px;padding:10; background-color: #EBE6E5;'><input type="button" Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;" value="Testing Table" onClick="window.location='View2.jsp';"></td>
<td style='border-radius: 10px;-moz-border-radius:10px; background-color: #EBE6E5;'><P><INPUT TYPE='SUBMIT' Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;" value="Main Table" onClick="window.location='View1OnlyBox.jsp';"></P></td>
</tr>

</table>


<table border=0 cellpadding = 15 style='border-radius: 10px;-moz-border-radius:10px;  background-color: #EBE6E5; margin-left:auto; margin-right:auto;' >

<tr>
<td style='border-radius: 10px;-moz-border-radius:10px; background-color: #EBE6E5;'><P><INPUT TYPE='SUBMIT' Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;" value="Bug Table" onclick="window.location='bugreports.jsp';"></P></td>
</tr>

</table>

</body>
</html>