package local_searches;

import java.util.*;

public abstract class HillClimbing {
	private String strategy;
	public queueState current;
	private int resnum = 10;
	 
	public void setStrategy(String strategy){
		this.strategy = strategy;
	}
	private int observed = 0, expanded = 0;
	public abstract queueState initialize();
	public abstract int setValue(int[] s);
	public abstract ArrayList<queueState> successors(queueState s);
	public void solve(){
		current = initialize();
		int rand;
		int counter=0;
		queueState neighbor;
		queueState min = new queueState(null,1000);
		queueState mnrestart = new queueState(null,500);
		ArrayList<queueState> successors = new ArrayList<queueState>();
		Random rnd = new Random();
		
		while(true){
			successors = successors(current);
			expanded++;
			System.out.println("Current Node "  + current.toString() );
			//System.out.println("minimum dar ejra"+mnrestart.value);

			
			if(strategy.equals("normal")){
				for(int i = 0; i < successors.size(); i++){
					observed++;
					neighbor = successors.get(i);
	                    System.out.println("neghbor Node: " + neighbor+"   neighbor value: " + neighbor.value);
	                   

					if(neighbor.value < min.value)
						min = neighbor;
					
					// System.out.println("min " + min+"   neighbor value: " + min.value);
	                 //   System.out.println("current " + current+"   neighbor value: " + current.value);
					
				}
//				we have the minimum
				if(min.value < current.value){
					current = min;
				}else{
					System.out.println(report());
					return;
				}
			}else if(strategy.equals("stochastic")){
//				just keep the moves that causes us ascend. Other moves are removed
				for(int i = successors.size() - 1; i >= 0; i--){
					if(successors.get(i).value >= current.value){
					System.out.println(successors.get(i) + " gonna be removed! " + successors.get(i).value+ "size::;"+successors.size());
						successors.remove(i);
					}
					observed++;
				}
				if(successors.size() == 0){// no better move
					System.out.println(report());
					return;
				}else{
//					choose a random better move
					neighbor = successors.get(rnd.nextInt(successors.size()));
					current = neighbor;
				}
				
			}else if(strategy.equals("first-choice")){
				rand = -1;
				while(successors.size() > 0){
					if(rand >= 0){
						successors.remove(rand);
						if(successors.size() == 0){break;}
					}
					rand = rnd.nextInt(successors.size());
					neighbor = successors.get(rand);
					observed++;
					if(neighbor.value < current.value){
						current = neighbor;
						break;
					}
				}
				if(successors.size() == 0){
					System.out.println(report());
					return;
				}
			}else if(strategy.equals("random-restart")){

				for(int i = 0; i < successors.size(); i++){
					observed++;
					neighbor = successors.get(i);
	                    System.out.println("neghbor Node: " + neighbor+"   neighbor value: " + neighbor.value);

					if(neighbor.value < min.value)
						min = neighbor;
					
				}
//				we have the minimum
				if(min.value < current.value){
					current = min;
				}else{
					if(counter==resnum){
					
						
					    System.out.println("Best of all rounds of restarts:"+mnrestart.toString());
						System.out.println("# of observed nodes : " + observed + "\n");
						System.out.println("# of expanded nodes : " + expanded + "\n");
						return;
					}else{
						
						counter++;
		                System.out.println(" round:"+counter+" "+current.toString());

						if(current.value < mnrestart.value){
							//System.out.println("\ncompared:  "+"current:"+ current.value+" min: " +mnrestart.value);

							mnrestart=  (queueState) current.clone();
							}
						System.out.println("minimum round:"+counter+" "+mnrestart.toString());

						shuffleArray(current.queue);
						current.value=setValue(current.queue);
						min = new queueState(null,1000);
							
					}
				}
				
			}
		}
	}
	public String report(){
		String res = "";
		res += "# of observed nodes : " + observed + "\n";
		res += "# of expanded nodes : " + expanded + "\n";
		res += "best state found so far : " + current + " ";
		return res;
	}
	public  void shuffleArray(int[] array)
	{
	    int index, temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	}
	
}
