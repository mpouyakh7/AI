package classic_search_algorithms;

import java.util.ArrayList;
//import java.io.*;

public class boatproblem extends Problem {
	static int l = 9;
	
	public boatproblem(){

		initialState = initialState();
		initialState.setPathCost(0);

		int[] t = new int[l];
		
			for(int j = 0; j < l; j++)
				t[j] = 0;
			
			goalState = new boatstate(t);

		}

	@Override
	public boolean goalTest(State s) {
		// TODO Auto-generated method stub
		boatstate p = (boatstate)s;
		for(int j = 0; j < l; j++)
			if(p.plane[j]!=0)
		return false;
		
		return true;
	}

	@Override
	public State initialState() {
		// TODO Auto-generated method stub

		int[] plane = new int[l];
		for(int j = 0; j < l;j++){
			   plane[j]=1;

		}

		return new boatstate(plane);
	}

	@Override
	public int stepCost(State s, Action a) {
		// TODO Auto-generated method stub
		return a.cost;
		}

	@Override
	public int getHeuristic(State s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String solutionStatement() {
		String res = "";
		boatstate pact = null;
		int cost = 0;
		res = res + "Best solution found:\n";
		for(int i = rep.stateSeqReverse.size() - 1; i >= 0; i--){
			pact = (boatstate) rep.stateSeqReverse.get(i);
			//cost += pact.cost;//here all costs are equal to 1
			for(int j = 0; j < l-1; j++){
			if(pact.plane[j]==0){
				res = res + j;
			}	
			}
			res = res + "\n";
			
		}
		cost = rep.stateSeqReverse.size();
		if(pact.plane[8]==0)
			res= res + "   boat on the right side";
		else res= res + "  boat on the left side";
		res = res + "\n";
		res = res + "Solution cost : " + cost;
		return res;		
	}

	@Override
	public ArrayList<Action> actions(State s) {
		ArrayList<Action> res = new ArrayList<Action>();
		boatstate pact = null;
		pact = (boatstate)s;
		
		boatAction possibleAction;
		String dir ;
		int b = 0;
		int m =0;
		int jj=0;
		
          if(pact.plane[8]==0)b=0;
           else b=1;

		for(int j = 0; j < l-1; j++){
			if( m<=6)
			if(pact.plane[m]==b&&pact.plane[m+1]==b){  
				
				jj=m+1;
				dir = m+" "+jj;
			  possibleAction = new boatAction(dir);
				//System.out.println("checking Action0 " + possibleAction.ba+ "size:"+res.size());
			
				res.add(possibleAction);
			}
			 m = j+2;
			
			 if(j<=5)
			 if(pact.plane[j]==b&&pact.plane[j+2]==b ){  
					
				 jj=j+2;
				 dir = j+" "+jj;
				  possibleAction = new boatAction(dir);
				  res.add(possibleAction);
				}
			
			 if(j<=3)
			 if(pact.plane[j]==b&&pact.plane[j+4]==b){  
					
				 jj=j+4;
				 dir = j+" "+jj;
				  possibleAction = new boatAction(dir);
				//	System.out.println("checking Action2 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
			
			 if(j<=1)
			 if(pact.plane[j]==b&&pact.plane[j+6]==b){  
					
				 jj= j+6;
				 dir = j+" "+jj;
				  possibleAction = new boatAction(dir);
					//System.out.println("checking Action3 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
			
				if(pact.plane[j]==b){  
				//	dir[0]=j;
					dir = j+" "+0;
				  possibleAction = new boatAction(dir);
					//System.out.println("checking Action4 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
				
			
		}
	
				return res;	
		}
		
	

	@Override
	public State result(State s, Action a) {
		boatstate b= (boatstate)s;
		boatstate p = new boatstate(((boatstate)s).plane);

		boatAction pa = (boatAction)a;
		String[] ac = pa.ba.split(" ");
		int x0,x1;
		x0= Integer.parseInt(ac[0]);
		x1= Integer.parseInt(ac[1]);
		//System.out.println("boatprobelem result actions:: "+pa.ba+ " "+x0+ " "+ x1);
		if(p.plane[8]==1){
		 p.plane[x0]=0;
		 if(x1!= 0) 
			 p.plane[x1]=0;
		 p.plane[8]=0;
		}else{
			 p.plane[x0]=1;
			 if(x1!= 0) 
				 p.plane[x1]=1;
			 p.plane[8]=1;
		}
		//System.out.println("boatprobelem resultend:: "+p.toString());

		return p;		
	}

	@Override
	public ArrayList<ReversedAction> reversedActions(State s) {
		ArrayList<ReversedAction> res = new ArrayList<ReversedAction>();
		boatstate pact = null;
		pact = (boatstate)s;
		//System.out.println("YO");
		boatReversedAction possibleAction;
		String dir ;
			int b = 0;
		int m =0;
		int jj=0;
		//System.out.println("correspond state: " +pact.toString());
          if(pact.plane[8]==0)b=0;
           else b=1;

		for(int j = 0; j < l-1; j++){
			if( m<=6)
			if(pact.plane[m]==b&&pact.plane[m+1]==b){  
				//dir[0]=m;
				//dir[1]=m+1;
				jj=m+1;
				dir = m+" "+jj;
			  possibleAction = new boatReversedAction(dir);
				//System.out.println("checking Action0 " + possibleAction.ba+ "size:"+res.size());
			
				res.add(possibleAction);
			}
			 m = j+2;
			
			 if(j<=5)
			 if(pact.plane[j]==b&&pact.plane[j+2]==b ){  
					//dir[0]=j;
					//dir[1]=j+2;
				 jj=j+2;
				 dir = j+" "+jj;
				  possibleAction = new boatReversedAction(dir);
				//	System.out.println("checking Action " + dir);
					//System.out.println("checking Action1 " + possibleAction.ba+ "size:"+res.size());
					res.add(possibleAction);
				}
			
			 if(j<=3)
			 if(pact.plane[j]==b&&pact.plane[j+4]==b){  
					//dir[0]=j;
					//dir[1]=j+4;
				 jj=j+4;
				 dir = j+" "+jj;
				  possibleAction = new boatReversedAction(dir);
					//System.out.println("checking Action2 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
			
			 if(j<=1)
			 if(pact.plane[j]==b&&pact.plane[j+6]==b){  
					//dir[0]=j;
					//dir[1]=j+6;
				 jj= j+6;
				 dir = j+" "+jj;
				  possibleAction = new boatReversedAction(dir);
				//	System.out.println("checking Action3 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
			
				if(pact.plane[j]==b){  
				//	dir[0]=j;
					dir = j+" "+0;
				  possibleAction = new boatReversedAction(dir);
					//System.out.println("checking Action4 " + possibleAction.ba+ "size:"+res.size());
				
					res.add(possibleAction);
				}
				
			
		}
				//	System.out.println(dir + " Added successfully");
		for(ReversedAction a: res){
			boatReversedAction pa = (boatReversedAction)a;
		//	System.out.println("resssss result actions:: "+pa.ba);
		}
				return res;	
	}

	@Override
	public State reversedResult(State s, ReversedAction r) {
		boatReversedAction t = (boatReversedAction)r;
		return result(s,new boatAction(t.ba));		
	}

}
