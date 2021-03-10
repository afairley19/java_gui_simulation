import java.util.*;
import javax.swing.*;

public class Forager extends Ant {
	
	long foragerSpan = 3650;
    public Random rando = new Random();
    //boolean forage;
    int collectFood = 1;
    int food = 0;
    boolean goHome;
    int noP = 0;
    long notAvail = 0;
    private static ColonyNode ColonyNode;

    public void next(int current) {
        ColonyNode target;
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
        if (goHome) {
            target = Colony.gameBoard[13][13];
            System.out.println("A Forager has discovered food."); 
            antMove(target);
        }  
    }
    public void antMove(ColonyNode colonyN) {
    	boolean q = position.throne();
    	int p = position.attract();
    	int parfum = 75;
    	int foodUnits = 5;
    	//int foodStores = position.groceries();
    	antMoveF(colonyN);

        while (goHome & q) {
        	int foodStores = position.groceries();
            position.numFood(collectFood + foodStores - food);
            goHome = false;  
        }
        if (p < (noP + parfum)) {
            position.numPheromone(p + foodUnits - noP);
            goHome = true;
        } return;
    }

    public void antExits() {
	    position.antGone(this);	
	    System.out.println("A Forager has died.");
    }
   
    public void antMoveF(ColonyNode colonyN) {
    	Forager.ColonyNode = colonyN;
    	position.antGone(this);
        position = colonyN;
        position.antPresent(this);
    }
 
    public void oldForager(int current) {
    	if (foragerSpan < current - origin) {
            antExits();
        }
    }
    public Forager(ColonyNode colonyN) {
        position = colonyN;
        ID = 0;
    }
    
    public Forager() {
    }
}