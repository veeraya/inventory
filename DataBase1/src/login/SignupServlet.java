package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
	 * Calls the UserStorage methods to add a new user
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User temp = new User();
		temp.setIbmId(request.getParameter("id").trim());
		temp.setNickname(request.getParameter("nm").trim());
		temp.setPassword(request.getParameter("pass").trim());
		String page="";
		HttpSession session = request.getSession();

		if(temp.getIbmId().equals("")||temp.getNickname().equals("")||temp.getPassword().equals(""))
		{
			session.setAttribute("isAllowed", "FAIL");
			page = "signup.jsp";
		}
			
		else
			{
			String result = UserStorage.checkIfExists(temp.getIbmId(), temp.getNickname());
			if(result.equals(""))
			{
				UserStorage.WriteNewUser(temp);
				User user = new User(); 
				user = temp;
				session.setAttribute("currentSessionUser",user); 
				page = session.getAttribute("currentURL").toString();
			}
			else
				{session.setAttribute("isExists", "FAIL");
				page = "signup.jsp";
				}
			}
		response.sendRedirect(page); 
	}

}
