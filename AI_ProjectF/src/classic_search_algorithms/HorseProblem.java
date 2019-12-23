package classic_search_algorithms;

import java.util.ArrayList;

public class HorseProblem extends Problem {
	//possible width values : 0-widthLimit - 1
	//possible height values : 0-heightLimit - 1
	State goalstate2;
	public HorseProblem(){
		
		initialState = initialState();
		initialState.pathCost = 0;
		goalState = new HorseState(0,6);
		goalstate2 = new HorseState(4,7);
	}
	
	@Override
	public boolean goalTest(State s) {
		if(goalState.isEqual(s)|| goalstate2.isEqual(s)) {
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public State initialState(){
		return new HorseState(5,0);
	}
	
	@Override
	public int stepCost(State s, Action a) {
		return a.cost;//each action costs exactly 1
	}
	
	@Override
	public int getHeuristic(State s){
		return 0;
	}
	
	@Override
	public String solutionStatement() {
		String res = "";
		HorseAction hr = null;
		int cost = 0;
		res = res + "Best solution found:\n";
		for(int i = rep.actionSeqReverse.size() - 1; i >= 0; i--){
			hr = (HorseAction) rep.actionSeqReverse.get(i);
			cost += hr.cost;//here all costs are equal to 1
			if(hr.dir == Direction.UP){
				res = res + "U";
			}else if(hr.dir == Direction.DOWN){
				res = res + "D";
			}else if(hr.dir == Direction.LEFT){
				res = res + "L";
			}else if(hr.dir == Direction.RIGHT){
				res = res + "R";
			}
		}
		res = res + "\n";
		res = res + "Solution cost : " + cost;
		return res;
	}
	
	@Override
	public State result(State s, Action a) {
		HorseState sr = (HorseState)s;
		HorseAction ar = (HorseAction)a;
		State r = new HorseState( sr.x + ar.dir.getHoriz() ,sr.y + ar.dir.getVertic());
		//System.out.println("old vs new::: "+ s.toString()+ "  "+ r.toString()+ " "+ar.dir);

		return r;
	}
	
	
	@Override
	public State reversedResult(State s, ReversedAction r) {
		return null;
	}
	
	@Override
	public ArrayList<Action> actions(State s) {
		ArrayList<Action> res = new ArrayList<Action>();
		HorseAction possibleAction;
		for(Direction dir : Direction.values()){
			possibleAction = new HorseAction(dir);
			if(notBlocked((HorseState) s, possibleAction) && !passLimits((HorseState) s, possibleAction)){
				res.add(possibleAction);
			}
		}
		return res;
	}
	
	
	@Override
	public ArrayList<ReversedAction> reversedActions(State s) {
		return null;
	}
	
	
	private boolean passLimits(HorseState s, HorseAction a){
		boolean res = false;
		if(s.x + a.dir.getHoriz() > 7 || s.x + a.dir.getHoriz() < 0
						|| s.y + a.dir.getVertic() > 7 || s.y + a.dir.getVertic() < 0){
			res = true;
		//	System.out.println("Passed the limits");
		}
		return res;
	}
	
	private boolean notBlocked(HorseState s, HorseAction a){
		boolean res = true;
		Direction dir = ((HorseAction) a).dir;
		
			if(     (s.x + dir.getHoriz()==1 && s.y+dir.getVertic()==2)||
					(s.x + dir.getHoriz()==5 && s.y+dir.getVertic()==3)||
					(s.x + dir.getHoriz()==3 && s.y+dir.getVertic()==4)||
			        (s.x + dir.getHoriz()==4 && s.y+dir.getVertic()==5)){
				res = false;
		//		System.out.println("blocked by obstacle");
			}
		
		return res;
	}
	
	
}