import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class Scout extends Ant {
	
	// Scouts live for 1 year
	long scoutSpan = 3650;
	ColonyNode target;
	boolean visi = true;
	public Random rando = new Random();
	int counter = 1;
	long ID = 0;
	private static ColonyNode ColonyNode;
	
	public void antMove(ColonyNode colonyN) {
    	boolean locVisi = position.openSq();
        antMoveS(colonyN);
        
        if (position.openSq()) {
        	System.out.println("We have been here before.");
        }
        else {
        	counter ++;
        	System.out.println("A Scout has uncovered new terrain. ");
            position.openForBusiness(visi);
        }
    }
	
    public void next(int current) {
    	ArrayList<ColonyNode> neighborArray = position.returnNeighbors();
    	int sz = neighborArray.size();
    	int r = rando.nextInt(sz);
    	int i;
    	//for (i = 0; i < current; i++) {
    	//Iterator it = 
    	while (true) {
		    target = neighborArray.get(r);
		    antMove(target);
	    	return;
    	}
    }
    public void antMoveS(ColonyNode colonyN) {
    	Scout.ColonyNode = colonyN;
    	position.antGone(this);
        position = colonyN;
        position.antPresent(this);
    }
    public void oldScout(int current) {
    	if (scoutSpan < (current - counter - origin)) {
        	System.out.println("A Scout has died.");
            antExits();
        }
    }
    public Scout(ColonyNode colonyN) {
		//int counter = 1;
        position = colonyN;       
    }
    
    public Scout() {
    }
}