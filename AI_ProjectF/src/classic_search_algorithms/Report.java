package classic_search_algorithms;
import java.util.*;
import java.io.*;

public class Report {
	public String searchType;
	public int numOfExpandedI;
	public int numOfExpandedG;

	public ArrayList<State> stateSeqReverse;
	public ArrayList<Action> actionSeqReverse;
	public PriorityQueue<State> frontierI,frontierG,exploredI,exploredG;
	public boolean isGraphSearch;
	
	public Report(PriorityQueue<State> frontierI, PriorityQueue<State> exploredI, boolean isGraphSearch
								, ArrayList<Action> actionSeqReverse, ArrayList<State> stateSeqReverse){
		this.frontierI = frontierI;
		this.exploredI = exploredI;
		this.isGraphSearch = isGraphSearch;
		this.actionSeqReverse = actionSeqReverse;
		this.stateSeqReverse = stateSeqReverse;
	}
	
	public Report(PriorityQueue<State> frontierI, PriorityQueue<State> exploredI,
					PriorityQueue<State> frontierG, PriorityQueue<State> exploredG
					, boolean isGraphSearch, ArrayList<Action> actionSeqReverse, ArrayList<State> stateSeqReverse){
		this(frontierI,exploredI,isGraphSearch,actionSeqReverse,stateSeqReverse);
		this.frontierG = frontierG;
		this.exploredG = exploredG;
	}

	
	public void setSearchType(String s){
		searchType = s;
	}
	
	public void setNumOfExpandedNodesI(int numOfExpandedNodesI){
		this.numOfExpandedI = numOfExpandedNodesI;
	}
	
	public void setNumOfExpandedNodesG(int numOfExpandedNodesG){
		this.numOfExpandedG = numOfExpandedNodesG;
	}
	
	@Override
	public String toString(){
		String res = "Report of Search " + searchType + " performed with ";
		res = res + ((isGraphSearch) ? " Graph Search\n" :" Tree Search\n") ;
		if(!(searchType.equals(("Depth Limited DFS")) || searchType.equals("Iterative Deepening DFS") ) ){
			res = res + "# of observed nodes(from initial state) : " + (frontierI.size() + numOfExpandedI) + "\n";
			res = res + "# of expanded nodes(from initial state) : " + numOfExpandedI + "\n";
		}else{
			res = res + "# of observed yet unexpanded nodes(from initial state) : " + numOfExpandedG + "\n";
			res = res + "# of expanded nodes(from initial state) : " + numOfExpandedI + "\n";
		}
		if(searchType.equals("BiDirectional")){
			res = res + "# of observed nodes(from goal state) : " + (frontierG.size() + numOfExpandedG) + "\n";
			res = res + "# of expanded nodes(from goal state) : " + numOfExpandedG + "\n";
		}
		return res;
	}
}
