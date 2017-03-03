package scripts.red_CWBooster.Data;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api.General;

public class Variables {
	private boolean run = true;
	public void setRun(Boolean b){
		this.run = b;
	}
	public boolean run() {
		return this.run;
	}
	
	private String status = null;
	public void setStatus(String status) {
		this.status = status;	
	}
	public String getStatus() {
		return this.status;
	}

	
	
	//GUI
	private boolean guiOn = true;
	public void setGUI(boolean b){
		this.guiOn = b;
	}
	public boolean guiOn(){
		return this.guiOn;
	}
	
	private int mouseSpeed;
	public void setMouseSpeed(int mouseSpeed){
		this.mouseSpeed = mouseSpeed;
	}
	public int getMouseSpeed() {
		// TODO Auto-generated method stub
		return this.mouseSpeed;
	}
	
	private List <Integer> worldList = new ArrayList <Integer>();
	public void setWorldList(String[] worldList){
		for(int i = 0; i < worldList.length; i++){
			this.worldList.add(Integer.parseInt(worldList[i]));
			General.println(worldList[i]);
		}

	} 
	private int worldListIndex = 0;
	public void worldListIndexNext(){
		worldListIndex++;
		if(worldListIndex >= worldList.size())
			 worldListIndex = 0;
		General.println("World index is:"+worldListIndex +" out of a list size:"+worldList.size());
	}
	private int world;
	public int getWorld(){
		world = worldList.get(worldListIndex);
		return world;
		
	}
	private int ticketMax = 999999999;
	public void setTicketMax(int ticketMax){
		this.ticketMax = ticketMax;
		
	}
	public int getTicketMax(){
		return this.ticketMax;
	}
	
	private boolean pairHoping = false;
	public void setPairHoping(){
		this.pairHoping = true;
	}
	public boolean pairHoping(){
		return this.pairHoping;
	}

	//GUI END
	private int failCount = 20;
	public boolean areWeStuck() {
		if(failCount >20){
			failCount = 0;
			return true;
		}
		return false;
	}
	private int startingTickets = 0;
	public void setStartingTickets(int num){
		this.startingTickets = num;
	}
	public int getStartingTickets(){
		return this.startingTickets;
	}
	
	

	
	public void addFail() {
		failCount++;
		
	}
}
