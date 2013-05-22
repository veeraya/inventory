package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveProfileChanges
 */
@WebServlet("/SaveProfileChanges")
public class SaveProfileChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProfileChanges() {
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
		//String newName = request.getParameter("nickname");
		String newPass = request.getParameter("pass");
		User currentUser = new User();
		HttpSession session = request.getSession(true);
		if (session.getAttribute("currentSessionUser") != null) 
		{
				 currentUser = (User) (session.getAttribute("currentSessionUser"));
		}
		if(currentUser!=null)
		{
			UserStorage.editUser(currentUser,newPass);
		}
		String page = session.getAttribute("currentURL").toString();

		response.sendRedirect(page);


}
}