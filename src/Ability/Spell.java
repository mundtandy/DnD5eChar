package Ability;

public class Spell extends Ability{
	private int level;
	private String cTime, components, description, duration, range, school;
	private boolean ritual;
	private String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };

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
	}

	public String toString() {
		return name+"\n"
				+ (level == 0 ? "Cantrip, " : getOrdinal() + " level ") + school + (ritual ? " (ritual)" : "") + "\n\n"
				+ "Casting Time: " + cTime + "\n"
				+ "Range: " + range + "\n"
				+ "Components: " + components + "\n"
				+ "Duration: " + duration + "\n\n"
				+ description;
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
