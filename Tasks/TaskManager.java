package scripts.red_CWBooster.Tasks;


import java.util.ArrayList;
import java.util.List;

import org.tribot.api.General;

import scripts.red_CWBooster.Data.Variables;




public class TaskManager{
	private final boolean DEBUG = false;
	public Variables variables;
	
	public TaskManager(Variables variables) {
        list = new ArrayList<>();
        this.variables = variables;
    }
	public final Task[] TASKS = {new EnterPortal(this), new InGame(this), new WaitingRoom()}; 
	private List<Task> list;

	
	//MAIN METHOS
    private void addTasks(Task... tasks) {
        for (Task task: tasks) {
            if (!list.contains(task)) {
            	if(DEBUG)
            		General.println("Task added");
                list.add(task);
            }
        }
    }

    public void removeTask(Task task) {
        if (list.contains(task)) {
        	if(DEBUG)
        		General.println("Task removes");
            list.remove(task);
        }
    }

    private void clearTasks() {
    	if(DEBUG)
    		General.println("Task cleared");
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public Task getValidTask() {
    	if(variables.areWeStuck())
    		resetTasks();
        if (list.size() > 0) {
        	for(int i = 0; i < list.size(); i++){
        		if(list.get(i).validate()){
        			return list.get(i);
        		}  
        		General.sleep(20, 40);
        	}        
        }
        return null;
    }
    private void resetTasks(){
    	clearTasks();
    	addTasks(TASKS);   
    }
}
