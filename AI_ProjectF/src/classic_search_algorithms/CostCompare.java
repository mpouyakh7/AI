package classic_search_algorithms;
import java.util.*;
public class CostCompare implements Comparator<State> {
	
	@Override
	public int compare(State o1, State o2) {
		if(o1.cost > o2.cost){
			return 1;
		}else if(o1.cost < o2.cost){
			return -1;
		}else{
			return 0;
		}
	}
}
