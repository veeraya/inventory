<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" import="chart.*" %>
<%@ page language="java" import="DataBase1.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chart for Data</title>
</head>

<body>
<%


ChartCreator chart = new ChartCreator();
chart.createCategoryChart();

%>
<img src = 'viewChart'/>



<table>
<tr><th>Status</th><th>p</th><th>z</th></tr>
<% 


ChartSummaryTable Table = new ChartSummaryTable();
table tbl = Table.createTable();

for(int i=0;i<tbl.getRows();i++)
{   
	out.println("<tr style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'>");
    for(int j=0;j<tbl.getColumns();j++)
    {
    	
            out.println("<td style='border-radius: 5px;-moz-border-radius:5px;background-color:#F5F1F0;'>");
        out.println(tbl.getValue(i,j));
        out.println("</td>");
        }
    }
out.println("</tr>");
    
%>

</table>

</body>
</html>