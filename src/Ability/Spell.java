package Ability;

import java.util.ArrayList;
import java.util.List;

public class Spell extends Ability{
	private int level;
	private String cTime, components, description, duration, range, school;
	private boolean ritual;
	private String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	private List<String> classes;
	
	public Spell(String name, String cTime, String components, String description, String duration, Long level, String range,
			String school, boolean ritual) {
		super(name);
		this.cTime = cTime;
		this.components = components;
		this.description = description;
		this.duration = duration;
		this.level = level.intValue();
		this.range = range;
		this.school = school;
		this.ritual = ritual;
		classes = new ArrayList<String>();
	}

	public String toString() {
		return name+"\n"
				+ (level == 0 ? "Cantrip, " : getOrdinal() + " level ") + school + (ritual ? " (ritual)" : "") + "\n\n"
				+ "Casting Time: " + cTime + "\n"
				+ "Range: " + range + "\n"
				+ "Components: " + components + "\n"
				+ "Duration: " + duration + "\n"
				+ "Classes: " + formatClasses() + "\n\n"
				
				+ description;
	}
	
	public String toHTMLString() {
		return "<b>" + name + "</b><br>"
				+ "<i>" + (level == 0 ? "Cantrip, " : getOrdinal() + " level ") + school + (ritual ? " (ritual)" : "") + "</i><br><br>"
				+ "Casting Time: " + cTime + "<br>"
				+ "Range: " + range + "<br>"
				+ "Components: " + components + "<br>"
				+ "Duration: " + duration + "<br>"
				+ "Classes: " + formatClasses() + "<br><br>"
				+ formatDescription(description);
	}
	
	public String formatDescription(String s) {
		String newDescr = s;
		
		newDescr = newDescr.replaceAll("[ ]{2,}", " ");
		newDescr = newDescr.replaceAll(". At Higher Levels.", ".<br>At Higher Levels.");
		newDescr = newDescr.replaceAll("At Higher Levels.", "<b>At Higher Levels.</b>");
		newDescr = newDescr.replaceAll("\n", "<br>");
		
		return newDescr;
	}
	
	public void addClass(String c) {
		classes.add(c);
	}
	
	public String formatClasses() {
		if(classes.isEmpty())
			return "None.";
		String toReturn = "";
		for (String c : classes){
			toReturn += c +",";
		}
		return toReturn.substring(0, toReturn.length()-1);
	}
	
	public String getOrdinal() {
		switch (level % 100) {
		    case 11:
		    case 12:
		    case 13:
		        return level + "th";
		    default:
		        return level + sufixes[level % 10];

		    }
	}

}
