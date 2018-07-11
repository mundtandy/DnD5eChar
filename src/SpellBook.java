import java.util.ArrayList;
import java.util.List;

public class SpellBook {
	private Node spellTree;
	
	public SpellBook() {
		spellTree = new Node('*');
	}
	
	public void addSpell(String spell) {
		char index;
		Node currentNode = spellTree;
		currentNode.increment();
		for(int i = 0; i < spell.length(); i++) {
			index = spell.charAt(i);
			Node temp = currentNode.getNode(index);
			if(temp == null) {
				temp = new Node(index);
				currentNode.addChild(temp);
			}
			currentNode=temp;
		}
		currentNode.addSpell(spell);
	}
	
	public void showTree() {
		System.out.println("Spells added: "+spellTree.getAccess());
		spellTree.displayTree("");
	}
	
}

class Node{
	private char letter;
	private List<Node> children;
	private String spell;
	private int access;
	
	public Node(char c) {
		letter = c;
		children = new ArrayList<Node>();
		spell = null;
		access = 0;
	}
	
	public int getAccess() {
		return access;
	}
	
	public void increment() {
		access++;
	}
	
	public char getLetter() {
		return letter;
	}
	
	public Node getNode(char c) {
		for(Node n : children) {
			if(n.getLetter() == c)
				return n;
		}
		return null;
	}
	
	public void addChild(Node n) {
		if(children.isEmpty())
			children.add(n);
		else {
			for(int i = 0; i < children.size(); i++) {
				if(children.get(i).getLetter() > n.getLetter()) {
					children.add(i, n);
					return;
				}
			}
			children.add(n);
		}
	}
	
	public void addSpell(String spell) {
		this.spell = spell;
	}
	
	public void displayTree(String spacing) {
		System.out.println(spacing+getLetter());
		if(spell != null)
			System.out.println(spacing+spell);
		for(Node n: children)
			n.displayTree(spacing+"  ");
	}
}
