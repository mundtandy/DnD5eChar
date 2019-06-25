package Interface;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Ability.Spell;
import Ability.SpellBook;


public class SpellList extends JPanel {
	private SpellBook spellBook;
	private JEditorPane displaySpell; 
	private JTextField searchField;
	private DefaultListModel model; 
	
	
	public SpellList(SpellBook spellBook) {
		this.spellBook = spellBook;
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(setLeftSearch());
		this.add(setRightSearch());
	}
	
	/**
	 * Panel Containing Search text box and results
	 * 
	 * @return
	 */
	public JPanel setLeftSearch() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout (searchPanel, BoxLayout.Y_AXIS));
		
		searchField = new JTextField ();
		searchField.setPreferredSize(new Dimension(300, 30));
		searchField.setMaximumSize(searchField.getPreferredSize());
		searchField.setMinimumSize(searchField.getPreferredSize());

		TextPrompt tempText = new TextPrompt("Spell Name", searchField);
		tempText.changeAlpha(0.5f);
		model = new DefaultListModel();
		newSearch("");

		JList searchList = new JList(model);
		
		searchList.setVisibleRowCount(-1);
		
		searchList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	if(!searchList.isSelectionEmpty()) //prevents crashing from model.clear() below
                		setDisplayText(spellBook.displaySpell(searchList.getSelectedValue().toString()));
                }
            }
        });

		searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
			newSearch(searchField.getText());
		});
		
		JScrollPane pane = new JScrollPane(searchList);
		pane.setPreferredSize(new Dimension(300, 400));
		pane.setMaximumSize(pane.getPreferredSize());
		pane.setMinimumSize(pane.getPreferredSize());

		searchPanel.add(searchField);
		searchPanel.add(pane);
		
		return searchPanel;
	}
	
	public void newSearch(String text) {
		model.clear();
		
		List<Spell> fl = spellBook.GetSearch(text);
		if(fl.size() == 0) {
			model.addElement("Empty");
		} else {				
			for(Spell s: fl) {
				model.addElement(s.getName());
			}
		}
			
	}
	
	public void setDisplayText(String s) {
		displaySpell.setText(s);
		displaySpell.setCaretPosition(0); 
	}
	
	public JPanel setRightSearch() {
		JPanel displayPanel = new JPanel();
		displaySpell = new JEditorPane();
		displaySpell.setContentType("text/html");
		//displaySpell = new JTextArea();
		
		//displaySpell.setLineWrap(true);
		//displaySpell.setWrapStyleWord(true);
		displaySpell.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		
		JScrollPane displayPane = new JScrollPane(displaySpell);
		displayPane.setPreferredSize(new Dimension(400, 450));
		displayPane.setMaximumSize(displayPane.getPreferredSize());
		displayPane.setMinimumSize(displayPane.getPreferredSize());
		
		displayPanel.add(displayPane);
		
		return displayPanel;
		
	}
	
	/**
	public void spellSearchMode() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
	
		mainPanel.add(setLeftSearch());
		mainPanel.add(setRightSearch());
		frame.getContentPane().add(mainPanel);

		frame.pack();
	}**/
	

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
