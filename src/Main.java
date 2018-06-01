import Ability.*;
import Dice.Dice;

public class Main {
  public static void main(String args[]) {
    Dice d = new Dice();
    System.out.println(d.roll(5, 12));
    
    int[] test = d.rollDetail(5, 12);
    
    for(int i : test)
      System.out.print(i+" + ");
    
    Spell[] tests = new Spell[20];
    for(int i = 0; i < tests.length; i++) {
      tests[i] = new Spell("test", i+1, null, null, null, null, null, null, false, false);
      System.out.println(tests[i]);
    }
    
    
  }
}
