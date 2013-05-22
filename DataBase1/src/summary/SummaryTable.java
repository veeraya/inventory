package summary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DataBase1.database1;
import DataBase1.database2;
import DataBase1.table;

/**
 * The table at Summary.JSP is generated/modified by this class.
 * 
 * @author raviab
 * 
 */
public class SummaryTable {
	private table Table;
	table sumTable;
	ArrayList<String> regen;
	ArrayList<String> commit;

	public SummaryTable() {

	}

	/**
	 * @return The string of new orders to be displayed on top of the table.
	 */
	@SuppressWarnings("deprecation")
	public String getSummaryRemarks() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Calendar currDtCal = Calendar.getInstance();

		// Zero out the hour, minute, second, and millisecond
		currDtCal.set(Calendar.HOUR_OF_DAY, 0);
		currDtCal.set(Calendar.MINUTE, 0);
		currDtCal.set(Calendar.SECOND, 0);
		currDtCal.set(Calendar.MILLISECOND, 0);

		Date currDt = currDtCal.getTime();
		Date yesDt = new Date();
		yesDt = currDtCal.getTime();
		yesDt.setDate(yesDt.getDate() - 1);
		Date toDt;
		if (Table == null) {
			return "null";
		} else {
			ArrayList<String> newOrd = new ArrayList<String>();
			for (int i = 1; i < Table.getRows(); i++) {
				String toDate = Table.getValue(i, 1);
				try {
					toDt = formatter.parse(toDate);
				} catch (ParseException e) {
					toDt = null;
					// Print some error message back to the user
				}
				if (Table.getValue(i, 11).trim().equals("MES Loose")||Table.getValue(i, 11).trim().equals("MES Undefined"))
					continue;
				if (currDt.equals(toDt) || yesDt.equals(toDt)) {

					String[] split = Table.getValue(i, 12).split("<br>");
					for(int k=0;k<split.length;k++)
						newOrd.add(split[k]);
				}
			}

			ArrayList<String> alreadyPrinted = new ArrayList<String>();
			
			int totnum=0;
			String toShow = "New orders : " + newOrd.size() + " <br>( ";
			for (int i = 0; i < newOrd.size(); i++) {
				int exists=-1;
				int num=0;
				for(int j=0;j<alreadyPrinted.size();j++)
				{
					if(newOrd.get(i).equals(alreadyPrinted.get(j)))
					exists++;
				}
				if(exists==-1)
				 {
					totnum++;
					alreadyPrinted.add(newOrd.get(i));
					num++;
					for(int k=i+1;k<newOrd.size();k++)
					{
						if(newOrd.get(k).equals(newOrd.get(i)))
							num++;
					}
					String Order= "";
					if(newOrd.get(i).indexOf("<br>")!=-1)
						Order = newOrd.get(i).substring(newOrd.get(i).indexOf("<br>")+3);
					else
						Order = newOrd.get(i);
					
					toShow += Order + " x " + Integer.toString(num)+", ";
					if(totnum%4==0)
						toShow += "<br>";
					}
				}
			toShow += " )";
			return toShow;
		}
	}

	
	
	
	public String getSummaryRemarksMES() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Calendar currDtCal = Calendar.getInstance();

		// Zero out the hour, minute, second, and millisecond
		currDtCal.set(Calendar.HOUR_OF_DAY, 0);
		currDtCal.set(Calendar.MINUTE, 0);
		currDtCal.set(Calendar.SECOND, 0);
		currDtCal.set(Calendar.MILLISECOND, 0);

		Date currDt = currDtCal.getTime();
		Date yesDt = new Date();
		yesDt = currDtCal.getTime();
		yesDt.setDate(yesDt.getDate() - 1);
		Date toDt;
		if (Table == null) {
			return "null";
		} else {
			ArrayList<String> newOrd = new ArrayList<String>();
			for (int i = 1; i < Table.getRows(); i++) {
				String toDate = Table.getValue(i, 1);
				try {
					toDt = formatter.parse(toDate);
				} catch (ParseException e) {
					toDt = null;
					// Print some error message back to the user
				}
				if((Table.getValue(i, 11).trim().equals("BOX MIMIC")||Table.getValue(i, 11).trim().equals("MES BOX")||Table.getValue(i, 11).trim().equals("BOX")))
					continue;
				if (currDt.equals(toDt) || yesDt.equals(toDt)) {

					String[] split = Table.getValue(i, 12).split("<br>");
					for(int k=0;k<split.length;k++)
						newOrd.add(split[k]);
				}
			}

			ArrayList<String> alreadyPrinted = new ArrayList<String>();
			
			int totnum=0;
			String toShow = "New orders : " + newOrd.size() + " <br>( ";
			for (int i = 0; i < newOrd.size(); i++) {
				int exists=-1;
				int num=0;
				for(int j=0;j<alreadyPrinted.size();j++)
				{
					if(newOrd.get(i).equals(alreadyPrinted.get(j)))
					exists++;
				}
				if(exists==-1)
				 {
					totnum++;
					alreadyPrinted.add(newOrd.get(i));
					num++;
					for(int k=i+1;k<newOrd.size();k++)
					{
						if(newOrd.get(k).equals(newOrd.get(i)))
							num++;
					}
					String Order= "";
					if(newOrd.get(i).indexOf("<br>")!=-1)
						Order = newOrd.get(i).substring(newOrd.get(i).indexOf("<br>")+3);
					else
						Order = newOrd.get(i);
					
					toShow += Order + " x " + Integer.toString(num)+", ";
					if(totnum%4==0)
						toShow += "<br>";
					}
				}
			toShow += " )";
			return toShow;
		}
	}


	/**
	 * fills the summary table and returns it
	 * 
	 * @return The summary table
	 */
	public table GetTable() {
		fillTable();
		fillSumTable();
		return sumTable;
	}

	/**
	 * fills table with vaues in database 1 & 2
	 */
	void fillTable() {

		table table2 = database2.values;
		table table1 = database1.values;

		// Table remarks = database1.startExec();//replace this with table of
		// remarks
		int rsize1 = table1.getRows();

		int csize1 = table1.getColumns();

		int rsize2 = table2.getRows();
		int csize2 = table2.getColumns();
		String id = "";
		int j;
		setCombinedTable(new table(rsize2, table1.getColumns()
				+ table2.getColumns()));
		for (int i = 1; i < rsize2; i++) {
			id = table2.getValue(i, 9);
			for (j = 0; j < csize2; j++) {
				Table.setValue(i, j, table2.getValue(i, j));
			}
			for (int i2 = 1; i2 < rsize1; i2++) {
				if (table1.getValue(i2, 0).equals(id)) {

					for (int j2 = 0; j2 < csize1; j2++) {
						Table.setValue(i, j2 + j, table1.getValue(i2, j2));
					}
				}
			}

		}

	}

	/**
	 * Fills the summary table basd on values in table
	 */
	void fillSumTable() {
		int rsize = Table.getRows();
		// int csize = 26;
		sumTable = new table(8, 8);
		for (int ti = 0; ti < 8; ti++)
			for (int tj = 0; tj < 8; tj++) {
				sumTable.setValue(ti, tj, "0");
			}

		readData();
		setFields();

		for (int i = 1; i < rsize; i++) {
			if (Table.getValue(i, 12).trim().contains("FHB")) {
				// if(table.getValue(i, 14)=="TTNPSYS")
				sumTable.setValue(6, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(6, 3)) + 1)));
			}

			else if (Table.getValue(i, 12).trim().contains("FHB MIMIC")) { // if
				// (table.getValue(i,
				// 14)=="TTNMSYS")
				sumTable.setValue(7, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(7, 3)) + 1)));

			}

			else if (Table.getValue(i, 12).trim().contains("M05")
					|| Table.getValue(i, 12).trim().contains("M10"))
				sumTable.setValue(4, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(4, 3)) + 1)));

			else if (Table.getValue(i, 12).trim().contains("M15")
					|| Table.getValue(i, 12).trim().contains("M32")
					|| Table.getValue(i, 12).trim().contains("M49")
					|| Table.getValue(i, 12).trim().contains("M66")
					|| Table.getValue(i, 12).trim().contains("M80"))
				sumTable.setValue(2, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(2, 3)) + 1)));

			else if (Table.getValue(i, 12).trim().contains("H20")
					|| Table.getValue(i, 12).trim().contains("H43")
					|| Table.getValue(i, 12).trim().contains("H66")
					|| Table.getValue(i, 12).trim().contains("H89")
					|| Table.getValue(i, 12).trim().contains("HA1"))
				sumTable.setValue(5, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(5, 3)) + 1)));

			else if (Table.getValue(i, 12).trim().contains("E10"))
				sumTable.setValue(1, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(1, 3)) + 1)));

			else if (Table.getValue(i, 12).trim().contains("E12")
					|| Table.getValue(i, 12).trim().contains("E26")
					|| Table.getValue(i, 12).trim().contains("E40")
					|| Table.getValue(i, 12).trim().contains("E56")
					|| Table.getValue(i, 12).trim().contains("E64"))
				sumTable.setValue(0, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(0, 3)) + 1)));

			else if (Table.getValue(i, 12).trim().contains("002"))
				sumTable.setValue(3, 3, Integer.toString((Integer
						.parseInt(sumTable.getValue(3, 3)) + 1)));

		}
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		int cur_month = date.getMonth();
		for (int i = 1; i < rsize; i++) {
			if (Table.getValue(i, 39) == null)
				continue;
			else if (Table.getValue(i, 39).trim().contains("29")) {

				// sumTable.setValue(i, 2,
				// Integer.toString((Integer.parseInt(sumTable.getValue(i,2))+1)));

				if (Table.getValue(i, 12).trim().contains("FHB")) {
					// if(table.getValue(i, 13)=="TTNPSYS")
					sumTable.setValue(6, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(6, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(6, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(6, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(6, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(6, 7)) + 1)));
						}
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				 if (Table.getValue(i, 12).trim().contains("FHB M")) { // if
					// (table.getValue(i,
					// 13)=="TTNMSYS")
					sumTable.setValue(7, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(7, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(7, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(7, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(7, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(7, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				 if (Table.getValue(i, 12).trim().contains("M05")
						|| Table.getValue(i, 12).trim().contains("M10")) {
					sumTable.setValue(4, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(4, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(4, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(4, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(4, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(4, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}  if (Table.getValue(i, 12).trim().contains("M15")
						|| Table.getValue(i, 12).trim().contains("M32")
						|| Table.getValue(i, 12).trim().contains("M49")
						|| Table.getValue(i, 12).trim().contains("M66")
						|| Table.getValue(i, 12).trim().contains("M80")) {
					sumTable.setValue(2, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(2, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(2, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(2, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(2, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(2, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}  if (Table.getValue(i, 12).trim().contains("H20")
						|| Table.getValue(i, 12).trim().contains("H43")
						|| Table.getValue(i, 12).trim().contains("H66")
						|| Table.getValue(i, 12).trim().contains("H89")
						|| Table.getValue(i, 12).trim().contains("HA1")) {
					sumTable.setValue(5, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(5, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(5, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(5, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(5, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(5, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}  if (Table.getValue(i, 12).trim().contains("E10")) {
					sumTable.setValue(1, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(1, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(1, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(1, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(1, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(1, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}  if (Table.getValue(i, 12).trim().contains("E11")
						|| Table.getValue(i, 12).trim().contains("E26")
						|| Table.getValue(i, 11).trim().contains("E40")
						|| Table.getValue(i, 12).trim().contains("E56")
						|| Table.getValue(i, 12).trim().contains("E64")) {
					sumTable.setValue(0, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(0, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(0, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(0, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(0, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(0, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}  if (Table.getValue(i, 12).trim().contains("002")) {
					sumTable.setValue(3, 4, Integer.toString((Integer
							.parseInt(sumTable.getValue(3, 4)) + 1)));
					try {
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month - 1) {
							sumTable.setValue(3, 6, Integer.toString((Integer
									.parseInt(sumTable.getValue(3, 6)) + 1)));
						}
						if (formatter.parse(Table.getValue(i, 3).trim())
								.getMonth() == cur_month) {
							sumTable.setValue(3, 7, Integer.toString((Integer
									.parseInt(sumTable.getValue(3, 7)) + 1)));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			// for( i=1;i<rsize;i++)
			// {
			sumTable.setValue(
					5,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(5, 3)) - Integer
							.parseInt(sumTable.getValue(5, 4)))));
			sumTable.setValue(
					6,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(6, 3)) - Integer
							.parseInt(sumTable.getValue(6, 4)))));
			sumTable.setValue(
					7,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(7, 3)) - Integer
							.parseInt(sumTable.getValue(7, 4)))));
			sumTable.setValue(
					4,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(4, 3)) - Integer
							.parseInt(sumTable.getValue(4, 4)))));
			sumTable.setValue(
					2,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(2, 3)) - Integer
							.parseInt(sumTable.getValue(2, 4)))));
			sumTable.setValue(
					1,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(1, 3)) - Integer
							.parseInt(sumTable.getValue(1, 4)))));
			sumTable.setValue(
					0,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(0, 3)) - Integer
							.parseInt(sumTable.getValue(0, 4)))));
			sumTable.setValue(
					3,
					5,
					Integer.toString((Integer.parseInt(sumTable.getValue(3, 3)) - Integer
							.parseInt(sumTable.getValue(3, 4)))));

			// }
		}
	}

	/**
	 * Sets the first column of the summary table
	 */
	private void setFields() {
		// TODO Auto-generated method stub

		sumTable.setValue(0, 0, "Z10");
		sumTable.setValue(1, 0, "ZMR");
		sumTable.setValue(2, 0, "ZGryphon");
		sumTable.setValue(3, 0, "ZBX");
		sumTable.setValue(4, 0, "ZGMR");
		sumTable.setValue(5, 0, "ZHelix");
		sumTable.setValue(6, 0, "FHB");
		sumTable.setValue(7, 0, "FHB MIMIC");
		// sumTable.setValue(0, 0, "Z10");

	}

	/**
	 * Sets the Regen and Commit of the Table
	 */
	public void readData() {
		readRegen();
		readCommit();
		for (int i = 0; i < 8; i++) {
			sumTable.setValue(i, 1, regen.get(i));
			sumTable.setValue(i, 2, commit.get(i));
		}

	}

	private void readCommit() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fstream = new FileInputStream("Commit.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			commit = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				commit.add(str);
			}

			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void readRegen() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fstream = new FileInputStream("Regen.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String str;
			regen = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				regen.add(str);
			}

			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Writes the regen and commit values to file
	 * 
	 * @param reg
	 *            The list of Regen values entered
	 * @param com
	 *            The list of Regen values entered
	 */
	public void writeData(ArrayList<String> reg, ArrayList<String> com) {
		regen = reg;
		commit = com;
		writeRegen();
		writeCommit();

	}
	
	
	public String getNewOrderstoday() throws IOException
	{
		String newor="Todays Orders: ";

    	Calendar cal = Calendar.getInstance();
    	

        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");

  
    	String datcurDate1 = newdateFormat.format(cal.getTime());
      	String datcurDateparse =  datcurDate1.replace("/", "~")+".txt";

		
		FileInputStream fstream = new FileInputStream(datcurDateparse);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		while ((str = br.readLine()) != null) {
			str = str.replace("<br>", ",");
			newor += "<br>" + str;
	}
		
		return newor;
	}
	
	
	public String getNewOrdersyest() throws IOException
	{
		String newor="Yesterday's Orders:  ";
	
    	Calendar cal = Calendar.getInstance();
		Calendar prevDt= (Calendar) cal.clone();
        prevDt.add(Calendar.DATE, -1);
        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	String prevD =  newdateFormat.format(prevDt.getTime());
      	String prevDparse =  prevD.replace("/", "~")+".txt";
    
		FileInputStream fstream = new FileInputStream(prevDparse);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		
		while ((str = br.readLine()) != null) {
			str = str.replace("<br>", ",");
			newor += "<br>" + str;
		
		
	}
		return newor;
	}

	
	
	
	public String getNewOrderstodaymes() throws IOException
	{
		String newor="Todays Orders: ";

    	Calendar cal = Calendar.getInstance();
    	

        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");

  
    	String datcurDate1 = newdateFormat.format(cal.getTime());
      	String datcurDateparse =  datcurDate1.replace("/", "~")+"mes.txt";

		
		FileInputStream fstream = new FileInputStream(datcurDateparse);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		while ((str = br.readLine()) != null) {
			str = str.replace("<br>", ",");
			newor += "<br>" + str;
	}
		
		return newor;
	}
	
	
	public String getNewOrdersyestmes() throws IOException
	{
		String newor="Yesterday's Orders:  ";
	
    	Calendar cal = Calendar.getInstance();
		Calendar prevDt= (Calendar) cal.clone();
        prevDt.add(Calendar.DATE, -1);
        DateFormat newdateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	String prevD =  newdateFormat.format(prevDt.getTime());
    	
      	String prevDparse =  prevD.replace("/", "~")+"mes.txt";
    
		FileInputStream fstream = new FileInputStream(prevDparse);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		
		while ((str = br.readLine()) != null) {
			str = str.replace("<br>", ",");
			newor += "<br>" + str;
		
		
	}
		return newor;
	}

	
	
	
	private void writeCommit() {

		FileWriter fstream;
		try {
			fstream = new FileWriter("Commit.txt");

			BufferedWriter out = new BufferedWriter(fstream);
			for (int i = 0; i < commit.size(); i++) {
				String line = commit.get(i);
				line += System.getProperty("line.separator");
				out.write(line);

			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
	
	private void writeRegen() {
		// TODO Auto-generated method stub
		FileWriter fstream;
		try {
			fstream = new FileWriter("Regen.txt");

			BufferedWriter out = new BufferedWriter(fstream);
			for (int i = 0; i < regen.size(); i++) {
				String line = regen.get(i);
				line += System.getProperty("line.separator");
				out.write(line);

			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public table getCombinedTable() {
		fillTable();
		return Table;
	}

	public void setCombinedTable(table table) {
		Table = table;
	}

}
