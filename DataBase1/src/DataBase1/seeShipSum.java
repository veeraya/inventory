package DataBase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class seeShipSum {
	
	public void checkifExists(String filename) throws IOException
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
	
	
	public table setVal(table Summ,ArrayList<String> month)
	{
		Summ.setValue(0, 0, "Model");
		Summ.setValue(0, 1, "Regen");
		Summ.setValue(0, 2, month.get(0));
		Summ.setValue(0, 3, month.get(1));
		Summ.setValue(0, 4, month.get(2));
		Summ.setValue(0, 5, "Total");
		Summ.setValue(0, 6, "Ship vs Regen");
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
	
	
	public table getSummary(String filename) throws IOException
	{
	String summfile = filename+" shippedsum.txt";
	filename = filename+" shipped.txt";
	ArrayList<String> month= new ArrayList<String>();
	if(filename.contains("Q1"))
	{	month.add("Jan");
	month.add("Feb");
	month.add("Mar");
	}
	else if(filename.contains("Q2"))
		{month.add("Apr");
	month.add("May");
	month.add("Jun");
		}
	else if(filename.contains("Q3"))
		{month.add("Jul");
	month.add("Aug");
	month.add("Sep");
		}	
	else if(filename.contains("Q4"))
	{	month.add("Oct");
	month.add("Nov");
	month.add("Dec");
	}
		FileInputStream	fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		ArrayList<String> contents = new ArrayList<String>();
		
		while ((str=br.readLine()) != null) {
			if(str!=null)
				contents.add(str);
		}
	
		
		
		
		
		
		String modelCat;
		String shipDt,mon;
		table SummTable = new table(12, 7);
		setVal(SummTable,month);
		table value = new table(10, 3);
		
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
		String[] values = new String[40];
		for(int i=0;i<contents.size();i++)
		{
			values = contents.get(i).split("@");
			modelCat = values[13].trim();
			shipDt = values[3];
			mon = shipDt.substring(0,2); 
			if(values[11].trim().equals("MES Loose")||values[11].trim().equals("MES Undefined"))
				continue;
			parseMonth = ((Integer.parseInt(mon)-1)%3);
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
			else
			{
				continue;
			}
		}
		
		for(int i=1;i<11;i++)
		{
			int total = Integer.parseInt(value.getValue(i-1, 0))+Integer.parseInt(value.getValue(i-1, 1))+Integer.parseInt(value.getValue(i-1, 2));
			SummTable.setValue(i, 2, value.getValue(i-1, 0));
			SummTable.setValue(i, 3, value.getValue(i-1, 1));
			SummTable.setValue(i, 4, value.getValue(i-1, 2));
			
			SummTable.setValue(i, 5, Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, 5));
			perc*=100;
			SummTable.setValue(i, 6, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 6,"Not Applicable");
			
			}
		
		int mon1tot=0;
		int mon2tot=0;
		int mon3tot=0;
		int tot=0;
		
		for(int i=1;i<11;i++)
		{
			mon1tot += Integer.parseInt(SummTable.getValue(i, 2));
			mon2tot += Integer.parseInt(SummTable.getValue(i, 3));
			mon3tot += Integer.parseInt(SummTable.getValue(i, 4));
			tot += Integer.parseInt(SummTable.getValue(i, 5));
		}	
			SummTable.setValue(11, 2, Integer.toString(mon1tot));
			SummTable.setValue(11, 3, Integer.toString(mon2tot));
			SummTable.setValue(11, 4, Integer.toString(mon3tot));
			SummTable.setValue(11, 5, Integer.toString(tot));	
	
		
		return SummTable;
	}

	public table getSummaryMes(String filename) throws IOException
	{
	String summfile = filename+" shippedsumMES.txt";
	filename = filename+" shipped.txt";
	ArrayList<String> month= new ArrayList<String>();
	if(filename.contains("Q1"))
	{	month.add("Jan");
	month.add("Feb");
	month.add("Mar");
	}
	else if(filename.contains("Q2"))
		{month.add("Apr");
	month.add("May");
	month.add("Jun");
		}
	else if(filename.contains("Q3"))
		{month.add("Jul");
	month.add("Aug");
	month.add("Sep");
		}	
	else if(filename.contains("Q4"))
	{	month.add("Oct");
	month.add("Nov");
	month.add("Dec");
	}
		FileInputStream	fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		ArrayList<String> contents = new ArrayList<String>();
		
		while ((str=br.readLine()) != null) {
			if(str!=null)
				contents.add(str);
		}
	
		
		
		
		
		
		String modelCat;
		String shipDt,mon;
		table SummTable = new table(12, 7);
		setVal(SummTable,month);
		table value = new table(10, 3);
		
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
		String[] values = new String[40];
		for(int i=0;i<contents.size();i++)
		{
			values = contents.get(i).split("@");
			modelCat = values[13];
			shipDt = values[3];
			mon = shipDt.substring(0,2); 
			 if(values[11].trim().equals("BOX MIMIC")||values[11].trim().equals("MES BOX")||values[11].trim().equals("BOX"))
					continue;
			parseMonth = ((Integer.parseInt(mon)-1)%3);
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
			int total = Integer.parseInt(value.getValue(i-1, 0))+Integer.parseInt(value.getValue(i-1, 1))+Integer.parseInt(value.getValue(i-1, 2));
			SummTable.setValue(i, 2, value.getValue(i-1, 0));
			SummTable.setValue(i, 3, value.getValue(i-1, 1));
			SummTable.setValue(i, 4, value.getValue(i-1, 2));
			
			SummTable.setValue(i, 5, Integer.toString(total));
			if(total!=0)
			{
			float perc = Float.parseFloat(SummTable.getValue(i, 1))/Float.parseFloat(SummTable.getValue(i, 5));
			perc*=100;
			SummTable.setValue(i, 6, Float.toString(perc)+"%");
			}
			else
				SummTable.setValue(i, 6,"Not Applicable");
			
			}
		
		int mon1tot=0;
		int mon2tot=0;
		int mon3tot=0;
		int tot=0;
		
		for(int i=1;i<11;i++)
		{
			mon1tot += Integer.parseInt(SummTable.getValue(i, 2));
			mon2tot += Integer.parseInt(SummTable.getValue(i, 3));
			mon3tot += Integer.parseInt(SummTable.getValue(i, 4));
			tot += Integer.parseInt(SummTable.getValue(i, 5));
		}	
			SummTable.setValue(11, 2, Integer.toString(mon1tot));
			SummTable.setValue(11, 3, Integer.toString(mon2tot));
			SummTable.setValue(11, 4, Integer.toString(mon3tot));
			SummTable.setValue(11, 5, Integer.toString(tot));	
	
		
		return SummTable;
	}

	
	
}
