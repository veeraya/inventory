package DataBase1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadRemarks
 */
@WebServlet("/ReadRemarks")
public class ReadRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static table remarkstab = null;
	private static ArrayList<String> remarks = new ArrayList<String>();
	private static ArrayList<String> priority = new ArrayList<String>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadRemarks() {
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
	static table Table;
	static String contents[];
	
	
	public static ArrayList<String> getArrayListRem()
	{
		return remarks;
	}
	
	public static ArrayList<String> getArrayListPri()
	{
		return priority;
	}
	
	public static ArrayList<String> getTable() throws IOException
	{
		Table=null;
		readContents();
		remarks = createTable();
		return remarks;
	}



	private static void readContents() throws IOException{

		File f=new File("remarks.txt");
		  if(!f.exists()){
			  f.createNewFile();
		  }
		try {
			FileInputStream fstream = new FileInputStream("remarks.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			int line=0;
			
			while ((str = br.readLine()) != null) {
				line++;
			}
			Table = new table(line,5);
			in.close();
			
			contents = new String[line];
			line=0;
			fstream = new FileInputStream("remarks.txt");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			while ((str = br.readLine()) != null) {
				if(str!=null)
				contents[line] = str;
				line++;
			}
			in.close();
		} 
		catch (Exception e) {
			System.err.println(e);
		}
	}

	private static ArrayList<String> createTable()
	{
		int isfirst = -1;
		priority = new ArrayList<String>(); 
		remarkstab = new table(contents.length,5);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormat.format(cal.getTime());
		ArrayList<String> rems= new ArrayList<String>();
		if(contents.length==0)
		{
			for(int i=0;i<database2.values.getRows();i++)
			{
				database2.values.setValue(i, 29, "0");
				database2.values.setValue(i, 25, "");
			}
				
		}
		for(int i=0;i<contents.length;i++)
		{
			isfirst = -1;
			int k= contents[i].indexOf('#');
			int x= contents[i].indexOf('^');
			int l = contents[i].lastIndexOf('*');
			int j = contents[i].indexOf('*')+1;
			int n = contents[i].indexOf("!@#$");
			if(j!=-1&&x!=-1&&k!=-1&&l!=-1)
			{
			String str = contents[i].substring(0, k);
			String rem = contents[i].substring(j,l);
			String name = contents[i].substring(x+1, j-1);
			
			String date = contents[i].substring(k+1, x);
			if(rem.contains("~"))
			rem = rem.replace('~', '\n');
			if(rem.equals("null"))
				rem="";
			if(rem.indexOf("\n")==1)
			{
				rem.replaceFirst("\n", "");
			}
			if(rem.indexOf(" ")==0)
			{
				rem.replaceFirst(" ", "");
			}
			String remDate = contents[i].substring(n+4);
			String remDatesub="";
			if(!remDate.equals("null"))
			remDatesub = remDate.substring(5);
			String seq = contents[i].substring(l+1,n);
			if(seq.contains("~"))	
			seq = seq.replace('~', '\n');
			if(seq.equals("")||seq.equals("null"))
				seq = "0";
			if(remDate.equals(curDate))
			{

				for(int a=i-1;a>=0;a--)
				{
					if(str.equals(remarkstab.getValue(a, 0))&&date.equals(remarkstab.getValue(a, 3)))
					{
						isfirst = 1;
						if(((!rem.equals(remarkstab.getValue(a, 1)))))
							{
							rems.add(str);
							break;
							}
						if(!seq.equals(remarkstab.getValue(a, 2)))
						{
							priority.add(str);
							break;
						}
						break;
					}
				}
				if(isfirst==-1)
				{
					if(!rem.equals(""))
						{
						rems.add(str);
						isfirst=1;
						}
					if(!seq.equals("0"))
						{
						priority.add(str);
						isfirst=1;
						}
					
				}
				
			}
			
			remarkstab.setValue(i, 0, str);
			remarkstab.setValue(i, 1, rem);
			remarkstab.setValue(i, 2, seq);
			remarkstab.setValue(i, 3, date);
			remarkstab.setValue(i, 4, name);
			
			for(int m=0;m<database2.values.getRows();m++)
			{
			if(database2.values.getValue(m, 9).equals(str)&&database2.values.getValue(m, 1).equals(date))
				{
				if(!remDatesub.equals(""))
				{rem = "\n"+remDatesub+":"+name+":"+rem;
				database2.values.setValue(m, 29, seq);
				database2.values.setValue(m, 25, rem);
				}
				else
				{database2.values.setValue(m, 29, "");
				database2.values.setValue(m, 25, "");
				}
				
				}
			}
		}
		
	}
		return rems;
	}
}
