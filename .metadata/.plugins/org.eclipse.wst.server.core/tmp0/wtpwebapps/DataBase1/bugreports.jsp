<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<LINK rel="stylesheet" href="Style.css" type="text/css">
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
  var a = 0;
  function toggle2 ()
  {
	alert("togle2");
  };
  function test()
  {
	  for( var i=0;i<5;i++)
	  alert("tesT");
  };
/* ]]> */
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" import="bugreporter.*" %>
<%@ page language="java" import="login.*" %>
<title>Bug Reports</title>
</head>
<body bgcolor="#EBE6E5">
  <% String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	%>
	<div id="wrapper">
 <% 
if(session.getAttribute("currentSessionUser")!=null){
User currentUser = (User)(session.getAttribute("currentSessionUser"));
out.println("Hello, <a href = 'Userprofile?name="+currentUser.getNickname()+"'>"); 
out.println(currentUser.getNickname()+"</a>!" ); 

out.println("<INPUT  Style='color:white;background-color:black; position:relative; left:700px;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Logout' onClick=\"window.location='logout' \" TYPE='SUBMIT'>");
}
else 
	out.println("<INPUT  Style='color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Login' onClick=\"window.location='login.jsp' \" TYPE='SUBMIT'>");
%>
<br><br>
<a href="ViewMain.jsp"><img src="home.jpg" width="40" height="40" border="2" style='position:relative;left:800px;border-radius: 35px;-moz-border-radius:35px;border: 3px black solid;'/></a>

<h1 align = 'center'>Bug Reports</h1>

<FORM METHOD=POST ACTION="SearchBugList">
<table align='center' border=1 border="black" cellpadding="5" id = "sumtable"  RULES=NONE FRAME=BOX style='border: 1px solid black;border-radius: 10px;-moz-border-radius:10px; border-style: solid; background-color: #000000;'>
<tr style='font-family: Arial;font-size: 10pt;background-color: #C3BEBD'><th>Status/Assigned to</th><th>Owner</th><th>Test Engineer</th><th>Manufacturing Engineer</th><th>MFG</th><th>Programmer</th>
</tr>
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
	{	out.println("<td align='center'>");
		out.println("<input type='submit' style='background-color:#C3BEBD;border-width:0px;'  name='button"+i+j+"'class='link' value ='"+table.getValue(i, j)+"'/>");
		//out.println(table.getValue(i, j));		
		out.println("</td>");
	}
	out.println("</tr>");
}
%>

</table>
</FORM>
<h2 align = 'center'>Bugs</h2>
<button hidden='true'  onclick='test()'>test</button>
<%

 BugList bugList = new BugList();
 ArrayList<Bug> bl = new ArrayList<Bug>();
 bl = bugList.getBugList();
