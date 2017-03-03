package scripts.red_CWBooster.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Objects;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSObject;

import scripts.redSpark.api.RedConditions;
import scripts.red_CWBooster.Data.Constants;

public class WaitingRoom implements Task{
	private final boolean DEBUG = true;
	RSObject [] exitPortal;

	@Override
	public void execute() {
		
		//Timing.waitCondition(RedConditions.interfaceUpCondition(Constants.GAME_TIMER.getMaster(), Constants.GAME_TIMER.getChild()), 5000);
		General.sleep(2000, 3000);
		
	}

	@Override
	public boolean validate() {
		exitPortal = Objects.findNearest(20, Constants.EXIT_PORTAL_NAME);
		//gameTime = Interfaces.get(Constants.GAME_TIMER.getMaster(), Constants.GAME_TIMER.getChild());//Jagex keeps changing the iterface ids
		if(DEBUG)
			General.println("WitingRoom VALIDATE = " + (exitPortal.length > 0));
		return exitPortal.length > 0 && ((exitPortal[0].getID() == Constants.WAITING_ROOM_EXIT_PORTAL_ID[0]) || (exitPortal[0].getID() ==Constants.WAITING_ROOM_EXIT_PORTAL_ID[1]));
	}

	public String status() {
		return "Waiting for game to start";
	}

}
