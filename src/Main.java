import Ability.*;
import Dice.Dice;
import Interface.Display;
import Parse.ReadJSON;

public class Main {
  public static void main(String args[]) {
   ReadJSON r = new ReadJSON();
   SpellBook testBook = new SpellBook();
   r.readSpells(testBook);
   
   
   testBook.showTree();
   
   
  }
}