for(int i=0;i<bl.size();i++)
{
	out.println("<div id='bugtitle"+i+"'>");
	out.println("<div class = 'comment'><br/><div class='line' style='color:grey;'></div>");
	out.println("<span class='bubble'><button style='background-color:#EBE6E5;border-width:0px;' onclick="+"\"toggleVisibility('bug"+i+"')\"><img height='25' width='25' src='toggle-expand.png'/></button>");
	out.println("<FORM METHOD=POST ACTION='ChangeBug' style='display:inline;'>");
	String tempUrl= "<a href = 'Userprofile?name="+bl.get(i).getFiler()+"'>"+bl.get(i).getFiler()+"</a>"; 
	out.println("<b>"+bl.get(i).getName()+"</b > | Assigned to <b>: "+bl.get(i).getAssignee()+"</b> | Filed by <b>:"+tempUrl+"</b> | Status : <b>"+bl.get(i).getSatus()+"</b>");
	
	if(session.getAttribute("currentSessionUser")!=null)
	{
		User currentUser = (User)(session.getAttribute("currentSessionUser"));
		if(currentUser.getNickname().equals("admin")||currentUser.getNickname().equals(bl.get(i).getFiler()))
			out.println("<INPUT TYPE='submit' style='background-image: url(delete.png);position:absolute;left:950px;border: none'  HEIGHT='20' WIDTH='20' value='' BORDER='0'  id = 'deletebug"+i+"'  name = 'deletebug"+i+"'>");
	}
	out.println("</span>");
	out.println("</span></div>");
	if(i==bl.size()-2)
		out.println("<div id='bug"+i+"' hidden='true'><span class='bubble'>");
	else 
		out.println("<div id='bug"+i+"' hidden = 'false'><span class='bubble'>");
	out.println("<br/><b>Description : </b>");
	out.println(bl.get(i).desc());
	out.println("<br/><br/>");
	out.println("<b>Re-assign to : </b><select name ='assignee"+i+"'>	<option value='none'>None</option><option value='Owner'>Owner</option>	<option value='TestEng'>Test Engineer</option>	<option value='ME'>Manufacturing Engineer</option><option value='MFG'>MFG</option><option value='Programer'>Programmer</option></select><br/>");
	out.println("<b>Change Status to : </b><input name = 'status"+i+"' type = 'radio' value = 'none'>Do no change</input><input name = 'status"+i+"' type = 'radio' value = 'open'>Open</input><input name = 'status"+i+"' type = 'radio' value = 'closed'>Closed</input><input name = 'status"+i+"' type = 'radio' value = 'urgent'>Urgent</input>");
	out.println("<INPUT TYPE='SUBMIT' value ='Submit Change' name = 'submitchange"+i+"'>");
	out.println("</form><br/><b>Comments :</b><br/><div class='line' style='color:grey;'>....................................................................................................</div><br/>");
	for(int j=0;j<bl.get(i).getComList().size();j++)
	{
		out.println("<b>"+bl.get(i).getComList().get(j).getName()+"</b> said <br/>"+bl.get(i).getComList().get(j).getContent());
		out.println("<br/></b><div class='line' style='color:grey;'>....................................................................................................</div><br/>");	
	}
	out.println("<br/><b>New Comment : </b>");
	out.println("<FORM METHOD=POST ACTION='AddCommentThread'>");
	  String temp1="";
	if(session.getAttribute("currentSessionUser")!=null){
	User currentUser = (User)(session.getAttribute("currentSessionUser"));
	// out.println(currentUser.getNickname()); 
	 temp1 = currentUser.getNickname();
	}
	
	out.println("</br><b>Name : </b><br/>"+temp1+"<input type='hidden' name ='name"+i+"' value ='"+temp1+"'/><br/><b>Comment : </b><br/><textarea name = 'comdesc"+i+"'></textarea><INPUT TYPE='SUBMIT' value ='Submit Comment' name = 'submitcom"+i+"'></form>");
	out.println("</span></div>");
	
}
//out.println("_______________________________________________<br/>");
%> 
<br/><br/>
<%if(session.getAttribute("currentSessionUser")!=null){
User currentUser = (User)(session.getAttribute("currentSessionUser"));
if(currentUser.getAccess()==1)
out.println("<div>");
else
out.println("<div hidden='true'>");
}
else
out.println("<div hidden='true'>");
%>
<FORM METHOD=POST ACTION="SaveBuglist"><span class='bubble'>New Bug : <br/>
Title: 
<input type='text' name ='name'/>
<br/>
Your Name: 
<% 
String temp="";
if(session.getAttribute("currentSessionUser")!=null){
User currentUser = (User)(session.getAttribute("currentSessionUser"));
out.println("<a href = 'Userprofile?name="+currentUser.getNickname()+"'>"); 
out.println(currentUser.getNickname()+"</a>" ); 
 temp = currentUser.getNickname();
}
%>

<input type='hidden' name ='Filer' value = <%out.println("'"+temp+"'");%> />
<br/>
Assigned to: <select name="assignee">
<option value="Owner">Owner</option>
<option value="TestEng">Test Engineer</option>
<option value="ME">Manufacturing Engineer</option>
<option value="MFG">MFG</option>
<option value="Programer">Programmer</option>
</select>
<br/>
Status <input name = 'status' type = 'radio' value = 'open' CHECKED='checked'>Open<input name = 'status' type = 'radio' value = 'closed'>Closed<input name = 'status' type = 'radio' value = 'urgent'>Urgent
<br/>
Description
<textarea name = 'Bug'>
</textarea>
<input type="hidden" name="MAX_FILE_SIZE" value="500" />
<INPUT TYPE='SUBMIT' value ='Submit Bug'><br><br>
<span style="font-size:15px;color:red">All Info is required</span></span></form>
</div>
</div>
</body>
</html>