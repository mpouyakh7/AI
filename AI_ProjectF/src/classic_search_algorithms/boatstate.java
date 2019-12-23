package classic_search_algorithms;

public class boatstate extends State {
	static int l = 9;
	public int[] plane = new int[l];
	
	public boatstate(int[] plane){
		
			for(int j = 0; j < l; j++){
				this.plane[j] = plane[j];
			}
		
	}
	
	@Override
	public boolean isEqual(State s) {
		boatstate p = (boatstate)s;
		
			for(int j = 0; j < l; j++){
				if(plane[j] != p.plane[j])
					return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		String res = "crossed people : \n";
		
			for(int j = 0; j < l; j++){
				if(j<l-1){
				if(this.plane[j]==0)
				res = res + j + " ";
				}else {
					if(this.plane[j]==0)res = res + "right" + " ";
					else res = res + "left" + " ";
				}
			}
			res = res + "\n";
		
		return res;
	}
}
