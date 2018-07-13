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
			JSONObject listJ = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("classspells.json"), "UTF-8"));
			
			String temp;
			JSONObject listAr;
			String test;
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
				//System.out.println(listAr.get("duration"));
			}
			/*
			JSONObject listAr = (JSONObject) spellsJ.get("Drawmij’s Instant Summons");
			String test = listAr.toString();
			System.out.println(test.substring(1, test.length() - 1).split("\",\"").length);
			
			/*"duration":"Until dispelled",
			"components":"V, S, M (a sapphire worth 1,000 gp)",
			"level":6,
			"school":"Conjuration",
			"ritual":true,
			"description":"You touch an object weighing 10 pounds or less whose longest dimension is 6 feet or less. The spell leaves an invisible mark on its surface and invisibly inscribes the name of the item on the sapphire you use as the material component. Each time you cast this spell, you must use a different sapphire.\n   At any time thereafter, you can use your action to speak the item\u2019s name and crush the sapphire. The item instantly appears in your hand regardless of physical or planar distances, and the spell ends.\n   If another creature is holding or carrying the item, crushing the sapphire doesn\u2019t transport the item to you, but instead you learn who the creature possessing the object is and roughly where that creature is located at that moment.\n   Dispel magic or a similar effect successfully applied to the sapphire ends this spell\u2019s effect.",
			"range":"Touch",
			"casting_time":"1 minute"*/
			
			
			//System.out.println(listAr.get(0));
			//Iterator<JSONArray> iterator = listAr.iterator();
			
			//System.out.println(iterator.next());
			
			//System.out.println(listJ.get("Druid"));
			

		} catch(ParseException | IOException e) {
			e.printStackTrace();

		}
	}
}

