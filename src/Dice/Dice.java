package Dice;

import java.util.Random;

public class Dice {
  private static Random rand;
  
  public Dice() {
    rand = new Random();
  }
  
  public int roll(int number, int type) {
    int total = 0;
    
    for(int i = 0; i < number; i++) {
      total += rand.nextInt(type) + 1;
    }
    
    return total;
  }
  
  public int[] rollDetail(int number, int type) {
    int[] total = new int[number];
    
    for(int i = 0; i < number; i++) {
      total[i] = rand.nextInt(type) + 1;
    }
    
    return total;
  }
}
