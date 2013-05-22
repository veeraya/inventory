<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" import="summary.*" %>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="login.*" %>
<LINK rel="stylesheet" href="Style.css" type="text/css">
<title>Summary Table</title>
<script>
function showtoday()
{
	var table = document.getElementById("today");
	if(table.hidden==true)
		table.hidden=false;
	else
		table.hidden=true;
	};
function showyest()
{
	var table = document.getElementById("yest");
	if(table.hidden==true)
		table.hidden=false;
	else
		table.hidden=true;
	};
	
	
	function showtodaymes()
	{
		var table = document.getElementById("todaymes");
		if(table.hidden==true)
			table.hidden=false;
		else
			table.hidden=true;
		};
	function showyestmes()
	{
		var table = document.getElementById("yestmes");
		if(table.hidden==true)
			table.hidden=false;
		else
			table.hidden=true;
		};
		
	
	
	function init()
	{
		var table = document.getElementById("yest");
		table.hidden=true;
		table = document.getElementById("today");
		table.hidden=true;
		table = document.getElementById("yestmes");
		table.hidden=true;
		table = document.getElementById("todaymes");
		table.hidden=true;
	};
	

</script>
</head>
<body bgcolor="#EBE6E5" onload="init()">
<%
  String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
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

	out.println("<INPUT  Style='position:relative;left:1300px;color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Logout' onClick=\"window.location='logout' \" TYPE='SUBMIT'>");
}
%>
<br>
<br>

<%
String nextpage="View1Only.jsp";
if(session.getAttribute("currentURLMain")!=null)
	nextpage = session.getAttribute("currentURLMain").toString();

out.println("<INPUT  Style=\"position:relative;left:650px;color:white;background-color:#002381;border-radius: 5px;-moz-border-radius:5px;\"  Value = \"Main Table\" onClick=\"window.location='"+nextpage+"' \" TYPE='SUBMIT'>");
%>	

<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:1250px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>
<h1 align='center' >Summary</h1>
<h2 align = 'center'>

<%
SummaryTable summarytable = new SummaryTable();
table sumtable = summarytable.GetTable();
out.println("<br>BOX<br>");
out.println(summarytable.getSummaryRemarks());
out.println("<br><br>MES<br>");
out.println(summarytable.getSummaryRemarksMES());
out.println("</h2><br>");
out.println("<div align = middle><h2>BOX</h2><button onclick='showtoday()' Style='height:20px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;'>Today's Orders</button>");
out.println("<button onclick='showyest()' Style='height:20px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;'>Yesterday's Orders</button></div>");
out.println("<table align='center'><tr><td>");
out.println("<table border='black'  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' id='today' align='center' border='1'><tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th colspan='2'>Todays Orders</th></tr>");
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><td>Order No</td><td>Order</td></tr>");
String yestorder = summarytable.getNewOrdersyest();
String todorder = summarytable.getNewOrderstoday();
String yestordermes = summarytable.getNewOrdersyestmes();
String todordermes = summarytable.getNewOrderstodaymes();
String []today = todorder.split("<br>");
String []yest = yestorder.split("<br>");
int size = today.length;


for(int i=1;i<size;i++)
{
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");	
String []orders ={"",""};
if(i<today.length)
orders = today[i].split("-");
out.println("<td>");
out.println(orders[0]);
out.println("</td>");
out.println("<td>");
out.println(orders[1]);
out.println("</td>");

out.println("</tr>");

}
out.println("</table></td><td>");


out.println("<table border='black'  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' id='yest'  align='center' border='1'><tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th colspan='2'>Yesterdays Orders</th></tr>");
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><td>Order No</td><td>Order</td></tr>");
size = yest.length;

for(int i=1;i<size;i++)
{
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");	

String[] ordersy = yest[i].split("-");
out.println("<td>");
out.println(ordersy[0]);
out.println("</td>");
out.println("<td>");
out.println(ordersy[1]);
out.println("</td>");
out.println("</tr>");

}
out.println("</table></td></tr></table>");

out.println("<br>");



