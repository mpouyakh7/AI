package local_searches;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public abstract class geneticProblem {
	
	public static int numgen = 50;
	public static int Size = 100;//initial population size
	public double mutationRate = 0.02;
	public double crossoverRate = 0.1;
	public double [] bestFitness =new double [numgen];
	public double [] worstFitness=new double [numgen];
	public double [] avgFitness = new double [numgen];
	public ArrayList<Double> bestFit, worstFit, avgFit;
	private int tornoment=10;
	protected int chrlength=49;
	public int maxcolor=4;
	public int yal=107;
	public int mutatedGenomes = (int) (chrlength * mutationRate * Size);
	private ChartFrame frame = null;
	
	public abstract void initialize();
	public abstract double fitness(int[] s);
	public abstract int[] crossover(int[] a, int[] b);
	public abstract int[] mutate(int[] s);
	//public GenState bestState;
	public int[][] population = new int [Size][chrlength];
	public int[][] newg = new int [Size][chrlength];
	public int[][] selection = new int [tornoment][chrlength];
    public  int[][] selected = new int [2][chrlength];

	public int[][] graph = new int [yal][2];
	
	
	
	public void runGen(){
		int numGen = 0;
		System.out.println("mutattednum:"+mutatedGenomes);

		while(numGen < this.numgen){
			
			
			for(int j=0;j<population.length;j++){
			for(int i=0; i<tornoment;i++){
				Random rnd = new Random();
				int cs =rnd.nextInt(population.length);
				selection[i]=population[cs];
			}//choosing parents
			slc(selection);//choosing best two parents
			newg[j]=crossover(selected[0],selected[1]);
			
			}//end of producing new childs	
			
			for(int i=0; i< mutatedGenomes;i++){//mutation
				Random rnd = new Random();
				int cs =rnd.nextInt(population.length);
				newg[cs]=mutate(newg[cs]);
			}
			//calculating bestfit worstfit and averagefit of i generation
			calculate(population,numGen);
			for(int j=0;j<population.length;j++)
				for(int i=0; i<chrlength;i++)
			        population[j][i]=newg[j][i];
			
			numGen++;
			
		}
		
	}
	public void calculate(int[][]a, int numg){
	    double fit[]=new double [a.length];
	    double sum=0;
	    double min=10000;
	    double max=0;
		for(int j=0;j<a.length;j++)
			fit[j]=fitness(a[j]);
		
		 
		for (int i = 0; i < fit.length; i++) {
	            sum += fit[i];
	            if (min > fit[i]) {
	                min = fit[i];
	            }
	            if (max < fit[i]) {
	                max = fit[i];
	            }
	        }
	        double average = (double) sum / fit.length;
	        bestFitness[numg]=max;
	        worstFitness[numg]=min;
	        avgFitness[numg]=average;
	}
	
	public void slc(int[][]a){
		double fit[]=new double [a.length];
		int twol[]= new int[2];
		
		for(int j=0;j<a.length;j++)
			fit[j]=fitness(a[j]);
		twol=twoLargest(fit);
		if(twol[0]!=twol[1]){
		selected[0]=a[twol[0]];
		selected[1]=a[twol[1]];}
		else{
			fit[twol[0]]=0;
			twol=twoLargest(fit);
			selected[0]=a[twol[0]];
			selected[1]=a[twol[1]];
		}
		
	}
	public static int[] twoLargest(double values[]){

		 double largestA = Integer.MIN_VALUE;
		double largestB = Integer.MIN_VALUE;

		    for(double value : values) {
		        if(value > largestA) {
		            largestB = largestA;
		            largestA = value;
		        } else if (value > largestB) {
		            largestB = value;
		        }
		    }
	    int[] tmp = new int[2];
		for(int i = 0; i < values.length; i++){
	    	if(largestA==values[i])
	    		tmp[0]=i;
	    	if(largestB==values[i])
	    		tmp[1]=i;
	    }
	    
	    return tmp ; 
	}
	
	public void report(){
		for(int i=0; i<numgen;i++){
		System.out.println("Reporting the generation"+(i+1)+ " : \n");
		System.out.println("\nBest Fitness : " + bestFitness[i]);
		System.out.println("\nWorst Fitness : " + worstFitness[i]);
	    System.out.println("\nAverage Fitness : " + avgFitness[i]);
	    }
	}
	  void displayChart() {

	        if (this.frame == null) {

	            final String title = "Num of generation: "+numgen+" "+";  Population: "+Size+ ";  MutationRate:"+mutationRate+ ";  TornumentSize: "+ tornoment;
	            final String xAxisLabel = "Generations";
	            final String yAxisLabel = "Amplitude";
	            final XYSeriesCollection data = new XYSeriesCollection();
	            data.addSeries(createSeriesOne());
	            data.addSeries(createSeriesTwo());
	            data.addSeries(createSeriesThree());

	            final JFreeChart chart = ChartFactory.createXYStepChart(
	                    title,
	                    xAxisLabel, yAxisLabel,
	                    data,
	                    PlotOrientation.VERTICAL,
	                    true,
	                    true,
	                    false
	            );

	            chart.setBackgroundPaint(new Color(216, 216, 216));
	            final XYPlot plot = chart.getXYPlot();
	            plot.setDomainAxis(new NumberAxis(xAxisLabel));
	            plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));
	            plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.0f));

	            this.frame = new ChartFrame("Plan Comparison", chart);
	            this.frame.pack();
	            RefineryUtilities.positionFrameRandomly(this.frame);
	            this.frame.setVisible(true);

	        } else {
	            this.frame.setVisible(true);
	            this.frame.requestFocus();
	        }

	    }

	    public XYSeries createSeriesOne() {
	        final XYSeries series1 = new XYSeries("Worst");
	        for(int i= 0;i<numgen;i++)
		        series1.add(i, worstFitness[i]);
	        return series1;
	    }

	    public XYSeries createSeriesTwo() {
	        final XYSeries series2 = new XYSeries("Best");
	        for(int i= 0;i<numgen;i++)
	        series2.add(i, bestFitness[i]);
	        
	        return  series2;
	    }
	    public XYSeries createSeriesThree() {
	        final XYSeries series2 = new XYSeries("Average");
	        for(int i= 0;i<numgen;i++)
	        series2.add(i, avgFitness[i]);
	        
	        return  series2;
	    }
}
