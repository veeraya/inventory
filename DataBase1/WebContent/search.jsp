<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon1.gif" type="image/x-icon" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body bgcolor="#EBE6E5">
<% String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	%>
<a href = "bugsummary.jsp">Summary</a>&nbsp;|&nbsp;
<a href = "bugreports.jsp">View All bugs</a>&nbsp;|&nbsp;
<a href = "search.jsp">Search</a><br/><br/>
<FORM METHOD=POST ACTION="SearchBugList">
Keywords : <input type = 'text' name = 'keywords'><br/><br/>
Filer : <input type = 'text' name = 'filer'><br/><br/>
Status : <input name = 'status' type = 'radio' value = 'all'>All<input name = 'status' type = 'radio' value = 'open'>Open<input name = 'status' type = 'radio' value = 'closed'>Closed<input name = 'status' type = 'radio' value = 'urgent'>Urgent<br/><br/>
Assigned to: <select name="assignee">
<option value="Owner">Owner</option>
<option value="TestEng">Test Engineer</option>
<option value="ME">Manufacturing Engineer</option>
<option value="MFG">MFG</option>
<option value="Programer">Programmer</option>
</select>
<INPUT TYPE='SUBMIT' value ='Submit Bug'></form>
</body>
</html>