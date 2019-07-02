package Parse;

import java.io.FileInputStream;
//TODO: https://stackoverflow.com/questions/22137333/read-json-multi-dimensional-array-in-java
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;

import Ability.Spell;
import Ability.SpellBook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
	public boolean testSpellList(SpellBook book) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject spellList = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("classspells.json")));
			
			String className;
			JSONArray classList;
			String spellArrayString;
			//List<String> spells;
			boolean missing = false;
			for(Object o : spellList.keySet()) {
				className = (String)o;
				book.addClass(className);
				classList = (JSONArray) spellList.get(className);
				for(int i = 0; i < classList.size(); i ++) {
					if(book.getLevelList().size() < i+1) {
						book.addSpellLevel(i);
					}
					spellArrayString = StripChars(classList.get(i).toString());
					
					if(!spellArrayString.equals("")) {
				    	for(String s : spellArrayString.split(",")) {
							if(book.displaySpell(StripChars(s)).equals("Select a Spell")) {
								missing = true;
								System.out.println("MISSING! "+className+": "+i+": "+StripChars(s));
							}
							else {
								book.GetSearch(StripChars(s)).get(0).addClass(className);
								book.addSpellToLevel(i,book.GetSearch(StripChars(s)).get(0));
							}
						}
						
					}
				}
				
			}
			if(!missing) {
				System.out.println("No spells missing in classspells.json");
				
				
			}
			
			return missing;
		} catch(ParseException | IOException e) {
			e.printStackTrace();

		}
		return false;
		
	}
		
	public String StripChars(String line) {
		if(line.length() == 2) {
			return "";
		}
		return line.substring(1, line.length()-1).replace("\\/", "/"); //yay escape characters! 
	}
	
	
	
	public void readSpells(SpellBook book) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject spellsJ = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("spells.json"), "UTF-8"));
			
			String temp;
			JSONObject listAr;
			Spell tempSpell;
			
			for(Object o : spellsJ.keySet()) {
				temp = (String) o;
				listAr = (JSONObject) spellsJ.get(temp);
				tempSpell = new Spell(temp, 
						(String) listAr.get("casting_time"), 
						(String) listAr.get("components"),
						(String) listAr.get("description"),
						(String) listAr.get("duration"),
						(Long) listAr.get("level"),
						(String) listAr.get("range"),
						(String) listAr.get("school"),
						(boolean) listAr.get("ritual"));
				book.addSpell(tempSpell);
			}		

		} catch(ParseException | IOException e) {
			e.printStackTrace();

		}
	}
}

