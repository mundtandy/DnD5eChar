package Interface;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Ability.SpellBook;

public class Display {
	private SpellBook spellBook;
	private JFrame frame;
	private JPanel panelStore, spellSearch;

	public Display(SpellBook sb) {
		frame = new JFrame("5e Character");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		panelStore = new JPanel(new CardLayout());
		
		createPanels(sb);
		
		frame.getContentPane().add(spellSearch);

		frame.pack();
	}
	
	public void createPanels(SpellBook spellBook) {
		spellSearch = new SpellList(spellBook);
		panelStore.add(spellSearch);
	}
	
	

	
}
