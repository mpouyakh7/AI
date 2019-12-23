package local_searches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class rubik extends SimulatedAnnealing {

	@Override
	public rubikstate initialize() {
		int[] rb = new int[54];
		String[] s = new String[9];
		rubikstate init;
		///*
		try {
			
			File f = new File("rubik.txt");
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			String readLine = "";
			
			//System.out.println("Reading file using Buffered Reader");
			int y = 0;
			while ((readLine = b.readLine()) != null) {
				//System.out.println(readLine);
				s = readLine.split(" ");
				for(int j = 0; j < 9; j++){
					rb[y+j] = Integer.parseInt(s[j]);
//					System.out.println(s[j]);
				}
				y=y+9;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		init =new rubikstate(rb,0);
		setValue(init);
		return init;
	}

	@Override
	public ArrayList<rubikstate> successors(rubikstate s) {
		ArrayList<rubikstate> res = new ArrayList<rubikstate>();

		rubikstate tmp = (rubikstate) s.clone();
		res.add(front(tmp));
		
		rubikstate tmp1 = (rubikstate) s.clone();
		res.add(frontc(tmp1));
		rubikstate tmp2 = (rubikstate) s.clone();
		res.add(back(tmp2));
		rubikstate tmp3 = (rubikstate) s.clone();
		res.add(backc(tmp3));
		rubikstate tmp4 = (rubikstate) s.clone();
		res.add(right(tmp4));
		rubikstate tmp5 = (rubikstate) s.clone();
		res.add(rightc(tmp5));
		rubikstate tmp6 = (rubikstate) s.clone();
		res.add(left(tmp6));
		rubikstate tmp7 = (rubikstate) s.clone();
		res.add(leftc(tmp7));
		rubikstate tmp8 = (rubikstate) s.clone();
		res.add(down(tmp8));
		rubikstate tmp9 = (rubikstate) s.clone();
		res.add(downc(tmp9));
		rubikstate tmp10 = (rubikstate) s.clone();
		res.add(top(tmp10));
		rubikstate tmp11 = (rubikstate) s.clone();
		res.add(topc(tmp11));
		
		// TODO Auto-generated method stub
		return res;
	}
public void setValue(rubikstate s){
		
		int y=0;
		int sum=0;
		for(int j=0 ; j<6;j++){
		for(int i = 0; i < 9; i++){
			if(s.rubik[i+y]==s.rubik[4+y]&&((i+y)!=(4+y)))
				sum+=1;
		}
		y=y+9;
		}
		s.value=sum;
		  
	}
	public rubikstate front (rubikstate s){
		rubikstate tm = (rubikstate) s.clone();
		s.rubik[10-1]=tm.rubik[16-1];s.rubik[11-1]=tm.rubik[13-1];
		s.rubik[12-1]=tm.rubik[10-1];s.rubik[15-1]=tm.rubik[11-1];
		s.rubik[18-1]=tm.rubik[12-1];s.rubik[17-1]=tm.rubik[15-1];
		s.rubik[16-1]=tm.rubik[18-1];s.rubik[7-1]= tm.rubik[45-1];
		s.rubik[8-1]=tm.rubik[42-1];s.rubik[13-1]=tm.rubik[17-1];
		s.rubik[9-1]=tm.rubik[39-1];s.rubik[46-1]=tm.rubik[7-1];
		s.rubik[49-1]=tm.rubik[8-1];s.rubik[52-1]=tm.rubik[9-1];
		s.rubik[21-1]=tm.rubik[46-1];s.rubik[20-1]=tm.rubik[49-1];
		s.rubik[19-1]=tm.rubik[52-1];s.rubik[39-1]=tm.rubik[19-1];
		s.rubik[42-1]=tm.rubik[20-1];s.rubik[45-1]=tm.rubik[21-1];
		setValue(s);
		return s;
		
	}
public rubikstate frontc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[10-1]=tm.rubik[12-1]; s.rubik[11-1]=tm.rubik[15-1];
	s.rubik[12-1]=tm.rubik[18-1]; s.rubik[15-1]=tm.rubik[17-1];
	s.rubik[18-1]=tm.rubik[16-1]; s.rubik[17-1]=tm.rubik[13-1];
	s.rubik[16-1]=tm.rubik[10-1]; s.rubik[13-1]=tm.rubik[11-1];
	s.rubik[7-1]= tm.rubik[46-1]; s.rubik[8-1]=tm.rubik[49-1];
	s.rubik[9-1]=tm.rubik[52-1]; s.rubik[46-1]=tm.rubik[21-1];
	s.rubik[49-1]=tm.rubik[20-1]; s.rubik[52-1]=tm.rubik[19-1];
	s.rubik[21-1]=tm.rubik[45-1]; s.rubik[20-1]=tm.rubik[42-1];
	s.rubik[19-1]=tm.rubik[39-1]; s.rubik[39-1]=tm.rubik[9-1];
	s.rubik[42-1]=tm.rubik[8-1]; s.rubik[45-1]=tm.rubik[7-1];
	setValue(s);
		return s;
		
	}
public rubikstate back (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[28-1]=tm.rubik[34-1]; s.rubik[29-1]=tm.rubik[31-1];
	s.rubik[30-1]=tm.rubik[28-1]; s.rubik[31-1]=tm.rubik[35-1];
	s.rubik[33-1]=tm.rubik[29-1]; s.rubik[34-1]=tm.rubik[36-1];
	s.rubik[35-1]=tm.rubik[33-1]; s.rubik[36-1]=tm.rubik[30-1];
	s.rubik[1-1]= tm.rubik[48-1]; s.rubik[2-1]=tm.rubik[51-1];
	s.rubik[3-1]=tm.rubik[54-1]; s.rubik[37-1]=tm.rubik[3-1];
	s.rubik[40-1]=tm.rubik[2-1]; s.rubik[43-1]=tm.rubik[1-1];
	s.rubik[48-1]=tm.rubik[27-1]; s.rubik[51-1]=tm.rubik[26-1];
	s.rubik[54-1]=tm.rubik[25-1]; s.rubik[25-1]=tm.rubik[37-1];
	s.rubik[26-1]=tm.rubik[40-1]; s.rubik[27-1]=tm.rubik[43-1];
	setValue(s);
	return s;
	
}
public rubikstate backc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[28-1]=tm.rubik[30-1]; s.rubik[29-1]=tm.rubik[33-1];
	s.rubik[30-1]=tm.rubik[36-1]; s.rubik[31-1]=tm.rubik[29-1];
	s.rubik[33-1]=tm.rubik[35-1]; s.rubik[34-1]=tm.rubik[28-1];
	s.rubik[35-1]=tm.rubik[31-1]; s.rubik[36-1]=tm.rubik[34-1];
	s.rubik[1-1]= tm.rubik[43-1]; s.rubik[2-1]=tm.rubik[40-1];
	s.rubik[3-1]=tm.rubik[37-1]; s.rubik[37-1]=tm.rubik[25-1];
	s.rubik[40-1]=tm.rubik[26-1]; s.rubik[43-1]=tm.rubik[27-1];
	s.rubik[48-1]=tm.rubik[1-1]; s.rubik[51-1]=tm.rubik[2-1];
	s.rubik[54-1]=tm.rubik[3-1]; s.rubik[25-1]=tm.rubik[54-1];
	s.rubik[26-1]=tm.rubik[51-1]; s.rubik[27-1]=tm.rubik[48-1];
	setValue(s);
	return s;
	
}
public rubikstate right (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[46-1]=tm.rubik[52-1]; s.rubik[47-1]=tm.rubik[49-1];
	s.rubik[48-1]=tm.rubik[46-1]; s.rubik[49-1]=tm.rubik[53-1];
	s.rubik[51-1]=tm.rubik[47-1]; s.rubik[52-1]=tm.rubik[54-1];
	s.rubik[53-1]=tm.rubik[51-1]; s.rubik[54-1]=tm.rubik[48-1];
	s.rubik[12-1]= tm.rubik[21-1]; s.rubik[15-1]=tm.rubik[24-1];
	s.rubik[18-1]=tm.rubik[27-1]; s.rubik[3-1]=tm.rubik[12-1];
	s.rubik[6-1]=tm.rubik[15-1]; s.rubik[9-1]=tm.rubik[18-1];
	s.rubik[21-1]=tm.rubik[30-1]; s.rubik[24-1]=tm.rubik[33-1];
	s.rubik[27-1]=tm.rubik[36-1]; s.rubik[30-1]=tm.rubik[3-1];
	s.rubik[33-1]=tm.rubik[6-1]; s.rubik[36-1]=tm.rubik[9-1];
	setValue(s);
	return s;
	
}
public rubikstate rightc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[46-1]=tm.rubik[48-1]; s.rubik[47-1]=tm.rubik[51-1];
	s.rubik[48-1]=tm.rubik[54-1]; s.rubik[49-1]=tm.rubik[47-1];
	s.rubik[51-1]=tm.rubik[53-1]; s.rubik[52-1]=tm.rubik[46-1];
	s.rubik[53-1]=tm.rubik[49-1]; s.rubik[54-1]=tm.rubik[52-1];
	s.rubik[12-1]= tm.rubik[3-1]; s.rubik[15-1]=tm.rubik[6-1];
	s.rubik[18-1]=tm.rubik[9-1]; s.rubik[3-1]=tm.rubik[30-1];
	s.rubik[6-1]=tm.rubik[33-1]; s.rubik[9-1]=tm.rubik[36-1];
	s.rubik[21-1]=tm.rubik[12-1]; s.rubik[24-1]=tm.rubik[15-1];
	s.rubik[27-1]=tm.rubik[18-1]; s.rubik[30-1]=tm.rubik[21-1];
	s.rubik[33-1]=tm.rubik[24-1]; s.rubik[36-1]=tm.rubik[27-1];
	setValue(s);
	return s;
	
}
public rubikstate left (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[37-1]=tm.rubik[43-1]; s.rubik[38-1]=tm.rubik[40-1];
	s.rubik[39-1]=tm.rubik[37-1]; s.rubik[40-1]=tm.rubik[44-1];
	s.rubik[42-1]=tm.rubik[38-1]; s.rubik[43-1]=tm.rubik[45-1];
	s.rubik[44-1]=tm.rubik[42-1]; s.rubik[45-1]=tm.rubik[39-1];
	s.rubik[1-1]= tm.rubik[28-1]; s.rubik[4-1]=tm.rubik[31-1];
	s.rubik[7-1]=tm.rubik[34-1]; s.rubik[10-1]=tm.rubik[1-1];
	s.rubik[13-1]=tm.rubik[4-1]; s.rubik[16-1]=tm.rubik[7-1];
	s.rubik[19-1]=tm.rubik[10-1]; s.rubik[22-1]=tm.rubik[13-1];
	s.rubik[25-1]=tm.rubik[16-1]; s.rubik[28-1]=tm.rubik[19-1];
	s.rubik[31-1]=tm.rubik[22-1]; s.rubik[34-1]=tm.rubik[25-1];
	setValue(s);
	return s;
	
}
public rubikstate leftc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[37-1]=tm.rubik[39-1]; s.rubik[38-1]=tm.rubik[42-1];
	s.rubik[39-1]=tm.rubik[45-1]; s.rubik[40-1]=tm.rubik[38-1];
	s.rubik[42-1]=tm.rubik[44-1]; s.rubik[43-1]=tm.rubik[37-1];
	s.rubik[44-1]=tm.rubik[40-1]; s.rubik[45-1]=tm.rubik[43-1];
	s.rubik[1-1]= tm.rubik[10-1]; s.rubik[4-1]= tm.rubik[13-1];
	s.rubik[7-1]= tm.rubik[16-1]; s.rubik[10-1]=tm.rubik[19-1];
	s.rubik[13-1]=tm.rubik[22-1]; s.rubik[16-1]=tm.rubik[25-1];
	s.rubik[19-1]=tm.rubik[28-1]; s.rubik[22-1]=tm.rubik[31-1];
	s.rubik[25-1]=tm.rubik[34-1]; s.rubik[28-1]=tm.rubik[1-1];
	s.rubik[31-1]=tm.rubik[4-1]; s.rubik[34-1]= tm.rubik[7-1];
	setValue(s);
	return s;
	
}
public rubikstate top (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[1-1]= tm.rubik[7-1]; s.rubik[2-1]=tm.rubik[4-1]; 
	s.rubik[3-1]=tm.rubik[1-1];  s.rubik[4-1]=tm.rubik[8-1];
	s.rubik[6-1]= tm.rubik[2-1]; s.rubik[7-1]= tm.rubik[9-1];
	s.rubik[8-1]=tm.rubik[6-1];  s.rubik[9-1]=tm.rubik[3-1];
	s.rubik[37-1]=tm.rubik[10-1]; s.rubik[38-1]=tm.rubik[11-1];
    s.rubik[10-1]=tm.rubik[46-1]; s.rubik[11-1]=tm.rubik[47-1];
    s.rubik[12-1]=tm.rubik[48-1]; s.rubik[39-1]=tm.rubik[12-1] ; 
    s.rubik[46-1]=tm.rubik[36-1]; s.rubik[47-1]=tm.rubik[35];
    s.rubik[48-1] =tm.rubik[34-1];s.rubik[36-1] = tm.rubik[37-1];
    s.rubik[34-1]=tm.rubik[39-1]; s.rubik[35-1]=tm.rubik[38-1]; 
	setValue(s);
	return s;
	
}
public rubikstate topc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[1-1]= tm.rubik[3-1]; s.rubik[2-1]=tm.rubik[6-1]; 
	s.rubik[3-1]=tm.rubik[9-1];  s.rubik[4-1]=tm.rubik[2-1];
	s.rubik[6-1]= tm.rubik[8-1]; s.rubik[7-1]= tm.rubik[1-1];
	s.rubik[8-1]=tm.rubik[4-1];  s.rubik[9-1]=tm.rubik[7-1];
	s.rubik[37-1]=tm.rubik[36-1]; s.rubik[38-1]=tm.rubik[35-1];
    s.rubik[10-1]=tm.rubik[37-1]; s.rubik[11-1]=tm.rubik[38-1];
    s.rubik[12-1]=tm.rubik[39-1]; s.rubik[39-1]=tm.rubik[34-1] ; 
    s.rubik[46-1]=tm.rubik[10-1]; s.rubik[47-1]=tm.rubik[11-1];
    s.rubik[48-1] =tm.rubik[12-1];s.rubik[36-1] = tm.rubik[46-1];
    s.rubik[34-1]=tm.rubik[48-1]; s.rubik[35-1]=tm.rubik[47-1]; 
	setValue(s);
	return s;
	
}
public rubikstate down (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[19-1]= tm.rubik[25-1]; s.rubik[20-1]=tm.rubik[22-1]; 
	s.rubik[21-1]=tm.rubik[19-1];  s.rubik[22-1]=tm.rubik[26-1];
	s.rubik[43-1]= tm.rubik[30-1]; s.rubik[44-1]= tm.rubik[29-1];
	s.rubik[45-1]=tm.rubik[28-1];  s.rubik[16-1]=tm.rubik[43-1];
	s.rubik[17-1]=tm.rubik[44-1]; s.rubik[18-1]=tm.rubik[45-1];
    s.rubik[52-1]=tm.rubik[16-1]; s.rubik[53-1]=tm.rubik[17-1];
    s.rubik[54-1]=tm.rubik[18-1]; s.rubik[28-1]=tm.rubik[54-1] ; 
    s.rubik[29-1]=tm.rubik[53-1]; s.rubik[30-1]=tm.rubik[52-1];
    s.rubik[24-1] =tm.rubik[20-1];s.rubik[25-1] = tm.rubik[27-1];
    s.rubik[26-1]=tm.rubik[24-1]; s.rubik[27-1]=tm.rubik[21-1]; 
	setValue(s);
	return s;
	
}
public rubikstate downc (rubikstate s){
	rubikstate tm = (rubikstate) s.clone();
	s.rubik[19-1]= tm.rubik[21-1]; s.rubik[20-1]=tm.rubik[24-1]; 
	s.rubik[21-1]=tm.rubik[27-1];  s.rubik[22-1]=tm.rubik[20-1];
	s.rubik[43-1]= tm.rubik[16-1]; s.rubik[44-1]= tm.rubik[17-1];
	s.rubik[45-1]=tm.rubik[18-1];  s.rubik[16-1]=tm.rubik[52-1];
	s.rubik[17-1]=tm.rubik[53-1]; s.rubik[18-1]=tm.rubik[54-1];
    s.rubik[52-1]=tm.rubik[30-1]; s.rubik[53-1]=tm.rubik[29-1];
    s.rubik[54-1]=tm.rubik[28-1]; s.rubik[28-1]=tm.rubik[45-1] ; 
    s.rubik[29-1]=tm.rubik[44-1]; s.rubik[30-1]=tm.rubik[43-1];
    s.rubik[24-1] =tm.rubik[26-1];s.rubik[25-1] = tm.rubik[19-1];
    s.rubik[26-1]=tm.rubik[22-1]; s.rubik[27-1]=tm.rubik[25-1]; 
	setValue(s);
	return s;
	
}

}
