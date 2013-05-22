package DataBase1;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class database1
 */
@WebServlet("/database1")
public class database1 extends HttpServlet {

    private static final long serialVersionUID = 1L;
     public static table values;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public database1() {

        super();
        // TODO Auto-generated constructor stub
    }

    public static table returnVal()
    {
        return values;
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
        catch(Exception e)
        {
            System.out.println("Connection error");
        }
        return con;
    }


    public static void closeCon(Statement select, Connection con) throws SQLException
    { select.close();
    con.close();
        }

    public static ResultSet getResult(Statement select, String id) throws SQLException
    {
        ArrayList<String> ManuNum= database2.getManArrayList();
        String query = " with raw_Load as (select Jxmfgn,"
                +" CASE"   
                         +" WHEN  time(Jxcsts) >= '11:00:00'  THEN date(Jxcsds) + 1 day"
                        +" ELSE date(Jxcsds)"
                      +" END as LOAD_DATE "

                +" from MFS2P010A.FCSPJX10"

                +" where Jxerms ='MB13 - Order Interfaced'"
                +" ) "
      


        +"SELECT distinct Wumfgn as System#,  jtcods as RPDS, Tlcods as Order#, jtdtes as ShpDt, "
                +"wumctl as WU#, wuprln as WU_Prod_LN,wuprod as Prod_ID,Wunmbr as Op#, Wuopst  as Op, "
                +"'b_IOFRM' as seq# , Jxmfgn, Load_Date "
                +" from mfs2p010a.fcsptl10 a left join mfs2p010a.FCSPWu10 b on wumfgn=tlmfgn "
                +" left join dcs1p010.lofpjt10 c on jtorno=Wuworn, "
                +" raw_Load d "
                +" where Jxmfgn like wumfgn and wumfgn in ('"+ManuNum.get(0)+"'";
        for(int i=1;i<ManuNum.size();i++)
        {
        query += ",'"+ManuNum.get(i)+"'";
        }     
        query += ") and tlidss ='0000' order by System#, Load_Date";
        ResultSet result = select.executeQuery(query);
        return result;
        }

     public static int getUnique(ResultSet result) throws SQLException
    {
      int rows=1;
        String prev="";
        String prevDate="";
        while(result.next())
        {
            if(!result.getString(1).equals(prev)||!result.getString(12).equals(prevDate))
                {rows++;
                    prev = result.getString(1);
                    prevDate = result.getString(12);
                }
            }
        return rows;
    }
  
     
     public static boolean compareProId(String proId1, String proId2)
     {
         String[] ids={"FRM","AIR","RAD","WY","NODE","TRS","SGR","MES"};
         int poslow=15;
         int poshigh=15;
         for(int i=0;i<ids.length;i++)
         {
             if(proId1.toUpperCase().contains(ids[i]))
                 poslow=i;
             if(proId2.toUpperCase().contains(ids[i]))
                 poshigh=i;
   
         }
         
         if(poslow<poshigh)
         return true;
         
         return false;
     }
     
     
     public static String getStatus(String status,String opno, String ftt, String fttSt, String RPDS)
     {
        int i=0;
         try
         {
        i=Integer.parseInt(opno);     
         }
         catch(Exception e)
         {}
         status=status.trim();
         
         if(opno.toUpperCase().contains("TEST"))
         {
        	 if(ftt.trim().equals(""))
                 return "PRE-TEST";
        	 else if(!fttSt.trim().equals("")&&!fttSt.contains("Complete"))
        		 return "TEST";
        	 else if(ftt.trim().contains("Complete"))
        		 return "TEST POST";
         }
         
         if(status.trim().equals(""))
             return "Not Release";
         else if(status.equals("KR")||status.equals("PW")||(opno.toUpperCase().contains("CKIT")&&status.equals("02")))
             return "Kit";
         else if(status.equals("02") && (opno.toUpperCase().contains("PPSK")||opno.toUpperCase().contains("PAKM")||opno.toUpperCase().contains("MESK")||opno.toUpperCase().contains("FRIN")||opno.toUpperCase().contains("BUDY")||i==9999||(i>=100&&i<699&&i!=410)))
             return "Build";
         else if(status.equals("02")&&opno.toUpperCase().contains("VI30"))
             return "VI30";
         else if((status.equals("07")||status.equals("02"))&&((ftt.trim().equals("")&&opno.toUpperCase().contains("TEST"))||opno.toUpperCase().contains("PRET")||opno.toUpperCase().contains("HP10")||opno.toUpperCase().contains("VI10")))
             return "PRE-TEST";
         else if((status.equals("07")||status.equals("02"))&&((opno.toUpperCase().contains("TEST")&&!fttSt.trim().equals("")&&!fttSt.contains("Complete"))||i==700||(!fttSt.trim().equals("")&&!fttSt.contains("Complete"))))
             return "TEST";
         else if(opno.toUpperCase().contains("HP20")||((status.equals("07")||status.equals("02"))&&(ftt.trim().contains("Complete")||i==410||opno.toUpperCase().contains("VI20")||opno.toUpperCase().contains("HP20")||opno.toUpperCase().contains("VI25"))))
             return "TEST POST";
         else if((i>800&&i<899)||(status.equals("08")&&(opno.toUpperCase().contains("CSHP")||opno.toUpperCase().contains("JPAN")||(i>800&&i<899))))
             return "CPC";
         else if(status.equals("09")&&(RPDS==null))
             return "";
         else if(status.equals("09")&&(Integer.parseInt(RPDS)<=19))
             return "COMPLETE";
         else if(status.equals("09")&&(Integer.parseInt(RPDS)>19))
             return "GEODIS";
         else if(status.equals("99"))
             return"CANCELLED";
         else if(status.equals("01"))
             return "ALTER";
         if(!status.trim().equals("")&&!status.toLowerCase().equals("cancelled"))
                System.out.println(opno);
       
         return status+"-"+opno;

     }
     
