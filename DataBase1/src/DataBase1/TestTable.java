package DataBase1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestTable
 */
@WebServlet("/TestTable")
public class TestTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static table values;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestTable() {
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
	}

	
	  public static Connection getGemConn()
	    {
	    	String url = "jdbc:db2://sglg1mfg.sg.ibm.com:50001/GEM_INFO";
	    	Connection con=null;
	    	 try {
	             Class.forName("com.ibm.db2.jcc.DB2Driver");
	         }
	         catch( Exception e ) {
	           System.out.println("Failed to load mSQL driver.");
	           return null;
	         }
	         try{
	         con = DriverManager.getConnection(url, "chawcl", "si11yrob");
	         //CS834CC
	         }
	         catch(Exception e)
	         {
	             System.out.println("Connection error");
	         }
	         return con;
	     
	    }
	  
	  
	  public static ResultSet getGemResult(Statement selectgem) throws SQLException
	    {
	    	String query = "(SELECT TestCell , machMod, machSer,OrderNum,Opnum,Title,OPStatus,ProcesseRT, (ProcesseRT / 60) as hrs, MOD(ProcesseRT,60)as mins , "
	+"Numfails,Statustime,starttime, date(current timestamp)-date(starttime)as Elapsed_days, USERNAME from table(gem.floorstatus()) as status "
	+"where (STATUSTIME + 3 days) > CURRENT TIMESTAMP  and TESTCELL not like 'SE%' and ORDERNUM not in ('11111', '111111') "
	+"order by substr(Testcell,1,2), MACHMOD )"; 
	    	ResultSet result = selectgem.executeQuery(query);
	        return result;
	        
	    }
	  
	    public static int getRows(ResultSet result) throws SQLException
	    {
	    	int rows=1;
	        while(result.next())
	        {
	            rows++;     }
	        return rows;
	   
	    	
	    }

	  
	  
	  public static table startExec() 
	  { 
		  try{
		  Statement selectgem = null;
		  Connection gemcon = getGemConn();
		  selectgem = gemcon.createStatement();
		  ResultSet gemres = getGemResult(selectgem);
          int numgemrows = getRows(gemres);
          numgemrows--;
          table gemsres = new table(numgemrows,14);
          gemres = getGemResult(selectgem);
          int gemrow=0;
         values = new table(numgemrows,9);
          while(gemres.next())
          {
        	  gemsres.setValue(gemrow, 0, gemres.getString(4));
        	  gemsres.setValue(gemrow, 1, gemres.getString(1));
         	  gemsres.setValue(gemrow, 2, gemres.getString(5));
         	  gemsres.setValue(gemrow, 3, gemres.getString(7));        	  
        	  gemsres.setValue(gemrow, 4, gemres.getString(9));
        	  gemsres.setValue(gemrow, 5, gemres.getString(10));
        	  gemsres.setValue(gemrow, 6, gemres.getString(11));
        	  gemsres.setValue(gemrow, 7, gemres.getString(14));

         	  gemsres.setValue(gemrow, 8, gemres.getString(2));        	  
        	  gemsres.setValue(gemrow, 9, gemres.getString(3));
        	  gemsres.setValue(gemrow, 10, gemres.getString(6));
        	  gemsres.setValue(gemrow, 11, gemres.getString(12));
        	  gemsres.setValue(gemrow, 12, gemres.getString(13));
        	  gemsres.setValue(gemrow, 13, gemres.getString(15));
        	  gemrow++;
          }
          
          
          
          for(int i=0;i<numgemrows;i++)
          {
        	  values.setValue(i,0 , gemsres.getValue(i, 0));
        	  values.setValue(i,1 ,gemsres.getValue(i, 1));
        	  values.setValue(i,2 ,"("+gemsres.getValue(i, 4)+":"+gemsres.getValue(i, 5)+") "+gemsres.getValue(i, 2)+"\n"+gemsres.getValue(i, 3)+"\n"
          					+"F: "+gemsres.getValue(i, 6)+" Ep:"+gemsres.getValue(i, 7));
        	  values.setValue(i,3 , gemsres.getValue(i, 8));
        	  values.setValue(i,4 , gemsres.getValue(i, 9));
        	  values.setValue(i,5 , gemsres.getValue(i, 10));
        	  values.setValue(i,6 , gemsres.getValue(i, 11));
        	  values.setValue(i,7 , gemsres.getValue(i, 12));
        	  values.setValue(i,8 , gemsres.getValue(i, 13));
          }
          
          
          
		  }
		  catch(Exception e)
		  {
	          e.printStackTrace();
		  }
		  return values;
	  }
}
