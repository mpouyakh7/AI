package local_searches;

import java.util.*;


import java.io.*;

public class graphcoloring extends geneticProblem{

	public int y=0;
	@Override
	public void initialize() {
		
		try {
			File f = new File("InputGraph.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";
			String[] s;
			//System.out.println("Reading file using Buffered Reader");
			 y = 0;
			while ((readLine = b.readLine()) != null) {
				s = readLine.split(" ");
//					fill the adjacency matrix
					for(int i = 0; i < s.length; i++){
						graph[y][i] = Integer.parseInt(s[i]);
					}
				
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		yal=y;
		Random rnd = new Random();
		for(int i = 0; i < Size; i++)
			for(int k=0; k<chrlength;k++)
			population[i][k] = rnd.nextInt(maxcolor);
		
		
	}
	
	
	
	@Override
	public double fitness(int[] s) {
		double sum = 0;
		
		for(int i = 0; i < graph.length; i++){
			if(s[graph[i][0]]!=s[graph[i][1]])
				sum+=1;
		}
		
				return sum;
	}

	@Override
	public int[] crossover(int[] a, int[] b) {
		Random rnd = new Random();
		int cs =rnd.nextInt(chrlength);
		int c[]= new int[chrlength];
		
		 for(int i=0; i <cs;i++)
			 c[i]=a[i];
			 
		 for( int i=cs; i <chrlength;i++)
			 c[i]=b[i];
		 
		return c;
		}

	@Override
	public int[] mutate(int[] s) {

		Random rnd = new Random();
		int cs =rnd.nextInt(maxcolor);
		int location = rnd.nextInt(chrlength);
		s[location]= cs;
		
		return s;
	}
	
}

