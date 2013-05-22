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
 * Servlet implementation class AddCommentThread
 */
@WebServlet("/AddCommentThread")
public class AddCommentThread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BugList bugList = new BugList();
		bugList.ReadBugList();
		ArrayList<Bug> bl = new ArrayList<Bug>();
		bl = bugList.getBugList();
		for (int i = 0; i < bl.size(); i++) {
			if (request.getParameter("submitcom" + i) != null) {

				if ((!request.getParameter("name" + i).trim().equals(""))
						&& (!request.getParameter("name" + i).trim().equals(""))) {
					Comment com = new Comment();
					com.name = request.getParameter("name" + i)
							.replace("~", "").replace("________________", "")
							.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "")
							.replace("----------------------------", "");
					com.content = request.getParameter("comdesc" + i)
							.replace("~", "").replace("________________", "")
							.replace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~", "")
							.replace("----------------------------", "");
					com.time = new Time(1234);
					Bug bug = new Bug();
					bug = bugList.getBugList().get(i);
					bug.comlist.add(com);
					bugList.SaveBugList();
				}
			}
		}
		response.setContentType("text/plain");
		PrintWriter out1 = response.getWriter();
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location",
				"bugreports.jsp");
	}

}
