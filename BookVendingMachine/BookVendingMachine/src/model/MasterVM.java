package model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import db.SqlDB;
import controller.OutofStockState;
import controller.OverStockState;
import model.VendingMachine;
import controller.VendingMachineState;


public class MasterVM {
	
	
	SqlDB sqlDB=new SqlDB();
	 ArrayList<VendingMachine> vmList=sqlDB.getVendingMachinesDetails();
	 
	public void update(int vmID,VendingMachineState vstate){
		if(vstate instanceof OutofStockState){
			System.out.println(" ALERT OUT OF STOCK in Vending Machine id :"+vmID);
		}else if (vstate instanceof OverStockState){
			System.out.println(" ALERT OVER STOCK in Vending Machine id :"+vmID);
		}
		
	}

	
	 public CategoryDataset createDataset(){
		 String category[] = new String[5];
		 
		   final String series1 = "Books added";
	        final String series2 = "Books deleted";
	     

	        // column keys...
	        final String category1 = "VM 12";
	        final String category2 = "VM 13";
	       
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		
		 
		 int i=0;
		 Iterator<VendingMachine> it=vmList.iterator();
			while(it.hasNext()){
				VendingMachine vm=it.next();
			    final int key = vm.getVmID();
			    category[i]="VM "+key;
			    ArrayList<Integer> transactionCount= sqlDB.getBooksAddedForEachMachine(key);
				   dataset.addValue(transactionCount.get(0), series1, category[i]);
			        dataset.addValue(transactionCount.get(1), series2, category[i]);
			        i++;
			    
		 }
		
		
		 
		 return dataset;
	 }
	 public JFreeChart createBarGraph(final CategoryDataset dataset) {
	        
	        // create the chart...
	        final JFreeChart chart = ChartFactory.createBarChart(
	            "Bar Chart ",         // chart title
	            "Vending Machine",               // domain axis label
	            "count",                  // range axis label
	            dataset,                  // data
	            PlotOrientation.VERTICAL, // orientation
	            true,                     // include legend
	            true,                     // tooltips?
	            false                     // URLs?
	        );

	        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

	        // set the background color for the chart...
	        chart.setBackgroundPaint(Color.white);

	        // get a reference to the plot for further customisation...
	        final CategoryPlot plot = chart.getCategoryPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);

	        // set the range axis to display integers only...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        // disable bar outlines...
	        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        
	        // set up gradient paints for series...
	        final GradientPaint gp0 = new GradientPaint(
	            0.0f, 0.0f, Color.blue, 
	            0.0f, 0.0f, Color.lightGray
	        );
	        final GradientPaint gp1 = new GradientPaint(
	            0.0f, 0.0f, Color.green, 
	            0.0f, 0.0f, Color.lightGray
	        );
	     
	        renderer.setSeriesPaint(0, gp0);
	        renderer.setSeriesPaint(1, gp1);
	     

	        final CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	        // OPTIONAL CUSTOMISATION COMPLETED.
	        
	        return chart;
	        
	    }


}
