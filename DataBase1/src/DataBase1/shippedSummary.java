package DataBase1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import login.User;
import login.UserStorage;

/**
 * Servlet implementation class shippedSummary
 */
@WebServlet("/shippedSummary")
public class shippedSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shippedSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession(true);
		String name = session.getAttribute("quarter").toString();
		String filename = name;
		session.setAttribute("filename", filename);
		seeShipSum Summary = new seeShipSum();
		table Summ = Summary.getSummary(filename);
		table SummMes = Summary.getSummaryMes(filename);
		session.setAttribute("tablesum", Summ);
		session.setAttribute("tablesumMes", SummMes);
		response.sendRedirect("viewshipSummary.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		ArrayList<String> numbers = new ArrayList<String>();
		if(request.getParameter("SubNums")!=null)
		{
			session.setAttribute("isMes", 0);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbers"+i));
				
				}
			String filename = session.getAttribute("filename").toString();
			String summfile = filename+" shippedsum.txt";

			
			FileWriter fstream = new FileWriter(summfile);
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i=0;i<numbers.size();i++)
					{
					out.write(numbers.get(i));
					out.newLine();
					}
				out.close();
			}

		
		if(request.getParameter("SubNumsMES")!=null)
		{
			session.setAttribute("isMes", 1);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbersm"+i));
				
				}
			String filename = session.getAttribute("filename").toString();
			String summfile = filename+" shippedsumMES.txt";

			
			FileWriter fstream = new FileWriter(summfile);
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i=0;i<numbers.size();i++)
					{
					out.write(numbers.get(i));
					out.newLine();
					}
				out.close();
			}
			response.sendRedirect("shippedSummary"); 
	}

}
