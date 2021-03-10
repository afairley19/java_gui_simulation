import java.util.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;

public class SimDriver {
    public static void main(String [] args) {
    	
    	System.out.println("Build the Colony and then select Run mode.");
        AntSimGUI gui = new AntSimGUI();
        Simulation newS = new Simulation(gui);
        gui.addSimulationEventListener(newS);

    }
}