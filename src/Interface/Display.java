package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Ability.Spell;
import Ability.SpellBook;

public class Display {
	private SpellBook spellBook;
	private JFrame frame;
	
	public Display(SpellBook sb) {
		this.spellBook = sb;
		frame = new JFrame("5e Character");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void show() {
  	    
	    //...create emptyLabel...
	    
	    //4. Size the frame.
	    frame.pack();
	
	    //5. Show it.
	    
	}
	
	public void spellSearchMode() {
		JPanel searchPanel = new JPanel();
		JTextField searchField = new JTextField ();
		TextPrompt tempText = new TextPrompt("Spell Name", searchField);
		tempText.changeAlpha(0.5f);
		JTextArea jl = new JTextArea(6,1);
	
		
		searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
			if(searchField.getText().length() == 0) {
			
				jl.setText("");
			} else {
				updatefound(spellBook.searchTree(searchField.getText()), jl);
			}
			//jl.setText(searchField.getText());
		});
		
		searchField.setPreferredSize( new Dimension( 200, 24 ) );
		jl.setPreferredSize( new Dimension( 200, 200 ) );
		
		searchPanel.add(searchField);
		searchPanel.add(jl);
		
		frame.getContentPane().add(searchPanel);
		
		frame.pack();
	}
	public void updatefound(List<Spell> spells, JTextArea jl) {
		if(spells.size() > 0) {
			for(int i = 0; i < spells.size(); i++) {
				if(i == 0) {
					jl.setText(spells.get(i).getName()+"\n");
				}
				else if(i < 10) {
					jl.append(spells.get(i).getName()+"\n");
				}
				else if (i == 10){
					jl.append("...");
					break;
				}
			}	
			
		}
		else
			jl.setText("None found");
	}
	
	public interface SimpleDocumentListener extends DocumentListener {
	    void update(DocumentEvent e);

	    @Override
	    default void insertUpdate(DocumentEvent e) {
	        update(e);
	    }
	    @Override
	    default void removeUpdate(DocumentEvent e) {
	        update(e);
	    }
	    @Override
	    default void changedUpdate(DocumentEvent e) {
	        update(e);
	    }
	}
}
