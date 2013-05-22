package bugreporter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;



public class BugList {

	ArrayList<Bug> buglist;


public void AddNewBug(Bug bug)
{
	readBugList();
	if(buglist==null)
		buglist = new ArrayList<Bug>();
	buglist.add(bug);
	
	
}


public void RemoveBug(int pos)
{
	readBugList();
	buglist.remove(pos);
	
	
}

public void ModifyBug(Bug bug)
{
	if(buglist.contains(bug))
	{
		int pos = buglist.indexOf(bug);
		buglist.remove(bug);
		buglist.add(pos,bug);
	}	
}
@SuppressWarnings("deprecation")
public ArrayList<Bug> getBugList()
{
	try{
		FileInputStream fstream = new FileInputStream("BUGLIST.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;


		buglist = new ArrayList<Bug>();
		//line=0;
		fstream = new FileInputStream("BUGLIST.txt");
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		int r=0;
		String line="", desc="";
		while ((str = br.readLine()) != null) 
		{
			Bug bug = new Bug();
			String tokens[] = str.split("~");
			bug.Filer=tokens[0];
			bug.name = tokens[1];
			bug.asignee=Assignee.valueOf(tokens[2]);
			bug.closedate=new Time(1234)	;		
			bug.filedate=new Time (1234);
			bug.status=Status.valueOf(tokens[5]);

			//			buglist.get(i).Filer+"~"+buglist.get(i).name+"~"
			//					+buglist.get(i).asignee+"~"+
			//			buglist.get(i).closedate.toString()+
			//			"~"+buglist.get(i).filedate.toString()+"~"+
			//			buglist.get(i).status+"~"+
			//			System.getProperty( "line.separator")+buglist.get(i).desc;
			//			  line+=System.getProperty( "line.separator")+
			//					  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+System.getProperty( "line.separator")+
			//					  buglist.get(i).getComString();
			//			  line+=System.getProperty( "line.separator")
			//					  +"----------------------------"+System.getProperty( "line.separator");
			//			
			while((str=br.readLine()) != null)
			{
				if(!str.trim().equals("~~~~~~~~~~~~~~~~~~~~~~~~~~~~"))
				{	if(desc=="")
						desc+=str;
					else
						desc=desc+"\n"+str;
				}
				else
				{
					bug.desc=desc;
					desc ="";
					break;
					
				}
			}
			while((str=br.readLine()) != null)
			{
				if(str.trim().equals("----------------------------"))
					break;
				tokens = str.split("~");
				Comment com = new Comment();
				com.name = tokens[0];
				com.time = new Time(1234);
				String comdesc = "";
				while((str=br.readLine()) != null)
				{
					if(!str.trim().equals("________________"))
					{	if(comdesc=="")
						comdesc+=str;
					else
						comdesc=comdesc+"\n"+str;
					}
					else
					{
						com.content=comdesc;
						desc ="";
						bug.comlist.add(com);
						break;
					}


				}
			}
			buglist.add(bug);
		}
		in.close();
		return buglist;
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	return buglist;
}
	
	

public void SaveBugList()
{
	FileWriter fstream;
	try {
		fstream = new FileWriter("BUGLIST.txt");

		  BufferedWriter out = new BufferedWriter(fstream);	
		  for(int i=0;i<buglist.size();i++)
		  {	String line = buglist.get(i).Filer+"~"+buglist.get(i).name+"~"+buglist.get(i).asignee+"~"+buglist.get(i).closedate.toString()+"~"+buglist.get(i).filedate.toString()+"~"+buglist.get(i).status+"~"+System.getProperty( "line.separator")+buglist.get(i).desc;
		  line+=System.getProperty( "line.separator")+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+System.getProperty( "line.separator")+buglist.get(i).getComString();
		  line+="----------------------------"+System.getProperty( "line.separator");
		  out.write(line);
		  	
		  }
		  out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public ArrayList<Bug> ReadBugList()
{
	readBugList();
	return buglist;
}
private void readBugList() {
	// TODO Auto-generated method stub
	
}

}