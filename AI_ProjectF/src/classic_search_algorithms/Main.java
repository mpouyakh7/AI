package classic_search_algorithms;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
    /*	RescuebotProblem rescue = null;
	    int m = 0, n = 0;
	    String[] s = new String[4];
	    int numWalls;
	    int[] walls = new int[1];
	    ///*
	    try {
		
		    File f = new File("InputRescuebot.txt");
				
		    BufferedReader b = new BufferedReader(new FileReader(f));
		
		    String readLine = "";
		
		    //System.out.println("Reading file using Buffered Reader");
				int y = 0;
		    while ((readLine = b.readLine()) != null) {
			    s = readLine.split(" ");
			    if(y == 0){
		      	m = Integer.parseInt(s[0]);
		      	n = Integer.parseInt(s[1]);
		      }else if (y == 1){//# of walls
		        numWalls = Integer.parseInt(s[0]);
		        walls = new int[numWalls * 4];
			    }else{//walls
		         walls [ 4 * (y - 2) ] = Integer.parseInt(s[0]) - 1;
		         walls [ 4 * (y - 2) + 1 ] = Integer.parseInt(s[1]) - 1;
		         walls [ 4 * (y - 2) + 2 ] = Integer.parseInt(s[2]) - 1;
		         walls [ 4 * (y - 2) + 3 ] = Integer.parseInt(s[3]) - 1;
			    }
			    
		      y++;
		    }
		    rescue = new RescuebotProblem(m, n, walls);
		    
		
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	    
	    */
			//*/
	    //BFS
//	    Search bfsSearch = new Search(rescue);
//	    bfsSearch.bfs(rescue,true);
//	    System.out.println(rescue.getReport());
	    
	    ///*
	    //DFS
//	    Search dfsSearch = new Search(rescue);
//	    dfsSearch.dfs(rescue,true);
//	    System.out.println(rescue.getReport());
	    //*/
	    
	    ///*
	    //DLS
//	    Search dlsSearch = new Search(rescue);
//	    dlsSearch.setSearchType("Depth Limited DFS");
//	    dlsSearch.dls(rescue,9);
//	    System.out.println(rescue.getReport());
	    //*/
	    
	    
	    ///*
	    //IDS
//	    Search idsSearch = new Search(rescue);
//	    idsSearch.ids(rescue);
//	    System.out.println(rescue.getReport());
	    //*/
	    
	    ///*
	    //UCS
//	    Search ucsSearch = new Search(rescue);
//	    ucsSearch.ucs(rescue,true);
//	    System.out.println(rescue.getReport());
	    ///*
	    
	    //AStar
//	    Search aStarSearch = new Search(rescue);
//	    aStarSearch.aStar(rescue,true);
//	    System.out.println(rescue.getReport());
	     //*/
	
	    ///*
	    //Bidirectional
//	    Search biDirSearch = new Search(rescue);
//	    biDirSearch.setSearchType("BiDirectional");
//	    biDirSearch.biDir(rescue,true);
//	    System.out.println(rescue.getReport());
	     //*/
	    /*
	    
	    
	    
	    
	    //*/
	    
	    //*/
	   // 2.1
    	/*
    PuzzleProblem puzzle = null;
	    //8-Puzzle
    puzzle = new PuzzleProblem();
	    //BFS
	    Search bfsSearch = new Search(puzzle);
	    bfsSearch.bfs(puzzle,true);
	    System.out.println(puzzle.solutionStatement());
    System.out.println(puzzle.getReport());
	*/
    	//1.1
    	
    	  //BFS
    	/*
    boatproblem boat = null;
    boat = new boatproblem();
    
    Search bfsSearch = new Search(boat);
    bfsSearch.bfs(boat,true);
    System.out.println(boat.solutionStatement());
    System.out.println(boat.getReport());
    	*/
    	
	    ///*
	    //DFS 2.2
    	/*
    	 PuzzleProblem puzzle = null;
 	    //8-Puzzle
        puzzle = new PuzzleProblem();
	    Search dfsSearch = new Search(puzzle);
        dfsSearch.dfs(puzzle,false);
	    System.out.println(puzzle.getReport());
	    
	   */ 
    	//1.3
    	/*
    	    boatproblem boat = null;
    	    boat = new boatproblem();
    	    Search dfsSearch = new Search(boat);
    	    dfsSearch.dfs(boat,true);
    	    System.out.println(boat.solutionStatement());
    	    System.out.println(boat.getReport());
	  */
  //1.2 bidirectional of boat
    	/*
    	 boatproblem boat = null;
 	    boat = new boatproblem();
 	    Search bidirec = new Search(boat);
 	    bidirec.biDir(boat,true);
 	    System.out.println(boat.solutionStatement());
 	    System.out.println(boat.getReport());
 	    */
    	
	    //DLS 2.3
    	/*
    	 PuzzleProblem puzzle = null;
  	    //8-Puzzle
         puzzle = new PuzzleProblem();
       Search dlsSearch = new Search(puzzle);
       dlsSearch.setSearchType("Depth Limited DFS");
       dlsSearch.dls(puzzle,3);
       System.out.println(puzzle.getReport());
//	    RescuebotAction rbact;
//	    ArrayList<Action> sol = dlsSearch.actionSeqReverse;
	   
	*/
	
	    
	  //  IDS 3.1
    	///*
    HorseProblem horse = null;
    horse = new HorseProblem();
    Search idsSearch = new Search(horse);
    idsSearch.setSearchType("Iterative Deepening DFS");
    idsSearch.ids(horse);
    System.out.println(horse.getReport());
	   // */
	
	    //UCS  3.2
    	/*
    	 HorseProblem horse = null;
         horse = new HorseProblem();
         Search ucsSearch = new Search(horse);
         ucsSearch.ucs(horse,true);
         System.out.println(horse.getReport());
	    */
    	
	    
	  // AStar 2.4 
    	/*
    	 PuzzleProblem puzzle = null;
          puzzle = new PuzzleProblem();
          Search aStarSearch = new Search(puzzle);
	    aStarSearch.aStar(puzzle,true);
	    System.out.println(puzzle.getReport());
	     int rr = (int) 0.3;
		    System.out.println(" rr"+ rr);
	*/
	    
	    //Bidirectional
//	    Search biDirSearch = new Search(puzzle);
//	    biDirSearch.biDir(puzzle,true);
//	    System.out.println(puzzle.getReport());
	    
	    //Rubik 2*2
    }
    
}

