<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<title>Shipped Summary</title>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="java.util.ArrayList"%><head>
<%@ page language="java" import="login.*"%>
<%@ page language="java" import="chart.*"%>
<script type="text/javascript">
window.onload=function()
		{
		onloaded();
		};
function onloaded()
{
	<%String isMes = session.getAttribute("isMes").toString();
	if(isMes.equals("1"))
		out.println("var ele = document.getElementById('initshow');");
	else
		out.println("var ele = document.getElementById('inithidden');");
	out.println("ele.hidden=true;");
	%>
	
	
};

function showMES()
{
	var ele1 = document.getElementById("initshow");
	ele1.hidden=true;
	ele1 = document.getElementById("inithidden");
	ele1.hidden=false;
	
};

function showBOX()
{
	var ele = document.getElementById("inithidden");
	ele.hidden=true;
	ele = document.getElementById("initshow");
	ele.hidden=false;
	return false;
};

</script>

</head>
<body bgcolor="#EBE6E5">
<% table ShippedSum;
ShippedSum = (table)session.getAttribute("tablesum");
%>
<% 
String getURL=request.getRequestURL().toString();
session.setAttribute("currentURL",getURL); 
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

	out.println("<INPUT  Style='position:relative;left:1300px;color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Logout' onClick=\"window.location='logout' \" TYPE='SUBMIT'>");
}
%>
<br>
<br>

<%
out.println("<INPUT  Style=\"position:relative;left:650px;color:white;background-color:#002381;border-radius: 5px;-moz-border-radius:5px;\"  Value = \"Shipped Table\" onClick=\"window.location='ViewShipped.jsp' \" TYPE='SUBMIT'>");
%>	

<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:1200px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>
<br><br>

<div id="initshow">
<div align="center" style='font-size:30px;'><b>
<%=session.getAttribute("quarter").toString()%></b></div>
<div align="center" style='font-family:Helvetica;font-size:30px'>BOX ORDERS
<input type="button" Style="border:1px;width:100px;background-color:#EBE6E5">
<INPUT Style="width: 120px;height:70px;color:white;background-color:#FF0000;border-radius: 5px;-moz-border-radius:5px;"  Value = "MES Orders" onclick = "showMES()" TYPE='button'>
</div>
<br><br><br>
<table>
<tr>
<td><input type='button' Style='border:1px;width:100px;background-color:#EBE6E5'></td>
<td>
<FORM METHOD=POST ACTION="shippedSummary">
<table cellpadding="5" border="black" cellpadding="0" id = "fullTable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;'>

<% 
out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
for(int j=0;j<ShippedSum.getColumns();j++)
{
	
	out.println("<th>"+ShippedSum.getValue(0, j)+"</th>");
	
}
out.println("</tr>");
for(int i=1;i<ShippedSum.getRows()-1;i++)
{
	out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
	for(int j=0;j<ShippedSum.getColumns();j++)
	{
		if(j==1)
			{
			 if(session.getAttribute("currentSessionUser")!=null)
	           {
	           	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	           	if(currentUser.getAccess()==1)
					out.println("<td><input type='textbox' size=\"5\" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbers"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");
	   		 else
					out.println("<td><input disabled='disabled' type='textbox' size=\"5\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbers"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");	   	     
	          	
	           }
			 else
					out.println("<td><input disabled='disabled' type='textbox' size=\"5\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbers"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");	   	     
			}
		else
		{

			out.println("<td>"+ShippedSum.getValue(i, j)+"</td>");	

		}
	}
	out.println("</tr>");
}
out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
out.println("<td>Total</td>");	
out.println("<td></td>");
for(int j=2;j<6;j++)
{
	out.println("<td>"+ShippedSum.getValue(11, j)+"</td>");	
}
out.println("<td></td>");
out.println("</tr>");
%>

</table>
<br>
<input type='button' Style='border:1px;width:80px;background-color:#EBE6E5'>
 <% if(session.getAttribute("currentSessionUser")!=null)
	           {
	           	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	           	if(currentUser.getAccess()==1)
					out.println("<INPUT  TYPE='SUBMIT' align='left' Style='height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;' Name = 'SubNums'  value='Submit Values'>");
	           }	
	           %>

</FORM>
</td><td><input type='button' Style='border:1px;width:300px;background-color:#EBE6E5'></td><td>


<%

ChartCreator chart = new ChartCreator();
chart.createSummaryChart(ShippedSum);

%>
<img src = 'viewShippedSummaryCharts'/>
</td>
</tr>
</table>
</div>
<div id="inithidden">
<div align="center" style='font-size:30px;'><b>
<%=session.getAttribute("quarter").toString()%></b>
</div>
<div align="center" style='font-family:Helvetica;font-size:30px'>MES ORDERS
<input type="button" Style="border:1px;width:100px;background-color:#EBE6E5">
<INPUT Style="width: 120px;height:70px;color:white;background-color:#FF0000;border-radius: 5px;-moz-border-radius:5px;"  Value = "BOX Orders" onclick = "showBOX()" TYPE='button'>
</div>
<br><br><br>
<table>
<%ShippedSum = (table)session.getAttribute("tablesumMes"); %>
<tr>
<td><input type='button' Style='border:1px;width:100px;background-color:#EBE6E5'></td>
<td>
<FORM METHOD=POST ACTION="shippedSummary">
<table cellpadding="5" border="black" cellpadding="0" id = "fullTable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;'>

<% 
out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
for(int j=0;j<ShippedSum.getColumns();j++)
{
	
	out.println("<th>"+ShippedSum.getValue(0, j)+"</th>");
	
}
out.println("</tr>");
for(int i=1;i<ShippedSum.getRows()-1;i++)
{
	out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
	for(int j=0;j<ShippedSum.getColumns();j++)
	{
		if(j==1)
			{
			 if(session.getAttribute("currentSessionUser")!=null)
	           {
	           	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	           	if(currentUser.getAccess()==1)
					out.println("<td><input type='textbox' size=\"5\" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbersm"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");
	   		 else
					out.println("<td><input disabled='disabled' type='textbox' size=\"5\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbersm"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");	   	     
	          	
	           }
			 else
					out.println("<td><input disabled='disabled' type='textbox' size=\"5\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' name='numbersm"+(i)+"' value="+ShippedSum.getValue(i, 1)+"></td>");	   	     
			}
		else
		{

			out.println("<td>"+ShippedSum.getValue(i, j)+"</td>");	

		}
	}
	out.println("</tr>");
}
out.println("<tr align='middle' style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
out.println("<td>Total</td>");	
out.println("<td></td>");
for(int j=2;j<6;j++)
{
	out.println("<td>"+ShippedSum.getValue(11, j)+"</td>");	
}
out.println("<td></td>");
out.println("</tr>");
%>

</table>
<br>
<input type='button' Style='border:1px;width:80px;background-color:#EBE6E5'>
 <% if(session.getAttribute("currentSessionUser")!=null)
	           {
	           	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	           	if(currentUser.getAccess()==1)
					out.println("<INPUT  TYPE='SUBMIT' align='left' Style='height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;' Name = 'SubNumsMES'  value='Submit Values'>");
	           }	
	           %>

</FORM>
</td><td><input type='button' Style='border:1px;width:300px;background-color:#EBE6E5'></td><td>


<%

chart = new ChartCreator();
chart.createSummaryChartMES(ShippedSum);

%>
<img src = 'viewShippedSummaryChartsMES'/>
</td>
</tr>
</table>

</div>
</div>
</body>
</html>