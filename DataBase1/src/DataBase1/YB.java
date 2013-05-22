package DataBase1;

import java.util.ArrayList;

public class YB {
	public static table YB ;
    public static ArrayList<String> getUniqueCol(int Col)
    {
    	
    	
    	int exists=-1;
    	ArrayList<String> Uvalues = new ArrayList<String>();
    	for(int i=0;i<YB.getRows();i++)
    	{	
    		String [] split = YB.getValue(i, Col).split(">");
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
    			Uvalues.add(split[k]);
    		}
    	}
    	return Uvalues;
    }
	
	public static table startfilter()
	{
		int canCount=0;
		String id="",date="";
		table Table = database2.values;
		table supTable = database1.values;
		int k=0;
		int rsize1 = supTable.getRows();
		int csize1 = 11;

		int rsize2= Table.getRows();
		int csize2 = 29;

		for(int i=1;i<Table.getRows();i++)
		{
			if(Table.getValue(i, 6).trim().toLowerCase().equals("yb"))
				canCount++;
		}
		YB = new table(canCount,40);
		for(int i=1,l=-1;i<rsize2;i++)
		{   k=0;
		    id = Table.getValue(i, 9);
		    date = Table.getValue(i, 1);
		    if(Table.getValue(i, 6).trim().toLowerCase().equals("yb"))
		    {
		    	l++;
		    for(int j=0;j<csize2;j++,k++)
		    {
		    	if(j==7)
		    		YB.setValue(l, j, Table.getValue(i,29));
		    	else
		    	YB.setValue(l, j, Table.getValue(i,j));

		    }
		    for(int i2=0;i2<rsize1;i2++)
		    {
		    	
		        if(supTable.getValue(i2, 0).equals(id)&&supTable.getValue(i2, 13).equals(date))
		        {
		            for(int j2=1;j2<csize1-1;j2++)
		            {
		            	YB.setValue(l, k+j2-1, supTable.getValue(i2,j2));  
		            }
		            break;
		        }
		       
		        
		    }
			}
		    }
		return YB;
	}

}
