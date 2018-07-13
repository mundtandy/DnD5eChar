package Ability;
import java.util.ArrayList;
import java.util.List;

public class SpellBook {
	private Node spellTree;
	
	public SpellBook() {
		spellTree = new Node('*');
	}
	
	public void addSpell(Spell spell) {
		char index;
		Node currentNode = spellTree;
		currentNode.increment();
		String currSpell = spell.getName();
		for(int i = 0; i < currSpell.length(); i++) {
			index = currSpell.charAt(i);
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
	
	public List<Spell> searchTree(String s){
		ArrayList<Spell> toReturn = new ArrayList<Spell>();
		Node current = spellTree;
		
		//traverse Tree till end of s is reached, immediately returning empty list if that
		//letter does not exist as a child (meaning no Spell exists with this name)
		for(int i = 0; i < s.length(); i++) {
			current = current.getNode(s.charAt(i));
			
			if(current == null)
				return toReturn;
		}
		
		addFound(current, toReturn);
		
		return toReturn;
		
	}
	
	public void addFound(Node n, List<Spell> toReturn) {
		if(n.getSpell() != null)
			toReturn.add(n.getSpell());
		
		for(Node childN : n.getChildren()) {
			addFound(childN, toReturn);
		}
	}
	
}

class Node{
	private char letter;
	private List<Node> children;
	private Spell spell;
	private int access;
	
	public Node(char c) {
		letter = c;
		children = new ArrayList<Node>();
		spell = null;
		access = 0;
	}
	
	public Spell getSpell() {
		return spell;
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
	
	public List<Node> getChildren() {
		return children;
	}
	
	public void addSpell(Spell spell) {
		this.spell = spell;
	}
	
	public void displayTree(String spacing) {
		System.out.println(spacing+getLetter());
		if(spell != null)
			System.out.println(spacing+spell.getName());
		for(Node n: children)
			n.displayTree(spacing+"  ");
	}
}
