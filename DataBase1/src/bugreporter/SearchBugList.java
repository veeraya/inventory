package bugreporter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchBugList
 */
@WebServlet("/SearchBugList")
public class SearchBugList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public SearchBugList() {
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
		//		BugList bugList = new BugList();
		//		bugList.ReadBugList();
		//		ArrayList<Bug> bl = new ArrayList<Bug>();
		//		bl = bugList.getBugList();
		//		ArrayList<Bug> toShow = new ArrayList<Bug>();
		//		ArrayList<Integer> hits = new ArrayList<Integer>();
		//		for(int i=0;i<bl.size();i++)
		//		{
		//			if(bl.get(i).desc.contains(request.getParameter("keywords")))
		//				hits.set(i,hits.get(i)+1);
		//			if(bl.get(i).name.contains(request.getParameter("keywords")))
		//				hits.set(i,hits.get(i)+1);
		//			if(bl.get(i).Filer.contains(request.getParameter("filer")))
		//				hits.set(i,hits.get(i)+1);
		//			if(bl.get(i).status.equals(Status.valueOf(request.getParameter("status"))))
		//				hits.set(i,hits.get(i)+1);
		//			if(bl.get(i).asignee.equals(Assignee.valueOf(request.getParameter("assignee"))))
		//				hits.set(i,hits.get(i)+1);		
		//						
		//		}
		//		
		BugList bugList = new BugList();
		bugList.ReadBugList();
		ArrayList<Bug> bl = new ArrayList<Bug>();
		bl = bugList.getBugList();
		ArrayList<Bug> toShow = new ArrayList<Bug>();
		ArrayList<Integer> bugIds = new ArrayList<Integer>();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(request.getParameter("button"+i+j)!=null)
				{
					for(int k=0;k<bl.size();k++)
					{
						if(bl.get(k).status.ordinal()==i&&bl.get(k).asignee.ordinal()==j)
						{	toShow.add(bl.get(k));
						bugIds.add(k);
						}
					}
				}
			}

		}


		HttpSession session = request.getSession(true);
		session.setAttribute("toShow", toShow);
		session.setAttribute("bugIds", bugIds);
		response.sendRedirect("results.jsp");




	}

}
