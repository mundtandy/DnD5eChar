package Customisation;
public class Skill {
  private String name;
  private int aScore;
  private String description;
  
  public Skill(String name, int aScore, String description) {
    this.name = name;
    this.aScore = aScore;
    this.description = description;
  }
  
  public String toString() {
    return name+" ("+getScore()+")\n"+description;
  }
  
  private String getScore() {
    switch(aScore) {
      case(0):
        return "Strength";
      case(1):
        return "Dexterity";
      case(2):
        return "Constitution";
      case(3):
        return "Intelligence";
      case(4):
        return "Wisdom";
      case(5):
        return "Charisma";
      default:
        return null;
    }
  }
}
