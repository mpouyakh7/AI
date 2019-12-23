package classic_search_algorithms;
import java.util.*;

public abstract class Problem {
	public State initialState;
	public State goalState;
	protected Report rep;
	
	public abstract boolean goalTest(State s);
	//public abstract void solution(State s);
	public abstract State initialState();
	public abstract int stepCost(State s, Action a);
	public abstract int getHeuristic(State s);
	
	public  State childNode(State s, Action a)//makes node based on result
	{
		State t = result(s,a);
		t.setParent(s);
		int pathCost = stepCost(s,a) + s.getPathCost();
		t.setPathCost(pathCost);
		
		t.setCause(a);
		return t;
	}
	public State parentNode(State s, ReversedAction r){
		State t = reversedResult(s,r);
		t.setReverseParent(s);
		t.setReverseCause(r);
		return t;
	}
	
	public void setRep(Report rep){
		this.rep = rep;
	}
	public String getReport(){
		return rep.toString() + solutionStatement();
	}
	public abstract String solutionStatement();
	public abstract ArrayList<Action> actions(State s);//actionlist
  public abstract State result(State s, Action a);
  
	public abstract ArrayList<ReversedAction> reversedActions(State s);
	public abstract State reversedResult(State s, ReversedAction r);
	
	//public abstract ReversedAction reverse(Action a);
	//public abstract Action reverse(ReversedAction r);
}
