import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Queen extends Ant  {
	
	// queen maximum life is 20 years
    // queen hatches an ant every 10 turns
    // queen eats each turn
    // simulation ends immediately if queen dies
    public AntSimGUI gui;
	public Ant createAnt;
	long queenSpan = 73000;
	boolean queenLives = true;
	int current;
	long ID = 0;
	long lastID = 0;
    int noneLeft = 0;
    int oneDay = 10;
    int foodEaten = 1;
    int counter = 1;
    int moveCounter;
    public Random rando = new Random();
    public Timer newTimer;
    public ArrayList<Ant> antArray;
    
    public void next(int current) {
    	//this.gui = gui;
    	int tenDays = current % oneDay;
    	//int tenthDay 
    	//gui.setTime(timeString());
    	moveCounter++;
        food();
        System.out.println("Queen says, 'MMM, it does go well with the chicken.'");
        System.out.println("Queen says, 'Delicious again, Pyotr.");
        senseEn();
        //if (current == oneDay) {
        //	moveCounter++;
       // }
        if (tenDays == noneLeft) {
        	moveCounter++;
            createNewAnts(createAnt);
            System.out.println("The colony grows stronger.");
        }
    }
    public String timeString() {
        String updatedTimer = ("A Day in the Life: " + moveCounter / oneDay);
        return updatedTimer;
      }
    public void food() {
        while (noneLeft == position.groceries()) {
        	System.out.println("The Queen has starved. Off with the Foragers heads.");
            antExits();
        }
        position.numFood(position.groceries() - foodEaten);
        System.out.println("Queen says, 'Nom, nom.'");
    }
    
    public int boolScout(Boolean scoutB) {
    	return scoutB.compareTo(false);
    	
    }
    public void createScout(Ant ant) {
    	createAnt = new Scout(position);        	
    	createAnt.antBorn(current);
   	    lastID = lastID + counter;
   	    createAnt.antIDs(lastID);
        position.antPresent(createAnt);
    }
    public void createSoldier(Ant ant) {
    	createAnt = new Soldier(position);        	
    	createAnt.antBorn(current);
   	    lastID = lastID + counter;
   	    createAnt.antIDs(lastID);
        position.antPresent(createAnt);
    }
    public void createForager(Ant ant) {
    	createAnt = new Forager(position);        	
    	createAnt.antBorn(current);
   	    lastID = lastID + counter;
   	    createAnt.antIDs(lastID);
        position.antPresent(createAnt);
    }
   
    public void createNewAnts(Ant ant) {
    	int dice = 3;
    	int antOdds = 4;
        moreAnts(dice, antOdds);
        moveCounter++;
        System.out.println("The colony grows stronger.");
    }
	private void moreAnts(int dice, int antOdds) {
		
		switch (rando.nextInt(dice)) {
		
		case 0:
	        	System.out.println("A new Scout is born.");
	        	createAnt = new Scout(position);        	
	        	createAnt.antBorn(current);
	       	    lastID = lastID + counter;
	       	    createAnt.antIDs(lastID);
	            position.antPresent(createAnt);
	            return;
	        
		case 1:
	        	System.out.println("A new Forager is born.");
		    	createAnt = new Forager(position);	    	
		    	createAnt.antBorn(current);
		   	    lastID = lastID + counter;
		   	    createAnt.antIDs(lastID);
		        position.antPresent(createAnt);
		        return;
	        
	    case 2:
	    	   	System.out.println("A new Soldier is born.");
	        	createAnt = new Soldier(position);        	
	        	createAnt.antBorn(current);
	       	    lastID = lastID + counter;
	       	    createAnt.antIDs(lastID);
	            position.antPresent(createAnt);
	            return;
	       }
	}
    
   public void senseEn() {
	   ColonyNode bLocation = Colony.gameBoard[19][0];
    	int bOdd = 3;
    	int bDice = 19;
    	while (queenLives) {
	        if (bOdd >= rando.nextInt(bDice)) {
	            Bala enemyEnters = new Bala(bLocation);
	            bLocation.antPresent(enemyEnters);
	            lastID = lastID + counter;
	            enemyEnters.antIDs(lastID);
	            System.out.println("A Bala has entered the colony!");
	        } return;
    	}
    }
   
   public int antCalculator(Ant myAntType) {
   	int ants = 0;
   	Class<? extends Ant> numAnts = myAntType.getClass();
   	Iterator<Ant> it = antArray.iterator();
   	while (it.hasNext())
   		if (it.next().getClass() == numAnts) {
               ants++;
   		}
		return ants;
   }
   public void endGame(int current) {
	   ColonyNode balaQueen = Colony.gameBoard[13][13];
	  // Bala enemyEnters = new Bala(balaQueen);
	   ColonyNode bLoc = Colony.gameBoard[13][13];
       boolean balaQ = true;
       ArrayList<Ant> friends = position.findFriends();
       ArrayList<Ant> badBala = position.balaNum();
       int nb = friends.size();
       int bb = badBala.size();
   	   while (queenSpan < current) {
           System.out.println("The Queen has died of natural causes.");
           queenLives = false;
           System.exit(1);
       }
   	   while (antCalculator(new Bala()) > 0) {
   		   System.out.println("The Queen has been assasinated.");
   		   queenLives = false;
   		   antExits();
   		   System.exit(1);
   	   }
       
       while (noneLeft >= position.groceries()) {
       	System.out.println("The Queen has starved to death.");
       	queenLives = false;
       	antExits();
       	System.exit(1);
       }
   }
  
    public void antExits() {
        Colony.simulator.gameOver();
        System.exit(1);
    }
    public Queen(ColonyNode colonyN) {
        position = colonyN;
    }
    
    public Queen() {
    }
}