package classic_search_algorithms;

public abstract class State {
	private State parent;
	private Action cause;//The action caused us to enter this state
	//may be needed
	private State reverseParent;
	private ReversedAction reverseCause;//The action causes this state to real goalState(just in bidirectional)

  public  int pathCost;//g
	public int cost;
	
	public abstract boolean isEqual(State s);
	//public abstract boolean equals(State s);
	
	
	public void setCost(int cost){
		this.cost = cost;
	}
	public int getCost(){
		return cost;
	}
	
	public void setCause(Action a){
		cause = a;
	}
	public void setReverseCause(ReversedAction r){ reverseCause = r;}
	
	public Action getCause(){
		return cause;
	}
  public ReversedAction getReverseCause(){return reverseCause;}
  
	public void setPathCost(int pathCost){
    this.pathCost = pathCost;
  }
  public int getPathCost(){
    return pathCost;
  }
  public void setParent(State s){
    this.parent = s;
  }
  public void setReverseParent(State s){
		this.reverseParent = s;
  }
  public State getParent(){
    	return parent;
    }
  public State getReverseParent(){
		return reverseParent;
  }
  public void refine(State parent, int pathcost, Action cause)
	{
		this.parent = parent;
		this.pathCost = pathcost;
		this.cause = cause;
	}
}
