<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page language="java" import="bugreporter.*" %>

<%@ page language="java" import="login.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK rel="stylesheet" href="Style.css" type="text/css">

<title>Results</title>
<script type="text/javascript">
/* <![CDATA[ */
   function toggleVisibility(controlId)
        {
            var control = document.getElementById(controlId);
            if(control.hidden == true)
                control.hidden = false;
            else if(control.hidden==false) 
                control.hidden = true;
              
        };
  
  function toggle2 ()
  {
	alert("togle2");
  };
  function test()
  {
	  for( i=0;i<5;i++)
	  alert("tesT");
  };
/* ]]> */
</script>

</head>
<body bgcolor="#EBE6E5">
<% String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	%>
<div id="wrapper">
<a href = "bugreports.jsp">View All bugs</a><br/><br>
<FORM METHOD=POST ACTION="SearchBugList">
<table align='center' border=1 border="black" cellpadding="5" id = "sumtable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;'>
<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th>Status/Assigned to</th><th>Owner</th><th>Test Engineer</th><th>Manufacturing Engineer</th><th>MFG</th><th>Programmer</th></tr>
<%@ page language="java" import="bugreporter.*" %>
<%
BugSumTable tablefetcher = new BugSumTable();
Table table = tablefetcher.GetTable();

for(int i=0;i<3;i++)
{	
	out.println("<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'>");
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
		out.println("<input type='submit'  name='button"+i+j+"'class='link' value ='"+table.getValue(i, j)+"'>");
		//out.println(table.getValue(i, j));		
		out.println("</td>");
	}
	out.println("</tr>");
}
%>

</table>
</FORM>


<%

	ArrayList<Bug> bl = new ArrayList<Bug>();
	ArrayList<Integer> bugIds = new ArrayList<Integer>();
	try{
	if(session.getAttribute("bugIds")!=null)
		bugIds = (ArrayList<Integer>)session.getAttribute("bugIds");
	if((session.getAttribute("toShow")!=null)&&(bugIds.size()!=0))
	{
		bl = (ArrayList<Bug>)session.getAttribute("toShow");
		System.out.println(bugIds.get(0).intValue());
		for(int i=0; i < bl.size(); i++)
		{
			out.println("<div id='bugtitle"+i+"' >");
			out.println("<div class = 'comment'><br/><div class='line' style='color:grey;'></div>");
			out.println("<span class='bubble'><button style='background-color:#EBE6E5;border-width:0px;' onclick="+"\"toggleVisibility('bug"+i+"')\"><img height='25' width='25' src='toggle-expand.png'/></button>");
			out.println("<b>"+bl.get(i).getName()+"</b > | Assigned to <b>: "+bl.get(i).getAssignee()+"</b> | Filed by <b>:"+bl.get(i).getFiler()+"</b> | Status : <b>"+bl.get(i).getSatus()+"</b></span>");
			out.println("</div>");
			if(i==bl.size()-2)
				out.println("<div id='bug"+i+"' hidden='true'><span class='bubble'>");
			else 
				out.println("<div id='bug"+i+"' hidden = 'false'><span class='bubble'>");
			out.println("<br/><b>Description : </b>");
			out.println(bl.get(i).desc());
			out.println("<br/><br/>");
			out.println("<FORM METHOD=POST ACTION='ChangeBug'>");
			out.println("Re-assign to: <select name ='assignee"+bugIds.get(i)+"'>	<option value='none'>None</option><option value='Owner'>Owner</option>	<option value='TestEng'>Test Engineer</option>	<option value='ME'>Manufacturing Engineer</option><option value='MFG'>MFG</option><option value='Programer'>Programmer</option></select><br/>");
			out.println("Change Status to <input name = 'status"+bugIds.get(i)+"' type = 'radio' value = 'none'>Do no change</input><input name = 'status"+bugIds.get(i)+"' type = 'radio' value = 'open'>Open</input><input name = 'status"+bugIds.get(i)+"' type = 'radio' value = 'closed'>Closed</input><input name = 'status"+bugIds.get(i)+"' type = 'radio' value = 'urgent'>Urgent</input>");
			out.println("<INPUT TYPE='SUBMIT' value ='Submit Change' name = 'submitchange"+bugIds.get(i)+"'>");
			out.println("</form><br/><b>Comments :</b><br/><br/>");
			for(int j=0;j<bl.get(i).getComList().size();j++)
			{
				out.println("<b>"+bl.get(i).getComList().get(j).getName()+"</b> said <br/>"+bl.get(i).getComList().get(j).getContent());
				out.println("<br/></b><div class='line' style='color:grey;'>....................................................................................................</div><br/>");	
			}
			out.println("<br/><b>New Comment : </b>");
			out.println("<FORM METHOD=POST ACTION='AddCommentThread'>");
			out.println("</br><b>Name : </b><br/><input type='text' name ='name"+bugIds.get(i)+"'/><br/><b>Comment : </b><br/><textarea name = 'comdesc"+bugIds.get(i)+"'></textarea><INPUT TYPE='SUBMIT' value ='Submit Comment' name = 'submitcom"+bugIds.get(i)+"'></form></span>");
			out.println("</div>");
		}
		//out.println("_______________________________________________<br/>");

		}
	
	else
		{
		out.println("<br>");
		out.println("<div align='middle' style='font-family:Helvetica;color:blue;font-size:30px;' >");
		out.println("No Results Found");
		out.println("</div>");}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
//             setNotification _sN = new setNotification();
//              if(session.getAttribute("error")!=null){
//                 _sN = (setNotification)session.getAttribute("error");
//              }
%>
<br/><br/>


</div>
</body>
</html>