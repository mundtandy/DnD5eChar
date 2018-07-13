package Parse;

import java.io.FileInputStream;
//TODO: https://stackoverflow.com/questions/22137333/read-json-multi-dimensional-array-in-java
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.parser.ParseException;

import Ability.Spell;
import Ability.SpellBook;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
	
	public void readSpells(SpellBook book) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject spellsJ = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("spells.json"), "UTF-8"));
			//JSONObject listJ = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("classspells.json"), "UTF-8"));
			
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

