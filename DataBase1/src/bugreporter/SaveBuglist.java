package bugreporter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.db2.jcc.a.b;

/**
 * Servlet implementation class SaveBuglist
 */
@WebServlet("/SaveBuglist")
public class SaveBuglist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveBuglist() {
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
		ArrayList<Bug> bl = new ArrayList<Bug>();
		
		//Bug bug = new Bug();
		//Comment com = new Comment();
		bl = bugList.getBugList();
		
		if(!(request.getParameter("Bug").replace("\n", "").replace("\r", "").equals("")))
		{
			Bug bug = new Bug();
			bug.name = request.getParameter("name").trim().replace("~", "").replace("________________","").replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "").replace("----------------------------","");
			bug.asignee = Assignee.valueOf(request.getParameter("assignee").trim());
			bug.status = Status.valueOf(request.getParameter("status"));
			bug.desc = request.getParameter("Bug").replace("~", "").replace("________________","").replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "").replace("----------------------------","");;
			bug.Filer  = request.getParameter("Filer").trim();
			bug.filedate = new Date(1245);
			bug.closedate = new Date(1245);
			bugList.AddNewBug(bug);
			bugList.SaveBugList();
			
		}
		response.setContentType("text/plain");
	     PrintWriter out1= response.getWriter();
	     response.setStatus(response.SC_MOVED_TEMPORARILY);
	     response.setHeader("Location", "bugreports.jsp");
		
		
//		
//		com.content="comtest";
//		com.name="comme";
//		com.time = new Time(1234);
//		
//		bug.asignee=Assignee.MFG;
//		bug.closedate = new  Time(1234);
//		bug.desc = "bugtest";
//		bug.filedate = new Time(1234);
//		bug.Filer = "bugsomeone";
//		bug.name = "bugname";
//		bug.status = Status.open;
//		bug.comlist = new ArrayList<Comment>();
//		bug.comlist.add(com);
//		bugList.AddNewBug(bug);
//		//bugList.SaveBugList();
//		
//		com.content="comtest2";
//		com.name="comme2";
//		com.time = new Time(1234);
//		bug.comlist.add(com);
//		bugList.AddNewBug(bug);
//		bug.name = "namebug2";
//		bugList.AddNewBug(bug);
//		bugList.SaveBugList();
		
		
	}

}
