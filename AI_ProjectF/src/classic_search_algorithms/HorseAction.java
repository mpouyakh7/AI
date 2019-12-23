package classic_search_algorithms;

public class HorseAction extends Action {
	Direction dir;
	public HorseAction(Direction dir){
		this.dir = dir;
		cost = 1;
	}
}