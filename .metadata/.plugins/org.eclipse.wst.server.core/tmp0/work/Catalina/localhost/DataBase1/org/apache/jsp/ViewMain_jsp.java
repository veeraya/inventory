/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.27
 * Generated at: 2013-05-22 06:20:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DataBase1.*;
import login.*;

public final class ViewMain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write("<!DOCTYPE html PUBLIC \"-s//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"favicon1.gif\" type=\"image/x-icon\" /> \r\n");
      out.write("<title>Home Page</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("window.onload=function()\r\n");
      out.write("{\r\n");
      out.write("\ttoggle();\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var isVisible=true;\r\n");
      out.write("var isVisiblec=true;\r\n");
      out.write("function toggle() {\r\n");
      out.write("\tvar ele = document.getElementById(\"maintab\");\r\n");
      out.write("\t\r\n");
      out.write("\tif(ele.hidden==true) {\r\n");
      out.write("\t\tisVisble=true;\r\n");
      out.write("    \t\tele.hidden=false;\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\telse if(ele.hidden==false)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tisVisible=false;\r\n");
      out.write("\t\tele.hidden=true;\r\n");
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("} \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function savesession()\r\n");
      out.write("{\r\n");
      out.write("\t");

	   String mannum = request.getParameter( "mannum" );
	   session.setAttribute( "ManNums", mannum );
	   
	
      out.write("\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body bgcolor=\"#EBE6E5\">\r\n");

session.setAttribute("currentURLMain","View1OnlyBox.jsp"); 
  String getURL=request.getRequestURL().toString();
	session.setAttribute("currentURL",getURL); 
	session.setAttribute("isMes", 1);
	session.setAttribute("isMesd", 1);


      out.write('\r');
      out.write('\n');
 
if(session.getAttribute("currentSessionUser")==null)
out.println("<INPUT  Style='color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Login' onClick=\"window.location='login.jsp' \" TYPE='SUBMIT'>");
else
{User currentUser = (User)(session.getAttribute("currentSessionUser"));
out.println("<div class='font-family: Helvetica;font-size: 60pt;'>");
out.println("<b>Hello, <a href = 'Userprofile?name="+currentUser.getNickname()+"'>"); 
out.println(currentUser.getNickname()+"</a>!</b>" ); 
out.println("</div>");
}

      out.write('\r');
      out.write('\n');
 
if(session.getAttribute("currentSessionUser")!=null)
{
	out.println("<input type='button' Style='border:1px;width:1200px;background-color:#EBE6E5'>");
	out.println("<INPUT  Style='color:white;background-color:black;border-radius: 5px;-moz-border-radius:5px;'  Value = 'Logout' onClick=\"window.location='logout' \" TYPE='SUBMIT'>");
}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<div align=\"middle\" style='font-family: Arial;font-size: 30pt;'><b>Manufacturing Operations Dashboard</b></div>\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");

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
		
      out.write("\t\r\n");
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("<table border=0 cellpadding = 15 style='border-radius: 10px;-moz-border-radius:10px;  background-color: #EBE6E5; margin-left:auto; margin-right:auto;' >\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td style='border-radius: 10px;-moz-border-radius:10px;padding:10; background-color: #EBE6E5;'><input type=\"button\" Style=\"height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;valign=top;\" value=\"Testing Table\" onClick=\"window.location='View2.jsp';\"></td>\r\n");
      out.write("<td style='border-radius: 10px;-moz-border-radius:10px; background-color: #EBE6E5;'><P><INPUT TYPE='SUBMIT' Style=\"height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;\" value=\"Main Table\" onClick=\"window.location='View1OnlyBox.jsp';\"></P></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table border=0 cellpadding = 15 style='border-radius: 10px;-moz-border-radius:10px;  background-color: #EBE6E5; margin-left:auto; margin-right:auto;' >\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("<td style='border-radius: 10px;-moz-border-radius:10px; background-color: #EBE6E5;'><P><INPUT TYPE='SUBMIT' Style=\"height:40px;border-radius: 5px;-moz-border-radius:5px;background-color:#99CC33;\" value=\"Bug Table\" onclick=\"window.location='bugreports.jsp';\"></P></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
