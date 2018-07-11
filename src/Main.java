import Ability.*;
import Dice.Dice;
import Interface.Display;
import Parse.ReadJSON;

public class Main {
  public static void main(String args[]) {
   ReadJSON r = new ReadJSON();
   r.readSpells();
   
   SpellBook testBook = new SpellBook();
   
   testBook.showTree();
   
   testBook.addSpell("Yes");
   testBook.addSpell("No");
   testBook.addSpell("Happy");
   testBook.addSpell("Spell");
   testBook.addSpell("Nin");
   testBook.addSpell("A book");
   testBook.addSpell("A car");
   
   
   testBook.showTree();
  }
}
