import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class Ant {
	
	public ColonyNode colonyN;
	public ColonyNode position;
	static long ID = 0;
	long current;
	boolean alive = true;
    long life = 0;
    long origin;
   
    public void antIDs(long id) {
        Ant.ID = id;
        System.out.println("Ant number " + id + " has entered the chat.");
    }
    public void getID(long id) {
    	return;
    }
    
    public void antBorn(long current) {
        babyAnt(current);
        System.out.println("Baby ants!");
    }
    private void babyAnt(long current) {
        origin = current;
    }
    
    public Ant(ColonyNode colonyN) {
        position = colonyN;
	}
 
    public void antExits() {
    	getAge();
    	System.out.println("Always look on the bright side of life.");
    	position.antGone(null);
    }
   
    public void antMove(ColonyNode nextNode) {
   
     }

    public void next(int current) {

    }
    private void getAge() {
		// TODO Auto-generated method stub
		
	}
	public Ant() {
	
	}
}