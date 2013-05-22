package login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveAccessChanges
 */
@WebServlet("/SaveAccessChanges")
public class SaveAccessChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAccessChanges() {
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
		ArrayList<User>list = UserStorage.getUsers();
		for(int i=0;i<list.size();i++)
		{
			if(request.getParameter("delete"+i)==null)
			{
			
			if(request.getParameter("access"+i)!=null)
			{
				User user=list.get(i);
				user.setAccess(1);
				
			}
			else 
			{
				User user=list.get(i);
				user.setAccess(0);
				
			}
			}
		}
		FileWriter fWriter = new FileWriter("Users.txt");
		BufferedWriter out = new BufferedWriter(fWriter);
		out.close();
		
		for(int i=0;i<list.size();i++)
		{
			if(request.getParameter("delete"+i)==null)
			{
				UserStorage.WriteNewUser2(list.get(i));
			}	
		}
	
		response.sendRedirect("ViewMain.jsp");
		
	}

}
