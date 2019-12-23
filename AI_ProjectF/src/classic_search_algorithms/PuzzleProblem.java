package classic_search_algorithms;

import java.util.ArrayList;
import java.io.*;

public class PuzzleProblem extends Problem {
	
	static int l = 3;
	
	public PuzzleProblem(){
		initialState = initialState();
		initialState.setPathCost(0);
		int last  = 1;
		int[][] t = new int[l][l];
		for(int i = 0; i < l; i++){
			for(int j = 0; j < l; j++){
				if(i==2 & j==2)
				t[i][j] = 0;
				else{ t[i][j]=last;last++;}
			}
		}
		goalState = new PuzzleState(t);
		
	}
	
	@Override
	public boolean goalTest(State s) {
		//boolean res = true;
		PuzzleState p = (PuzzleState)s;
		int last = 1;
		for(int i = 0; i < l; i++){
			for(int j = 0; j < l; j++){
				if(last==9&p.plane[2][2]==0)
					return true;
					
				if(last != p.plane[i][j]){
					
					
				   return false;
				}
				last++;
			}
		}
		return true;
	}
	
	@Override
	public State initialState() {
		int[][] plane = new int[l][l];
		String[] s = new String[l];
		///*
		try {
			
			File f = new File("Input8Puzzle.txt");
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			String readLine = "";
			
			//System.out.println("Reading file using Buffered Reader");
			int y = 0;
			while ((readLine = b.readLine()) != null) {
				//System.out.println(readLine);
				s = readLine.split(" ");
				for(int j = 0; j < l; j++){
					plane[y][j] = Integer.parseInt(s[j]);
//					System.out.println(s[j]);
				}
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PuzzleState(plane);
	}
	
	@Override
	public int stepCost(State s, Action a) {
		return a.cost;
	}
	
	@Override
	public int getHeuristic(State s) {
		PuzzleState p = (PuzzleState)s;
		int h = 0;
		int  row, column;
		for(int i = 0; i < l; i++){
			for(int j = 0; j < l; j++){
				row = (int) (p.plane[i][j] / (l+0.5));
				column = p.plane[i][j] % l;
				if(p.plane[i][j]!=0){
                if(j==2)
				h += Math.abs(i - row) + Math.abs(j - column+2);
                else
    				h += Math.abs(i - row) + Math.abs(j - (column-1));
				}else{
					if(j==0&&i==0)h +=4;
					else if(j==0&&i==1|| j==1&&i==0)h+=3;
					else if(j==1&&i==2 || j==2&&i==1)h+=1;
					else if(j==2&&i==2)h +=0;
					else h +=2;
				
				}
			}
		}
		return h;
	}
	
	@Override
	public String solutionStatement() {
		String res = "";
		PuzzleAction pact = null;
		int cost = 0;
		res = res + "Best solution found:\n";
		for(int i = rep.actionSeqReverse.size() - 1; i >= 0; i--){
			pact = (PuzzleAction) rep.actionSeqReverse.get(i);
			cost += pact.cost;//here all costs are equal to 1
			if(pact.dir == Direction.UP){
				res = res + "U";
			}else if(pact.dir == Direction.DOWN){
				res = res + "D";
			}else if(pact.dir == Direction.LEFT){
				res = res + "L";
			}else if(pact.dir == Direction.RIGHT){
				res = res + "R";
			}
		}
		res = res + "\n";
		res = res + "Solution cost : " + cost;
		return res;
	}
	
	@Override
	public ArrayList<Action> actions(State s) {
		
		ArrayList<Action> res = new ArrayList<Action>();
		//System.out.println("YO");
		PuzzleAction possibleAction;
		
		for(Direction dir : Direction.values()){
			possibleAction = new PuzzleAction(dir);
			//	System.out.println("checking Action " + dir);
			if(!passLimits((PuzzleState) s, possibleAction)){
				res.add(possibleAction);
				//	System.out.println(dir + " Added successfully");
			}
		}
		return res;
	}
	
	private boolean passLimits(PuzzleState s, PuzzleAction a){
		boolean res = false;
		if(s.x + a.dir.getVertic() >= l || s.x + a.dir.getVertic() < 0
						|| s.y + a.dir.getHoriz() >= l || s.y + a.dir.getHoriz() < 0){
			res = true;
			//	System.out.println("Passed the limits");
		}
		return res;
	}
	private boolean passLimits(PuzzleState s, PuzzleReversedAction r){
		return passLimits(s,new PuzzleAction(r.dir));
	}
	
	@Override
	public State result(State s, Action a) {
		PuzzleState p = new PuzzleState(((PuzzleState)s).plane);
		PuzzleAction pa = (PuzzleAction)a;
		//swap the place of 0 with it's adjacent cell with respect to Action pa
		int tmp = p.plane[p.x + pa.dir.getVertic()][p.y + pa.dir.getHoriz()];
		p.plane[p.x + pa.dir.getVertic()][p.y + pa.dir.getHoriz()] = 0;
		p.plane[p.x][p.y] = tmp;
		p.x = p.x + pa.dir.getVertic();
		p.y = p.y + pa.dir.getHoriz();
		return p;
	}
	
	@Override
	public ArrayList<ReversedAction> reversedActions(State s) {
		ArrayList<ReversedAction> res = new ArrayList<ReversedAction>();
		PuzzleReversedAction possibleReversedAction;
		for(Direction dir : Direction.values()){
			possibleReversedAction = new PuzzleReversedAction(dir);
			if(!passLimits((PuzzleState) s, possibleReversedAction)){
				res.add(possibleReversedAction);
				//	System.out.println(dir + " Added successfully");
			}
		}
		return res;
	}
	
	@Override
	public State reversedResult(State s, ReversedAction r) {
		PuzzleReversedAction t = (PuzzleReversedAction)r;
		return result(s,new PuzzleAction(t.dir));
	}
}
