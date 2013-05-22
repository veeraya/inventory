package DataBase1;

import java.io.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.User;

import org.apache.jasper.tagplugins.jstl.core.Out;


import ModifyTable.ModifyTable;


/**
 * Servlet implementation class SaveRemarks
 */
@WebServlet("/SaveRemarks")
public class SaveRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRemarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static void save(HttpServletRequest request) throws IOException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormat.format(cal.getTime());
		HttpSession session = request.getSession(true);
		String nextPage = session.getAttribute("currentURLMain").toString();
		table reference = database2.values;
		String type="";
		if(nextPage.toLowerCase().contains("box"))
		{
			type="Box";
		}
		else
			type = "Mes";
		
		FileWriter fstream = new FileWriter("remarks.txt",true);
		  BufferedWriter out = new BufferedWriter(fstream);
		  String line,remark,id,priority="";
		  for(int i=1;i<reference.getRows();i++)
		  {
			  if(reference.getValue(i, 6).trim().equals("Cancelled")||reference.getValue(i, 6).trim().equals("Shipped")||reference.getValue(i, 6).trim().equals("YB")||reference.getValue(i, 6).trim().equals("JMET"))
				  continue;
			  if((type.equals("Mes")&&(reference.getValue(i, 11).trim().equals("MES Loose")||reference.getValue(i, 11).trim().equals("MES Undefined")||reference.getValue(i, 11).trim().equals("MES BOX")))||
					  (type.equals("Box")&&(reference.getValue(i, 11).trim().equals("BOX MIMIC")||reference.getValue(i, 11).trim().equals("BOX"))))
			  {
				  id = reference.getValue(i, 9);
			  remark = request.getParameter( "rem"+id+reference.getValue(i, 1) );
				if(remark!=null)
			  	{
					remark=remark.replace('\n', '~');
					remark = remark.replace("\r", "");
					if(remark.indexOf('~')==1||remark.indexOf('~')==0)
					{
						remark = remark.replaceFirst("~", "");
					}
					if(remark.indexOf('~')==1||remark.indexOf('~')==0)
					{
						remark = remark.replaceFirst("~", "");
					}
					if(remark.indexOf(' ')==0)
					{
						remark = remark.replaceFirst(" ", "");
					}
					if(remark.lastIndexOf("~")==remark.length()||remark.lastIndexOf("~")==remark.length()-1&&remark.lastIndexOf("~")!=-1)
					{
						remark = remark.substring(0,remark.lastIndexOf("~"));
					}
					if(remark.lastIndexOf(" ")==remark.length()-1&&remark.lastIndexOf(" ")!=-1)
					{
						remark = remark.substring(0,remark.lastIndexOf(" "));
					}

			  	}
				if(remark==null||remark.equals(" ")||remark.equals(""))
					remark="null";
			  priority = request.getParameter("seq"+id+reference.getValue(i, 1));
				if(priority!=null) 
					{
					priority = priority.split("\n")[0];
					priority=priority.replace('\n', '~');
					priority = priority.replace("\r", "");
					}
				if(priority==null||priority.equals("0"))
					priority = "null";
					
				
				User currentUser = (User) (session.getAttribute("currentSessionUser"));
				

				
				boolean ispresent=false;
				table rem = ReadRemarks.remarkstab;
			
				for(int k=0;k<rem.getRows();k++)
				{
					String subrem="";
					int pos = remark.indexOf(":");
					int posd = pos;
					int posn = remark.indexOf("~");
					if(pos!=-1)
					{
						subrem = remark.substring(pos+1);
						pos= subrem.indexOf(":");
						subrem = subrem.substring(pos+1);
					}
					if(reference.getValue(i, 1).equals(rem.getValue(k, 3))&&id.equals(rem.getValue(k, 0)))
						ispresent=true;
					
					if(reference.getValue(i, 1).equals(rem.getValue(k, 3))&&id.equals(rem.getValue(k, 0))&&(posn<posd&&posn!=-1))
					{
						line = id+"#"+reference.getValue(i, 1)+"^"+currentUser.getIbmId()+"*"+remark+"*"+priority+"!@#$"+curDate;

						  out.write(line);
						  out.newLine();

					}
					
					else if(reference.getValue(i, 1).equals(rem.getValue(k, 3))&&id.equals(rem.getValue(k, 0))&&(posn==-1&&posd==-1))
					{
						line = id+"#"+reference.getValue(i, 1)+"^"+currentUser.getIbmId()+"*"+remark+"*"+priority+"!@#$"+curDate;

						  out.write(line);
						  out.newLine();

					}

					
					
				}
				
				
					 
			  
			  if(ispresent==false&&remark.equals("null"))
			  {
					line = id+"#"+reference.getValue(i, 1)+"^null*"+remark+"*"+priority+"!@#$null";
					  out.write(line);
					  out.newLine();

			  }
			  
			  else if(ispresent==false&&!remark.equals("null"))
			  {
				  line = id+"#"+reference.getValue(i, 1)+"^"+currentUser.getIbmId()+"*"+remark+"*"+priority+"!@#$"+curDate;
				  out.write(line);
					  out.newLine();

			  }
			  
			  }
		  //Close the output stream
		  
		}
		  out.close();
	}

}
