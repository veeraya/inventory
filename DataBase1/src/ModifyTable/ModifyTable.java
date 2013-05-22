package ModifyTable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase1.SaveRemarks;
import DataBase1.database2;
import DataBase1.table;

/**
 * Servlet implementation class ModifyTable
 */
@WebServlet("/ModifyTable")
public class ModifyTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public static int isSorted = -1;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyTable() {
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
		HttpSession session = request.getSession(true);
		table newData=null;
		String nextPage = "View1.jsp";
		if (request.getParameter("saveData")!=null)
		{
			saveData.saveDatabase();
		     nextPage = session.getAttribute("currentURLMain").toString();
		     isSorted = 1;
		}
		
		

		
		else if(request.getParameter("SubRems")!=null){
		SaveRemarks.save(request);
			nextPage =  session.getAttribute("currentURLMain").toString();
		}
		
		else if(request.getParameter("reloadbutton")!=null)
		{
			isSorted=-1;
			newData=database2.sortTable();
			nextPage =session.getAttribute("currentURLMain").toString();

		}
		
		

		session.setAttribute("values", newData);
		     response.setContentType("text/plain");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", nextPage);
	
	}
	
	
	
	public static String FilterTable(int columnNumber, String Value)
	{
		//System.out.println("Value");
		return Value;
	}
	
	
	
	
	
}
