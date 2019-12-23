package local_searches;
import java.lang.reflect.Array;
import java.util.*;

public abstract class SimulatedAnnealing {
	private String coolingSchedule;
	private double coolingRateL = 0.5;
	private double coolingRatex = 0.005;

	private int c = 10,n0 = 10;
	private double coef = 10.0;
	public int expanded = 0, observed = 0;
	private double temp;//Temperature
	public rubikstate bestStateFound;
	public void setCoolingSchedule(String coolingSchedule){
		this.coolingSchedule = coolingSchedule;
	}
	
	public abstract rubikstate initialize();
	public abstract ArrayList<rubikstate> successors(rubikstate s);
	
	
	public void solve(){
	
		temp = 120.0;
		int t = 0;
		temp = schedule(t);
		rubikstate current = initialize();
		expanded++;
		rubikstate next;
		ArrayList<rubikstate> successors = new ArrayList<rubikstate>();
		Random rnd = new Random();
		boolean help = true;
		while(temp > 1){
			temp = schedule(t);
			successors = successors(current);
	//System.out.println("the temp:"+	temp);
			if(help){
				observed += successors.size();
			}
			next = successors.get(rnd.nextInt(successors.size()));

			if(next.value >= current.value){
				current = next;
				expanded++;
			}else if(next.value < current.value){
				if(rnd.nextDouble() < Math.exp(10*(next.value - current.value) / temp)){
					current = next;
					expanded++;
				}else{
					help = false;
					continue;
				}
			}
			t = t + 1;
			help = true;
			bestStateFound = current;
			System.out.println("Iteration " + t + bestStateFound + "\n" + bestStateFound.value);
			System.out.println("Report of iteration " + t + "\n" + report());
			
		}
		bestStateFound = current;
		System.out.println(bestStateFound + "\n" + bestStateFound.value);
//		System.out.println(report());
	}
	
	private double schedule(double t){
		if(coolingSchedule.equals("logarithmic")){
			//temp = (100) / Math.log(t + 100);
			temp = 120/(1+ Math.log10(t + 1));
		}else if(coolingSchedule.equals("linear")){
			temp = temp - coolingRateL;
		}else if(coolingSchedule.equals("exponential")){
			temp = temp * (1 - coolingRatex);
		}
		return temp;
	}
	
	public String report(){
		String res = "";
		res += "# of observed nodes : " + observed + "\n";
		res += "# of expanded nodes : " + expanded + "\n";
		res += "best state found so far : " + bestStateFound + "\n";
		return res;
	}
	
}
