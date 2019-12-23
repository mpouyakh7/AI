package classic_search_algorithms;

import java.util.*;

public class Search {
	int stackHelp = 0;//helps us make Stack and Queue when BFS/DFS(with it's variants) is used
	
	public static ArrayList<State> stateSeqReverse = new ArrayList<State>();
	public static ArrayList<Action> actionSeqReverse = new ArrayList<Action>();
	
	
	private PriorityQueue<State> frontierI = new PriorityQueue<State>(5,new CostCompare());
	private PriorityQueue<State> frontierG = new PriorityQueue<State>(5,new CostCompare());
	private PriorityQueue<State> exploredI = new PriorityQueue<State>(5,new CostCompare());
	private PriorityQueue<State> exploredG = new PriorityQueue<State>(5,new CostCompare());
	
	private Problem p;
	private Report rep;
	private boolean graphSearch;
	private int numOfExpandedI = 0, numOfExpandedG = 0;//numOfExpandedG serves as unexpanded nods in rdls method
	private String searchType;
	int collision=0;
	public Search(Problem p){
		this.p = p;
	}
	
	
	public void solution(State s){
		while(!s.isEqual(p.initialState)){
			//add cause of s to actionSeqReverse and the s itself to stateSeqReverse
			
			actionSeqReverse.add(s.getCause());
			stateSeqReverse.add(s);
//			System.out.println(s);
			s = s.getParent();
		}
		stateSeqReverse.add(s);
		rep = new Report(frontierI,exploredI,frontierG,exploredG,graphSearch,actionSeqReverse,stateSeqReverse);
		rep.setNumOfExpandedNodesG(numOfExpandedG);
		rep.setNumOfExpandedNodesI(numOfExpandedI);
		rep.setSearchType(searchType);
//		System.out.println("$$3" + frontierI.size());
		p.setRep(rep);
	}
	
	/*
	clears all the stuff and starts from scratch
	 */
	public void clear(){
		p = null;
		rep = null;
		graphSearch = false;
		numOfExpandedI = 0;
		numOfExpandedG = 0;
		frontierG = null;
		exploredG = null;
		frontierI = null;
		exploredI = null;
		actionSeqReverse = null;
		stateSeqReverse = null;
	}
	
	public void reversedSolution(State s){//starts from s and reaches the goalState
		//then solution method is called from the goalState. It's applied only to BiDirectional search
		State t;
		State q = s;
		
//		System.out.println("performind reversed solution from " + s);
		
		while(!s.isEqual(p.goalState)){
			collision++;
//			System.out.println("$$2" + frontierI.size());
			t = s.getReverseParent();
//			System.out.println("reverse parent is" + t);
			t.setParent(s);
			t.setCause((s.getReverseCause()).reverse());
			//PuzzleReversedAction rc = (PuzzleReversedAction)s.getReverseCause();
			//PuzzleAction a = (PuzzleAction)t.getCause();
//			System.out.println("reverse cause and cause are " + rc.dir + "  " + a.dir);
			t.setPathCost(s.pathCost + p.stepCost(s,t.getCause()));
			s = s.getReverseParent();
		}
		//now s is equal to a goalState
		System.out.println("collisionnn: "+collision);

		solution(s);
	}
	
