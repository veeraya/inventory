package bugreporter;

import java.util.ArrayList;

public class BugSumTable {

	Table table;
	BugList bugList ;
	ArrayList<Bug> bl;
	
	public Table GetTable()
	{
		bugList = new BugList();
		ArrayList<Bug> bl = new ArrayList<Bug>();
		bl = bugList.getBugList();
	table = new Table(4,6);
	for(int i=0;i<4;i++)
		for(int j=0;j<6;j++)
			table.setValue(i, j, "0");
	
	
	
		for(int k=0;k<bl.size();k++)
		{
			int i = bl.get(k).status.ordinal();
			int j = bl.get(k).asignee.ordinal();
			int value = Integer.parseInt(table.getValue(i, j))+1;
			table.setValue(i,j,String.valueOf(value));
		}
		
		return table;
		
	}
	
}
