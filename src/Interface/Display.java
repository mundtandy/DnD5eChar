package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
		searchPanel.setLayout(new BoxLayout (searchPanel, BoxLayout.Y_AXIS));
		//searchPanel.setPreferredSize(new Dimension(300, 500));

		JTextField searchField = new JTextField ();
		searchField.setPreferredSize(new Dimension(300, 30));
		searchField.setMaximumSize(searchField.getPreferredSize());
		searchField.setMinimumSize(searchField.getPreferredSize());

		TextPrompt tempText = new TextPrompt("Spell Name", searchField);
		tempText.changeAlpha(0.5f);
		DefaultListModel model = new DefaultListModel();
		model.addElement("Empty");

		JList searchList = new JList(model);
		searchList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		searchList.setVisibleRowCount(-1);
		

		searchField.getDocument().addDocumentListener((SimpleDocumentListener) e -> {
			model.clear();
			List<Spell> fl = spellBook.searchTree(searchField.getText());
			if(searchField.getText().length() == 0 || fl.size() == 0) {
				model.addElement("Empty");
			} else {
				for(Spell s: fl) {
					model.addElement(s.getName());
				}
				System.out.println(model.getSize());

			}
			//jl.setText(searchField.getText());
		});
		JScrollPane pane = new JScrollPane(searchList);
		pane.setPreferredSize(new Dimension(300, 400));
		pane.setMaximumSize(pane.getPreferredSize());
		pane.setMinimumSize(pane.getPreferredSize());


		searchPanel.add(searchField);
		searchPanel.add(pane);

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
