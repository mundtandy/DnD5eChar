package Ability;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellBook {
	private Node spellTree;
	private List<SpellLevel> levels;
	private List<String> classes;
	
	public SpellBook() {
		spellTree = new Node('*');
		levels = new ArrayList<SpellLevel>();
		classes = new ArrayList<String>();
	}
	
	public void addSpell(Spell spell) {
		char index;
		Node currentNode = spellTree;
		currentNode.increment();
		String currSpell = spell.getName().toLowerCase();
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
	
	public List<String> getClasses(){
		return classes;
	}
	
	public void addClass(String s) {
		if(!classes.contains(s)) {
			classes.add(s);
		}
	}
	
	public List<SpellLevel> getLevelList(){
		return levels;
	}
	
	public void showTree() {
		System.out.println("Spells added: "+spellTree.getAccess());
		spellTree.displayTree("");
	}
	
	public String displaySpell(String s) {
		Node current = spellTree;
		String toSearch = s.toLowerCase();
		
		for(int i = 0; i < toSearch.length(); i++) {
			current = current.getNode(toSearch.charAt(i));
			
			if(current == null)
				return "Select a Spell";
		}
		
		return (current.getSpell() != null ? current.getSpell().toHTMLString() : "Select a Spell");
	}
	
	public List<Spell> GetSearch(String s){
		return ((s == null || s.length() == 0) ? getAll() : searchTree(s));
		
	}
	
	public void addSpellLevel(int i) {
		levels.add(new SpellLevel(i));
	}
	
	public List<Spell> getAll(){
		List<Spell> toReturn = new ArrayList<Spell>();
		
		for(char alphabet = 'a'; alphabet <='z'; alphabet++ )
		{
			toReturn.addAll(searchTree(Character.toString(alphabet)));
		}
		
		return toReturn;
	}
	
	public List<Spell> searchTree(String s){
		ArrayList<Spell> toReturn = new ArrayList<Spell>();
		Node current = spellTree;
		String toSearch = s.toLowerCase();
		//traverse Tree till end of s is reached, immediately returning empty list if that
		//letter does not exist as a child (meaning no Spell exists with this name)
		for(int i = 0; i < toSearch.length(); i++) {
			current = current.getNode(toSearch.charAt(i));
			
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

class MasterNode{
	private List<Node>[] nodeList;
	
	public MasterNode() {
		nodeList = new List[27];
		Arrays.setAll(nodeList,  ArrayList :: new);
	}
	
	public void archiveNode(Node n) {
		char letter = n.getLetter();
		if(!Character.isLetter(letter)) {
			nodeList[27].add(n);
		}
		else {
			int index = (int)Character.toLowerCase(letter) - 'a'+1;
			nodeList[index].add(n);
		}
	}
}

class SpellLevel{
	private int level;
	private List<Spell> spells;
	
	public SpellLevel(int level) {
		this.level = level;
		spells = new ArrayList<Spell>();
	}
	
	public void AddSpell(Spell s) {
		
		for(int i = 0; i < spells.size(); i ++) {
			if(spells.get(i).name.compareTo(s.name) < 0){
				spells.add(i, s);
				return;
			}
		}
		spells.add(s);
	}
	
	public int GetLevel() {
		return level;
	}
	
	public List<Spell> getSpells(){
		return spells;
	}
	
	public List<Spell> getSpellsByClass(String className){
		List<Spell> toReturn = new ArrayList<Spell>();
		for(int i = 0; i < spells.size(); i++) {
			if(spells.get(i).ClassHas(className)) {
				toReturn.add(spells.get(i));
			}
		}
		
		return toReturn;
	}
}

class Node{
	private char letter;
	private List<Node> children;
	private Spell spell;
	private int access;
	
	public Node(char c) {
		letter = Character.toLowerCase(c);
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
