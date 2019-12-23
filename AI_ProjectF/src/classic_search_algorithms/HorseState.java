package classic_search_algorithms;


public class HorseState extends State {
	public int x;
	public int y;
	
	public HorseState(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public boolean isEqual(State s) {
		boolean res = false;
		if(((HorseState)s).x == x && ((HorseState)s).y == y){
			res = true;
		}
		return res;
	}
	
	@Override
	public String toString(){
		return String.format("State with x  = %d , y = %d\n",x,y);
	}
	
}
