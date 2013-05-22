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
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class dataSummary
 */
@WebServlet("/dataSummary")
public class dataSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dataSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession(true);

		table Summ = dataSummary.getSummary();
		table SummWeek = dataSummary.getSummaryWeek();
		table SummMES = dataSummary.getSummaryMES();
		table SummWeekMES = dataSummary.getSummaryWeekMES();
		session.setAttribute("tablesum", Summ);
		session.setAttribute("tablesumweek", SummWeek);
		session.setAttribute("tablesumMES", SummMES);
		session.setAttribute("tablesumweekMES", SummWeekMES);
		response.sendRedirect("viewvaluesSummary.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession(true);
	ArrayList<String> numbers = new ArrayList<String>();
		if(request.getParameter("SubNums")!=null)
		{
			session.setAttribute("isMesd", 0);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbers"+i));
				
				}
			FileWriter fstream = new FileWriter("datasum.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i=0;i<numbers.size();i++)
					{
					out.write(numbers.get(i));
					out.newLine();
					}
				out.close();
			}
		
		if(request.getParameter("SubNumsWeek")!=null)
		{
			session.setAttribute("isMesd", 0);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbersweek"+i));
				
				}
			FileWriter fstream = new FileWriter("datasumweek.txt");
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
			session.setAttribute("isMesd", 1);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbersm"+i));
				
				}
			FileWriter fstream = new FileWriter("datasummes.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i=0;i<numbers.size();i++)
					{
					out.write(numbers.get(i));
					out.newLine();
					}
				out.close();
			}
		
		if(request.getParameter("SubNumsWeekMES")!=null)
		{
			session.setAttribute("isMesd", 1);
			for(int i=1;i<11;i++)
				{
				numbers.add(request.getParameter("numbersweekm"+i));
				
				}
			FileWriter fstream = new FileWriter("datasumweekmes.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i=0;i<numbers.size();i++)
					{
					out.write(numbers.get(i));
					out.newLine();
					}
				out.close();
			}
		
			response.sendRedirect("dataSummary"); 
	}

	public static void checkifExists(String filename) throws IOException
	{
		 File f;
		  f=new File(filename);
		  if(!f.exists()){
		  f.createNewFile();
		  FileWriter fstream = new FileWriter(filename);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");
		  out.newLine();
		  out.write("0");

		  out.close();
		  }
		  
		
		
	}
	
	
	public static ArrayList<String> sortByMonth(ArrayList<String> month)
	{
		String[] values= new String[month.size()];String temp="";
		for(int i=0;i<month.size();i++)
			values[i]= month.get(i);
		
		String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		for(int k=0;k<values.length-1;k++)
		{
			for(int j=k+1;j<values.length;j++)
			{
		int fpos=15;
    	int spos=15;
    	for(int i=0;i<months.length;i++)
    	{
    		if(months[i].equals(values[j]))
    			fpos=i;
    		if(months[i].equals(values[k]))
    			spos=i;
    	}
    	if(fpos<spos)
    		{
    		temp = values[k];
    		values[k]=values[j];
    		values[j]=temp;
    		}
			}
			}
		ArrayList<String> newMonth= new ArrayList<String>();
		for(int i=0;i<month.size();i++)
			newMonth.add(values[i]);
		
		return newMonth;
		
	}
	
	
	public static table setVal(table Summ,ArrayList<String> month)
	{
		Summ.setValue(0, 0, "Model");
		Summ.setValue(0, 1, "Regen");
		for(int i=0;i<month.size();i++)
			Summ.setValue(0, 2+i, month.get(i));
		Summ.setValue(0, 2+month.size(), "Total");
		Summ.setValue(0, 2+month.size()+1, "Load vs Regen");
		Summ.setValue(1, 0, "FHB");
		Summ.setValue(2, 0, "FHA");
		Summ.setValue(3, 0, "FHB Mimic");
		Summ.setValue(4, 0, "zGMR");
		Summ.setValue(5, 0, "zHelix");
		Summ.setValue(6, 0, "zMR");
		Summ.setValue(7, 0, "z10 EC");
		Summ.setValue(8, 0, "zBX");
		Summ.setValue(9, 0, "zMR");
		Summ.setValue(10, 0, "Crypto Card");
		
		
		return Summ;
	}
	
	
	public static table getSummary() throws IOException
	{
	String summfile ="datasum.txt";
	checkifExists(summfile);
	ArrayList<String> month= new ArrayList<String>();
String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
table values = database2.values;
	for(int i=1;i<values.getRows();i++)
	{

		if(values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
			continue;
	
		String date = values.getValue(i, 1).substring(5,7);
		int datei = Integer.parseInt(date)-1;
		int ispresent=-1;
		for(int j=0;j<month.size();j++)
		{
			if(month.get(j).equals(months[datei]))
				ispresent++;
		}
		if(ispresent==-1)
			month.add(months[datei]);
	}

		month = sortByMonth(month);
		
		
		String modelCat;
		String shipDt,mon;
		table SummTable = new table(12, 4+month.size());
		setVal(SummTable,month);
		table value = new table(10, month.size());
		
		checkifExists(summfile);
		FileInputStream	rfstream = new FileInputStream(summfile);
		DataInputStream rin = new DataInputStream(rfstream);
		BufferedReader rbr = new BufferedReader(new InputStreamReader(rin));
		String rstr=null;
		int line=0;
		
		while ((rstr=rbr.readLine()) != null) {
			if(rstr!=null)
			{
				line++;
			SummTable.setValue(line, 1, rstr);
			}
			}
		
		
		for(int i=0;i<value.getRows();i++)
		{
			for(int j=0;j<value.getColumns();j++)
			{
				value.setValue(i, j, "0");
			}
		}
		int parseMonth;
		for(int i=1;i<values.getRows();i++)
		{
			if(values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
				continue;
		
			modelCat = values.getValue(i, 13);
			mon = values.getValue(i, 1).substring(5,7);
			String monthval = months[Integer.parseInt(mon)-1];
			parseMonth = month.indexOf(monthval);
			if	(modelCat.contains("FHB Mimic"))
	    		value.setValue(2, parseMonth, Integer.toString(Integer.parseInt(value.getValue(2, parseMonth))+1));
			else if(modelCat.contains("FHB"))
				value.setValue(0, parseMonth, Integer.toString(Integer.parseInt(value.getValue(0, parseMonth))+1));
			else if	(modelCat.contains("FHA"))
	    		value.setValue(1, parseMonth, Integer.toString(Integer.parseInt(value.getValue(1, parseMonth))+1));
			else if	(modelCat.contains("zGMR"))
	    		value.setValue(3, parseMonth, Integer.toString(Integer.parseInt(value.getValue(3, parseMonth))+1));
			else if	(modelCat.contains("zGryphon"))
	    		value.setValue(4, parseMonth, Integer.toString(Integer.parseInt(value.getValue(4, parseMonth))+1));
			else if	(modelCat.contains("zHelix"))
	    		value.setValue(5, parseMonth, Integer.toString(Integer.parseInt(value.getValue(5, parseMonth))+1));
			else if	(modelCat.contains("zMR"))  				
	    		value.setValue(6, parseMonth, Integer.toString(Integer.parseInt(value.getValue(6, parseMonth))+1));
			else if	(modelCat.contains("z10 EC"))
	    		value.setValue(7, parseMonth, Integer.toString(Integer.parseInt(value.getValue(7, parseMonth))+1));
			else if	(modelCat.contains("zBX"))
	    		value.setValue(8, parseMonth, Integer.toString(Integer.parseInt(value.getValue(8, parseMonth))+1));
			else if	(modelCat.contains("Crypto Card"))
				value.setValue(9, parseMonth, Integer.toString(Integer.parseInt(value.getValue(9, parseMonth))+1));
		}
		
		for(int i=1;i<11;i++)
		{
			int total = 0;
			for(int k=0;k<month.size();k++)
			{
				SummTable.setValue(i, 2+k, value.getValue(i-1, k));
				total+=Integer.parseInt(value.getValue(i-1, k));
			}
			SummTable.setValue(i, 2+month.size(), Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, month.size()+2));
			perc*=100;
			SummTable.setValue(i, 2+month.size()+1, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 2+month.size()+1,"Not Applicable");
			
			}
		int [] montot = new int[month.size()+1];
	
		
		for(int i=1;i<11;i++)
		{
			for(int j=0;j<month.size()+1;j++)
			{
				montot[j] += Integer.parseInt(SummTable.getValue(i, 2+j));
			}
			
		}	
		for(int j=0;j<month.size()+1;j++)
		{
			SummTable.setValue(11, 2+j, Integer.toString(montot[j]));
		}
		return SummTable;
	}

	
	
	public static ArrayList<String> sortWeek(ArrayList<String> data)
	{
		
		String[] values= new String[data.size()];String temp="";
		for(int i=0;i<data.size();i++)
			values[i]= data.get(i);
		
		for(int k=0;k<values.length-1;k++)
		{
			for(int j=k+1;j<values.length;j++)
			{
		int fpos=Integer.parseInt(values[j]);
    	int spos=Integer.parseInt(values[k]);

    	if(fpos<spos)
    		{
    		temp = values[k];
    		values[k]=values[j];
    		values[j]=temp;
    		}
			}
			}
		ArrayList<String> newdata= new ArrayList<String>();
		for(int i=0;i<data.size();i++)
			newdata.add(values[i]);
		
		return newdata;
		
	}
	

	public static table getSummaryWeek() throws IOException
	{
	String summfile ="datasumweek.txt";
	checkifExists(summfile);
	ArrayList<String> week= new ArrayList<String>();
	
	table values = database2.values;
	for(int i=1;i<values.getRows();i++)
	{ 
		if(values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
		continue;
	
		String date = values.getValue(i, 2);
		int ispresent=-1;
		for(int j=0;j<week.size();j++)
		{
			if(week.get(j).equals(date))
				ispresent++;
		}
		if(ispresent==-1)
			week.add(date);
	}

	week = sortWeek(week);
		
		
		String weekval;
		String modelCat;

		table SummTable = new table(12, 4+week.size());
		setVal(SummTable,week);
		table value = new table(10, week.size());
		
		checkifExists(summfile);
		FileInputStream	rfstream = new FileInputStream(summfile);
		DataInputStream rin = new DataInputStream(rfstream);
		BufferedReader rbr = new BufferedReader(new InputStreamReader(rin));
		String rstr=null;
		int line=0;
		
		while ((rstr=rbr.readLine()) != null) {
			if(rstr!=null)
			{
				line++;
			SummTable.setValue(line, 1, rstr);
			}
			}
		
		
		for(int i=0;i<value.getRows();i++)
		{
			for(int j=0;j<value.getColumns();j++)
			{
				value.setValue(i, j, "0");
			}
		}
		int parseMonth;
		for(int i=1;i<values.getRows();i++)
		{
			 if(values.getValue(i, 11).trim().equals("MES Loose")||values.getValue(i, 11).trim().equals("MES Undefined"))
					continue;
				
			if(!values.getValue(i,4 ).trim().equals(""))
				continue;
			modelCat = values.getValue(i, 13);
			weekval = values.getValue(i, 2);
			parseMonth = week.indexOf(weekval);
			if	(modelCat.contains("FHB Mimic"))
	    		value.setValue(2, parseMonth, Integer.toString(Integer.parseInt(value.getValue(2, parseMonth))+1));
			else if(modelCat.contains("FHB"))
				value.setValue(0, parseMonth, Integer.toString(Integer.parseInt(value.getValue(0, parseMonth))+1));
			else if	(modelCat.contains("FHA"))
	    		value.setValue(1, parseMonth, Integer.toString(Integer.parseInt(value.getValue(1, parseMonth))+1));
			else if	(modelCat.contains("zGMR"))
	    		value.setValue(3, parseMonth, Integer.toString(Integer.parseInt(value.getValue(3, parseMonth))+1));
			else if	(modelCat.contains("zGryphon"))
	    		value.setValue(4, parseMonth, Integer.toString(Integer.parseInt(value.getValue(4, parseMonth))+1));
			else if	(modelCat.contains("zHelix"))
	    		value.setValue(5, parseMonth, Integer.toString(Integer.parseInt(value.getValue(5, parseMonth))+1));
			else if	(modelCat.contains("zMR"))  				
	    		value.setValue(6, parseMonth, Integer.toString(Integer.parseInt(value.getValue(6, parseMonth))+1));
			else if	(modelCat.contains("z10 EC"))
	    		value.setValue(7, parseMonth, Integer.toString(Integer.parseInt(value.getValue(7, parseMonth))+1));
			else if	(modelCat.contains("zBX"))
	    		value.setValue(8, parseMonth, Integer.toString(Integer.parseInt(value.getValue(8, parseMonth))+1));
			else if	(modelCat.contains("Crypto Card"))
				value.setValue(9, parseMonth, Integer.toString(Integer.parseInt(value.getValue(9, parseMonth))+1));
		}
		
		for(int i=1;i<11;i++)
		{
			int total = 0;
			for(int k=0;k<week.size();k++)
			{
				SummTable.setValue(i, 2+k, value.getValue(i-1, k));
				total+=Integer.parseInt(value.getValue(i-1, k));
			}
			SummTable.setValue(i, 2+week.size(), Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, week.size()+2));
			perc*=100;
			SummTable.setValue(i, 2+week.size()+1, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 2+week.size()+1,"Not Applicable");
			
			}
		int [] montot = new int[week.size()+1];
	
		
		for(int i=1;i<11;i++)
		{
			for(int j=0;j<week.size()+1;j++)
			{
				montot[j] += Integer.parseInt(SummTable.getValue(i, 2+j));
			}
			
		}	
		for(int j=0;j<week.size()+1;j++)
		{
			SummTable.setValue(11, 2+j, Integer.toString(montot[j]));
		}
		return SummTable;
	}

	
	
	
	
	
	
	
	
	
	public static table getSummaryMES() throws IOException
	{
	String summfile ="datasummes.txt";
	checkifExists(summfile);
	ArrayList<String> month= new ArrayList<String>();
String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
table values = database2.values;
	for(int i=1;i<values.getRows();i++)
	{
		if(values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX"))
		 continue;
	
		String date = values.getValue(i, 1).substring(5,7);
		int datei = Integer.parseInt(date)-1;
		int ispresent=-1;
		for(int j=0;j<month.size();j++)
		{
			if(month.get(j).equals(months[datei]))
				ispresent++;
		}
		if(ispresent==-1)
			month.add(months[datei]);
	}

		month = sortByMonth(month);
		
		
		String modelCat;
		String shipDt,mon;
		table SummTable = new table(12, 4+month.size());
		setVal(SummTable,month);
		table value = new table(10, month.size());
		
		checkifExists(summfile);
		FileInputStream	rfstream = new FileInputStream(summfile);
		DataInputStream rin = new DataInputStream(rfstream);
		BufferedReader rbr = new BufferedReader(new InputStreamReader(rin));
		String rstr=null;
		int line=0;
		
		while ((rstr=rbr.readLine()) != null) {
			if(rstr!=null)
			{
				line++;
			SummTable.setValue(line, 1, rstr);
			}
			}
		
		
		for(int i=0;i<value.getRows();i++)
		{
			for(int j=0;j<value.getColumns();j++)
			{
				value.setValue(i, j, "0");
			}
		}
		int parseMonth;
		for(int i=1;i<values.getRows();i++)
		{
			 if(values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX"))
				 continue;
			if(!values.getValue(i,4 ).trim().equals(""))
				continue;
			modelCat = values.getValue(i, 13);
			mon = values.getValue(i, 1).substring(5,7);
			String monthval = months[Integer.parseInt(mon)-1];
			parseMonth = month.indexOf(monthval);
			if	(modelCat.contains("FHB Mimic"))
	    		value.setValue(2, parseMonth, Integer.toString(Integer.parseInt(value.getValue(2, parseMonth))+1));
			else if(modelCat.contains("FHB"))
				value.setValue(0, parseMonth, Integer.toString(Integer.parseInt(value.getValue(0, parseMonth))+1));
			else if	(modelCat.contains("FHA"))
	    		value.setValue(1, parseMonth, Integer.toString(Integer.parseInt(value.getValue(1, parseMonth))+1));
			else if	(modelCat.contains("zGMR"))
	    		value.setValue(3, parseMonth, Integer.toString(Integer.parseInt(value.getValue(3, parseMonth))+1));
			else if	(modelCat.contains("zGryphon"))
	    		value.setValue(4, parseMonth, Integer.toString(Integer.parseInt(value.getValue(4, parseMonth))+1));
			else if	(modelCat.contains("zHelix"))
	    		value.setValue(5, parseMonth, Integer.toString(Integer.parseInt(value.getValue(5, parseMonth))+1));
			else if	(modelCat.contains("zMR"))  				
	    		value.setValue(6, parseMonth, Integer.toString(Integer.parseInt(value.getValue(6, parseMonth))+1));
			else if	(modelCat.contains("z10 EC"))
	    		value.setValue(7, parseMonth, Integer.toString(Integer.parseInt(value.getValue(7, parseMonth))+1));
			else if	(modelCat.contains("zBX"))
	    		value.setValue(8, parseMonth, Integer.toString(Integer.parseInt(value.getValue(8, parseMonth))+1));
			else if	(modelCat.contains("Crypto Card"))
				value.setValue(9, parseMonth, Integer.toString(Integer.parseInt(value.getValue(9, parseMonth))+1));
		}
		
		for(int i=1;i<11;i++)
		{
			int total = 0;
			for(int k=0;k<month.size();k++)
			{
				SummTable.setValue(i, 2+k, value.getValue(i-1, k));
				total+=Integer.parseInt(value.getValue(i-1, k));
			}
			SummTable.setValue(i, 2+month.size(), Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, month.size()+2));
			perc*=100;
			SummTable.setValue(i, 2+month.size()+1, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 2+month.size()+1,"Not Applicable");
			
			}
		int [] montot = new int[month.size()+1];
	
		
		for(int i=1;i<11;i++)
		{
			for(int j=0;j<month.size()+1;j++)
			{
				montot[j] += Integer.parseInt(SummTable.getValue(i, 2+j));
			}
			
		}	
		for(int j=0;j<month.size()+1;j++)
		{
			SummTable.setValue(11, 2+j, Integer.toString(montot[j]));
		}
		return SummTable;
	}

	
	
	
	

	public static table getSummaryWeekMES() throws IOException
	{
	String summfile ="datasumweekmes.txt";
	checkifExists(summfile);
	ArrayList<String> week= new ArrayList<String>();
	
	table values = database2.values;
	for(int i=1;i<values.getRows();i++)
	{
		 if(values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX"))
			 continue;
		
		String date = values.getValue(i, 2);
		int ispresent=-1;
		for(int j=0;j<week.size();j++)
		{
			if(week.get(j).equals(date))
				ispresent++;
		}
		if(ispresent==-1)
			week.add(date);
	}

	week = sortWeek(week);
		
		
		String weekval;
		String modelCat;

		table SummTable = new table(12, 4+week.size());
		setVal(SummTable,week);
		table value = new table(10, week.size());
		
		checkifExists(summfile);
		FileInputStream	rfstream = new FileInputStream(summfile);
		DataInputStream rin = new DataInputStream(rfstream);
		BufferedReader rbr = new BufferedReader(new InputStreamReader(rin));
		String rstr=null;
		int line=0;
		
		while ((rstr=rbr.readLine()) != null) {
			if(rstr!=null)
			{
				line++;
			SummTable.setValue(line, 1, rstr);
			}
			}
		
		
		for(int i=0;i<value.getRows();i++)
		{
			for(int j=0;j<value.getColumns();j++)
			{
				value.setValue(i, j, "0");
			}
		}
		int parseMonth;
		for(int i=1;i<values.getRows();i++)
		{
			 if(values.getValue(i, 11).trim().equals("BOX MIMIC")||values.getValue(i, 11).trim().equals("MES BOX")||values.getValue(i, 11).trim().equals("BOX"))
				 continue;
			
			if(!values.getValue(i,4 ).trim().equals(""))
				continue;
			modelCat = values.getValue(i, 13).trim();
			weekval = values.getValue(i, 2);
			parseMonth = week.indexOf(weekval);
			if	(modelCat.contains("FHB Mimic"))
	    		value.setValue(2, parseMonth, Integer.toString(Integer.parseInt(value.getValue(2, parseMonth))+1));
			else if(modelCat.contains("FHB"))
				value.setValue(0, parseMonth, Integer.toString(Integer.parseInt(value.getValue(0, parseMonth))+1));
			else if	(modelCat.contains("FHA"))
	    		value.setValue(1, parseMonth, Integer.toString(Integer.parseInt(value.getValue(1, parseMonth))+1));
			else if	(modelCat.contains("zGMR"))
	    		value.setValue(3, parseMonth, Integer.toString(Integer.parseInt(value.getValue(3, parseMonth))+1));
			else if	(modelCat.contains("zGryphon"))
	    		value.setValue(4, parseMonth, Integer.toString(Integer.parseInt(value.getValue(4, parseMonth))+1));
			else if	(modelCat.contains("zHelix"))
	    		value.setValue(5, parseMonth, Integer.toString(Integer.parseInt(value.getValue(5, parseMonth))+1));
			else if	(modelCat.contains("zMR"))  				
	    		value.setValue(6, parseMonth, Integer.toString(Integer.parseInt(value.getValue(6, parseMonth))+1));
			else if	(modelCat.contains("z10 EC"))
	    		value.setValue(7, parseMonth, Integer.toString(Integer.parseInt(value.getValue(7, parseMonth))+1));
			else if	(modelCat.contains("zBX"))
	    		value.setValue(8, parseMonth, Integer.toString(Integer.parseInt(value.getValue(8, parseMonth))+1));
			else if	(modelCat.contains("Crypto Card"))
				value.setValue(9, parseMonth, Integer.toString(Integer.parseInt(value.getValue(9, parseMonth))+1));
			
		}
		
		for(int i=1;i<11;i++)
		{
			int total = 0;
			for(int k=0;k<week.size();k++)
			{
				SummTable.setValue(i, 2+k, value.getValue(i-1, k));
				total+=Integer.parseInt(value.getValue(i-1, k));
			}
			SummTable.setValue(i, 2+week.size(), Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, week.size()+2));
			perc*=100;
			SummTable.setValue(i, 2+week.size()+1, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 2+week.size()+1,"Not Applicable");
			
			}
		int [] montot = new int[week.size()+1];
	
		
		for(int i=1;i<11;i++)
		{
			for(int j=0;j<week.size()+1;j++)
			{
				montot[j] += Integer.parseInt(SummTable.getValue(i, 2+j));
			}
			
		}	
		for(int j=0;j<week.size()+1;j++)
		{
			SummTable.setValue(11, 2+j, Integer.toString(montot[j]));
		}
		return SummTable;
	}

	
	
	
	
}
