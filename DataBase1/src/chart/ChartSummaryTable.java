package chart;

import java.util.ArrayList;

import summary.SummaryTable;

import DataBase1.database2;
import DataBase1.table;

/**
 * This creates summary table displayed in Chart.jsp
 * @author raviab
 *
 */
public class ChartSummaryTable {
	table tbl = new table(8,3);
	//SummaryTable summaryTable = new SummaryTable();
	//table comtbl = summaryTable.getCombinedTable();
	ArrayList<Integer> statusp = new ArrayList<Integer>();
	ArrayList<Integer> statusz = new ArrayList<Integer>();
	/**
	 * 
	 * @return values of database 2
	 */
	public table getComTable()
	{
		 return database2.values;
		
	}
	/**
	 * Creates the table to be displayed and populates fields
	 * @return The table to be displayed
	 */
	public table createTable()
	{
		
		
		table comtbl = getComTable();
		for(int i=0;i<8;i++)
		{	statusp.add(0);
			statusz.add(0);
		}
		for(int i=0;i<comtbl.getRows();i++)
		{
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("GEODIS")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(0, statusp.get(0)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("CPC")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(1, statusp.get(1)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("POST TEST")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(2, statusp.get(2)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("TEST")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(3, statusp.get(3)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("PRE-TEST")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(4, statusp.get(4)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("BUILD")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(5, statusp.get(5)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("KIT")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(6, statusp.get(6)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("NOT RELEASE")&&getModel(comtbl.getValue(i, 12)).equals("P"))
				statusp.set(7, statusp.get(7)+1);
		
		}
		
		for(int i=0;i<comtbl.getRows();i++)
		{
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("GEODIS")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(0, statusz.get(0)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("CPC")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(1, statusz.get(1)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("POST TEST")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(2, statusz.get(2)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("TEST")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(3, statusz.get(3)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("PRE-TEST")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(4, statusz.get(4)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("BUILD")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(5, statusz.get(5)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("KIT")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(6, statusz.get(6)+1);
			if(comtbl.getValue(i, 24).toString().trim().toUpperCase().equals("NOT RELEASE")&&getModel(comtbl.getValue(i, 12)).equals("Z"))
				statusz.set(7, statusz.get(7)+1);
		
		}
		
		tbl = new table(8,3);
		tbl.setValue(0, 0, "Geodis");
		tbl.setValue(1, 0, "CPC");
		tbl.setValue(2, 0, "POST TEST");
		tbl.setValue(3, 0, "TEST");
		tbl.setValue(4, 0, "PRETEST");
		tbl.setValue(5, 0, "BUILD");
		tbl.setValue(6, 0, "KIT");
		tbl.setValue(7, 0, "NOT RELEASE");
		
		for(int i=0;i<8;i++)
		{
			tbl.setValue(i, 1, statusp.get(i).toString());
			tbl.setValue(i, 2, statusz.get(i).toString());
		}
		return tbl;

	}
    private String getModel(String value) {
		// TODO Auto-generated method stub
    	if(value.trim().equals("FHB")||value.trim().equals("FHB MIMIC"))
    		return "P";
    	else return "Z";
	//
	}

}
