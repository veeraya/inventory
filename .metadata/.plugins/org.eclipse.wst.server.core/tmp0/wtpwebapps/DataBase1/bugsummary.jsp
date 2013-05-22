<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK rel="stylesheet" href="style.css" type="text/css">
<title>Bug Summary</title>
</head>
<body bgcolor="#EBE6E5">
<% String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	%>
<a href = "bugsummary.jsp">Summary</a>&nbsp;|&nbsp;
<a href = "bugreports.jsp">View All bugs</a>
<a href = "search.jsp">Search</a>
<br/><br/>
<table align='center' border=1 border="black" cellpadding="5" id = "sumtable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;'>
<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th>Status/Assigned to</th><th>Owner</th><th>Test Engineer</th><th>Manufacturing Engineer</th><th>MFG</th><th>Programmer</th></tr>
<%@ page language="java" import="bugreporter.*" %>
<%
BugSumTable tablefetcher = new BugSumTable();
Table table = tablefetcher.GetTable();

for(int i=0;i<3;i++)
{	
	out.println("<tr>");
	out.println("<td>");
	if(i==0)
		out.println("Open");
	if(i==1)
		out.println("Urgent");
	if(i==2)
		out.println("Closed");
	out.println("</td>");
	for(int j=0;j<5;j++)
	{	out.println("<td>");
		out.println(table.getValue(i, j));		
		out.println("</td>");
	}
	out.println("</tr>");
}
%>

</table>
</body>
</html>