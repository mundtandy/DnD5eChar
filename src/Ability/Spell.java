package Ability;

public class Spell extends Ability{
  private Long level;
  private String cTime, components, description, duration, range, school;
  private boolean ritual;

  public Spell(String name, String cTime, String components, String description, String duration, Long level, String range,
		  String school, boolean ritual) {
    super(name);
    this.cTime = cTime;
    this.components = components;
    this.description = description;
    this.duration = duration;
    this.level = level;
    this.range = range;
    this.school = school;
    this.ritual = ritual;
  }
  
 
  
}
