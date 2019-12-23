package local_searches;
import java.util.*;

public  class queue extends HillClimbing{
	private int k=2;//number of queue
	
	@Override
	public queueState initialize() {
		
		queueState res = null;
		Random rnd = new Random();
		int[] qu = new int[3*k];
		
		//for(int i = 0; i < 3*k; i++)
		//	qu[i] = rnd.nextInt(10);
		
		qu[0]=2;
		qu[1]=8;
		qu[2]=6;
		qu[3]=5;
		qu[4]=2;
		qu[5]=0;
		
		res=new queueState(qu,setValue(qu));
		
		return res;
	}
	
	@Override
	public int setValue(int[] s){
		
		int[] tmp = new int[k];
		int max=0;
		
		for(int i = 0; i < 3*k; i++)
				tmp[i/3]+= s[i];
		
		for (int i = 0; i < tmp.length; i++) 
            if (max < tmp[i]) 
                max = tmp[i];
            
		return  max;
	}
	
	@Override
	public ArrayList<queueState> successors(queueState s) {
		ArrayList<queueState> res = new ArrayList<queueState>();
	queueState pouya=s;
		int t=0;
		
		for(int i = 0; i < 3*k; i++)
			for(int j = i+1; j < 3*k; j++){
				
			queueState tmp =  (queueState) pouya.clone();
		
			t= tmp.queue[i];
			tmp.queue[i] = tmp.queue[j];
			tmp.queue[j] = t;
			tmp.value=setValue(tmp.queue);
			res.add(tmp);
			}
		
		return res;
	}
	
	
	
}
