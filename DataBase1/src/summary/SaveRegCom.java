package summary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveRegCom
 */
@WebServlet("/SaveRegCom")
public class SaveRegCom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SaveRegCom() {
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
	 * Writes the data(regen, commit) to file
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	SummaryTable stable = new SummaryTable();
	ArrayList<String> regen = new ArrayList<String>();
	ArrayList<String> commit = new ArrayList<String>();
	for(int i=0;i<8;i++)
	{
		regen.add("0");	
		commit.add("0");	
	}
	
	for(int i=0;i<8;i++)
	{
		try{
		if(request.getParameter("field"+Integer.toString(i)+Integer.toString(1))!=null)
			try{
				int val = Integer.parseInt((request.getParameter("field"+Integer.toString(i)+Integer.toString(1))));
			regen.add(i,Integer.toString(val));	
			}
		catch(Exception e){
		regen.add(i,"");}
			}
		catch(Exception e)
		{
			regen.add(i, "");
			}
		try{
		if(request.getParameter("field"+Integer.toString(i)+Integer.toString(2))!=null)
		{		int valcom = Integer.parseInt(request.getParameter("field"+Integer.toString(i)+Integer.toString(2)));
			commit.add(i,Integer.toString(valcom));
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			commit.add(i,"");
		
		}
	}
	stable.writeData(regen,commit);
	 response.setContentType("text/plain");
     PrintWriter out1= response.getWriter();
     response.setStatus(response.SC_MOVED_TEMPORARILY);
     response.setHeader("Location", "Summary.jsp");
	
	
	}

}
