package chart;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.tomcat.jni.Time;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import DataBase1.TestTable;
import DataBase1.database2;
import DataBase1.table;

/**
 * Used to create a JPEG chart based on values in the summary table
 * @author raviab
 *
 */
public class ChartCreator {
    
	table tbl ;
	ArrayList<Integer> statusp = new ArrayList();
	ArrayList<Integer> statusz = new ArrayList();
	
	/**
	 * populates tbl with values from database2
	 */
	public void createTable()
	{
		 tbl = database2.values;
		
	}
	
	/**
	 * Creates a chart based on the test table
	 * </br> The chart shows the number of orders(which will finish testing) expected at a particular time 
	 */
	public void createTestChart()
	{
		createTestTable();
		table testtbl = TestTable.startExec();
		int rsize = testtbl.getRows();
		int csize = testtbl.getColumns();
		ArrayList<Integer> times = new ArrayList<Integer>();
		for(int i=0;i<36;i++)
		{
			times.add(0);
		}
	 	for(int i=0;i<rsize;i++)
	 	{	String value = testtbl.getValue(i, 2);
	 		value = value.trim().substring(1,value.indexOf(')'));
	 		if(!value.trim().equals("0:0"))
	 			times.add(roundUp(value).getHours(), times.get(roundUp(value).getHours())+1);
	 	}
	 	
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
       for(int i=0;i<36;i++)
    	   categoryDataset.setValue(times.get(i),"", Integer.toString(i));
       
       JFreeChart chart = ChartFactory.createBarChart
               ("Test times ", // Title
                "Time",              // X-Axis label
                "Orders",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartTest(chart);
	 	
	}
	 		
	
	
	
	/**
	 * Writes the chart created to a file
	 * @param chart
	 */
	private void saveChartTest(JFreeChart chart){
        String fileName="testchart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }


	/**
	 * This rounds up the time to the nearest hour
	 * @param value the date to be rounded up
	 * @return the rounded up date
	 */
	@SuppressWarnings("deprecation")
	private Date roundUp(String value) {
		// TODO Auto-generated method stub
		int pos = value.indexOf(':');
		int hours = Integer.parseInt(value.substring(0, pos));
		int mins = Integer.parseInt(value.substring(pos+1));
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	 	Date date = new Date();
	 	date.setHours(date.getHours()+hours);
	 	date.setMinutes(date.getMinutes()+mins);
	 	if (date.getMinutes() > 30)
			date.setHours(date.getHours()+1);
	 	date.setMinutes(0);
	 	
		mins = 0;
	 	return date;
	}

	private void createTestTable() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void createDataChartMES(table dataSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
	 	for(int i=0;i<dataSum.getColumns()-4;i++)
    	   categoryDataset.setValue(Integer.parseInt(dataSum.getValue(11, 2+i)),dataSum.getValue(11, 2+i), dataSum.getValue(0, 2+i));
    	              
       JFreeChart chart = ChartFactory.createBarChart
               ("Load Summary ", // Title
                "Month",              // X-Axis label
                "Loaded",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartdataSummMES(chart);
	 	
	}
	private void saveChartdataSummMES(JFreeChart chart){
        String fileName="dataSummchartMES.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 400, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	
	
	
	public void createDataWeekChartMES(table dataSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
	 	for(int i=0;i<dataSum.getColumns()-4;i++)
    	   categoryDataset.setValue(Integer.parseInt(dataSum.getValue(11, 2+i)),dataSum.getValue(11, 2+i), dataSum.getValue(0, 2+i));
    	              
       JFreeChart chart = ChartFactory.createBarChart
               ("Load Summary ", // Title
                "Week",              // X-Axis label
                "Loaded",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartdataweekSummMES(chart);
	 	
	}
	private void saveChartdataweekSummMES(JFreeChart chart){
        String fileName="dataweekSummchartMES.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	

	
	public void createDataChart(table dataSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
	 	for(int i=0;i<dataSum.getColumns()-4;i++)
    	   categoryDataset.setValue(Integer.parseInt(dataSum.getValue(11, 2+i)),dataSum.getValue(11, 2+i), dataSum.getValue(0, 2+i));
    	              
       JFreeChart chart = ChartFactory.createBarChart
               ("Load Summary ", // Title
                "Month",              // X-Axis label
                "Loaded",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartdataSumm(chart);
	 	
	}
	private void saveChartdataSumm(JFreeChart chart){
        String fileName="dataSummchart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 400, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	
	
	
	public void createDataWeekChart(table dataSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
	 	for(int i=0;i<dataSum.getColumns()-4;i++)
    	   categoryDataset.setValue(Integer.parseInt(dataSum.getValue(11, 2+i)),dataSum.getValue(11, 2+i), dataSum.getValue(0, 2+i));
    	              
       JFreeChart chart = ChartFactory.createBarChart
               ("Load Summary ", // Title
                "Week",              // X-Axis label
                "Loaded",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartdataweekSumm(chart);
	 	
	}
	private void saveChartdataweekSumm(JFreeChart chart){
        String fileName="dataweekSummchart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	
	
	
	public void createSummaryChart(table shippedSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 2)),shippedSum.getValue(11, 2), shippedSum.getValue(0, 2));
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 3)),shippedSum.getValue(11, 3), shippedSum.getValue(0, 3));
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 4)),shippedSum.getValue(11, 4), shippedSum.getValue(0, 4));
           
       JFreeChart chart = ChartFactory.createBarChart
               ("Shipped Summary ", // Title
                "Month",              // X-Axis label
                "Shipped",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartTestshipSumm(chart);
	 	
	}
	private void saveChartTestshipSumm(JFreeChart chart){
        String fileName="ShippedSummchart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 400, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	

	public void createSummaryChartMES(table shippedSum)
	{
	 	DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
	 	 
        //Enrollment in Bachelors level
     
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 2)),shippedSum.getValue(11, 2), shippedSum.getValue(0, 2));
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 3)),shippedSum.getValue(11, 3), shippedSum.getValue(0, 3));
    	   categoryDataset.setValue(Integer.parseInt(shippedSum.getValue(11, 4)),shippedSum.getValue(11, 4), shippedSum.getValue(0, 4));
           
       JFreeChart chart = ChartFactory.createBarChart
               ("Shipped Summary ", // Title
                "Month",              // X-Axis label
                "Shipped",// Y-Axis label
                categoryDataset,         // Dataset
                PlotOrientation.VERTICAL,
                true,                     // Show legend
                true,
                false
               );

  saveChartTestshipSummMES(chart);
	 	
	}
	private void saveChartTestshipSummMES(JFreeChart chart){
        String fileName="ShippedSummchartMES.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 400, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
	/**
	 * Creates a chart based on the summary table. It requires the summary table to be populated.
	 */

	public void createCategoryChart()
    {
 
		createTable();
		//ArrayList<Integer> status = new ArrayList();
		for(int i=0;i<8;i++)
		{	statusp.add(0);
			statusz.add(0);
		}
		for(int i=0;i<tbl.getRows();i++)
		{
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("GEODIS")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(0, statusp.get(0)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("CPC")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(1, statusp.get(1)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("POST TEST")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(2, statusp.get(2)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("TEST")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(3, statusp.get(3)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("PRE-TEST")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(4, statusp.get(4)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("BUILD")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(5, statusp.get(5)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("KIT")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(6, statusp.get(6)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("NOT RELEASE")&&getModel(tbl.getValue(i, 12)).equals("P"))
				statusp.set(7, statusp.get(7)+1);
		
		}
		
		for(int i=0;i<tbl.getRows();i++)
		{
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("GEODIS")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(0, statusz.get(0)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("CPC")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(1, statusz.get(1)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("POST TEST")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(2, statusz.get(2)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("TEST")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(3, statusz.get(3)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("PRE-TEST")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(4, statusz.get(4)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("BUILD")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(5, statusz.get(5)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("KIT")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(6, statusz.get(6)+1);
			if(tbl.getValue(i, 24).toString().trim().toUpperCase().equals("NOT RELEASE")&&getModel(tbl.getValue(i, 12)).equals("Z"))
				statusz.set(7, statusz.get(7)+1);
		
		}
		
		
        DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
 
        //Enrollment in Bachelors level
        categoryDataset.setValue(statusp.get(0), "p", "GEODIS");
        categoryDataset.setValue(statusp.get(1), "p", "CPC");
        categoryDataset.setValue(statusp.get(2), "p", "POST TEST");
        categoryDataset.setValue(statusp.get(3), "p","TEST");
        categoryDataset.setValue(statusp.get(4), "p","PRETEST");
        categoryDataset.setValue(statusp.get(5), "p","BUILD");
        categoryDataset.setValue(statusp.get(6), "p","KIT");
        categoryDataset.setValue(statusp.get(7), "p","NOT RELEASE");
 
        //Enrollment in Masters level
        categoryDataset.setValue(statusz.get(0), "z", "GEODIS");
        categoryDataset.setValue(statusz.get(1), "z", "CPC");
        categoryDataset.setValue(statusz.get(2), "z", "POST TEST");
        categoryDataset.setValue(statusz.get(3), "z","TEST");
        categoryDataset.setValue(statusz.get(4), "z","PRETEST");
        categoryDataset.setValue(statusz.get(5), "z","BUILD");
        categoryDataset.setValue(statusz.get(6), "z","KIT");
        categoryDataset.setValue(statusz.get(7), "z","NOT RELEASE");
  
        //Enrollment in PhD level

 
        JFreeChart chart = ChartFactory.createBarChart
                     ("Shipping Data1 ", // Title
                      "Categories",              // X-Axis label
                      "Number ",// Y-Axis label
                      categoryDataset,         // Dataset
                      PlotOrientation.VERTICAL,
                      true,                     // Show legend
                      true,
                      false
                     );
 
        saveChart(chart);
    }
 
    /**
     * @param value The model category (FHB, zGMR etc)
     * @return the model type ("P" or "Z")
     */
    private String getModel(String value) {
		// TODO Auto-generated method stub
    	if(value.trim().equals("FHB")||value.trim().equals("FHB MIMIC"))
    		return "P";
    	else return "Z";
	//
	}

	/**
	 * saves the category chart to file
	 * @param chart the chart to be saved
	 */
	public void saveChart(JFreeChart chart)
    {
        String fileName="chart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 1400, 600);
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Problem occurred creating chart.");
    }
    }
}
