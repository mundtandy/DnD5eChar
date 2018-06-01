package Ability;

public class Spell extends Ability{
  private int level;
  private String school, range, castingTime, components, source, description;
  private boolean ritual, concentration;

  public Spell(String name, int lvl, String scl, String rng, String cstTm, String cmps, String src, String desc,
      boolean ritl, boolean conc) {
    super(name);
    level = lvl; 
    school = scl;
    range = rng;
    castingTime = cstTm;
    components = cmps;
    source = src;
    description = desc;
    ritual = ritl;
    concentration = conc;
  }
  
  public String toString() {
    return name +
        "\n"+ordinal(level);
  }
  
  String ordinal(int num)  {
      String[] suffix = {"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
      int m = num % 100;
      return String.valueOf(num) + suffix[(m > 3 && m < 21) ? 0 : (m % 10)];
  }
}
