package classic_search_algorithms;

public enum Direction {
	UP(0,-1),
	DOWN(0,1),
	LEFT(-1,0),
	RIGHT(1,0);
	private int horiz;
	private int vertic;
	Direction(int horiz, int vertic){
		this.horiz = horiz;
		this.vertic = vertic;
	}
	public int getHoriz(){
		return horiz;
	}
	public int getVertic(){
		return vertic;
	}
	public static Direction reverse(Direction d){
		Direction res = null;
		if(d == UP){
			res = DOWN;
		}else if(d == DOWN){
			res = UP;
		}else if(d == RIGHT){
			res = LEFT;
		}else if(d == LEFT){
			res = RIGHT;
		}
		return res;
	}
}
