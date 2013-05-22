package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	 * Logs in the user after checking and redirects to the main/invalid page
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try { 
			HttpSession session = request.getSession();
			String page = "ViewMain.jsp";
			if(session.getAttribute("currentURL")!=null)
				page = session.getAttribute("currentURL").toString();
			User user = new User(); 
		user.setIbmId(request.getParameter("un"));
		user.setPassword(request.getParameter("pw")); 
		user = UserStorage.login(user); 
		if(user.valid==true) 
		{ 
		//user = UserStorage.checkUser(user.getIbmId(), user.getPassword());
		session = request.getSession(true); 
		session.setAttribute("currentSessionUser",user); 
		response.sendRedirect(page); 
		//logged-in page 
		} 
		else 
			{session.setAttribute("isValid", "FAIL");
			response.sendRedirect("login.jsp"); }
		//error page 
		} catch (Throwable theException) { theException.printStackTrace(); }
		}
		
	}


