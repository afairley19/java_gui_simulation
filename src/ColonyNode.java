import java.util.*;

import javax.swing.*;

public class ColonyNode {

	public ColonyNodeView colNodeView;
    public static Colony colony;
	public ArrayList<Ant> antArray;
	public ArrayList<Ant> plusArray;
    public ArrayList<Ant> exitArray;
    long aCounter;
    boolean openSq;
    int food;
    int pheromone;
    int pher = attract();
    int herMajesty = 1;
    int i = 0;
    int current;
    int n = 0;
    int noms = 15;
    int oneDay = 10;
    int half = 2;
    boolean herHighness;
    boolean notHerHighness;
    boolean queenIsAlive = true;
    boolean queenIsDead = false;
    boolean cruising;
    Random rando = new Random();
    public int x; 
    public int y;
	//private Object;
    int ants = 0;
	public Object queenAnt;
	int nextF = 5;
    public void build(Colony colony) {
        ColonyNode.colony = colony;
    }
    public void show() {
    	colNodeView.showNode();
    }
    public void hide() {
    	colNodeView.hideNode();
    }
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
    public int boolC(Boolean cruising) {
    	return cruising.compareTo(false);	
    }
    public int boolV(Boolean openSq) {
    	return openSq.compareTo(false);
    	
    }
    public ColonyNode(ColonyNodeView colNodeView, int x, int y) {
    	boolean nv = false;
    	int f = 0;
    	this.colNodeView = colNodeView;
    	//int f = rando.nextInt(n);
    	antLists();
        //colNodeView = new ColonyNodeView();
    	//colNodeView.antIDs("(" + x + ", " + y + ")");
        co(x, y, nv, f);
        numFood();
    }
    private void co(int x, int y, boolean nv, int f) {
        this.x = x;
        this.y = y;
        openSq = nv;
        aCounter = n - f;
    }
    
    public void openForBusiness(boolean op) {
        openSq = op;
        if (openSq != false) {
            show();
        } else {
        	hide();  
        }
    }
    public int antCalculator(Ant myAntType) {
    	int ants = 0;
    	Class<? extends Ant> numAnts = myAntType.getClass();
    	Iterator<Ant> it = antArray.iterator();
    	while (it.hasNext())
    		//int ants = 0;
    		if (it.next().getClass() == numAnts) {
                ants++;
    		}
		return ants;
    }
    
    public void antPresent(Ant createAnt) {
	   switch (boolC(cruising)) {
	   case 0:
			antArray.add(createAnt);
			break;
	   default:
			plusArray.add(createAnt);
			break;
	   }
       refresh();	
    }
    
    public void antGone(Ant createAnt) {
        switch (boolC(cruising)) {
		case 0:
			antArray.remove(createAnt);
			break;
		default:
			exitArray.add(createAnt);
			break;
		}
        refresh();
    }
    
    public void antLists() {
    	antArray = new ArrayList<Ant>();
        plusArray = new ArrayList<Ant>();
        exitArray = new ArrayList<Ant>(); 	
    }

    public void showQueen() {
    	herHighness = queenIsAlive;
    	colNodeView.setQueen(herHighness);
    	colNodeView.showQueenIcon();
    }
    public void hideQueen() {
    	notHerHighness = queenIsDead;
    	colNodeView.setQueen(notHerHighness);
    	colNodeView.hideQueenIcon();
    }
    public void showScout() {
    	colNodeView.showScoutIcon();
    }
    public void hideScout() {
    	colNodeView.hideScoutIcon();
    }
    public void showForager() {
    	colNodeView.showForagerIcon();
    }
    public void hideForager() {
    	colNodeView.hideForagerIcon();
    }
    public void showSoldier() {
    	colNodeView.showSoldierIcon();
    }
    public void hideSoldier() {
    	colNodeView.hideSoldierIcon();
    }
    public void showBala() {
    	colNodeView.showBalaIcon();
    }
    public void hideBala() {
    	colNodeView.hideBalaIcon();
    }
    public void foodPher() {
    	colNodeView.setFoodAmount(food);
        colNodeView.setPheromoneLevel(pheromone);
    }
    public void numFood() {
    	int cFoods = rando.nextInt(nextF);
    	int foods = rando.nextInt(noms);
    	int f = 0;
    	//int f = rando.nextInt(n);
    	if (cFoods <= f) {
        	food = foods;
    	} 
    }
    
