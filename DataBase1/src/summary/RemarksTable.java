package summary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *Stores and modifies the list of Remarks
 * @author raviab
 *
 */
public class RemarksTable {
//Remark remarks [];
static ArrayList<Remark> remarks;

/**
 * Gets the remark  at the required position
 * @param pos The index of the remark needed 
 * @return The specified remark
 */
public Remark getRemarkAt(int pos)
{
	if(remarks==null)
		readRemarksTable();
	return remarks.get(pos);
}

/**
 * Sets the remark at the specified index of the ArrayList remarks
 * @param pos Index of the remark to be set
 * @param remark The remark to be set
 */
public void setRemarkAt(int pos,Remark remark)
{		
	remarks.set(pos, remark);
	saveRemarksTable();
}

/**
 * @return Size of ArrayList remarks
 */
public int getSize()
{
	if(remarks == null)
		return 0;
	
	return remarks.size();
	
}

/**
 * Reads the contents from the file and creates the ArrayList of remarks
 */
public static  void readRemarksTable()
{
	try {
		FileInputStream fstream = new FileInputStream("SummaryRemarksTable.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		int comment_no=0;
		
		while ((str = br.readLine()) != null) {
			if(str=="-------------------------")
				comment_no++;
		}
		
		in.close();
		
		remarks = new ArrayList<Remark>();
		//line=0;
		fstream = new FileInputStream("SummaryRemarksTable.txt");
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		int r=0;
		String com="";
		while ((str = br.readLine()) != null) 
		{
			Remark temp = new Remark();
			temp.setPerson(str.substring(0, str.indexOf('~')));
			temp.setTimestamp((str.substring(str.indexOf('~')+1)));
			while((str=br.readLine()) != null)
			{
				if(!str.trim().equals("--------------------------------------------------"))
				{	if(com=="")
						com+=str;
					else
						com=com+"\n"+str;
				}
				else
				{
					temp.setComment(com);
					com ="";
					remarks.add(temp);
					break;
				}
			}
			
		}
		in.close();
	} 
	catch (Exception e) {
		System.err.println(e);
	}
}


/**
 * Writes the remarks to file
 */
static void saveRemarksTable()
{
	  FileWriter fstream;
	try {
		fstream = new FileWriter("SummaryRemarksTable.txt");

		  BufferedWriter out = new BufferedWriter(fstream);	
		  for(int i=0;i<remarks.size();i++)
		  {	String line = remarks.get(i).getPerson()+"~"+remarks.get(i).getTimestamp();
		  line+=System.getProperty( "line.separator")+remarks.get(i).getComment();
		  	line+=System.getProperty( "line.separator")+"--------------------------------------------------";
		  	out.write(line+System.getProperty( "line.separator"));
		  	
		  }
		  out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	  
}

public static void insertRemark(Remark tempRemark) {
	// TODO Auto-generated method stub
	if(remarks==null)
	{
		remarks = new ArrayList<Remark>();
	}
	remarks.add(tempRemark);
}

}