     public static String getStatus(String status,String opno, String ftt, String fttSt)
     {
         int i=0;
        try
        {
       i=Integer.parseInt(opno);     
        }
        catch(Exception e)
        {}
        status=status.trim();
        
        if(opno.toUpperCase().contains("TEST"))
        {
       	 if(ftt.trim().equals(""))
                return "PRE-TEST";
       	 else if(!fttSt.trim().equals("")&&!fttSt.contains("Complete"))
       		 return "TEST";
       	 else if(ftt.trim().contains("Complete"))
       		 return "TEST POST";
        }
        
     if(status.trim().equals(""))
         return "Not Release";
     else if(status.equals("KR")||status.equals("PW")||(opno.toUpperCase().contains("CKIT")&&status.equals("02")))
         return "Kit";
     else if(status.equals("02") && (opno.toUpperCase().contains("PPSK")||opno.toUpperCase().contains("PAKM")||opno.toUpperCase().contains("MESK")||opno.toUpperCase().contains("FRIN")||opno.toUpperCase().contains("BUDY")||i==9999||(i>=100&&i<699&&i!=410)))
         return "Build";
     else if(status.equals("02")&&opno.toUpperCase().contains("VI30"))
         return "VI30";
     else if((status.equals("07")||status.equals("02"))&&((ftt.trim().equals("")&&opno.toUpperCase().contains("TEST"))||opno.toUpperCase().contains("PRET")||opno.toUpperCase().contains("HP10")||opno.toUpperCase().contains("VI10")))
         return "PRE-TEST";
     else if((status.equals("07")||status.equals("02"))&&((opno.toUpperCase().contains("TEST")&&!fttSt.trim().equals("")&&!fttSt.contains("Complete"))||i==700||(!fttSt.trim().equals("")&&!fttSt.contains("Complete"))))
         return "TEST";
     else if(opno.toUpperCase().contains("HP20")||((status.equals("07")||status.equals("02"))&&(ftt.trim().contains("Complete")||i==410||opno.toUpperCase().contains("VI20")||opno.toUpperCase().contains("HP20")||opno.toUpperCase().contains("VI25"))))
         return "TEST POST";
     else if((i>800&&i<899)||(status.equals("08")&&(opno.toUpperCase().contains("CSHP")||opno.toUpperCase().contains("JPAN")||(i>800&&i<899))))
         return "CPC";
     else if(status.equals("09"))
         return "COMPLETE";
     else if(status.equals("99"))
         return"CANCELLED";
     else if(status.equals("01"))
         return "ALTER";
     if(!status.trim().equals("")&&!status.toLowerCase().equals("cancelled"))
            System.out.println(opno);
        
     return status+"-"+opno;

     }
     
     
     public static String getOrderType(String type, String proid,int framecount, String productLine,String model)
     {
         if(model.trim().equals("001"))
             return "MES Loose";
         else if(type.trim().equals("3"))
            return "BOX";
        else if(type.trim().equals("1")||type.trim().equals("MES Undefined"))
            {
            if(framecount==0 && !productLine.trim().equals("TTNMSYS"))
                return "MES Loose";
            else if(productLine.trim().equals("TTNMSYS"))
                return "BOX MIMIC";
            else if(framecount>0&& ((proid.toLowerCase().contains("io")&& proid.toLowerCase().contains("frm")) || proid.toLowerCase().equals("19frame")))
                return "MES Loose";
            else if(framecount>0)
                return "MES BOX";
            }
        
         return type;
         
     }
  
  
    public static int[][] getStatus(int status[][], String result, int i)
    {
        if(result.equals("A"))
            status[i][0]++;
        else if(result.equals("S"))
            status[i][1]++;
        else if(result.equals("W"))
            status[i][2]++;
        else if(result.equals("C"))
            status[i][3]++;
        else if(result.equals("D"))
            status[i][4]++;
        else if(result.equals("N"))
            status[i][5]++;  
        return status;
    }


