import Dice.Dice;

public class Main {
  public static void main(String args[]) {
    Dice d = new Dice();
    System.out.println(d.roll(5, 12));
    
    int[] test = d.rollDetail(5, 12);
    
    for(int i : test)
      System.out.print(i+" + ");
  }
}