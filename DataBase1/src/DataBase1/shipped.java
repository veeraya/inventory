package DataBase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class shipped
 */
@WebServlet("/shipped")
public class shipped extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public shipped() {
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
		
		shippedData getList = new shippedData();
		ArrayList<String> num  = getList.getallshipped();
		int numSelected=-1;
		for(int i=0;i<num.size();i=i+2)
		{
			
			if(request.getParameter("Q"+(num.get(i))+"-"+(num.get(i+1)))!=null)
			{
				numSelected=i;
			}
		}
		table recTable = getList.retrieveData(numSelected);
		HttpSession session = request.getSession(true);
		session.setAttribute("quarter", "Q"+(num.get(numSelected))+"-"+(num.get(numSelected+1)));
		session.setAttribute("tablereq", recTable);
		session.setAttribute("datareq", getList);
		
	     response.setContentType("text/plain");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "../DataBase1/ViewShipped.jsp");
	}

	
	
	public static void saveShipped() throws IOException
	{
		Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
		int q = month/4+1;
		
		File f=new File("Q"+Integer.toString(q)+"-"+Integer.toString(year)+" shipped.txt");
		  if(!f.exists()){
		  f.createNewFile();
		  
		  }
		//String[] contents;
		ArrayList<String> contents = new ArrayList<String>();
		String str;
		
		FileInputStream fstream = new FileInputStream("Q"+Integer.toString(q)+"-"+Integer.toString(year)+" shipped.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		while ((str=br.readLine()) != null) {
			if(str!=null)
				contents.add(str);
		}
		in.close();
		
		
		ArrayList<String> shipped = new ArrayList<String>();
		String data="";
		String id="",date="";
		table Table = database2.values;
		table supTable = database1.values;
		int k=0,isExists;
		int rsize1 = supTable.getRows();
		int csize1 = 11;

		int rsize2= Table.getRows();
		int csize2 = 29;

		for(int i=1;i<rsize2;i++)
		{  
			k=0;
			data="";
		    id = Table.getValue(i, 9);
		    date = Table.getValue(i, 1);
		    if(Table.getValue(i, 6).equals("Shipped"))
		    {
		    	for(int j=0;j<csize2;j++,k++)
		    {
		    		if(j!=7&&j!=23)
		    			data+=Table.getValue(i, j)+"@";
		    		else if(j==23)
		    			data+=Table.getValue(i, j).replace("\n", "#")+"@";
		    		else
		    			data+=Table.getValue(i, 29)+"@";
		    }
		    for(int i2=0;i2<rsize1;i2++)
		    {
		    	
		        if(supTable.getValue(i2, 0).equals(id)&&supTable.getValue(i2, 13).equals(date))
		        {
		            for(int j2=1;j2<csize1-1;j2++)
		            {
		            	data+=supTable.getValue(i2, j2)+"@";
		            }
		        }
		       
		        
		    }
		    isExists =-1;
		    for(int m=0;m<contents.size();m++)
		    {
		    	if(data.equals(contents.get(m)))
		    		isExists++;
		    }
		    if(isExists==-1)
		    	shipped.add(data);
		    }
		}

		FileWriter fstream1 = new FileWriter("Q"+Integer.toString(q)+"-"+Integer.toString(year)+" shipped.txt",true);
		  BufferedWriter out = new BufferedWriter(fstream1);
		  for(int i=0;i<shipped.size();i++)
		  {
			  out.write(shipped.get(i));
			  out.newLine();
		  }

		  out.close();
	}
	
	

}
