import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.Timer;
import java.util.EventListener;

public class Simulation implements ActionListener, SimulationEventListener {
	
	public static AntSimGUI gui;
    public Colony colonySimulator;
    public Thread process;
    public ColonyView colonyView;
    boolean steppedSetup;
    boolean gameActive;
    int colonySize = 27;
    int current = 0;
    int oneDay = 10;
    int next = 1;
    long timer = 50;
    long stepTimer = 500000;
    public Timer newTimer;
    int moveCounter;
    
    public ColonyView returnView() {
		return colonyView;
    }
 
    public void simulationEventOccurred(SimulationEvent simEvent) {
        int buttonPressed = simEvent.getEventType();
        final int stepRun = SimulationEvent.STEP_EVENT;
        final int setupColony = SimulationEvent.NORMAL_SETUP_EVENT;
        final int continuousRun = SimulationEvent.RUN_EVENT;
        newTimer = new Timer(1000, this);
        switch (buttonPressed) {
        case setupColony:
        	System.out.println("The Colony has been built.");
            colonySimulator.begin();
            break;
        case continuousRun:
            steppedSetup = !true;
           // gui.setTime(dayCounter());
            process = new Thread() {
                public void run() {
                	//gui.setTime(dayCounter());
                	moveCounter++;
                	//current++;
                    contTurn();
                   // processStart();
                    }  //void processStart() 
                }; 
                processStart();
                break;
        case stepRun:
        	steppedSetup = true;
        	gui.setTime(dayCounter());
            process = new Thread() {
                public void run() {
                    stepTurn();
                }
            };
            processStart();
            break;
        } 
    }
    public String dayCounter() {
        String updatedTimer = ("A Day in the Life: " + moveCounter / oneDay);
        return updatedTimer;
      }
 
    public void stepTurn() {
    	boolean stepSetup = steppedSetup;
    	while (gameActive & stepSetup) {
    		colonySimulator.next(current);
    		current = current + next;
    		moveCounter = moveCounter + next;
    		st(); //moveCounter++;
    	} //moveCounter++;
    }

    private void st() {
        try {
        	Thread.sleep(stepTimer);
        } catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
    } 
    
	 public void contTurn() {
	    	boolean normalSetup = !steppedSetup;
	    	moveCounter++;
	    	while (gameActive & normalSetup) {
	    		colonySimulator.next(current);
	    		current = current + next;
	    		moveCounter = moveCounter + next;
	    		nt(); moveCounter++;
	    	} moveCounter++;
	    }

    private void nt() {
        try {
        	Thread.sleep(timer);
        	moveCounter++;
        } catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
    }
	 public void processStart() {
		 
		 process.start();
		// moveCounter++;
	 }
	// public void run() {
		 
//	 }
    
    public void gameOver() {
    	int n = 0;
        gameActive = !true;
        System.out.println("The Queen has died.");
        JOptionPane.showMessageDialog(gui, "The Colony has crumbled.", "Game Over", JOptionPane.PLAIN_MESSAGE);
        System.exit(1);
    }

    
    public Simulation(AntSimGUI gui) {
        Simulation.gui = gui;
    	steppedSetup = true;
    	//boolean gameActive = true;
    	gameActive = true;
    	int current = 0;
    	//newTimer = new Timer(99, (ActionListener) this);
        //gui = gui;
        ColonyView colSim = simB();
        gui.initGUI(colSim);	
    }

    private ColonyView simB() {
        colonySimulator = new Colony(new ColonyView(colonySize, colonySize), this);
        ColonyView colSim = Colony.returnView();
        return colSim;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

