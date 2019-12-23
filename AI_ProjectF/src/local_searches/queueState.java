package local_searches;

public class queueState implements Cloneable{
	public int[] queue;
	public int value;

	public queueState(int[] queue,int value){
		this.queue = queue;
		this.value = value;
	}
	public String toString(){
		String res = "queue :";
		for(int i = 0; i < this.queue.length; i++){
			res += " " + queue[i];
			
			if(i<(queue.length-1)&&(i+1)%3==0) res+=",";
		}
		res += "  value: "+ this.value;
		return res;
	}
	public Object clone(){ 
		int[] copy=new int[queue.length];
		for(int i=0;i<queue.length;i++)
			copy[i]=queue[i];
	   queueState tmp = new queueState(copy, value);
	return tmp;
	}
}
