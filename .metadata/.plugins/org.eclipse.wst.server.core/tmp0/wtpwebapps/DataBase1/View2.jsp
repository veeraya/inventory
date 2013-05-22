 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<title>Test Table</title>
<%@ page language="java" import="DataBase1.*" %>
<%@ page language="java" import="java.io.*"%>

</head>
<body>
<INPUT  Style="color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;"  Value = "View Chart for Data" onClick="window.location='testchart.jsp' " TYPE='SUBMIT'>
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:1200px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>
<br><br>
<table border=1 cellpadding=5 style='border-radius: 10px;-moz-border-radius:10px; border-style: solid; border-color: #000000; padding:10; background-color: #D7D3D2; margin-left:auto; margin-right:auto;' >
<tr style='border-radius: 10px;-moz-border-radius:10px;' ><th>Order No<th>Test Cell</th><th>Test Time</th><th>MACHMOD</th><th>MACHSER</th><th>Title</th><th>Status Time</th><th>Start Time</th><th>UserName</th></tr>


<% 


table Table = TestTable.startExec();
int rsize = Table.getRows();
int csize = Table.getColumns();

for(int i=1;i<rsize;i++)
{   
	out.println("<tr style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'>");
    for(int j=0;j<csize;j++)
    {
    	
            out.println("<td style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'>");
        out.println(Table.getValue(i,j));
        out.println("</td>");
        }
    }
out.println("</tr>");
    
%>

</table>
</body>
</html>