import java.util.ArrayList;
import java.util.List;

import Customisation.*;

public class Character {  
  private String name;
  private int level, experience, speed;
  private char alignment, size;
  private List<String> languages;
  
  private Race race;
  private Background background;
  /*
   * TODO: What about multiclass?
   * 
   * Perhaps 2 arraylists
   * List<PClass> classes and List<Integer> classLevels
   * 
   * When you level up, you choose a class to put a point into and get any
   * current level in that class + 1 class features? 
   */
  private List<PClass> pClass;
  private List<Integer> classLevels;
    
  private int[] AbilityScore;
  
  //TODO: better method for prof/expeterise
  private List<Skill> proficiency, expertise;
  private List<Feat> feats;
    
  public Character(String name) {
    this.name = name;
    level = 1;
    experience = 0;
    
    pClass = new ArrayList<PClass>();
    classLevels = new ArrayList<Integer>();
    
    proficiency = new ArrayList<Skill>();
    expertise = new ArrayList<Skill>();
    languages = new ArrayList<String>();
    feats = new ArrayList<Feat>();
  }
  
  public void levelUp() {
    //need to check experience >= current level exp+1's experience
    level++;
  }
  
  public void addExp(int exp) {
    experience+= exp;
  }
  
  public void addClass(PClass newClass) {
    int classIndex = pClass.indexOf(newClass);
    
    if(classIndex == -1) { //either creating new class or choosing a multiclass
      pClass.add(newClass);
      classLevels.add(1);
      //addFeatures(newClass.getLevel(1));
    } else {
      int newLvl = classLevels.get(classIndex) + 1;
      classLevels.set(classIndex, newLvl);
      //addFeatures(newClass.getLevel(newLvl));
    }
  }
  
  
  //Setters 
  public void setRace(Race race) {
    this.race = race;
  }
    
  public void setBackground(Background bg) {
    this.background = bg;
  }
  
  public void setAlign(char a) {
    this.alignment = a;
  }
  
  public void setSize(char s) {
    this.size = s;
  }
  
  public void setSpeed(int speed) {
    this.speed = speed;
  }
}
