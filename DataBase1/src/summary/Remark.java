package summary;

import java.sql.Timestamp;

//import com.sun.jmx.snmp.Timestamp;

/**
 * @author raviab
 *	
 */
public class Remark {

	String person;
	Timestamp timestamp;
	String comment;
	
	public void setPerson(String personName)
	{
		//this.person = person;
		person = personName;
	}
	public void setTimestamp(String stamp)
	{
		timestamp = Timestamp.valueOf(stamp);
	}
	public void setComment(String commentText)
	{
		comment= commentText;
	}
	
	public String getComment()
	{
		return comment;
	}
	
	public String getPerson()
	{
		return person;
	}
	
	public String getTimestamp()
	{
		return timestamp.toString();
	}
	
}
