package classic_search_algorithms;

public class PuzzleAction extends Action {
	Direction dir;
	public PuzzleAction(Direction dir){
		this.dir = dir;
		cost = 1;
	}
}
