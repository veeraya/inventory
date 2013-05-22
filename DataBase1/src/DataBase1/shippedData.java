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

public class shippedData {
	
	public  table shipped;
    public  int hasSelected = -1;

    public  ArrayList<String> num=new ArrayList<String>();
    
    
	public ArrayList<String> getUniqueCol(int Col)
    {
    	
    	
    	int exists=-1;
    	ArrayList<String> Uvalues = new ArrayList<String>();
    	for(int i=0;i<shipped.getRows();i++)
    	{
    		if(!(shipped.getValue(i, Col)==null)&&!shipped.getValue(i, Col).trim().equals(""))
    		{String [] split = shipped.getValue(i, Col).split("<br>");
    		for(int k=0;k<split.length;k++)
    		{
    		exists=-1;
    		
    		for(int j=0;j<Uvalues.size();j++)
    		{
    			if(Uvalues.get(j).trim().equals(split[k].trim()))
    			{
    				exists++;
    			}
    		}
    		if((exists==-1 && !split[k].trim().equals(""))||(split[k].trim().equals("")&&i==0))
    			Uvalues.add(split[k].trim());
    		}
    	}
    		}
    	return Uvalues;
    }
	
	
	
	public ArrayList<String> getallshipped()
	{
		num = new ArrayList<String>();
		for(int yr = 2012;true;yr++)
		{
			for(int qua = 2;3>1;qua=(qua%3)+1)
			{
					File f=new File("Q"+Integer.toString(qua)+"-"+Integer.toString(yr)+" shipped.txt");
					if(!f.exists()){
						return num;
					}
					num.add(Integer.toString(qua));
					num.add(Integer.toString(yr));
		  }
		}

	}
	
	public table retrieveData(int numSelected) throws IOException
	{
		Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
		int q = month/4+1;
		FileInputStream fstream;
		  
		ArrayList<String> contents = new ArrayList<String>();
		String str;
		if(numSelected==-1)
		fstream = new FileInputStream("Q"+Integer.toString(q)+"-"+Integer.toString(year)+" shipped.txt");	
		else
			fstream = new FileInputStream("Q"+(num.get(numSelected))+"-"+(num.get(numSelected+1))+" shipped.txt");	
		
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		while ((str=br.readLine()) != null) {
			if(str!=null)
				contents.add(str);
		}
		in.close();
		
		shipped = new table(contents.size(),38);
		String[] data;
	for(int i=0;i<contents.size();i++)
	{
		data = contents.get(i).split("@");
		for(int j=0;j<data.length;j++)
		{
			if(j==23)
				shipped.setValue(i, j, data[j].replace("#", "\n"));
			else
				shipped.setValue(i, j, data[j]);
		}
	}
	return shipped;
	} 

	
}
