package DataBase1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModifyTable.ModifyTable;
import ModifyTable.saveData;

//import com.ibm.security.cmp.PKIStatusInfo;

/**
 * Servlet implementation class database2
 */
@WebServlet("/database2")
public class database2 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<String> OrderNum = new ArrayList<String>();
    private static ArrayList<String> ManuNum = new ArrayList<String>();
    private static ArrayList<String> mannums = new ArrayList<String>();
    private static int getBy=1;
    private static int rowNum;
    public static table values;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public database2() {
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
    	mannums = new ArrayList<String>();
    	String[] manNums = new String[2];
    	getBy=1;
    	String number="";
    	
   // TODO Auto-generated method stub
    	String dataCat = String.valueOf(request.getParameter("dataCat"));
    	if (dataCat.equals("isMan"))
		{
			getBy=0;
		}

    	if (dataCat.equals("isTime"))
		{
			getBy=1;
		}

    	String nums = request.getParameter("mannum");
    	HttpSession session = request.getSession(true);
    	session.setAttribute("time", request.getParameter("time"));
    	nums=nums.trim();
    	try{
    		manNums = nums.split(",");
    	for(int i=0;i<manNums.length;i++)
    	{
    		number = manNums[i];
    		number = number.replace("'", "");
    		number = number.replace(",", "");
    		mannums.add(manNums[i]);
    	}
    	}
    	catch(Exception e)
    	{
		     response.setContentType("text/plain");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", "../DataBase1/View1.jsp");	
    	}
    	

	     response.setContentType("text/plain");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", "../DataBase1/View1.jsp");
    	
    }

    

    public static Connection getPSSDCon()
    {
    	String url = "jdbc:db2://b01cirdb042.ahe.pok.ibm.com:3735/ODRPTDB";
    	Connection con=null;
    	 try {
             Class.forName("com.ibm.db2.jcc.DB2Driver");
         }
         catch( Exception e ) {
           System.out.println("Failed to load mSQL driver.");
           return null;
         }
         try{
         con = DriverManager.getConnection(url, "chawcl", "may55day");
         }
         catch(Exception e)
         {
             System.out.println("Connection error");
         }
         return con;
     
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
         }
         catch(Exception e)
         {
             System.out.println("Connection error");
         }
         return con;
     
    }

    
    
    public static Connection getConn(Statement select)
    {  
        String url = "jdbc:db2://qryprod.rchland.ibm.com:446/QRYPROD";

        Connection con=null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        }
        catch( Exception e ) {
          System.out.println("Failed to load mSQL driver.");
          return null;
        }
        try{
        con = DriverManager.getConnection(url, "chawcl", "candy4cc");
        }
        catch (Exception exception) {
            exception.printStackTrace();

        }
        return con;
    }

    

    public static Connection getCustConn()
    {  
        String url = "jdbc:db2://MEUB5.vipa.uk.ibm.com:446/UKMFG5";

        Connection con=null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        }
        catch( Exception e ) {
          System.out.println("Failed to load mSQL driver.");
          return null;
        }
        try{
        con = DriverManager.getConnection(url, "CS834CC", "may55day");
        }
        catch(Exception e)
        {
            System.out.println("Connection error");
        }
        return con;
    }

    
 
    
    
    public static ArrayList<String> getManArrayList()
    {
    	return ManuNum;
    }
    public static String  dateParse(String shpDt)
    {
    	
        String yr="",mon="",date="";
    	String dt="0";
    	try{
    	if(shpDt.contains("/"))
    		return shpDt;
    	
    	else if (shpDt.contains("-"))
    	{
            yr = shpDt.substring(2, 4);
            mon = shpDt.substring(5,7);
            date = shpDt.substring(8, 10);
            dt = mon+"/"+date+"/"+yr;
    	}
    	

    	else if(!shpDt.equals(""))
        {
            yr = shpDt.substring(2, 4);
            mon = shpDt.substring(4,6);
            date = shpDt.substring(6, 8);
            dt = mon+"/"+date+"/"+yr;
        }
        
    	}
    	catch(Exception e)
    		{

    		}
    	return dt;
    }

    
    public static int sortbyStatus(String first,String second)
    {
    	String [] status= {"GEODIS","COMPLETE","CPC","VI30","TEST POST","TEST","PRE-TEST","ALTER","BUILD","KIT","NOT RELEASE"};
    	String firstUpp = first.toUpperCase();
    	String secondUpp = second.toUpperCase();
    	int fpos=15;
    	int spos=15;
    	for(int i=0;i<status.length;i++)
    	{
    		if(status[i].equals(firstUpp))
    			fpos=i;
    		if(status[i].equals(secondUpp))
    			spos=i;
    	}
    	if(fpos<spos)
    		return 1;
    	else if(fpos==spos)
    		return 0;
    	
    	return -1;
    }
    

	public static table sortTable() {
		String[] temp = new String[30];
		int topnum = 0, lowernum = 0;
		for (int i = 1; i < values.getRows() - 1; i++) {
			for (int j = i + 1; j < values.getRows(); j++) {
				try {
					String up = values.getValue(i, 29);
					up = up.replace("\n", "");
					up = up.replace("\r", "");
					String low = values.getValue(j, 29);
					low = low.replace("\n", "");
					low = low.replace("\r", "");

					topnum = Integer.parseInt(up);
					lowernum = Integer.parseInt(low);
					if (values.getValue(j, 29) != null
							&& values.getValue(i, 29) != null
							&& lowernum > topnum) {
						for (int k = 0; k < 30; k++) {
							temp[k] = values.getValue(j, k);
						}
						for (int k = 0; k < 30; k++) {
							values.setValue(j, k, values.getValue(i, k));
						}
						for (int k = 0; k < 30; k++) {
							values.setValue(i, k, temp[k]);
						}
					} else if (values.getValue(j, 29) != null
							&& values.getValue(i, 29) != null
							&& lowernum == topnum) {
						if (values.getValue(j, 24) != null
								&& values.getValue(i, 24) != null
								&& sortbyStatus(values.getValue(j, 24),values.getValue(i, 24))
								> 0) {

							for (int k = 0; k < 30; k++) {
								temp[k] = values.getValue(j, k);
							}
							for (int k = 0; k < 30; k++) {
								values.setValue(j, k, values.getValue(i, k));
							}
							for (int k = 0; k < 30; k++) {
								values.setValue(i, k, temp[k]);
							}

						} else if (values.getValue(j, 24) != null
								&& values.getValue(i, 24) != null
								&& sortbyStatus(values.getValue(j, 24),values.getValue(i, 24))
								== 0) {

							if (values.getValue(j, 18) != null
									&& values.getValue(i, 18) != null
									&& values.getValue(j, 18).compareTo(
											values.getValue(i, 18)) > 0) {

								for (int k = 0; k < 30; k++) {
									temp[k] = values.getValue(j, k);
								}
								for (int k = 0; k < 30; k++) {
									values.setValue(j, k, values.getValue(i, k));
								}
								for (int k = 0; k < 30; k++) {
									values.setValue(i, k, temp[k]);
								}

							} else if (values.getValue(j, 18) != null
									&& values.getValue(i, 18) != null
									&& values.getValue(j, 18).compareTo(
											values.getValue(i, 18)) == 0) {
								if (values.getValue(j, 16) != null
										&& values.getValue(i, 16) != null
										&& values.getValue(j, 16).compareTo(
												values.getValue(i, 16)) > 0) {

									for (int k = 0; k < 30; k++) {
										temp[k] = values.getValue(j, k);
									}
									for (int k = 0; k < 30; k++) {
										values.setValue(j, k,
												values.getValue(i, k));
									}
									for (int k = 0; k < 30; k++) {
										values.setValue(i, k, temp[k]);
									}

								}

							}

						}

					}
				} catch (Exception e) {
				}
			}
		}
		return values;
	}


    public static void closeCon(Statement select, Connection con) throws SQLException
    { select.close();
    con.close();
        }
    
    public static ResultSet getCustomer(Statement select, ArrayList<String> Order) throws SQLException
    {
    	String query = "select con, Cust_Name from JBR.CORD_C a left join JBR.JBRV9550 b on Cno = Cust_Number "
+"where Ordou = Ibm_Leg_Country_No "
+"and con in ('"+Order.get(0)+"'";
    	for(int i=1;i<Order.size();i++)
    	{
    		query+=",'"+ Order.get(i) +"'";
    	}    
    	query += ")";
   ResultSet result = select.executeQuery(query);
   return result;
    }
    

    public static ResultSet getResult(Statement select, String id) throws SQLException
    {
    	
    	
    	try{
    		 String query = "SELECT '' as Quarter, '' as Load_Date, '' as Load_Week,  '' as Shipped_Date, '' as Cancelled_Date, '' as Status_Week,  '' as Category, tlidss as Order_Seq, '' as Test_Cell#, tlmfgn as Mfg#,  ororno as Order#, Tltypz as Order_Type,"
    			        +" Ormmdl as Model, Orsyst as Model#, Tlpprl as Prod_Line, '' as Customer, Tlctry as Country, Orschd as PSSD, '' as RSSD, '' as Config_F_N_D, '' as FOE, '' as Test_Time, tlcods as Status, '' as Remark,"
    			        +" '' as Kit_Shorts, '' as Installation_Shorts, '' as Genuine_Shorts"
    			         +" from mfs2p010a.fcsptl10 a "
    			        +" left join mfs2p010a.FCSPWu10 b on wumfgn=tlmfgn"
    			        +" left join mfs2p010a.FCSPOR10 c on tlmfgn=ormfgn "
    			        +" left join dcs1p010.lofpjt10 d on jtorno=Wuworn "

    			        //+" where tlschd >'2012-04-01'"
    			        +" where tlschd between current date - 30 days and current date + 60 days"
    			        +" and tlmfgn !='ADOPTED'"
    			        +" and (tlidss in ('0000') or tlidss like '#%')"
    			        +" and tlmfgn in "
    			        +"('"+ mannums.get(0)+"'";
        for(int i=1;i<mannums.size();i++)
        {
        	query+=",'"+ mannums.get(i) +"'";
        }        
        	query+=")"
    			        //+"('0MK1LMH','1AJGJK2','1AJGJK3','1AJGJK4','1AJGJK5','1AJGJK6','1AJGJK9','0TC6668','0MV643G','0TC6692','0FN94B8','0FN94CB','0OC98UY')"
    			        +"order by Order#,Status";
    		 ResultSet result = select.executeQuery(query);
    	        return result;
    	}

    	catch(Exception e)
        {String query = "SELECT distinct '' as Quarter, '' as Load_Date, '' as Load_Week,  '' as Shipped_Date, '' as Cancelled_Date, '' as Status_Week,  '' as Category, tlidss as Order_Seq, '' as Test_Cell#, tlmfgn as Mfg#,  ororno as Order#, Tltypz as Order_Type,"
        +" Ormmdl as Model, Orsyst as Model#, Tlpprl as Prod_Line, '' as Customer, Tlctry as Country, Orschd as PSSD, '' as RSSD, '' as Config_F_N_D, '' as FOE, '' as Test_Time, tlcods as Status, '' as Remark,"
        +" '' as Kit_Shorts, '' as Installation_Shorts, '' as Genuine_Shorts"
         +" from mfs2p010a.fcsptl10 a "
        +" left join mfs2p010a.FCSPWu10 b on wumfgn=tlmfgn"
        +" left join mfs2p010a.FCSPOR10 c on tlmfgn=ormfgn "
        +" left join dcs1p010.lofpjt10 d on jtorno=Wuworn "

        //+" where tlschd >'2012-04-01'"
        +" where tlschd between current date - 60 days and current date + 60 days"
        +" and tlmfgn !='ADOPTED'"
        +" and (tlidss in ('0000') or tlidss like '#%')"
        +" and tlmfgn in "
        +"('0BA9FGD','0GY4LGK')"
        //+"('0MK1LMH','1AJGJK2','1AJGJK3','1AJGJK4','1AJGJK5','1AJGJK6','1AJGJK9','0TC6668','0MV643G','0TC6692','0FN94B8','0FN94CB','0OC98UY')"
        +"order by Mfg#,Status";
        ResultSet result = select.executeQuery(query);
        return result;
        }
      
      
        }
   
    public static ResultSet getResultDate(Statement select, String id) throws SQLException
    {
    	
    	

    		 String query = "SELECT distinct '' as Quarter, '' as Load_Date, '' as Load_Week,  '' as Shipped_Date, '' as Cancelled_Date, '' as Status_Week,  '' as Category, tlidss as Order_Seq, '' as Test_Cell#, tlmfgn as Mfg#,  ororno as Order#, Tltypz as Order_Type,"
    			        +" Ormmdl as Model, Orsyst as Model#, Tlpprl as Prod_Line, '' as Customer, Tlctry as Country, Orschd as PSSD, '' as RSSD, '' as Config_F_N_D, '' as FOE, '' as Test_Time, tlcods as Status, '' as Remark,"
    			        +" '' as Kit_Shorts, '' as Installation_Shorts, '' as Genuine_Shorts"
    			         +" from mfs2p010a.fcsptl10 a "
    			        +" left join mfs2p010a.FCSPWu10 b on wumfgn=tlmfgn"
    			        +" left join mfs2p010a.FCSPOR10 c on tlmfgn=ormfgn "
    			        +" left join dcs1p010.lofpjt10 d on jtorno=Wuworn "

    			        +" where tlschd between current date - 30 days and current date + 60 days"
    			        +" and tlmfgn !='ADOPTED'"
    			        +" and (tlidss in ('0000') or tlidss like '#%') and tlidss=oridss "
    			        +"order by Mfg#, Order#,Status";

    		 ResultSet result = select.executeQuery(query);
    	        return result;


      
        }
   
    
    
    public static ResultSet getGemResult(Statement selectgem) throws SQLException
    {
    	String query = "(SELECT TestCell , machMod, machSer,OrderNum,Opnum,Title,OPStatus,ProcesseRT, (ProcesseRT / 60) as hrs, MOD(ProcesseRT,60)as mins , "
+"Numfails,Statustime,starttime, date(current timestamp)-date(starttime)as Elapsed_days, USERNAME from table(gem.floorstatus()) as status "
+"where (STATUSTIME + 3 days) > CURRENT TIMESTAMP  and TESTCELL not like 'SE%' and ORDERNUM not in ('11111', '111111') "
+"order by Statustime desc,substr(Testcell,1,2), MACHMOD )"; 
    	ResultSet result = selectgem.executeQuery(query);
        return result;
        
    }
    
    
    public static ResultSet getPSSDResult(Statement selectgem, ArrayList<String> Order) throws SQLException
    {
    	String query = "(select meson as Order#, Schsh as PSSD , Reqsd as RSSD,Fofin as FOE from "
+"DB2COATG.COJ1J0T0"
+" WHERE (Meson in ('" + Order.get(0)+"'";
for(int i=1;i<Order.size();i++)
{
	query+=",'"+ Order.get(i) +"'";
}
query += "))) union "
+"(select Plorn as Order#, Boxsh as PSSD , Aldpf as RSSD,Fofin as FOE from "
+"DB2COATG.COS1S0T0 "
+"WHERE (Plorn in ('"+Order.get(0)+"'";
for(int i=1;i<Order.size();i++)
{
	query+=",'"+ Order.get(i) +"'";
}
query +=")))"; 
    	ResultSet result = selectgem.executeQuery(query);
        return result;
        
    }
    
    
    public static ResultSet getLoadCancelDate(Statement select, ArrayList<String> Man) throws SQLException
    {
        String query = "with raw_Load as ("

+"select Jxmfgn,"
+"CASE   "
 +"        WHEN  time(Jxcsts) >= '11:00:00'  THEN date(Jxcsds) + 1 day"
  +"      ELSE date(Jxcsds)"
   +"   END as LOAD_DATE " 

+"from MFS2P010A.FCSPJX10"

+" where Jxerms ='MB13 - Order Interfaced' "
+"and Jxmfgn in ('"+ Man.get(0)+"'";
        for(int i=1;i<Man.size();i++)
        {
        	query+=",'"+ Man.get(i) +"'";
        }        
query+=")"

+" ) ,"

+" raw_Cancel as ("

+" select Jxmfgn,"
+" CASE   "
 +      "  WHEN  time(Jxcsts) >= '11:00:00'  THEN date(Jxcsds) + 1 day "
 +     "  ELSE date(Jxcsds) "
 +    " END as Cancel_DATE " 

+" from MFS2P010A.FCSPJX10 "

+"where (Jxerms like '%System Cancelled%' or Jxerms like '%CANCELLED SYSTEM%' )"
+"and Jxmfgn in  ('"+ Man.get(0)+"'";
        for(int i=1;i<Man.size();i++)
        {
        	query+=",'"+ Man.get(i) +"'";
        }        
query+=")"

+") ,"


+"raw_min as (select a.Jxmfgn, Load_Date, min(CANCEL_DATE) as cancel_date"
+" from raw_Load a left join raw_Cancel b on a.Jxmfgn=b.Jxmfgn "
+"and Load_date<=Cancel_date "
+"group by a.JxMfgn, Load_date "
+")" 
+" select Jxmfgn, min(Load_Date) as Load_Date, week(min(Load_Date)) as Load_Week,   cancel_date, week(cancel_date) as Status_Week from raw_min "
+"group by Jxmfgn, cancel_date";
    	
        ResultSet result = select.executeQuery(query);
        return result;
        }

    
    public static String getCat(String orderSeq, String PSSD, String RSSD, String RPDS, String canDate, String orderNo)
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();

        Calendar prevMonth = (Calendar) cal.clone();
        prevMonth.add(Calendar.MONTH, -1);
        String curDate = dateFormat.format(cal.getTime());
    	String prevM =  dateFormat.format(prevMonth.getTime());
        String mon  = curDate.substring(5,7);
        int monthint = Integer.parseInt(mon);
        String prevmon = prevM.substring(5,7);
    	String dat = curDate.substring(8,10);
    	if(!canDate.equals(""))
    		return "Cancelled";
    	
    	if(orderSeq.indexOf('#')==0 && !canDate.equals(""))
    		return "Cancelled";
    	else if(RPDS.equals("29"))
    		return "Shipped";
    	else if(orderSeq.toUpperCase().indexOf('J')==0)
    		return "JMET";
    	else if((orderNo.toUpperCase().indexOf("YB")==0)||(orderNo.toUpperCase().indexOf("YK")==0)||(orderNo.toUpperCase().indexOf("Z7")==0))
    		return "YB";
    	try{
       	String PSSDmon = PSSD.substring(0, 2);
    	String RSSDmon = RSSD.substring(0, 2);
    	String PSSDdat = PSSD.substring(3, 5);
    	int PSSDmonint = Integer.parseInt(PSSDmon);
    	int RSSDmonint = Integer.parseInt(RSSDmon);
    	int PSSDdatint = Integer.parseInt(PSSDdat);
       	
    	int nextPSSDmonth = PSSDmonint+1;
    	int nextmonint = monthint+1;
    	if(nextmonint==13)
		nextmonint=1;
    	if(nextPSSDmonth==13)
    		nextPSSDmonth=1;
    	if(PSSDmon.equals(mon)&&(RSSDmon.compareTo(mon)<=0))
    		return "Good";
    	else if(((PSSDdatint<15 && PSSDmonint==nextmonint)||PSSDmonint<nextmonint) && RSSDmonint<=monthint)
    		return "Pend";
    	else if(PSSDmon.compareTo(mon)<0||(PSSDdat.compareTo(dat)<0)&&PSSDmon.compareTo(mon)==0)
    		return "Overdue";
    	else if((PSSDdatint>15 && PSSDmonint<monthint)||(RSSDmonint==nextmonint))
    		return "Bad";
    	
    	return "Bad";}
    	catch(Exception e){
    		return "Bad";
    	}
    	
    }
    
    
    public static String getCat(String orderSeq, String PSSD, String RSSD, String canDate, String orderNo)
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();

        Calendar prevMonth = (Calendar) cal.clone();
        prevMonth.add(Calendar.MONTH, -1);
        String curDate = dateFormat.format(cal.getTime());
    	String prevM =  dateFormat.format(prevMonth.getTime());
        String mon  = curDate.substring(5,7);
        String prevmon = prevM.substring(5,7);
    	String dat = curDate.substring(8,10);
    	if(!canDate.equals(""))
    		return "Cancelled";
    	
    	if(orderSeq.indexOf('#')==0 && !canDate.equals(""))
    		return "Cancelled";

    	else if(orderSeq.indexOf('J')==0)
    		return "JMET";
    	else if((orderNo.indexOf("YB")==0)||(orderNo.indexOf("YK")==0)||(orderNo.indexOf("Z7")==0))
    		return "YB";
    	try{
       	String PSSDmon = PSSD.substring(0, 2);
    	String RSSDmon = RSSD.substring(0, 2);
    	String PSSDdat = PSSD.substring(3, 5);
    	int nextPSSDmonth = Integer.parseInt(PSSDmon)+1;
    	if(PSSDmon.equals(mon)&&(RSSDmon.compareTo(mon)<=0))
    		return "Good";
    	else if(PSSDdat.compareTo("15")<0 && PSSDmon.compareTo(String.valueOf(nextPSSDmonth))<=0 && (RSSDmon.compareTo(mon)<=0))
    		return "Pend";
    	else if(PSSDmon.compareTo(mon)<0||(PSSDdat.compareTo(dat)<0)&&PSSDmon.compareTo(mon)==0)
    		return "Overdue";
    	else if((PSSDdat.compareTo("15")>0 && PSSDmon.compareTo(String.valueOf(nextPSSDmonth))<=0)||(RSSDmon.compareTo(mon)>0))
    		return "Bad";
    	
    	return "";
    			}
    	catch(Exception e){
    		return "";
    	}
    	
    }
    
    
    public static String getModelType(String Model)
    {
    	if(Model.trim().equals("FHB"))
    		return "FHB";
    	if(Model.trim().equals("FHA"))
    		return "FHA";
    	else if(Model.trim().equals("FHB Mimic"))
    		return "FHB Mimic";
    	else if(Model.trim().equals("M05")||Model.trim().equals("M10"))
    		return "zGMR";
    	else if(Model.trim().equals("M15")||Model.trim().equals("M32")||Model.trim().equals("M49")||Model.trim().equals("M66")||Model.trim().equals("M80"))
    		return "zGryphon";
    	else if(Model.trim().equals("H20")||Model.trim().equals("H43")||Model.trim().equals("H66")||Model.trim().equals("H89")||Model.trim().equals("HA1")||Model.trim().equals("H23")||Model.trim().equals("H49"))
    		return "zHelix";
    	else if(Model.trim().equals("E10"))
    		return "zMR";	    				
    	else if(Model.trim().equals("E12")||Model.trim().equals("E26")||Model.trim().equals("E40")||Model.trim().equals("E56")||Model.trim().equals("E64"))
    		return "z10 EC";
    	else if(Model.trim().equals("002"))
    		return "zBX";
    	else if(Model.trim().equals("001"))
    		return "Crypto Card";
    	return "";
      }
    
    public static ArrayList<String> getUniqueCol(int Col)
    {
    	
    	
    	int exists=-1;
    	ArrayList<String> Uvalues = new ArrayList<String>();
    	try{for(int i=0;i<values.getRows();i++)
    	{	
    		 if(values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("BOX"))
					continue;

    		String [] split = values.getValue(i, Col).split("<br>");
    		for(int k=0;k<split.length;k++)
    		{
    		exists=-1;
    		if(split[k].trim().equals("")&&i!=0)
    			continue;
    		for(int j=0;j<Uvalues.size();j++)
    		{
    			if(Uvalues.get(j).trim().equals(split[k].trim()))
    			{
    				exists++;
    			}
    		}
    		if(((exists==-1 && !split[k].trim().equals(""))||(split[k].trim().equals("")&&i==0))&&!values.getValue(i, 6).equals("Cancelled")&&!values.getValue(i, 6).equals("Shipped")&&!values.getValue(i, 6).equals("YB")&&!values.getValue(i, 6).equals("JMET"))
    			Uvalues.add(split[k].trim());
    		}
    	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return Uvalues;
    }
    
    
    
    public static ArrayList<String> getUniqueColBox(int Col)
    {
    	
    	
    	int exists=-1;
    	ArrayList<String> Uvalues = new ArrayList<String>();
    	try{for(int i=0;i<values.getRows();i++)
    	{	
    		 if(values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
    				continue;
    		String [] split = values.getValue(i, Col).split("<br>");
    		for(int k=0;k<split.length;k++)
    		{
    		exists=-1;
    		if(split[k].trim().equals("")&&i!=0)
    			continue;
    	
    		for(int j=0;j<Uvalues.size();j++)
    		{
    			if(Uvalues.get(j).trim().equals(split[k].trim()))
    			{
    				exists++;
    			}
    		}
    		if(((exists==-1 && !split[k].trim().equals(""))||(split[k].trim().equals("")&&i==0))&&!values.getValue(i, 6).equals("Cancelled")&&!values.getValue(i, 6).equals("Shipped")&&!values.getValue(i, 6).equals("YB")&&!values.getValue(i, 6).equals("JMET"))
    			Uvalues.add(split[k].trim());
    		}
    	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return Uvalues;
    }
    
	public static void checkifExists(String filename) throws IOException
	{
		 File f;
		  f=new File(filename);
		  if(!f.exists()){
		  f.createNewFile();
		  FileWriter fstream = new FileWriter(filename);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.append("");
		  out.close();
		  }
	}
    
	
	public static Boolean checkiforderexists(String file1, String file2, String order) throws IOException
	{
		FileInputStream fstream = new FileInputStream(file1);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		
		while ((str = br.readLine()) != null) {
			if(order.equals(str))
				return false;
		}
		
		fstream = new FileInputStream(file2);
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		
		
		while ((str = br.readLine()) != null) {
			if(order.equals(str))
				return false;
		}
		
		
		return true;
	}
	
    public static void getNewOrders(String time) throws IOException
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
        String curDate = dateFormat.format(cal.getTime());
        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Calendar calnew = Calendar.getInstance();

  
    	String datcurDate1 = newdateFormat.format(cal.getTime());
      	String datcurDateparse =  datcurDate1.replace("/", "~")+".txt";
    	checkifExists(datcurDateparse);

    	FileWriter fstream = new FileWriter(datcurDateparse,true);
		 BufferedWriter out = new BufferedWriter(fstream);
       
			Calendar prevDt= (Calendar) cal.clone();
	        prevDt.add(Calendar.DATE, -1);
	    	String prevD =  newdateFormat.format(prevDt.getTime());
	      	String prevDparse =  prevD.replace("/", "~")+".txt";
	    	checkifExists(prevDparse);
	    	FileWriter fstream1 = new FileWriter(prevDparse,true);
			 BufferedWriter out1 = new BufferedWriter(fstream1);
	           
		 
			 String datcurDate2 =  datcurDate1.replace("/", "-");
    	String hrs =  curDate.substring(11,13);
        String min  = curDate.substring(14,16);
        for(int i=1;i<values.getRows();i++)
        {
        	if (values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
				continue;
			
        	String date = values.getValue(i, 1);
        	
        	if(date.trim().equals(datcurDate2))
        		{
        		if(!checkiforderexists(prevDparse,datcurDateparse,values.getValue(i, 9)+" - "+values.getValue(i, 12)))
            		continue;
        			if(hrs.compareTo(time)>0)
        				{out.append(values.getValue(i, 9)+" - "+values.getValue(i, 12));
        				out.newLine();}
        			else
        				{
        				if(!checkiforderexists(prevDparse,datcurDateparse,values.getValue(i, 9)+"-"+values.getValue(i, 12)))
        	        		continue;
        				out1.append(values.getValue(i, 9)+" - "+values.getValue(i, 12));
        				out1.newLine();
        				}
        		}
        }
        out.close();
        out1.close();
    
    }
    
 
    public static void getNewOrdersmes(String time) throws IOException
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
        String curDate = dateFormat.format(cal.getTime());
        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Calendar calnew = Calendar.getInstance();

  
    	String datcurDate1 = newdateFormat.format(cal.getTime());
      	String datcurDateparse =  datcurDate1.replace("/", "~")+"mes.txt";
    	checkifExists(datcurDateparse);

    	FileWriter fstream = new FileWriter(datcurDateparse,true);
		 BufferedWriter out = new BufferedWriter(fstream);
       
			Calendar prevDt= (Calendar) cal.clone();
	        prevDt.add(Calendar.DATE, -1);
	    	String prevD =  newdateFormat.format(prevDt.getTime());
	      	String prevDparse =  prevD.replace("/", "~")+"mes.txt";
	    	checkifExists(prevDparse);
	    	FileWriter fstream1 = new FileWriter(prevDparse,true);
			 BufferedWriter out1 = new BufferedWriter(fstream1);
	           
		 
			 String datcurDate2 =  datcurDate1.replace("/", "-");
    	String hrs =  curDate.substring(11,13);
        String min  = curDate.substring(14,16);
        for(int i=1;i<values.getRows();i++)
        {
        	if((values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX")))
				continue;
		
        	String date = values.getValue(i, 1);
        	
        	if(date.trim().equals(datcurDate2))
        		{
        		if(!checkiforderexists(prevDparse,datcurDateparse,values.getValue(i, 9)+" - "+values.getValue(i, 12)))
            		continue;
        			if(hrs.compareTo(time)>0)
        				{out.append(values.getValue(i, 9)+" - "+values.getValue(i, 12));
        				out.newLine();}
        			else
        				{
        				if(!checkiforderexists(prevDparse,datcurDateparse,values.getValue(i, 9)+"-"+values.getValue(i, 12)))
        	        		continue;
        				out1.append(values.getValue(i, 9)+" - "+values.getValue(i, 12));
        				out1.newLine();
        				}
        		}
        }
        out.close();
        out1.close();
    
    }

    
    
    public static boolean checkforLoad(int rowTill, String order, String loaddate)
    {
    	for(int i=0;i<rowTill;i++)
    	{
    		if(values.getValue(i, 9).equals(order)&&(values.getValue(i, 1).equals(loaddate)))
    			return false;
    	}
    	return true;
    }
    
    public static int getUniqueWithDate(ResultSet result, table loadTable) throws SQLException
    {
        int rows=1;
        String prev="",prevdate= "";
        while(result.next())
        {
            if(!result.getString(10).equals(prev))
                {
            	prevdate="";
            	for(int i=0;i<loadTable.getRows();i++)
            	{
            		if(!loadTable.getValue(i, 1).equals(prevdate)&&loadTable.getValue(i, 0).equals(result.getString(10)))
            			{
            			rows++;
            			prevdate = loadTable.getValue(i, 1);
            			}
            		
            	}
            		rows++;
                    prev = result.getString(10);
                }
            }
        return rows;
    }
  
    

    public static String getQuarter(String PSSD, String Category)
    {
    	if(PSSD.equals("0"))
    		return "0";
    	String Month = PSSD.substring(0, 2);
    	String Year = PSSD.substring(6);
    	Calendar cal=Calendar.getInstance();
    	int curyear=cal.get(Calendar.YEAR);
    	String curyr = Integer.toString(curyear);
    	curyr = curyr.substring(2);
    	 if(!Category.equals("Pend"))
    	 {
    		 if(Month.equals("01")||Month.equals("02")||Month.equals("03"))
    			 return "Q1";
    		 else if(Month.equals("04")||Month.equals("05")||Month.equals("06"))
    			 return "Q2";
    		 else if(Month.equals("07")||Month.equals("08")||Month.equals("09"))
    			 return "Q3";
    		 else if(Month.equals("10")||Month.equals("11")||Month.equals("12"))
    			 return "Q4";
    	 }
    	 else
    	 {
    		 if((Month.equals("01")&&Year.equals(curyr))||Month.equals("02")||Month.equals("03")||Month.equals("04"))
    			 return "Q1";
    		 else if(Month.equals("04")||Month.equals("05")||Month.equals("06")||Month.equals("07"))
    			 return "Q2";
    		 else if(Month.equals("07")||Month.equals("08")||Month.equals("09")||Month.equals("08"))
    			 return "Q3";
    		 else if(Month.equals("10")||Month.equals("11")||Month.equals("12")||Month.equals("01"))
    			 return "Q4";
  
    	 }
    	 return "";
    }
    public static int getUnique(ResultSet result) throws SQLException
    {
        int rows=1;
        String prev="";
        while(result.next())
        {
            if(!result.getString(10).equals(prev))
                {rows++;
                    prev = result.getString(10);
                }
            }
        return rows;
    }
  
    
    public static int getRows(ResultSet result) throws SQLException
    {
    	int rows=1;
        while(result.next())
        {
            rows++;     }
        return rows;
   
    	
    }
    
    public static void getOrderandMan(ResultSet result) throws SQLException
    {
    	OrderNum = new ArrayList<String>();
        ManuNum = new ArrayList<String>();
    	 while(result.next())
    	 {
    		 OrderNum.add(result.getString(11));
    		 ManuNum.add(result.getString(10));
    	 }
    }

    
public static ArrayList<String> getStatistics() throws ParseException
    {   
    	ArrayList<String> stats = new ArrayList<String>();
    	ArrayList<String> newOrder = new ArrayList<String>();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();
    	String curDate = dateFormat.format(cal.getTime());
        Calendar prevMonth = (Calendar) cal.clone();
        prevMonth.add(Calendar.DATE, -1);
        String prevDate = dateFormat.format(prevMonth.getTime());
    	Date dt = dateFormat.parse(curDate);
    	int good=0,bad=0,pend=0,yb=0,jmet=0,overdue=0;
    	for(int i=0;i<values.getRows();i++)
    	{
    		if(values.getValue(i, 6).trim().toLowerCase().equals("good"))
    			good++;
    		else if(values.getValue(i, 6).trim().toLowerCase().equals("bad"))
    			bad++;
    		else if(values.getValue(i, 6).trim().toLowerCase().equals("pend"))
    			pend++;
    		else if(values.getValue(i, 6).trim().toLowerCase().equals("yb"))
    			yb++;
    		else if(values.getValue(i, 6).trim().toLowerCase().equals("jmet"))
    			jmet++;
    		else if(values.getValue(i, 6).trim().toLowerCase().equals("overdue"))
    			overdue++;
    		if(values.getValue(i, 1).trim().equals(curDate)||values.getValue(i, 1).trim().equals(prevDate))
    			newOrder.add(values.getValue(i, 9));
    	}
    	stats.add(Integer.toString(good));
    	stats.add(Integer.toString(bad));
    	stats.add(Integer.toString(pend));
    	stats.add(Integer.toString(yb));
    	stats.add(Integer.toString(jmet));
    	stats.add(Integer.toString(overdue));
    	for(int i=0;i<newOrder.size();i++)
    	{
    		stats.add(newOrder.get(i));
    	}
    	return stats;
}
       
    
    public static void startExec()
    {    //String id = "0GY4XV5";
        try {
        
          Statement select=null,selectld=null,selectgem=null,selectcust=null,selectPSSD = null;
          Connection con = getConn(select);
          Connection gemcon = getGemConn();
          Connection custcon = getCustConn();
          Connection PSSDCon = getPSSDCon();
          select = con.createStatement();
          selectPSSD = PSSDCon.createStatement();
          selectld = con.createStatement();
          selectgem = gemcon.createStatement();
          selectcust = custcon.createStatement();
          String pQuarter="";
          String pLdDate="";
          String pLdWeek="";
          String pShpDate="";
          String pCanDate="";
          String pStaWeek="";
          String pOrderCat="";
          String pOrderSeq="";
          String pTestCell="";
          String pOrderType="";
          String pModel="";
          String pModelNo="";
          String pProLine="";
          String pCust="";
          String pCount="";
          String pPSSD="";
          String pRSSD="";
          String pConfig="";
          String pFOE="";
          String pTestTime="";
          String pStatus="";
          String pRem="";
          String pKitsh="";
          String pinstsh="";
          String pGenSh="";
          String order ="";
          String pTestTimeR = " : ";
        
        
          String sysNo = "";
  
          String System="";
          String Quarter="";
          String LdDate="";
          String LdWeek="";
          String ShpDate="";
          String CanDate="";
          String StaWeek="";
          String OrderCat="";
          String OrderSeq="";
          String TestCell="";
         
          String OrderType="";
          String Model="";
          String ModelNo="";
          String ProLine="";
          String Cust="";
          String Country="";
          String PSSD="";
          String RSSD="";
          String Config="";
          String FOE="";
          String TestTime="";
          String Status="";
          String Rem="";
          String Kitsh="";
          String instsh="";
          String GenSh="";
          String OrderNo="";
          String Orderp = "";
          String Modelp="";
          String ModelNop="";
          String ModelCatp="";
          String TestTimeR = " : ";
          
        
          ResultSet result = null,ldcan=null,gemres=null,PSSDres,custres;
          if(getBy==0)
        	  result = getResult(select,"");
          else 
        	  result = getResultDate(select,"");
          getOrderandMan(result);
          ldcan  =getLoadCancelDate(selectld, ManuNum);
          gemres = getGemResult(selectgem);
          PSSDres  =getPSSDResult(selectPSSD, OrderNum);
          custres = getCustomer(selectcust, OrderNum);
          int numPSSD = getRows(PSSDres);
          int numldrows = getRows(ldcan);
          int numgemrows = getRows(gemres);
          int numcustrows = getRows(custres);
          //numldrows--;
          numgemrows--;
          numPSSD--;
          numcustrows--;
          table ldcanres = new table(numldrows,5);
          table gemsres = new table(numgemrows,8);
          table PSSDresult = new table(numPSSD,4);
          table cusres = new table(numcustrows,2);
          ldcan = getLoadCancelDate(selectld, ManuNum);
          gemres = getGemResult(selectgem);
          PSSDres  =getPSSDResult(selectPSSD, OrderNum);
          custres = getCustomer(selectcust, OrderNum);

          //result.beforeFirst();
          int rowNumber=0;
          int ldrow=0;
          int gemrow=0;
          int PSSDrow = 0;
          int custrow=0;
          while(custres.next())
          {
        	  cusres.setValue(custrow, 0, custres.getString(1));
        	  cusres.setValue(custrow, 1, custres.getString(2));
        	  custrow++;
          }
        
          while(PSSDres.next())
          {
        	  PSSDresult.setValue(PSSDrow, 0, PSSDres.getString(1));
        	  PSSDresult.setValue(PSSDrow, 1, PSSDres.getString(2));
        	  PSSDresult.setValue(PSSDrow, 2, PSSDres.getString(3));
        	  PSSDresult.setValue(PSSDrow, 3, PSSDres.getString(4));
        	  PSSDrow++;
          }
          
          
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
        	  gemrow++;
          }
          
          
          while(ldcan.next())
          {
        	  ldcanres.setValue(ldrow, 0, ldcan.getString(1));
        	  ldcanres.setValue(ldrow, 1, ldcan.getString(2));
        	  ldcanres.setValue(ldrow, 2, ldcan.getString(4));
        	  ldcanres.setValue(ldrow, 3, ldcan.getString(3));
        	  ldcanres.setValue(ldrow, 4, ldcan.getString(5));        	  
        	  
        	  ldrow++;
          }
          
          //int numrows = getUniqueWithDate(result,ldcanres);
          values = new table(numldrows,30);
          if(getBy==0)
        	  result = getResult(select,"");
          else 
        	  result = getResultDate(select,"");
     
          while(result.next()) { // process results one row at a time
        	  rowNum=rowNumber;
            Quarter = result.getString(1);
            LdDate = result.getString(2);
            LdWeek = result.getString(3);
            ShpDate = result.getString(4);
            CanDate = result.getString(5);
            StaWeek = result.getString(6);
            OrderCat = result.getString(7);
            OrderSeq = result.getString(8);
            TestCell = result.getString(9);
            OrderType = result.getString(12);
            Model = result.getString(13);
            ModelNo = result.getString(14);
            ProLine = result.getString(15);
            Cust = result.getString(16);
            Country = result.getString(17);
            PSSD = result.getString(18);
            RSSD = result.getString(19);
            Config = result.getString(20);
            FOE = result.getString(21);
            TestTime = result.getString(22);
            Status = result.getString(23);
            Rem = result.getString(24);
            Kitsh = result.getString(25);
            instsh = result.getString(26);
            GenSh = result.getString(27);
            System = result.getString(10);
            OrderNo = result.getString(11);
            TestTimeR = " : ";
            for(int i=0;i<numcustrows;i++)
            {
            if(cusres.getValue(i, 0).trim().equals(OrderNo))
            	Cust = cusres.getValue(i, 1);
            }
            for(int i=0;i<numPSSD;i++)
            {
            	if((PSSDresult.getValue(i, 0)).equals(OrderNo))
            	{
            		PSSD = PSSDresult.getValue(i, 1);
            		RSSD = PSSDresult.getValue(i, 2);
            		FOE = PSSDresult.getValue(i, 3);
            	}
            	
            }
            
            for(int i=0;i<numgemrows;i++)
            {
            	if((gemsres.getValue(i, 0)).equals(OrderNo))
            	{
            		TestCell = gemsres.getValue(i, 1);
            		TestTime = gemsres.getValue(i, 2)+"\n"+gemsres.getValue(i, 3)+"\n"
            					+"F: "+gemsres.getValue(i, 6)+" Ep:"+gemsres.getValue(i, 7);
            		if(!gemsres.getValue(i, 2).equals(""))
            			TestTimeR = gemsres.getValue(i, 4)+":"+gemsres.getValue(i, 5);
            	}
            }
            
            
            for(int i=0;i<ldrow;i++)
            {
            	if(ldcanres.getValue(i, 0).equals(System))
            	{
            		LdWeek = ldcanres.getValue(i,3);
            		if(ldcanres.getValue(i,4)!=null)
            			StaWeek = ldcanres.getValue(i,4);
            		else
            			StaWeek = "";
            			LdDate = ldcanres.getValue(i,1); 
            		if(ldcanres.getValue(i,2)!=null)	
            			CanDate = ldcanres.getValue(i, 2); 
            		else 
            			CanDate = "";
            			
            			 if(checkforLoad(rowNumber, System , LdDate)&&(!System.equals(sysNo)||(!LdDate.equals(pLdDate)&&(System.equals(sysNo))))){
            				 
            				 
            				 	values.setValue(rowNumber, 0, pQuarter);
            	                values.setValue(rowNumber, 1, pLdDate);
            	                values.setValue(rowNumber, 2, pLdWeek);
            	                values.setValue(rowNumber, 3, pShpDate);
            	                values.setValue(rowNumber, 4, pCanDate);
            	                values.setValue(rowNumber, 5, pStaWeek);
            	        		values.setValue(rowNumber, 6, pOrderCat);
            	                values.setValue(rowNumber, 7, pOrderSeq);

            	                values.setValue(rowNumber, 8, pTestCell);
            	                values.setValue(rowNumber, 9, sysNo);
            	                values.setValue(rowNumber, 11, pOrderType);
            	                pModel = pModel.trim();
            	                values.setValue(rowNumber, 12, Modelp);
            	                values.setValue(rowNumber, 13,  ModelCatp);
            	                values.setValue(rowNumber, 14, ModelNop);
            	                values.setValue(rowNumber, 15, pProLine);
            	                pCust = pCust.trim();
            	                pCount = pCount.trim();
            	                values.setValue(rowNumber, 16, pCust);
            	                values.setValue(rowNumber, 17, pCount);
            	                
            	                pPSSD=dateParse(pPSSD);
            	                if(!pRSSD.trim().equals("")&&order.indexOf("4")!=0)
            	                	pRSSD=dateParse(pRSSD);
            	                values.setValue(rowNumber, 0, getQuarter(pPSSD, pOrderCat));
            	                //pQuarter = getQuarter(PSSD,);
            	                values.setValue(rowNumber, 18, pPSSD);
            	                values.setValue(rowNumber, 19, pRSSD);
            	                values.setValue(rowNumber, 20, pConfig);
            	                values.setValue(rowNumber, 21, pFOE);
            	                values.setValue(rowNumber, 22, pTestTimeR);
            	                values.setValue(rowNumber, 23, pTestTime);
            	                values.setValue(rowNumber, 24, pStatus);
            	                
            	                
            	                if(!pCanDate.trim().equals(""))
            	            	{
            	            	values.setValue(rowNumber, 6, "Cancelled");
            	            	values.setValue(rowNumber, 24, "Cancelled");
            	            	}
            	                if(pOrderType.trim().equals("3"))
            	                	values.setValue(rowNumber, 11, "BOX");
            	                else if(pOrderType.trim().equals("1")&&pProLine.trim().contains("MES"))
            	                	values.setValue(rowNumber, 11, "MES Loose");
            	                else if(pOrderType.trim().equals("1"))
            	                	values.setValue(rowNumber, 11, "MES Undefined");
            	                values.setValue(rowNumber, 25, pRem);
            	                values.setValue(rowNumber, 26, pKitsh);
            	                values.setValue(rowNumber, 27, pinstsh);
            	                values.setValue(rowNumber, 28, pGenSh);
            	                values.setValue(rowNumber, 10, Orderp);
            	                values.setValue(rowNumber, 29, "0");
            	              
            	              
            	              
            	                 order = OrderNo;
            	                 pQuarter=Quarter;
            	                 pLdDate=LdDate;
            	                 pLdWeek=LdWeek;
            	                 pShpDate=ShpDate;
            	                 pCanDate=CanDate;
            	                 pStaWeek=StaWeek;
            	                 pOrderCat=OrderCat;
            	                 pOrderSeq=OrderSeq;
            	                 pTestCell=TestCell;
            	                 pOrderType=OrderType;
            	                 pModel=Model;
            	                 pModelNo=ModelNo;
            	                 pProLine=ProLine;
            	                 pCust=Cust;
            	                 pCount=Country;
            	                 pPSSD=PSSD;
            	                 pRSSD=RSSD;
            	                 pConfig=Config;
            	                 pFOE=FOE;
            	                 pTestTime=TestTime;
            	                 pTestTimeR=TestTimeR;
            	                 pStatus=Status;
            	                 pRem=Rem;
            	                 pKitsh=Kitsh;
            	                 pinstsh=instsh;
            	                 pGenSh=GenSh;
            	                 sysNo = System;
            	                 Modelp = pModel;
            	                 ModelNop = pModelNo;
            	                 Orderp = order;
            	                 ModelCatp = getModelType(Modelp);
            	              
            	                rowNumber++;
            	              

            	              
            	                       }

            	
            	
            	
            	}
            }
            
            
                     if(sysNo.equals(System)&&!order.equals(OrderNo))
                    	 {
                    	 Orderp = Orderp+"<br>"+OrderNo;
     	                Modelp += "<br>"+Model;
     	                ModelNop += "<br>"+ ModelNo;
     	                ModelCatp += "<br>"+getModelType(Model);
                    	 }

          }
        
        
        
          values.setValue(rowNumber, 0, pQuarter);
        values.setValue(rowNumber, 1, pLdDate);
        values.setValue(rowNumber, 2, pLdWeek);
        values.setValue(rowNumber, 3, pShpDate);
        values.setValue(rowNumber, 4, pCanDate);
        values.setValue(rowNumber, 5, pStaWeek);
        values.setValue(rowNumber, 6, pOrderCat);
        values.setValue(rowNumber, 7, pOrderSeq);

         values.setValue(rowNumber, 8, pTestCell);
        values.setValue(rowNumber, 9, sysNo);
        values.setValue(rowNumber, 11, pOrderType);
        values.setValue(rowNumber, 12, Modelp);
        if(ModelCatp.indexOf("<br>")==0)
        	ModelCatp=ModelCatp.substring(4);
        values.setValue(rowNumber, 13, ModelCatp);
        values.setValue(rowNumber, 14, ModelNop);
        values.setValue(rowNumber, 15, pProLine);
        pCust = pCust.trim();
        pCount = pCount.trim();
        values.setValue(rowNumber, 16, pCust);
        values.setValue(rowNumber, 17, pCount);
        pPSSD=dateParse(pPSSD);
        if(!pRSSD.trim().equals("")&&order.indexOf("4")!=0)
        	pRSSD=dateParse(pRSSD);
	values.setValue(rowNumber, 18, pPSSD);
        values.setValue(rowNumber, 19, pRSSD);
        values.setValue(rowNumber, 20, pConfig);
        values.setValue(rowNumber, 21, pFOE);
        values.setValue(rowNumber, 22, pTestTimeR);
        values.setValue(rowNumber, 23, pTestTime);
        values.setValue(rowNumber, 24, pStatus);

        if(!pCanDate.trim().equals("")||pStatus.trim().equals("99"))
    	{
    	values.setValue(rowNumber, 6, "Cancelled");
    	values.setValue(rowNumber, 24, "Cancelled");
    	}
        if(pOrderType.trim().equals("3"))
        	values.setValue(rowNumber, 11, "BOX");
        else if(pOrderType.trim().equals("1"))
        	values.setValue(rowNumber, 11, "MES Undefined");
        
        values.setValue(rowNumber, 0, getQuarter(pPSSD, pOrderCat));
        values.setValue(rowNumber, 25, pRem);
        values.setValue(rowNumber, 26, pKitsh);
        values.setValue(rowNumber, 27, pinstsh);
        values.setValue(rowNumber, 28, pGenSh);
        values.setValue(rowNumber, 10, Orderp);
        values.setValue(rowNumber, 29, "0");
         closeCon(select,con);
         closeCon(selectgem,gemcon);
         closeCon(selectld,con);
         closeCon(selectcust,custcon);
         
         
        }
        catch( Exception e ) {
          e.printStackTrace();
        }

      
      
    }

	 public static Connection getConn()
	    {  
	        String url = "jdbc:db2://qryprod.rchland.ibm.com:446/QRYPROD";
	
	        Connection con=null;
	
	        try {
	            Class.forName("com.ibm.db2.jcc.DB2Driver");
	        }
	        catch( Exception e ) {
	          System.out.println("Failed to load mSQL driver.");
	          return null;
	        }
	        try{
	        con = DriverManager.getConnection(url, "chawcl", "candy4cc");
	        }
	        catch(Exception e)
	        {
	            System.out.println("Connection error");
	        }
	        return con;
	    }
	
	
}