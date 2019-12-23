package local_searches;

public class main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
       graphcoloring gn = new graphcoloring();
       gn.initialize();
       gn.runGen();
       gn.report();
       gn.displayChart();  
       
		/*
		queueState hh;
		queueState gg;
		queue gc = new queue();
		*/
		/*
	    queueState mm = gc.initialize();
	    hh=mm;
	    gg=hh;
	    System.out.println("hh1 "  + hh.toString() );
		System.out.println("mm1 "  + mm.toString() );
	    System.out.println("gg1 "  + hh.toString() );
	    //mm=gc.initialize();
	   // mm.value+=2;
	   // gg.value+=6;
		System.out.println("hh2 "  + hh.toString() );
		System.out.println("mm2 "  + mm.toString() );
	    System.out.println("gg1 "  + gg.toString() );
*/     // queue gc = new queue();
		//gc.setStrategy("normal");
     //	gc.solve();
		
		/*
		rubik gc = new rubik();
		//System.out.println(	gc.initialize().toString());
		//System.out.println(gc.down(gc.initialize()).toString());
		gc.setCoolingSchedule("exponential");
        gc.solve();
        */
	
	}
}
