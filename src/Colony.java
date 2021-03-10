import java.util.*;
import java.util.ArrayList;
import javax.swing.*;

public class Colony {
	public static Simulation simulator;
    public static ColonyNode [][] gameBoard;
    public static ArrayList<ColonyNode> neighbor;
    public static ColonyNode colonyN;
    public static ColonyNodeView colNodeView;
    public static ColonyView colonyView;
    int colSize = 27;
    int qT = 13;
    int qF = 1000;
    int current;
    int n = 0;
    long foragerFreq = 50;
	long scoutFreq = 4;
	long soldierFreq = 10;
	public Integer x;
	public Integer y;
	
	public int returnX() {
    	while (true) {
    		return x;
    	}
    }
    public int returnY() {
    	while (true) {
    		return y;
    	}
    } 
	public void begin() {
		boolean visi = true;
		int x = 0;
		int y = 0;
		
		/*gameBoard[12][12].openForBusiness();
        gameBoard[12][13].openForBusiness();
        gameBoard[12][14].openForBusiness();
        
        gameBoard[13][12].openForBusiness();
        gameBoard[13][13].openForBusiness();
        gameBoard[13][14].openForBusiness();
        
        gameBoard[14][12].openForBusiness();
        gameBoard[14][13].openForBusiness();
        gameBoard[14][14].openForBusiness();*/
		//ColonyNode cn = new ColonyNode(colNodeView, x, y);
	    //gameBoard[x][y] = square;
		
        for (x = 0; colSize > x; x++) {
            for (y = 0; colSize > y; y++) {
            	//colN();
            	coord(x, y);
                if ((x == qT) & (y == qT)) {
                	spawn();
                } 
            } 
        } 
        gameBoard[12][12].openForBusiness(visi);
        gameBoard[12][13].openForBusiness(visi);
        gameBoard[12][14].openForBusiness(visi);
        
        gameBoard[13][12].openForBusiness(visi);
        gameBoard[13][13].openForBusiness(visi);
        gameBoard[13][14].openForBusiness(visi);
        
        gameBoard[14][12].openForBusiness(visi);
        gameBoard[14][13].openForBusiness(visi);
        gameBoard[14][14].openForBusiness(visi);
    }
	private void coord(int x, int y) {
		colNodeView = new ColonyNodeView();
		colonyN = new ColonyNode(colNodeView, x, y);
		Colony.colonyN.build(this);
		Colony.colonyView.addColonyNodeView(colNodeView, x, y);
		Colony.addNeighbor(colonyN, x, y);
	}
	
	// this didn't count the ant IDs properly
	
//	public void foragerB() {
//		//final Queen queenAnt = new Queen(colonyN);
//		for (int fora = 0; fora < foragerFreq; fora++) {
//			//final Queen queenAnt = new Queen(colonyN);
//    		System.out.println("A new Forager is born.");
//    		queenAnt.createForager(new Forager(Colony.gameBoard[13][13]));
//        }
//	}
//	public void scoutB() {
//		//final Queen queenAnt = new Queen(colonyN);
//    	for (int sco = 0; sco < scoutFreq; sco++) {
//    		//final Queen queenAnt = new Queen(colonyN);
//    		System.out.println("A new Scout is born.");
//            queenAnt.createScout(new Scout(Colony.gameBoard[13][13]));
//        }
//	}
//	public void soldierB() {
//		//final Queen queenAnt = new Queen(colonyN);
//		for (int sol = 0; sol < soldierFreq; sol++) {
//			//final Queen queenAnt = new Queen(colonyN);
//			System.out.println("A new Soldier is born.");
//            queenAnt.createSoldier(new Soldier(Colony.gameBoard[13][13]));
//		}
//	}
	public void spawn() {
		final Queen queenAnt = new Queen(colonyN);
		Colony.gameBoard[13][13].antPresent(queenAnt);
    	Colony.gameBoard[13][13].numFood(qF);
    	//foragerB();
    	for (int fora = 0; fora < foragerFreq; fora++) {
    		System.out.println("A new Forager is born.");
    		queenAnt.createForager(new Forager(Colony.gameBoard[13][13]));
        }
    	//scoutB();
    	for (int sco = 0; sco < scoutFreq; sco++) {
    		System.out.println("A new Scout is born.");
            queenAnt.createScout(new Scout(Colony.gameBoard[13][13]));
        }
    	//soldierB();
    	for (int sol = 0; sol < soldierFreq; sol++) {
			System.out.println("A new Soldier is born.");
            queenAnt.createSoldier(new Soldier(Colony.gameBoard[13][13]));
		}
		
	}
	public static ColonyView returnView() {
		while (true) {
			return colonyView;
		}
	}
	public Colony(ColonyView view, Simulation simulator) {
		gameBoard = new ColonyNode [colSize][colSize];
		colonyView = view;
		ArrayList<Ant> antArray;
		Ant createAnt;
		Colony.simulator = simulator;
	}
	public ArrayList<ColonyNode> returnNeighbors(ColonyNode colonyN) { 
		neighbor = new ArrayList<ColonyNode>();
        int a = -1;
        int b = -1;
        int num = 1;
        //int counts = 1;
        
        while (true) {
	        for (a = -1;num >= a -n; a++) {
	        	for (b = -1; num >= b; b++) {
	            	//Object Exception;
					if (n != a) {
	                    expand(colonyN, a, b);
	                }  //else 
					if (n != b) {
	                    expand(colonyN, a, b);
	               } 
	            }
	        }
	        return neighbor;
        }
    }
	private void expand(ColonyNode colonyN, int a, int b) {
		try {
		    Colony.neighbor.add(gameBoard[colonyN.returnX() + a + n][colonyN.returnY() + b + n]);
		} catch (ArrayIndexOutOfBoundsException e) {
              }
	}
	public void next(int current) {
		int count = 0;
    	int i = 0;
    	int j = 0;
        for (i = 0; colSize > i; i++) {
            j = createB(current, i);
        } count++;
    }
	private int createB(int current, int i) {
		int j;
		for (j = 0; colSize > j; j++) {
		    Colony.gameBoard[i][j].next(current);
		}
		return j;
	}
	
	
	public static void addNeighbor(ColonyNode square, Integer x, Integer y) {
		returnView();
		ColonyNode colonyN = new ColonyNode(colNodeView, x, y);
	    Colony.gameBoard[x][y] = square;
	    colNodeView.antIDs("(" + x + ", " + y + ")");
	}
	
	
}
