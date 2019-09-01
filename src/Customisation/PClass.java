package Customisation;

import org.json.simple.JSONObject;

import Dice.Dice;

public class PClass {
	private String Name;
	private Dice HitDie;
	private String[] SavingThrowProf;
	private String MultiPreReq;
	
	public PClass(String name, JSONObject data) {
		this.Name = name;
		HitDie = new Dice((String) data.get("Hit Die"));
		SavingThrowProf = ((String)data.get("Saving Throw Prof")).split(",");
		MultiPreReq = (String) data.get("Multiclass PreReq");	
	}
	
	public String GetName() {
		return Name;
	}
	
	public int AddHP(int level) {
		return level == 1 ? HitDie.GetMax() : HitDie.GetAvg();
	}
}
