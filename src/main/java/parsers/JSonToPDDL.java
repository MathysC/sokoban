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
private ArrayList<String> floorPlan;
private ArrayList<String> goal;
private ArrayList<String> boxes;
private String guardPosition;

public JSonToPDDL(String pathToFile) {
	parser = new JSONParser();
	
	try {
		reader = new FileReader(pathToFile);
		JSONObject temp = (JSONObject ) parser.parse(reader);
		floorPlan= new ArrayList<String> ();
		goal=new ArrayList<String>();
		boxes=new ArrayList<String>();
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

public void createHeader() throws IOException {
	ArrayList<String> map = new ArrayList<String>(Arrays.asList(mapModel.split("\n")));
	writer.append("(define ( problem "+mapName + " )");
	writer.append("(:domain SOKOBAN)\n");
	writer.append("(:objects\n");
}
public void createObjects() throws IOException{
	boolean betweenWalls=false;
	int row=1;
	int cell=1;
	int destNumber=1;
	char[] arrayMap=new char[mapModel.length()];
	mapModel.getChars(0, mapModel.length(), arrayMap, 0);
	for (int i =0 ; i<mapModel.length(); i++) {
		if(arrayMap[i]=='#') 
			betweenWalls=!betweenWalls;
		if (betweenWalls) {
		switch (arrayMap[i])
		{
		case ' ':
			writer.append("f"+row+cell+"- floor\n");
			floorPlan.add("f"+row+cell);
			cell++;
			break;
		case '.':
			writer.append("f"+row+cell+"- floor\n");
			floorPlan.add("f"+row+cell);
			goal.add("f"+row+cell+" ");
			writer.append("Dest"+destNumber+"- destination\n");
			destNumber++;
			cell++;
			break;
		case '@':
			writer.append("f"+row+cell+"- floor\n");
			guardPosition="f"+row+cell;
			cell++;
			break;
		}
		}
		
	}
}

public void createPDDL() {
	try {
		createHeader();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public static void main(String[] args) {
	JSonToPDDL jst= new JSonToPDDL("test1.json");
}
}
