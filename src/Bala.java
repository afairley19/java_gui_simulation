import java.util.*;
import javax.swing.*;

public class Bala extends Ant {
	//
	long balaSpan = 1825;
	long ID;
	int lastID;
	int notB = 0;
	int b = 1;
	int sol = 0;
	public Random rando = new Random();
    int r = rando.nextInt();
    int counter = 1;
	private static ColonyNode ColonyNode;
    //ArrayList<Ant> friends = position.findFriends();
	//ArrayList<ColonyNode> neighborArray = position.returnNeighbors();
	//ArrayList<Ant> friends = position.findFriends();
	
	public void antMove(ColonyNode colonyN) {
		Bala.ColonyNode = colonyN;
		position.antGone(this);
        position = colonyN;
        position.antPresent(this);
    }
	
    public void next(int current) {
    	//int notB
       int counter = 1;
       ColonyNode bLoc = Colony.gameBoard[13][13];
       boolean balaQ = true;
       ArrayList<Ant> friends = position.findFriends();
       ArrayList<Ant> badBala = position.balaNum();
       int nb = friends.size();
       int bb = badBala.size();
   	   ArrayList<ColonyNode> neighborArray = position.returnNeighbors();
       ColonyNode target;
       target = neighborArray.get(rando.nextInt(neighborArray.size() + notB));
       
       if (notB >= nb | sol >= nb) { // the bala walks
    	   counter++;
    	   antMove(target); 
       } else {
    	   System.out.println("They are fighting!");
    	   friends.get(sol).antExits();
    	   System.out.println("A Soldier has been defeated!");
    	   System.out.println("Breaking news: A colony member has been killed by enemy factions.");
    	   return;
      
       }
       if (position.herHighness) {
    	   System.out.println("The Queen has been assassinated.");
    	   Colony.simulator.gameOver();
           System.exit(1);
       }
    }
   
    public void oldBala(int current) {
    	if (balaSpan < current - origin) {
            antExits();
            System.out.println("An enemy has died!");
        }
    }
    public Ant get(int num) {
		// TODO Auto-generated method stub
		return null;
	}
    public void moveB(ColonyNode colonyN) {
		
    }
    
    public Bala(ColonyNode colonyN) {
        position = colonyN;
        lastID = 0;
        ID = 0;
    }
    
    public Bala() {
    }
}