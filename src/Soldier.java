import java.util.*;

import javax.swing.*;

public class Soldier extends Ant {
	
    long soldierSpan = 3650;
    long ID = 0;
    public Random rando = new Random();
    int noBala = 0;
    int counter = 1;
    int num = 0;
    int chance = 1;
    private static ColonyNode ColonyNode;
    
    public void fightB() {
    	
    }

    public void next(int current) {
    	//int num;
    	//int i = 0;
        ArrayList<ColonyNode> neighborArray = position.returnNeighbors();
        int sz = neighborArray.size();
        ArrayList<ColonyNode> openSquares = new ArrayList<ColonyNode>();
        Iterator<ColonyNode> it = neighborArray.iterator();
        //Integer i = (Integer) it.next();
        while (it.hasNext()) {
        	ColonyNode i = it.next();
        	if (i.openSq()) {
                openSquares.add(i);
                int sv = openSquares.size();       
                //counter++;
                antMove(openSquares.get(rando.nextInt(sv)));
        	}
        }
    }
    public void antMove(ColonyNode colonyN) {
    	counter++;
    	Soldier.ColonyNode = colonyN;
        position.antGone(this);
        position = colonyN;
        position.antPresent(this);
    }
  
    public void oldSoldier(int current) {
    	//int num;
        if (soldierSpan < (current - origin)) {
        	System.out.println("A Soldier has died of old age. The Queen has lowered the flag to half mast.");
            antExits();
        }
    }
    public Soldier(ColonyNode colonyN) {
        position = colonyN;
        
        //int counter = 1;
    }
    
    public Soldier() {
    }
}