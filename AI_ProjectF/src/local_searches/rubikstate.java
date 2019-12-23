package local_searches;

public class rubikstate implements Cloneable{
int value;
public int[] rubik;
public rubikstate(int[] rubik,int value){
	this.rubik = rubik;
	this.value = value;
}
public String toString(){
	String res = "\n";
	for(int i = 0; i < this.rubik.length; i++){
		res += " " + rubik[i];
		
		if((i+1)%9==0) res+="\n";
	}
	res += "  value: "+ this.value;
	return res;
}
public Object clone(){ 
	int[] copy=new int[rubik.length];
	for(int i=0;i<rubik.length;i++)
		copy[i]=rubik[i];
	rubikstate tmp = new rubikstate(copy, value);
return tmp;
}
}