    public static String printStatus(int[][] status, int i)
    {
        String toPrint="";
            for(int j=0;j<6;j++)
            {        if(status[i][j]!=0)
                        {if(j==0)
                        toPrint+="</br> "+"A:"+Integer.toString(status[i][j]);
                        else if(j==1)
                        toPrint+="</br> "+"S:"+Integer.toString(status[i][j]);
                        else if(j==2)
                        toPrint+="</br> "+"W:"+Integer.toString(status[i][j]);
                        else if(j==3)
                        toPrint+="</br> "+"C:"+Integer.toString(status[i][j]);
                        else if(j==4)
                        toPrint+="</br> "+"D:"+Integer.toString(status[i][j]);
                        else if(j==5)
                        toPrint+="</br> "+"N:"+Integer.toString(status[i][j]);
                        }
            }
            return toPrint;
    }

  
    public static String  shpDtParse(String shpDt)
    {
        if(shpDt==null)
            return "0";
        String dt="0";
        String yr="",mon="",date="";
        if(!shpDt.equals("0"))
        {
            yr = shpDt.substring(0, 2);
            mon = shpDt.substring(2,4);
            date = shpDt.substring(4, 6);
            dt = mon+"/"+date+"/"+yr;
        }
        return dt;

    }
    public static table startExec()
    {    String id = "0GY4XV5";
        try {
        
          Statement select=null;
          Connection con = getConn(select);
          select = con.createStatement();


          String pOpno="";
          String sysNo = "";
          String comProId="";
          int framecnt=0;
          int nodecnt=0;
          int drawercnt=0;
          int sgrcnt=0;
          int mescnt=0;
          int cvrcnt=0;
          int scvrcnt=0;
          int hgtcnt=0;
          String pOp="";

          int [][]status = new int [8][6];
          for(int i=0;i<7;i++)
              for(int j=0;j<6;j++)
              {        status[i][j]=0;
              }
          String pRPDS = "";
          String pOrderno = "";
          String pShpDt = "";
          String pproid="";

          String plddate ="";
          String highProId="";
          String highopno="";

          ResultSet result = null;
          result = getResult(select,id);
        
          //int rowCount = result.getInt(1);
          //System.out.print(rowCount);
          int numrows = getUnique(result);
          
         values = new table(numrows,15);


          result = getResult(select,id);
          int rowNumber=0;
          while(result.next()) { // process results one row at a time
              String Systemno = result.getString(1);
            String RPDS = result.getString(2);
            String Orderno = result.getString(3);
            String ShpDt = result.getString(4);
            ShpDt = shpDtParse(ShpDt);
            String proid = result.getString(7);
            String Op= result.getString(9);
            //String lddate = result.getString(9);
            String LoadDate = result.getString(12);
            String opno=result.getString(8);
            if(!Systemno.equals(sysNo)||!LoadDate.equals(plddate)){
                if(RPDS!="")
                { 
                values.setValue(rowNumber, 0, sysNo);        
                if(pRPDS!=null)
                    values.setValue(rowNumber, 9, pRPDS);
                else
              values.setValue(rowNumber, 9, "");
                
                values.setValue(rowNumber, 11, pOrderno);
                values.setValue(rowNumber, 12, highProId);
                values.setValue(rowNumber, 10, pShpDt);
                values.setValue(rowNumber, 2, Integer.toString(nodecnt)+ printStatus(status,0));
                values.setValue(rowNumber, 3, Integer.toString(drawercnt)+ printStatus(status,1));
                values.setValue(rowNumber, 4, Integer.toString(sgrcnt)+ printStatus(status,2));
                values.setValue(rowNumber, 1, Integer.toString(framecnt)+ printStatus(status,3));
                values.setValue(rowNumber, 5, Integer.toString(mescnt)+ printStatus(status,4));
                values.setValue(rowNumber, 6, Integer.toString(hgtcnt)+ printStatus(status,5));
                values.setValue(rowNumber, 8, Integer.toString(cvrcnt)+ printStatus(status,7));
                values.setValue(rowNumber, 7, Integer.toString(scvrcnt)+ printStatus(status,6));
                values.setValue(rowNumber, 13, plddate);
                for(int i=1;i<database2.values.getRows();i++)
                {
                    if(sysNo.equals(database2.values.getValue(i, 9))&&plddate.equals(database2.values.getValue(i, 1)))
                    {
                        database2.values.setValue(i, 3, pShpDt);
                        String orderSeq = database2.values.getValue(i, 7);
                        String PSSD = database2.values.getValue(i, 18);
                        String RSSD = database2.values.getValue(i, 19);
                        String canDate = database2.values.getValue(i, 4);
                        if(pRPDS==null)
                            {
                            database2.values.setValue(i, 6, database2.getCat(orderSeq,PSSD,RSSD,canDate,database2.values.getValue(i, 10)));
                            }
                        else if(!database2.values.getValue(i, 6).contains("Can"))
                            database2.values.setValue(i, 6, database2.getCat(orderSeq,PSSD,RSSD,pRPDS,canDate,database2.values.getValue(i, 10)));
                        database2.values.setValue(i, 20, Integer.toString(framecnt)+"/"+Integer.toString(nodecnt)+"/"+Integer.toString(drawercnt));
                        database2.values.setValue(i, 11, getOrderType(database2.values.getValue(i, 11),comProId,framecnt,database2.values.getValue(i, 15),database2.values.getValue(i, 12)));
                        if(pRPDS==null)
                        {database2.values.setValue(i, 24, getStatus(database2.values.getValue(i, 24),highopno,database2.values.getValue(i, 23).trim(),database2.values.getValue(i, 23)));
                          }
                        else
                            database2.values.setValue(i, 24, getStatus(database2.values.getValue(i, 24),highopno,database2.values.getValue(i, 23).trim(),database2.values.getValue(i, 23),pRPDS));
                        if(!database2.values.getValue(i, 0).trim().equals(""))
                            database2.values.setValue(i, 0, database2.getQuarter(PSSD, database2.values.getValue(i, 6)));
                        if(!database2.values.getValue(i, 4).trim().equals(""))
                            {
                            database2.values.setValue(i, 24,"CANCELLED");
                            database2.values.setValue(i, 6,"Cancelled");
                            }
                    }
                }
                //printStatus(status,0);
                //printStatus(status,1);
                //printStatus(status,2);
                //printStatus(status,3);
                //printStatus(status,4);
                //printStatus(status,5);
                //printStatus(status,7);
                //printStatus(status,6);
                //
                rowNumber++;
                }
              
                pOp=Op;
                pOpno=opno;
                plddate = LoadDate;
                pproid = proid;
                sysNo=Systemno;
                pRPDS=RPDS;
                pOrderno=Orderno;
                pShpDt = ShpDt;
                framecnt=0;
                nodecnt=0;
                drawercnt=0;
                sgrcnt=0;
                mescnt=0;
                cvrcnt=0;
                scvrcnt=0;
                hgtcnt=0;
                comProId="";
                highopno="";
                highProId="";
                //plddate = lddate;
                for(int i=0;i<8;i++)
                    for(int j=0;j<6;j++)
                    {        status[i][j]=0;
                    }
            }
            
                comProId += proid;
                if(compareProId(proid, highProId))
                        {
                    highProId=proid;
                    highopno = opno; 
                        }
                if((proid.toLowerCase().contains("node"))||(proid.toLowerCase().contains("wy"))||(proid.toLowerCase().contains("nde")))
                        {nodecnt++;
                        status = getStatus(status, Op,0);

                        }
                else if ((proid.toLowerCase().contains("trs"))||(proid.toLowerCase().contains("cha"))||(proid.toLowerCase().contains("drw"))||(proid.toLowerCase().contains("homerun")))
                {drawercnt++;
                status = getStatus(status, Op,1);


                }

                else if ((proid.toLowerCase().contains("sgr"))||(proid.toLowerCase().contains("omfb"))||(proid.toLowerCase().contains("fcsi")))
                {sgrcnt++;
                status = getStatus(status, Op,2);

                }
                

                else if ((proid.toLowerCase().contains("mes"))||(proid.toLowerCase().contains("4765frm")))
                {mescnt++;

                status = getStatus(status, Op,4);

    }

                else if((proid.toLowerCase().contains("frm"))||(proid.toLowerCase().contains("frame"))||(proid.toLowerCase().contains("rad"))||(proid.toLowerCase().contains("air")))
                {framecnt++;
                status = getStatus(status, Op,3);

                }



                else if ((proid.toLowerCase().contains("hgt")))
                {hgtcnt++;status = getStatus(status, Op,5);
                }

                else if ((proid.toLowerCase().contains("scvr")))
                {scvrcnt++;
                 status = getStatus(status, Op,6);

                }

                else if ((proid.toLowerCase().contains("cvr")))
                {cvrcnt++;
                 status = getStatus(status, Op,7);

                }



          }
        
        
        

          values.setValue(rowNumber, 0, sysNo);
            if(pRPDS!=null)
          values.setValue(rowNumber, 9, pRPDS);
            else
          values.setValue(rowNumber, 9, "");
            
          values.setValue(rowNumber, 11, pOrderno);
          values.setValue(rowNumber, 10, pShpDt);
          values.setValue(rowNumber, 12, pproid);
          values.setValue(rowNumber, 2, Integer.toString(nodecnt)+ printStatus(status,0));
          values.setValue(rowNumber, 3, Integer.toString(drawercnt)+ printStatus(status,1));
          values.setValue(rowNumber, 4, Integer.toString(sgrcnt)+ printStatus(status,2));
          values.setValue(rowNumber, 1, Integer.toString(framecnt)+ printStatus(status,3));
          values.setValue(rowNumber, 5, Integer.toString(mescnt)+ printStatus(status,4));
          values.setValue(rowNumber, 6, Integer.toString(hgtcnt)+ printStatus(status,5));
          values.setValue(rowNumber, 8, Integer.toString(cvrcnt)+ printStatus(status,7));
          values.setValue(rowNumber, 7, Integer.toString(scvrcnt)+ printStatus(status,6));
          values.setValue(rowNumber, 13, plddate);
          for(int i=1;i<database2.values.getRows();i++)
          {
              if(sysNo.equals(database2.values.getValue(i, 9))&&plddate.equals(database2.values.getValue(i, 1)))
              {
                  database2.values.setValue(i, 3, pShpDt);
                  String orderSeq = database2.values.getValue(i, 7);
                  String PSSD = database2.values.getValue(i, 18);
                  String RSSD = database2.values.getValue(i, 19);
                  String canDate = database2.values.getValue(i, 4);
                  if(pRPDS==null)
                      {
                      database2.values.setValue(i, 6, database2.getCat(orderSeq,PSSD,RSSD,canDate,database2.values.getValue(i, 10)));
                        }
                  else if(!database2.values.getValue(i, 6).contains("Can"))
                      database2.values.setValue(i, 6, database2.getCat(orderSeq,PSSD,RSSD,pRPDS,canDate,database2.values.getValue(i, 10)));
                  database2.values.setValue(i, 20, Integer.toString(framecnt)+"/"+Integer.toString(nodecnt)+"/"+Integer.toString(drawercnt));
                  database2.values.setValue(i, 11, getOrderType(database2.values.getValue(i, 11),comProId,framecnt,database2.values.getValue(i, 15),database2.values.getValue(i, 12)));
                  if(pRPDS==null)
                  {database2.values.setValue(i, 24, getStatus(database2.values.getValue(i, 24),highopno,database2.values.getValue(i, 23).trim(),database2.values.getValue(i, 23)));
                      }
                  else
                      database2.values.setValue(i, 24, getStatus(database2.values.getValue(i, 24),highopno,database2.values.getValue(i, 23).trim(),database2.values.getValue(i, 23),pRPDS));
                  if(!database2.values.getValue(i, 0).trim().equals(""))
                      database2.values.setValue(i, 0, database2.getQuarter(PSSD, database2.values.getValue(i, 6)));
                  if(!database2.values.getValue(i, 4).trim().equals(""))
                      {
                      database2.values.setValue(i, 24,"CANCELLED");
                      database2.values.setValue(i, 6,"Cancelled");
                      }
              }
              
          }
          closeCon(select,con); 

         return values;
        }
        catch( Exception e ) {
          e.printStackTrace();
        }
        return new table(0,0);
      
      
    }
} 