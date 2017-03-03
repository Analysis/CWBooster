package scripts.red_CWBooster.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSObject;
import scripts.redSpark.antiBan.AntiBan;
import scripts.redSpark.api.DynamicWait;
import scripts.redSpark.api.RedConditions;
import scripts.red_CWBooster.Data.Constants;

public class InGame implements Task {
	private final boolean DEBUG = true;
	private RSInterface gameTime1;
	private RSInterface gameTime2;
	private RSInterface startingRoomTimer1;
	private RSInterface startingRoomTimer2;
	private RSObject[] door;
	private String status = "In Game";
	private TaskManager manager;
 
	public InGame(TaskManager taskManager) {
		this.manager = taskManager;
	}

	@Override
	public void execute() {
		startingRoomTimer1 = Interfaces.get(Constants.STARTING_ROOM_TIMER[0].getMaster(), Constants.STARTING_ROOM_TIMER[0].getChild());
		startingRoomTimer2 = Interfaces.get(Constants.STARTING_ROOM_TIMER[1].getMaster(), Constants.STARTING_ROOM_TIMER[1].getChild());
		if((startingRoomTimer1 != null && !startingRoomTimer1.isHidden() && startingRoomTimer1.getText() != "")
				|| (startingRoomTimer2 != null && !startingRoomTimer2.isHidden() && startingRoomTimer2.getText() != "")){//TODO very messy needs changing
			status = "Walking trough " + Constants.DOOR_NAME;
			door = Objects.findNearest(10, Constants.DOOR_NAME);
			if(door.length > 0){
				
				if(!door[0].isOnScreen())
					Camera.turnToTile(door[0].getPosition());
				if(!door[0].click("Pass " + Constants.DOOR_NAME)){
					General.println("Failed to click on " + Constants.DOOR_NAME);
					Walking.walkTo(door[0].getPosition());
					return;
				}else{
					General.println("Passed tought the "+ Constants.DOOR_NAME);
					manager.variables.worldListIndexNext();
				}
				DynamicWait.waitUntilIdle(2000, 3000);
			}else{
				if(DEBUG)
					General.println("Can't find "+ Constants.DOOR_NAME);
			}
			
		}else{
			if(DEBUG)
				General.println("Not in starting room");
			
			if(gameTime1 != null){//TODO very messy needs changing
				if(DEBUG){
					General.println(gameTime1.getText());
					General.println(Integer.parseInt(gameTime1.getText().split(" ")[0]));
					
				}
				
				if(Integer.parseInt(gameTime1.getText().split(" ")[0]) > 5){
					int world = manager.variables.getWorld();
					General.println("Trying to hop to world:"+world);
					if(!FCInGameHopper.hop(world)){
						General.println("Failed to hop to world:" +world);
						return;
					}else{
						manager.variables.worldListIndexNext();
						General.println("World hop to world:"+world+" sucsesful");
					}
				}else{
					Timing.waitCondition(RedConditions.inAreaCondition(Constants.CASTLE_WARS), 5000);
						
					status = "Waiting for game to end.Next world:"+manager.variables.getWorld();
				}
					
			}
			if(gameTime2 != null ){//TODO very messy needs changing
				if(DEBUG){
					General.println(gameTime2.getText());
					General.println(Integer.parseInt(gameTime2.getText().split(" ")[0]));
					
				}
				
				if(Integer.parseInt(gameTime2.getText().split(" ")[0]) > 5){
					int world = manager.variables.getWorld();
					General.println("Trying to hop to world:"+world);
					if(!FCInGameHopper.hop(world)){
						General.println("Failed to hop to world:" +world);
						return;
					}else{
						manager.variables.worldListIndexNext();
						General.println("World hop to world:"+world+" sucsesful");
					}
				}else{
					Timing.waitCondition(RedConditions.inAreaCondition(Constants.CASTLE_WARS), 5000);
						//manager.variables.worldListIndexNext();
					status = "Waiting for game to end.Next world:"+manager.variables.getWorld();
				}
					
			}
			
		}
		
	}

	public boolean validate() {
		gameTime1 = Interfaces.get(Constants.GAME_TIMER[0].getMaster(), Constants.GAME_TIMER[0].getChild());
		gameTime2 = Interfaces.get(Constants.GAME_TIMER[1].getMaster(), Constants.GAME_TIMER[1].getChild());
		if(DEBUG)
			General.println("InGame VALIDATE = " +((gameTime1 !=null && !gameTime1.isHidden()) || (gameTime2 !=null && !gameTime2.isHidden())));
		return (gameTime1 !=null && !gameTime1.isHidden()) || (gameTime2 !=null && !gameTime2.isHidden());//TODO very messy, needs changing
	}

	public String status() {
		return status;
	}

}