    public void refresh() {
        int aCount = 0;
        int solNum = antCalculator(new Soldier());
        int scoNum = antCalculator(new Scout());
        int foNum = antCalculator(new Forager());
        int balNum = antCalculator(new Bala());
        colNodeView.setSoldierCount(solNum);
        colNodeView.setScoutCount(scoNum);
        colNodeView.setForagerCount(foNum);
        colNodeView.setBalaCount(balNum);
        foodPher();
        
        extracted(aCount, solNum, scoNum, foNum, balNum);
    }
	private void extracted(int aCount, int solNum, int scoNum, int foNum, int balNum) {
		if (herMajesty == antCalculator(new Queen())) {
            showQueen();
            System.out.println("All hail the Queen!");
        } else {
        	hideQueen();
        }
        
        if (aCount < solNum) {
            showSoldier();
        } else {
        	hideSoldier();
        }
        
        if (aCount < scoNum) {
            showScout();
        } else {
        	hideScout();
        }
        
        if (aCount < foNum) {
            showForager(); 
        } else {
        	hideForager();
        }
       
        if (aCount < balNum) {
            showBala();
        } else { 
        	hideBala();
        }
	}
    
    public boolean openSq() {
    	while (true) {
    		return openSq;
    	}
    }
    
    public void next(int current) {
    	
    	if (current % oneDay == n) {
            this.numPheromone(pher);
        }
        cruising = true;
 
        antArray.forEach(a->a.next(current));
        
        cruising = false;

        exitArray.forEach(i->antArray.remove(i));
        
        plusArray.forEach(j->antArray.add(j));
        
        plusArray.clear();
        
        refresh();
    }

    public void numFood(int foodCount) {
    	food = foodCount;
    }

    public int groceries() {
    	while (true) {
    		//int foodCount = 0;
    		//food = foodCount;
    		return food;
    	}
    }

    public void numPheromone(int pheromoneCount) {
    	pheromone = pheromoneCount;
    }

    public int attract() {
    	while (true) {
    		return pheromone;
    	}
    }
    public boolean gotPheromone() {
    	while (attract() >= 0) {
    		
    	}
		return true;
    }
    public ArrayList<ColonyNode> returnNeighbors() {
        return colony.returnNeighbors(this);
    }

    public boolean throne() {
    	while (true) {
    		return herHighness;
    	}
    	//return notHerHighness;
    }
    // ant list
    public ArrayList<Ant> antDirectory(Ant type) {
        ArrayList<Ant> antType = new ArrayList<Ant>();
        Iterator<Ant> it = antType.iterator();
        while (it.hasNext()) {
            dir(type, antType);
        }
        return antType;
    }
    private void dir(Ant type, ArrayList<Ant> antType) {
        if (antArray.get(ants).getClass() == type.getClass()) {
            antType.add(antArray.get(ants));
        }
    }
    // enemy list
    public ArrayList<Ant> balaNum() {
    	ArrayList<Ant> bbala = new ArrayList<Ant>();
        Class<? extends Bala> ba = new Bala().getClass();
        Iterator<Ant> b = bbala.iterator();
        while (b.hasNext()) {
        	Ant bb = b.next();
        	gb(bbala, ba); 
        }
        return bbala;
    }
    private void gb(ArrayList<Ant> bbala, Class<? extends Bala> ba) {
        if (antArray.get(ants).getClass() == ba) {
            bbala.add(antArray.get(ants));
        }
    }
    // friend list
    public ArrayList<Ant> findFriends() {
        ArrayList<Ant> friends = new ArrayList<Ant>();
        int i;
        int antss = antArray.size();
        Class<? extends Bala> b = new Bala().getClass();
        Iterator<Ant> f = friends.iterator();
        while (f.hasNext()) {
        	fb(friends, b); 
        } 
        return friends;
    }
    private void fb(ArrayList<Ant> friends, Class<? extends Bala> b) {
        if (antArray.get(ants).getClass() != b) {
            friends.add(antArray.get(ants));
        }
    }

	public void build() {
		// TODO Auto-generated method stub
	}

}