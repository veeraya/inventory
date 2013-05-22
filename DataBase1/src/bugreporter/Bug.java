package bugreporter;
import java.util.ArrayList;
import java.util.Date;




public class Bug {

	String Filer;
	String name;
	String desc;
	Status status;
	Assignee asignee;
	Date filedate;
	Date closedate;
	
	ArrayList<Comment> comlist = new ArrayList<Comment>();
	public String getComString() {
		String line="";
		// TODO Auto-generated method stub
		for(int i=0;i<comlist.size();i++)
		{
			line+=comlist.get(i).name+"~"+comlist.get(i).time.toString()+System.getProperty( "line.separator");
			line+=comlist.get(i).content;
			line+=System.getProperty( "line.separator")+"________________"+System.getProperty( "line.separator");
		}
		return line;
	}
	
	public ArrayList<Comment> getComList()
	{
		return comlist;
	}
	public String getName()
	{
		return name;
	}
	public String desc()
	{
		return desc;
	}
	public String getFiler()
	{
		return Filer;
	}
	public String getSatus()
	{
		return status.toString();
	}
	public String getAssignee()
	{
		return asignee.toString();
	}
	
}



