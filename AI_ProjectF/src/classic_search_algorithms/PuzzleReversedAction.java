package classic_search_algorithms;

public class PuzzleReversedAction extends ReversedAction {
	Direction dir;
	public PuzzleReversedAction(Direction dir){
		this.dir = dir;
		cost = 1;
	}
	@Override
	public Action reverse() {
		return new PuzzleAction(Direction.reverse(dir));
	}
}
