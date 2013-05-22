package DataBase1;

public class table {

    int rows;
    int columns;
    String values[][];
    public table(int r, int c)
    {
        rows =r;columns =c;
        values = new String[r][c];
    }
    
    public void printTable()
    {
    	for(int i=0;i<rows;i++)
    	{	for(int j=0;j<columns;j++)
    			if(getValue(i, j)!=null&&getValue(i, j)!="")
    			System.out.println(getValue(i, j));
    	}System.out.println("\n");
    	}
   
    public void setValue(int i, int j,String value)
    {
        values[i][j]=value;
    }
   
    public String getValue(int i, int j)
    {
        return values[i][j];
    }
   
    public int getRowOfid(String string,String date)
    {
        for(int i=0;i<rows-1;i++)
        {
            if(values[i][0].equals(string)&&values[i][3].equals(date))
                return i ;
           
        }
       
        return -1;
       
    }
    
    public int getColumns()
    {
    	return columns;
    }
   
    public int getRows()
    {
        return rows;
    }
}