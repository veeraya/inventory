package bugreporter;

public class Table {

	int rows;
	int columns;
	String values[][];
	Table(int r, int c)
	{
		rows =r;columns =c;
		values = new String[r][c];
	}
	
	public void setValue(int i, int j,String value)
	{
		values[i][j]=value;
	}
	
	public String getValue(int i, int j)
	{
		return values[i][j];
	}
	
	public int getRowOfid(String string)
	{
		for(int i=0;i<rows;i++)
		{
			if(values[i][0].equalsIgnoreCase(string))
				return i ;
			
		}
		
		return -1;
		
	}
	
	public int getRows()
	{
		return rows;
	}
}