	public  void biDir(Problem p, boolean graphSearch) {
		searchType = "BiDirectional";
		this.graphSearch = graphSearch;
		int stackHelpI = 0, stackHelpG = 0;
		State s = p.initialState, g = p.goalState, child,parent;
		//we don't use goalTest here.Instead, we take intersection of two
//		frontiers
		s.setCost(stackHelpI++);
		g.setCost(stackHelpG++);
		frontierI.add(s);
		frontierG.add(g);
		while(true){
			if(frontierI.isEmpty() || frontierG.isEmpty()){
				//we can neither reach Goal from Initial nor vice versa
				break;
			}
			s = frontierI.poll();
//			System.out.println(s + " is polled from frontierI");
			
			if(member(s,frontierG)){//a solution has been found
				State t = get(s,frontierG);//reverseParent and reverseCause for s should be determined
				s.setReverseParent(t.getReverseParent());
				s.setReverseCause(t.getReverseCause());
//				System.out.println("solution has been found from frontierI " + s);
				reversedSolution(s);
				return;
			}
			
			if(graphSearch){
				exploredI.add(s);
			}
			numOfExpandedI++;
			
			for(Action a : p.actions(s)){
				child = p.childNode(s,a);
				child.setCost(stackHelpI++);
				if(graphSearch){
					if(!(member(child,frontierI) || member(child,exploredI))){
						frontierI.add(child);
//						System.out.println(child + " is added to frontierI");
					}
				}else{
					frontierI.add(child);
				}
			}
		
//			now the turn goes for opposite side
			g = frontierG.poll();
//			System.out.println(g + " is polled from frontierG");
			
			if(member(g,frontierI)){//a solution has been found
				State t = get(g,frontierI);//parent and cause for g should be determined
				g.setCause(t.getCause());
				g.setParent(t.getParent());
//				System.out.println("solution has been found from frontierG  " + g);
//				System.out.println("$$1" + frontierI.size());
//				System.out.println(frontierG.size());
				reversedSolution(g);
//				solution(p.goalState);
				return;
			}
			
			if(graphSearch){
				exploredG.add(g);
			}
			numOfExpandedG++;
			for(ReversedAction r : p.reversedActions(g)){
				parent = p.parentNode(g,r);
				parent.setCost(stackHelpG++);
				if(graphSearch){
					if(!(member(parent,frontierG) || member(parent,exploredG))){
						frontierG.add(parent);
//						System.out.println(parent + " is added to frontierG");
					}
				}else{
					frontierG.add(parent);
				}
			}
		}

	}
	
	public void bfs(Problem p, boolean graphSearch) {

	PuzzleState g = (PuzzleState) p.goalState;
		searchType = "BFS";
		this.graphSearch = graphSearch;
		State s = p.initialState;
		State child;
		System.out.println("goal state :"+ g.toString());
		System.out.println("init state :"+ s.toString());

	
		if(p.goalTest(s)){
			//System.out.println("ENTER1");
			solution(s);
			//System.out.println("EXIT1");
			return;
		}
		
//		frontierI = new PriorityQueue<State>(5, new CostCompare());
//		exploredI = new PriorityQueue<State>(5, new CostCompare());
		
		s.setCost(stackHelp++);
		frontierI.add(s);
		while(true){
			if(frontierI.isEmpty()){
				break;
			}
			s = frontierI.poll();
			System.out.println("here "+s.toString());

		//System.out.println(s + " polled from the PriorityQueue");
//			PuzzleState t = (PuzzleState)s;
//			if(t.plane[0][0] == 0 && t.plane[0][1] == 1 && t.plane[0][2] == 2 && t.plane[1][0] == 3 &&
//							t.plane[1][1] == 4 && t.plane[1][2] == 5){
		//System.out.println("SUSPECT polled from Priority Queue: " + s);
//			}
			if(graphSearch){
				exploredI.add(s);
			}
			numOfExpandedI++;
			
			//	if(p.actions(s) != null){
			//		System.out.println(p.actions(s).size());
			//	}
			//for(Action a: p.actions(s)){
			//	boatAction pa = (boatAction)a;
			//	System.out.println("boatprobelem result actions:: "+pa.ba[0]+" "+pa.ba[1]);
			//}
			for(Action a: p.actions(s)){
				//	System.out.println(" making childs:"+);
				child = p.childNode(s,a);
						System.out.println("childdd:: "+child.toString());
				child.setCost(stackHelp++);
				
				if(graphSearch){
					if(!(member(child,frontierI) || member(child,exploredI))){
						if(p.goalTest(child)){
							
							//System.out.println("ENTER2");
							solution(child);
							//System.out.println("EXIT2");
							//System.out.println(child + "from" + child.getParent() + " solved the problem");
							return;
						}
						frontierI.add(child);
					}
				}else{
					frontierI.add(child);
				}
				
			}
		}
		stackHelp = 0;
	}
	

