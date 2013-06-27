// CArtAgO artifact code for project prj_MAS_Bingo

package tablet;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cartago.Artifact;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;

public class Bingo extends Artifact {
	
	private Set <Integer> box = new HashSet <Integer>();
	
	String internalStatus = "void";
	int MAXNumberSorted = 40;
	int MAXSold = 02;
	int sold = 0;
	
	void init() {
		defineObsProperty("numSorted", 0);
		signal("status", "selling");
		internalStatus = "selling";
	}
	
	@OPERATION
	void sell(){
		sold++;
		if (sold >= MAXSold){
			signal("status", "ready");
		}			
	}
	
	@OPERATION
	void start(){
		signal("status", "started");
		internalStatus = "started";
		execInternalOp("sortNumbers");		
	}
	
	@INTERNAL_OPERATION
	void stop(){
		internalStatus = "stop";
		signal("status", "stoped");
	}
	
	@OPERATION
	void winner(){
		internalStatus = "stop";
		signal("status", "stoped");
	}
	
	@INTERNAL_OPERATION
	void sortNumbers(){
		Random r = new Random();
		int counter = 0;
		while (!internalStatus.equals("stop")){			
			int x = r.nextInt(100);
			if (box.add(x)){                                                                                 //garantir a não repetição dos números;				
				counter++;
				getObsProperty("numSorted").updateValue(x);
				signal("status", "sorted");
				await_time(3000);	
			}			
			if (counter >= MAXNumberSorted)
				execInternalOp("stop");
		}		
	}
	
}

