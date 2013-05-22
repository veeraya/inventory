package bugreporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeBug
 */
@WebServlet("/ChangeBug")
public class ChangeBug extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBug() {
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
		 BugList bugList = new BugList();
		 bugList.ReadBugList();
		 ArrayList<Bug> bl = new ArrayList<Bug>();
		 bl = bugList.getBugList();
		 
		for(int i=0;i<bl.size();i++)
		{
			if(request.getParameter("submitchange"+i)!=null)
			{
//				Comment com = new Comment();
//				com.name = request.getParameter("name"+i);
//				com.content = request.getParameter("comdesc"+i);
//				com.time = new Time(1234);
				Bug bug = new Bug();
				bug = bugList.getBugList().get(i);
//				bug.comlist.add(com);
				try{
				if(!(request.getParameter("assignee"+i).trim().equals("none")))
					bug.asignee = Assignee.valueOf(request.getParameter("assignee"+i));
				}
				catch (Exception e)
				{
					;
				}
				try{
				if(!request.getParameter("status"+i).trim().equals("none"))
					bug.status = Status.valueOf(request.getParameter("status"+i));
				}
				catch(Exception e){;}
				bugList.SaveBugList();
			}
				
		}
		
		for(int i=0;i<bl.size();i++)
		{
			if(request.getParameter("deletebug"+i)!=null)
			{
//				Comment com = new Comment();
//				com.name = request.getParameter("name"+i);
//				com.content = request.getParameter("comdesc"+i);
//				com.time = new Time(1234);

				bugList.RemoveBug(i);
//			
				bugList.SaveBugList();
			}
				
		}
		
		response.setContentType("text/plain");
	     PrintWriter out1= response.getWriter();
	     response.setStatus(response.SC_MOVED_TEMPORARILY);
	     response.setHeader("Location", "bugreports.jsp");
	}

}
