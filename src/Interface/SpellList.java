package Interface;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	public JPanel CreateDictSearch() {
		JPanel dictSearch = new JPanel();
		dictSearch.setLayout(new BoxLayout (dictSearch, BoxLayout.Y_AXIS));

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

		dictSearch.add(searchField);
		dictSearch.add(pane);
		
		return dictSearch;
	}
	
	public JPanel CreateClassSearch() {
		JPanel classSearch = new JPanel();
		classSearch.setLayout(new BoxLayout (classSearch, BoxLayout.Y_AXIS));

		String[] classArray = spellBook.getClasses().toArray(new String[spellBook.getClasses().size()]);;
		
		
		
		final JComboBox<String> cb = new JComboBox<String>(classArray);

	    cb.setVisible(true);
    	JList searchList = new JList(model);
		
		searchList.setVisibleRowCount(-1);
		
	    JScrollPane pane = new JScrollPane(searchList);
		pane.setPreferredSize(new Dimension(300, 400));
		pane.setMaximumSize(pane.getPreferredSize());
		pane.setMinimumSize(pane.getPreferredSize());

		

		

		classSearch.add(cb);
		classSearch.add(pane);
		
		return classSearch;
	}
	
	/**
	 * Panel Containing Search text box and results
	 * 
	 * @return
	 */
	public JPanel setLeftSearch() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout (searchPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout (buttonPanel, BoxLayout.X_AXIS));
		
		JButton dictButton = new JButton("Spell Search");
		JButton classButton = new JButton("Class Spells");
		JPanel swapper = new JPanel(new CardLayout());
		
		ActionListener listen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(swapper.getLayout());
				String panelToSwap = arg0.getSource().equals(dictButton) ? "DICT" : "CLASS";
				cl.show(swapper, panelToSwap);
			}	
		};
		
		dictButton.addActionListener(listen);
		classButton.addActionListener(listen);
		
		buttonPanel.add(dictButton);
		buttonPanel.add(classButton);
		searchPanel.add(buttonPanel);
		
		
		swapper.add(CreateDictSearch(), "DICT");
		swapper.add(CreateClassSearch(), "CLASS");
		searchPanel.add(swapper);
		
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
