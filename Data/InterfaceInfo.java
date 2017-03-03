package scripts.red_CWBooster.Data;
/*
 * @Red_Spark
 * Date 01/02/2016
 * Stores interface values for Master,Child and Component
 */
public class InterfaceInfo {
	private int Master;
	private int Child;
	private int Component;
	
	public InterfaceInfo(int Master, int Child, int Component){
		this.Master = Master;
		this.Child = Child;
		this.Component = Component;
	}
	public InterfaceInfo(int Master, int Child){
		this.Master = Master;
		this.Child = Child;
	}
	
	public int getMaster(){
		return this.Master;
	}
	
	public int getChild(){
		return this.Child;
	}
	
	//This can return null
	public int getComponent(){
		return this.Component;
	}

}
