package scripts.red_CWBooster.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.redSpark.api.DynamicWait;
import scripts.redSpark.api.walking07.Walking07;
import scripts.red_CWBooster.Data.Constants;
import scripts.redSpark.api.RedConditions;

public class EnterPortal implements Task{
	private final boolean DEBUG = true;
	private RSObject [] guthixPortal;
	private TaskManager manager;

	public EnterPortal(TaskManager taskManager) {
		this.manager = taskManager;
	}
	public void execute() {
		int  tickets = Inventory.getCount(Constants.TICKET_NAME);
		if(tickets >= manager.variables.getTicketMax()){
			manager.variables.setRun(false);
			General.println("Ticket max reacked");
			return;//breaks out of the method
		}
			
		General.println("Current world is:" + Game.getCurrentWorld());
		int world = manager.variables.getWorld();
		if(Game.getCurrentWorld() != world+300){
			
			General.println("Trying to hop to world:"+world);
			if(!FCInGameHopper.hop(world)){
				General.println("Failed to hop to world:" +world);
				return;
			}else{
				
				General.println("World hop to world:"+world+" sucsesful");
				Timing.waitCondition(RedConditions.IN_GAME_CONDITION, 6000);
					//manager.variables.worldListIndexNext();
			}
		}
		if(guthixPortal[0] != null){
			if(!guthixPortal[0].isOnScreen()){
				if(!walkToPortal())
					General.println("Failed to walk to "+Constants.PORTAL_NAME);
			}
			if(!guthixPortal[0].click("Enter "+Constants.PORTAL_NAME))
				General.println("Failed to click on "+ Constants.PORTAL_NAME );
			else{
				DynamicWait.waitUntilIdle(1000, 2000);
			}
		}
			
		
	}
	public boolean validate() {
		guthixPortal = Objects.findNearest(20, Constants.PORTAL_NAME);
		if(DEBUG)
			General.println("EnterPortal VALIDATE = "+ (guthixPortal.length >0));
		return guthixPortal.length >0;
	}

	public String status() {
		return "Entering Portal";
	}
	private boolean walkToPortal(){
		RSTile[] path = Walking.generateStraightPath(guthixPortal[0].getPosition());
		if(path.length == 0)
			return false;
		return Walking07.walkPath(path, 5, RedConditions.objectOnScreenCondition(guthixPortal[0]));
	}

}
