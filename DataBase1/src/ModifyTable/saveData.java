package ModifyTable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.jasper.tagplugins.jstl.core.Out;

import DataBase1.SaveRemarks;
import DataBase1.database2;
import DataBase1.database1;
import DataBase1.table;

public class saveData {

public static void saveDatabase() throws IOException
{
	FileWriter fstream = new FileWriter("savedData.txt",true);
	  BufferedWriter out = new BufferedWriter(fstream);
	String line="";
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDate = dateFormat.format(cal.getTime());
	
	out.newLine();
	out.newLine();
	out.append(curDate);
	out.newLine();
	out.append("------------------------------------");
	  out.newLine();
	  table Table2 = database2.values;

	table Table1 = database1.values;
	int rsize1 = Table1.getRows();
	int csize1 = 11;

	int rsize2= Table2.getRows();
	int csize2 = 29;
	String id="", date = "";
	for(int i=1;i<rsize2;i++)
	{   
		line="";
	    id = Table2.getValue(i, 9);
	    date = Table2.getValue(i, 1);
	    //System.out.println(id);
	    for(int j=0;j<csize2;j++)
	    {
	    	line = Table2.getValue(i,j).trim();
	    	switch(j){
	    		case 0:
	    			out.append("Quarter: "+line);
	    			out.newLine();
	    			break;
	    		case 1:
	    			out.append("Load Date: "+line);
	    			out.newLine();
	    			break;
	    		case 2:
	    			out.append("Load Week: "+line);
	    			out.newLine();
	    			break;
	    		case 3:
	    			out.append("Shipped Date: "+line);
	    			out.newLine();
	    			break;
	    		case 4:
	    			out.append("Cancelled Date: "+line);
	    			out.newLine();
	    			break;
	    		case 5:
	    			out.append("Status Week: "+line);
	    			out.newLine();
	    			break;
	    		case 6:
	    			out.append("Order Category: "+line);
	    			out.newLine();
	    			break;
	    		case 7:
	    		    out.append("Order Seq#: "+line);
	    		    out.newLine();
	    		    break;
	    		case 8:
	    			out.append("Test Cell: "+line);
	    			out.newLine();
	    			break;
	    		case 9:
	    			out.append("Mfg#: "+line);
	    			out.newLine();
	    			break;
	    		case 10:
	    			out.append("Order#: "+line);
	    			out.newLine();
	    			break;
	    		case 11:
	    			out.append("Order Type: "+line);
	    			out.newLine();
	    			break;
	    		case 12:
	    			out.append("Model: "+line);
	    			out.newLine();
	    			break;
	    		case 13:
	    			out.append("Model Category: "+line);
	    			out.newLine();
	    			break;
	    		case 14:
	    			out.append("Model Number: "+line);
	    			out.newLine();
	    			break;
	    		case 15:
	    			out.append("Product Line: "+line);
	    			out.newLine();
	    			break;
	    		case 16:
	    			out.append("Customer: "+line);
	    			out.newLine();
	    			break;
	    		case 17:
	    			out.append("Country: "+line);
	    			out.newLine();
	    			break;
	    		case 18:
	    			out.append("PSSD: "+line);
	    			out.newLine();
	    			break;
	    		case 19:
	    			out.append("RSSD: "+line);
	    			out.newLine();
	    			break;
	    		case 20:
	    			out.append("Config: "+line);
	    			out.newLine();
	    			break;
	    		case 21:
	    			out.append("FOE: "+line);
	    			out.newLine();
	    			break;
	    		case 22:
	    			out.append("Test Time Rem: "+line);
	    			out.newLine();
	    			break;
	    		case 23:
	    			out.append("Test Time: "+line);
	    			out.newLine();
	    			break;
	    		case 24:
	    			out.append("Status: "+line);
	    			out.newLine();
	    			break;
	    		case 25:
	    			out.append("Remarks: "+line);
	    			out.newLine();
	    			break;
	    		case 26:
	    			out.append("Kit Short: "+line);
	    			out.newLine();
	    			break;
	    		case 27:
	    			out.append("Installation Short: "+line);
	    			out.newLine();
	    			break;
	    		case 28:
	    			out.append("Genuine Short: "+line);
	    			out.newLine();
	    			break;
	    
	    
	    	}

	    }

	    for(int i2=0;i2<rsize1;i2++)
	    {
	        if(Table1.getValue(i2, 0).equals(id)&&Table1.getValue(i2, 13).equals(date))
	        {
	           
	            for(int j2=1;j2<csize1;j2++)
	            {
	            	line = Table1.getValue(i2, j2);
	            	switch(j2)
	               {
	               case 1:
		    			out.append("Frame: "+line);
		    			out.newLine();
		    			break;   
	               case 2:
		    			out.append("Node: "+line);
		    			out.newLine();
		    			break;
	               case 3:
		    			out.append("SGR: "+line);
		    			out.newLine();
		    			break;
	               case 4:
		    			out.append("MES: "+line);
		    			out.newLine();
		    			break;
	               case 5:
		    			out.append("SGT: "+line);
		    			out.newLine();
		    			break;
	               case 6:
		    			out.append("SCVR: "+line);
		    			out.newLine();
		    			break;
	               case 7:
		    			out.append("CVR: "+line);
		    			out.newLine();
		    			break;
	               case 18:
		    			out.append("RPDS: "+line);
		    			out.newLine();
		    			break;
		    
	               }
	             }
	        }
	    }
		  out.newLine();
		  out.append("------------------------------------");
			out.newLine();
		}
	
		  out.newLine();
		  out.append("------------------------------------");
		  out.newLine();
		  out.append("------------------------------------");
			out.newLine();
			out.newLine();

			
			out.close();  
}
	
	

	
}