	public void dfs(Problem p, boolean graphSearch) {
		this.graphSearch = graphSearch;
		State s = p.initialState;
		PuzzleState t;
		searchType = "DFS";
		State child;
		if(p.goalTest(s)){
			solution(s);
			return;
		}
		
		s.setCost(stackHelp--);
		frontierI.add(s);
		while(true){
			if(frontierI.isEmpty()){
				break;
			}
			s = frontierI.poll();
			if(graphSearch){
				exploredI.add(s);
			}
			numOfExpandedI++;
			
			for(Action a: p.actions(s)){
				child = p.childNode(s,a);
				child.setCost(stackHelp--);
				if(graphSearch){
					if(!(member(child,frontierI) || member(child,exploredI))){
						if(p.goalTest(child)){
							//System.out.println("ENTER2");
							solution(child);
							//System.out.println("EXIT2");
							//System.out.println(child + "from" + child.getParent() + " solved the problem");
							return;
						}
						frontierI.add(child);
					}
				}else{
					if(!(member(child,frontierI))){
						//& !ancestor(child,s)){
						if(ancestor(child,s)){
					System.out.println("loop occured and there is no solution");
					return;

						}
					if(p.goalTest(child)){
						solution(child);
						return;
					}
					System.out.println("ENTER2:"+child);
					frontierI.add(child);
					}
				}
				
			}
		}
		stackHelp = 0;
	}
	
	/*
	implemented based on Russel
	 */
	public boolean dls(Problem p,int limit){
		searchType = "Depth Limited DFS";
		boolean res = rdls(p.initialState,p,limit);
		if(stateSeqReverse.size() != 0){
			System.out.println("solution found within the limit " + limit);
			return false;
		}else System.out.println("solution has not been found within the limit " + limit);
		return res;
	}
	
	
	/*
	returns true if cutoff has been occurred
	else solution is valued
	 */
	private boolean rdls(State s, Problem p, int limit ){
		boolean cutoffOccurred;
		boolean result;
		State child;
		if(p.goalTest(s)){
		//	System.out.println(s + " is solution");
			solution(s);
			return false;//a solution has been found within the cutoff limit
		}else if(limit == 0){
		//	System.out.println("cutoff occurred at " + s + "\n");
			return true;//cutoff
		}else{
			cutoffOccurred = false;
			//explored.add(s);//before we expand we count it as an explored node(state)
//			System.out.println(s + " is going to be expanded having depth of " + limit + "for the iteration " + numOfExpandedI
//			 + " having " + numOfExpandedG + " unexpanded nodes");
			
			numOfExpandedI++;
			numOfExpandedG--;
			for(Action a :  p.actions(s)){
			//	System.out.println("making childs");
				child = p.childNode(s,a);
				numOfExpandedG++;
			//	System.out.println(child);
				if(!ancestor(child,s)) {
					result = rdls(child, p, limit - 1);
					if (result == true) {//cutoff has been occurred
						cutoffOccurred = true;
					}else {
						if(stateSeqReverse.size() != 0){//solution has been found
							return false;
						}
					}
				}
			}
			if(cutoffOccurred){
				return true;
			}else{
				return false;//if a solution is found, then stateSeqReverse should be nonempty
				//if there is no solution, then stateSeqReverse would be empty
				//in both cases we return false because there is not a cutoff encountered
			}
		}
	}
	
