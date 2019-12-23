package classic_search_algorithms;

public class boatReversedAction extends ReversedAction{
	String ba;
	public boatReversedAction(String dir){
		this.ba = dir;
		cost = 1;
	}
	@Override
	public Action reverse() {
		return new boatAction(ba);
	}

}
