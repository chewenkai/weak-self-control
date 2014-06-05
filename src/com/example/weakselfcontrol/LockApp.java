package com.example.weakselfcontrol;
/**
 * LockApp µÃÂ¿‡
 * @author TAOJX
 *
 */
public class LockApp {
	
	public String getPackageName(){
		return packageName;
	}
	
	public String getClassName(){
		return className;
	}
	
	public void setPackageName(String packageName){
		this.packageName = packageName;
	}
	
	public void setClassName(String className){
		this.className = className;
	}
	
	private String packageName;
	
	private String className;
}
