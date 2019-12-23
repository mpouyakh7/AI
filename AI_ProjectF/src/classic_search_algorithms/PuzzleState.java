package classic_search_algorithms;

public class PuzzleState extends State {
	static int l = 3;
	public int[][] plane = new int[l][l];
	public int x,y;//coordinates of element 0 within the matrix
	public PuzzleState(int[][] plane){
		for(int i = 0; i < l; i++){
			for(int j = 0; j < l; j++){
				this.plane[i][j] = plane[i][j];
				if(plane[i][j] == 0){
					x = i;
					y = j;
				}
			}
		}
	}
	
	@Override
	public boolean isEqual(State s) {
		PuzzleState p = (PuzzleState)s;
		for(int i = 0; i < l; i++){
			for(int j = 0; j < l; j++){
				if(plane[i][j] != p.plane[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		String res = "Plane : \n";
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				res = res + plane[i][j] + " ";
			}
			res = res + "\n";
		}
		return res;
	}
}