out.println("<div align = middle>");out.println("<h2>MES</h2><button onclick='showtodaymes()' Style='height:20px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;'>Today's Orders</button>");
out.println("<button onclick='showyestmes()' Style='height:20px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;'>Yesterday's Orders</button></div>");
out.println("<table align='center'><tr><td>");
out.println("<table border='black'  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' id='todaymes' align='center' border='1'><tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th colspan='2'>Todays Orders</th></tr>");
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><td>Order No</td><td>Order</td></tr>");


today = todordermes.split("<br>");
yest = yestordermes.split("<br>");
size = today.length;


for(int i=1;i<size;i++)
{
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");	
String []orders ={"",""};
if(i<today.length)
orders = today[i].split("-");
out.println("<td>");
out.println(orders[0]);
out.println("</td>");
out.println("<td>");
out.println(orders[1]);
out.println("</td>");

out.println("</tr>");

}
out.println("</table></td><td>");


out.println("<table border='black'  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' id='yestmes'  align='center' border='1'><tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th colspan='2'>Yesterdays Orders</th></tr>");
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><td>Order No</td><td>Order</td></tr>");
size = yest.length;

for(int i=1;i<size;i++)
{
out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");	

String[] ordersy = yest[i].split("-");
out.println("<td>");
out.println(ordersy[0]);
out.println("</td>");
out.println("<td>");
out.println(ordersy[1]);
out.println("</td>");
out.println("</tr>");

}
out.println("</table></td></tr></table>");

out.println("<br>");out.println("<br>");



%>

<div align="center">
<FORM METHOD=POST ACTION="SaveRegCom">
<table  border="black" cellpadding="5" id = "sumtable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;' >

<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th>Name</th><th>Regen</th><th>commit</th><th>Total #</th><th>Shipped #</th><th>Unshipped #</th><th>%Load</th><th>%Shipped</th><th>Shipped last month</th><th>Shipped this month</th>
</tr>


<% 

int rsize = sumtable.getRows();
int csize = sumtable.getColumns();
int totcount =0;
int shpcount=0;
int dncount=0;
int comtot=0;
int regtot=0;

for(int i=0;i<rsize;i++)
{
	totcount += Integer.parseInt(sumtable.getValue(i, 3));
	shpcount += Integer.parseInt(sumtable.getValue(i, 5));
	dncount += Integer.parseInt(sumtable.getValue(i, 4));
	if(!sumtable.getValue(i, 2).trim().equals(""))
		comtot += Integer.parseInt(sumtable.getValue(i, 2));
	if(!sumtable.getValue(i, 1).trim().equals(""))
	regtot+= Integer.parseInt(sumtable.getValue(i, 1));
}

for(int i=0;i<rsize;i++)
{	
	
	out.println("<tr style='background-color: #C3BEBD'>");

	for(int j=0;j<csize+2;j++){
		
		out.println("<td>");
		if(j==1||j==2)
		{
			
    if(session.getAttribute("currentSessionUser")!=null)
	{
	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	 if(currentUser.getAccess()==1)
		  out.println("<input type='textbox' size=\"7\" style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' value='"+sumtable.getValue(i,j)+"'"+"name = 'field"+Integer.toString(i)+Integer.toString(j)+"'>");
	 else
		  out.println("<input disabled='disabled' type='textbox' size=\"7\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' value='"+sumtable.getValue(i,j)+"'"+"name = 'field"+Integer.toString(i)+Integer.toString(j)+"'>");
		
	}
    else
		  out.println("<input disabled='disabled' type='textbox' size=\"7\" style='color:black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;resize:none;' value='"+sumtable.getValue(i,j)+"'"+"name = 'field"+Integer.toString(i)+Integer.toString(j)+"'>");
		

			
			
			
						
		}
		else if(j==6)
		{
			try{
			out.println(String.format("%.3g%n", 
((Float.parseFloat(sumtable.getValue(i, 3))*100)/Float.parseFloat(sumtable.getValue(i, 1))))+"%");
			}
			catch(Exception e)
			{
				out.println("NA");
			}
		}
			
		
		else if(j==7)
		{
			try{
			out.println(String.format("%.3g%n", 
((Float.parseFloat(sumtable.getValue(i, 4))*100/Float.parseFloat(sumtable.getValue(i, 1)))))+"%");
			}
			catch(Exception e)
			{
				out.println("NA");
			}
		}
		else if(j==8||j==9)
		{
			out.println(sumtable.getValue(i,j-2));
		}
		else
			out.println(sumtable.getValue(i,j));
		out.println("</td>");
		
	}
		
	
	out.println("</tr>");
}
out.println("<tr style='background-color: #C3BEBD'>");
out.println("<td style='background-color: #C3BEBD'>");
out.println("<b>TOTAL : </b>");
out.println("</td>");
out.println("<td>");
out.println(regtot);
out.println("</td>");
out.println("<td>");
out.println(comtot);
out.println("</td>");
out.println("<td><b>");
out.println(totcount);
out.println("</td></b>");
out.println("<td><b>");
out.println(dncount);
out.println("</td></b>");
out.println("<td style='background-color: #C3BEBD'><b>");
out.println(shpcount);
out.println("</td></b>");
out.println("<td style='background-color: #C3BEBD'yy><b>");
out.println(String.format("%.4g%n",totcount*100.0/regtot));
out.println("</td></b>");
out.println("<td style='background-color: #C3BEBD'><b>");
out.println(String.format("%.4g%n",dncount*100.0/regtot));
out.println("</td></b>");
out.println("</tr>");
	
