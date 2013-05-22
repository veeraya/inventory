package summary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;

import login.User;
/**
 * Servlet implementation class SaveRemarksTable
 */
@WebServlet("/SaveRemarksTable")
public class SaveRemarksTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRemarksTable() {
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
	 * Writes data(Remark entered) to file
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int isNewRem=-1;
		String line = request.getParameter("remarks");
		Remark tempRemark = new Remark();
		tempRemark.setComment(line);
		HttpSession session = request.getSession(true);
		
		if(request.getParameter("submitbutton")!=null)
		{
		
		if(session.getAttribute("currentSessionUser")!=null)
		{
		User currentUser = (User)(session.getAttribute("currentSessionUser"));
		 isNewRem++;
		 tempRemark.setPerson(currentUser.getNickname()); 
		}

		if(isNewRem!=-1)
		{java.util.Date date= new java.util.Date();
		tempRemark.setTimestamp(new Timestamp(date.getTime()).toString());
		RemarksTable.readRemarksTable();
		RemarksTable.insertRemark(tempRemark);
		RemarksTable.saveRemarksTable();
		}
		}
		RemarksTable.readRemarksTable();
		
		for(int i=0;i<RemarksTable.remarks.size();i++)
		{
			if(request.getParameter("deleterem"+i)!=null)
			{
//				Comment com = new Comment();
//				com.name = request.getParameter("name"+i);
//				com.content = request.getParameter("comdesc"+i);
//				com.time = new Time(1234);

				RemarksTable.remarks.remove(i);
//			
				RemarksTable.saveRemarksTable();
				break;
			}
				
		}
		
		
		
		response.sendRedirect("Summary.jsp");

		
//		FileWriter fstream = new FileWriter("SummaryRemarks.txt");
//		  BufferedWriter out = new BufferedWriter(fstream);	
//		  String line = request.getParameter("remarks");
//		  out.write(line);
//		  out.close();
//		  response.setContentType("text/plain");
//	        PrintWriter out1= response.getWriter();
//	        response.setStatus(response.SC_MOVED_TEMPORARILY);
//	        response.setHeader("Location", "http://localhost:8080/Summary/Summary.jsp");

	}

}
