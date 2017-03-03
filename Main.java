package scripts.red_CWBooster;
/*
 * Red_Spark
 * 31/01/2016
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.redSpark.antiBan.AntiBan;
import scripts.red_CWBooster.Data.Constants;
import scripts.red_CWBooster.Data.Variables;
import scripts.red_CWBooster.Tasks.Task;
import scripts.red_CWBooster.Tasks.TaskManager;


@ScriptManifest(authors = "Red_Spark", category = "Test", name = "Red_CWBooster", version = 0.5)
public class Main extends Script implements Painting{

	private final boolean DEBUG = true;
	
	final ScriptManifest MANIFEST = (ScriptManifest)this.getClass().getAnnotation(ScriptManifest.class);
	public final String TITLE = MANIFEST.name()+" "+MANIFEST.version()+" by Red_Spark";
	private Variables variables = new Variables();
	private TaskManager manager = new TaskManager(variables);
	
	private int ticketsEarned;
	
	private long startTime;
	private long timeRan;
	
	public void run() {
		onStart();
		if(Game.getGameState() != 30){
			while(Login.login()){
				General.sleep(500, 800);
			}
		}
		if(Game.getGameState() == 30){
			variables.setStartingTickets(Inventory.getCount(Constants.TICKET_NAME));	
		}
			
		mainLoop(20, 60);
		
	}

	private void mainLoop(int min, int max){
		while(variables.run()){
			Task task = manager.getValidTask();
			if(task != null){
				variables.setStatus(task.status());
				task.execute();
				sleep(min, max);
				
			}else{
				if(DEBUG)
					println("task == null");
				variables.addFail();
			}
		}
	}
	private boolean onStart() {
		AntiBan.create();
		General.println("----------------------------------------------------------------");
		GUI gui = new GUI(variables);
		gui.setVisible(true);
        while(variables.guiOn()){//Waiting for the user to input all GUI settings
                sleep(500);
        }
        General.println("Mouse Speed set to:"+variables.getMouseSpeed());
        Mouse.setSpeed(variables.getMouseSpeed());
        General.println("----------------------------------------------------------------");
        
        startTime = System.currentTimeMillis();
        return true;
	}

	public void onPaint(Graphics g) {
		ticketsEarned = Inventory.getCount(Constants.TICKET_NAME) - variables.getStartingTickets();
		timeRan = System.currentTimeMillis() - startTime;
		
		//DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,###");
		
		g.setFont(new Font("Verdana", Font.BOLD, 20));
        g.setColor(new Color(255, 0, 51));
        g.drawString(TITLE, 10, 40);
            
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString("Time Running:"+ Timing.msToString(timeRan), 10, 60);
        g.drawString("Tickest earned:"+ ticketsEarned, 10, 80);
        g.drawString("Tickest earned P/H:"+(long)(ticketsEarned * 3600000 / timeRan), 10, 100);
        g.drawString("Current Action:"+variables.getStatus(), 10, 120);
		
	}


}
