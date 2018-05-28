package Options;
import java.util.List;

import Dice.Dice;

public class Background {
  private String name, description, type;
  //type = homebrew, or original location of background (Player's Handbook, Basic Rules etc) 
  //tags = as used on DND Beyond
  
  private List<Skill> proficiencies;
  private String[] feature;
  private List<String> tags, languages, personality, ideal, bond, flaw;

  public Background(String name, String desc, String type, List<String> tags, 
      List<Skill> profs, List<String> lang, String[] feature,
      List<String> pers, List<String> ideal, List<String> bond, List<String> flaw) {
    this.name = name;
    this.description = desc;
    this.type = type;
    this.tags = tags;
    this.proficiencies = profs;
    this.languages = lang;
    this.feature = feature;
    this.personality = pers;
    this.ideal = ideal;
    this.bond = bond;
    this.flaw = flaw;
  }
  
  /**
   * Used if someone wishes to select randomly from a list of their background 
   * characteristics.
   * 
   * @param c Refers to the particular list
   * 
   * @return the randomly selected entry of that list
   */
  public String getDescrip(char c) {
    List<String> descripList;
    
    switch(c) {
      case 'p':
        descripList = personality;
        break;
      case 'i':
        descripList = ideal;
        break;
      case 'b':
        descripList = bond;
        break;
      case 'f':
        descripList = flaw;
        break;
      default:
        return null;
    }
    
    Dice d = new Dice();
    
    return descripList.get(d.roll(1, descripList.size()));
  }
  
}