%>


</table>
<%

%>
<br/>
<%    if(session.getAttribute("currentSessionUser")!=null)
{
	User currentUser = (User)(session.getAttribute("currentSessionUser"));
  if(currentUser.getAccess()==1)
out.println("<div align ='center'><INPUT TYPE='SUBMIT' Style='height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;' value='Submit Changes'></div>");
}
%>
</form>
</div>
<br/>
<div id = 'wrapper'>
<FORM METHOD=POST ACTION="SaveRemarksTable"><b>Submit a remark : </b><br/><br/>

<div id ='comments'>
<h2 align='center'>Comments</h2>
<%
RemarksTable remarks = new RemarksTable();
Remark remark = new Remark();
remarks.readRemarksTable();
for(int i=remarks.getSize()-1;i>=0;i--)
{
	if(i==remarks.getSize()-5)
		out.println("<div id='extra'>");
	out.println("<div class = 'comment'><br/><div class='line' style='color:grey;'><p style='border-bottom:thick dotted #B9B4B3;'></p></div>");
	
	remark = remarks.getRemarkAt(i);
	out.println("<br/>");
	out.println("Posted at "+remark.getTimestamp());
	out.println("by<b> ");
	out.println(remark.getPerson());
	if(session.getAttribute("currentSessionUser")!=null)
	{
		User currentUser = (User)(session.getAttribute("currentSessionUser"));
		if(currentUser.getNickname().equals("admin")||currentUser.getNickname().equals(remark.getPerson()))
			out.println("<INPUT TYPE='submit' style='background-image: url(delete.png);position:absolute;left:950px;border: none'  HEIGHT='20' WIDTH='20' value='' BORDER='0'  id = 'deletebug"+i+"'  name = 'deleterem"+i+"'>");
	}
	
	out.println("</b><br/></br>");
	out.println("<textarea name = 'remarks' disabled='disabled' rows = '8' cols='70' style='color: black;border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;' >"+remark.getComment().trim()+"</textarea>");
	out.println("</div>");

}
if(remarks.getSize()>5)
	out.println("</div>");
%>


<br/><br/>
<p style='border-bottom:thick dotted #000000;'></p>
</div>
<%

if(session.getAttribute("currentSessionUser")==null)
{
out.println("<div hidden = 'true'>");
 
}
%>

<div id = 'submitRemark'><br/>
Name : 
<%
String name ="";
if(session.getAttribute("currentSessionUser")!=null)
{
User currentUser = (User)(session.getAttribute("currentSessionUser"));
 out.println(currentUser.getNickname());
 
}

 %>
 <b>
<% out.println("<input type='text'  hidden='true' name ='name' value =' "+name+"'/>");%> 
</b>
<br/>
<br/>
Remark : <br/><br/><textarea name = 'remarks' rows = "7" cols="40" style='border-radius: 5px;-moz-border-radius:5px;background-color:#FFFFFF;'>
</textarea><br/><br/>
<INPUT name='submitbutton' id ='submitbutton' Style="height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;" TYPE='SUBMIT' align="middle" value = 'Submit Remark'>
</div>

</form>


</div>
</body>
</html>