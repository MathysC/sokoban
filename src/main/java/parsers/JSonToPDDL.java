package parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class JSonToPDDL {
private FileReader reader;
private JSONParser parser;
private String mapName;
private boolean isTest;
private boolean isValidator;
private String mapModel;
private FileWriter writer;

public JSonToPDDL(String pathToFile) {
	parser = new JSONParser();
	
	try {
		reader = new FileReader(pathToFile);
		JSONObject temp = (JSONObject ) parser.parse(reader);
		
		mapName= temp.get("title").toString();
		mapModel = temp.get("testIn").toString();
		isTest= (temp.get("isTest").equals("true"));
		isValidator = (temp.get("isValidator").equals("true"));
		writer =new FileWriter("gf"+mapName+".pddl");
	} catch (IOException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(mapName+mapModel);
}

public void createPDDL() {
	ArrayList<String> map = new ArrayList<String>(Arrays.asList(mapModel.split("\n")));
	writer.append("(define ( problem "+mapName + " )");
	writer.append("(:domain SOKOBAN)");
	
	
}
public static void main(String[] args) {
	JSonToPDDL jst= new JSonToPDDL("test1.json");
}
}
