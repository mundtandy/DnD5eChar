package Parse;

//TODO: https://stackoverflow.com/questions/22137333/read-json-multi-dimensional-array-in-java
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
	public void readSpells() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject spellsJ = (JSONObject) parser.parse(new FileReader("spells.json"));
			JSONObject listJ = (JSONObject) parser.parse(new FileReader("classspells.json"));
			
			JSONArray listAr = (JSONArray) listJ.get("Druid");
			
			System.out.println(listAr.get(0));
			Iterator<JSONArray> iterator = listAr.iterator();
			
			System.out.println(iterator.next());
			
			System.out.println(listJ.get("Druid"));
			

		} catch(ParseException | IOException e) {
			e.printStackTrace();

		}
	}
}

