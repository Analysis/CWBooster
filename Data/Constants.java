package scripts.red_CWBooster.Data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {
	public static final String PORTAL_NAME = "Guthix Portal";
	public static final int [] WAITING_ROOM_EXIT_PORTAL_ID = {4389, 4390};
	//public static final int GAME_ROOM_EXIT_PORTAL_ID;//and by game i mean in castle wars mini-game
	public static final String EXIT_PORTAL_NAME = "Portal";
	public static final String DOOR_NAME = "Energy Barrier";
	public static final String TICKET_NAME = "Castle wars ticket";
	
	//public static final RSArea START_ROOM = new RSArea(new RSTile(2423,3072, 1), new RSTile(2431,3080, 1));
	public static final RSArea CASTLE_WARS = new RSArea(new RSTile(2445, 3098, 0), new RSTile(2438, 3082, 0));
	
	
	public static final InterfaceInfo[] STARTING_ROOM_TIMER = {new InterfaceInfo(58,20),new InterfaceInfo(59,20)};//2 interfaces since each side has its own interface id
	public static final InterfaceInfo[] GAME_TIMER = {new InterfaceInfo(58,21),new InterfaceInfo(59,21)};//2 interfaces since each side has its own interface id

}
