import java.awt.List;
import java.util.ArrayList;

import Ability.*;
import Customisation.PClass;
import Dice.Dice;
import Interface.Display;
import Parse.ReadJSON;

public class Main {
  public static void main(String args[]) {
   ReadJSON r = new ReadJSON();
   SpellBook testBook = new SpellBook();
   r.readSpells(testBook);
   r.testSpellList(testBook);
   ArrayList<PClass> classes =  r.readClasses();
   
   PClass found = classes.get(5);
   
   System.out.println("Class: "+found.GetName());
   int HP = 0;
   for(int i = 1; i < 21; i ++) {
	   HP += found.AddHP(i);
	   System.out.println("Level: "+i+". HP now: "+HP);
   }
   //testBook.showTree();
   
   //for(Spell s : testBook.searchTree("An"))
	//   System.out.println(s.getName());
   
   
   Display testshow = new Display(testBook);
   
  }
  
  
}