	private boolean ancestor(State a, State b){//checks if a is an ancestor of b
		boolean res = false;
		do{
			if(a.isEqual(b)){
				res = true;
			}
			b = b.getParent();
			
		}while(b != null);
		return res;
	}
	public void ids(Problem p) {
		searchType = "Iterative Deepening DFS";
		int depth = 0;
		while(true){
			if(!dls(p,depth)){//either failed or a solution has been found
				if(stateSeqReverse.size() != 0){//a solution has been found
				System.out.println("A solution has been found within the limit of " + depth);
					return;
				}else{//failure
           				System.out.println("Can't find a solution");
					break;
				}
			}else{
//				System.out.println("Cutoff occurred within the limit of " + depth);
			}
			depth++;
		}
	}
	private int f(State s){
		//Any function for computing a cost of a state should be implemented here
//		return s.pathCost;
		return p.getHeuristic(s) + s.pathCost;
	}

	public void ucs(Problem p,boolean graphSearch){
		searchType = "Uniform Cost Search";
		ucs(p,false,graphSearch);
	}
	
	public  void ucs(Problem p, boolean isAstar, boolean graphSearch) {
		this.graphSearch = graphSearch;
		State s = p.initialState;
		State child;
		if(isAstar) {
			s.setCost(f(s));
		}else{
			s.setCost(s.pathCost);//s.pathCost = 0
		}
		
		frontierI.add(s);
		while(true){

			if(frontierI.isEmpty()){

				break;
			}
			s = frontierI.poll();
			if(p.goalTest(s)){
				solution(s);
				return;
			}
			if(graphSearch){
				exploredI.add(s);

			}
			numOfExpandedI++;
			//System.out.println("numofexpanded:: " + numOfExpandedI+"");
			//System.out.println("costt:: " + s.getCost()+"\n");

			for(Action a : p.actions(s)){
				//System.out.println("ssssssssss:: " +s.toString()+" "+ ((HorseAction)a).dir);

				child = p.childNode(s,a);
				//System.out.println("childdd:: " +child.toString()+" "+ ((HorseAction)a).dir+"\n");

//				child.setCost(child.pathCost);
				if(isAstar) {
					child.setCost(f(child));
				}else{
				//	//child.setCost(child.pathCost);//s.pathCost = 0
					//System.out.println("exploreddd:: " +exploredI.size()+"");

				}
				if(graphSearch){
					//System.out.println("frontierrrrr::: "+ frontierI.size());
					if(!(member(child,frontierI)|| member(child,exploredI))){
						
						frontierI.add(child);
						//System.out.println("insideeeeee::: "+ frontierI.size());
					}else if(member(child,frontierI)){
						State r = get(child,frontierI);//We absolutely know r != null
						if(r.pathCost > child.pathCost){//if child was in frontier with a higher pathCost it should be replaced
							r.refine(child.getParent(),child.pathCost,child.getCause());
						}
					}
				}else{
					frontierI.add(child);
				}
			}
		}
	}
	
	public  void aStar(Problem p,boolean graphSearch){
		searchType = "AStar";
		ucs(p,true,graphSearch);
	}
	
	
	
	/*
	checks whether s exists in c
	 */
	private boolean member(State s, Collection<State> c){

		Iterator it = c.iterator();
		boolean res = false;
		State t = null;
		while(it.hasNext()){
			t = (State) it.next();
			if(s.isEqual(t)){

				res = true;
				//			System.out.println(t + "equals " + s);
				break;
			}
			
		}
//		if(((PriorityQueue<State>)c).equals(frontier)){
		//System.out.println("res = " + res);
//		}
		//if(!res){System.out.println("finished iterating");}
		//System.out.println("sssss va tttt::: "+ s.toString()+ "  "+ t.toString());
		//System.out.println("memberrrr::: "+ res);

		return res;
	}
	
	private State get(State s, Collection<State> c){
		Iterator it = c.iterator();
		State res = null;
		State t;
		while(it.hasNext()){
			t = (State) it.next();
			if(s.isEqual(t)){
				res = t;
				break;
			}
		}
		return res;
	}
	public void setSearchType(String s){
		searchType = s;
	}
}
