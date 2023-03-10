package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
		File file= new File(mapName);
		file.createNewFile();
		writer =new FileWriter(file,true);
		
		
	} catch (IOException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(mapName+mapModel);
}

public void createHeader() throws IOException {
	FileOutputStream fileout= new FileOutputStream("gf.pddl");
	
	ArrayList<String> map = new ArrayList<String>(Arrays.asList(mapModel.split("\n")));
	writer.append("(define ( problem "+mapName + " )");
	writer.append("(:domain SOKOBAN)\n");
	writer.append("(:objects\n");
	writer.flush();
}
public void createObjects() throws IOException{
	//code qui fait une arraylist de string nommé map faut changer le code pour iterer dessus
	ArrayList<String> map = new ArrayList<String>(Arrays.asList(mapModel.split("\n")));

	boolean betweenWalls=true;
	int row=1;
	int cell=1;
	int destNumber=1;
	int boxNumber=1;
	char[] arrayMap=new char[mapModel.length()];
	mapModel.getChars(0, mapModel.length(), arrayMap, 0);
	for (int i =0 ; i<map.size(); i++) {
		row++;
		cell=1;
		for(int j=0;j<map.get(i).length();j++) {
//		if(map.get(i).charAt(j)=='#' && (j==0 || j == map.get(i).length()-1)) 
//			betweenWalls=!betweenWalls;
		if (betweenWalls) {
		switch (map.get(i).charAt(j))
		{
		case ' ':
			writer.append("f"+row+cell+" - floor\n");
			floorPlan.add("f"+row+cell);
			cell++;
			break;
		case '.':
			writer.append("f"+row+cell+" - floor\n");
			floorPlan.add("f"+row+cell);
			goal.add("f"+row+cell+" ");
			writer.append("Dest"+destNumber+" - destination\n");
			destNumber++;
			cell++;
			break;
		case '@':
			writer.append("f"+row+cell+" - floor\n");
			floorPlan.add("f"+row+cell);
			guardPosition="f"+row+cell;
			cell++;
			break;
		case '+':
			writer.append("f"+row+cell+" - floor\n");
			guardPosition="f"+row+cell;
			floorPlan.add("f"+row+cell);
			goal.add("f"+row+cell+" ");
			writer.append("Dest"+destNumber+" - destination\n");
			destNumber++;
			cell++;
			break;
		case '$':
			writer.append("f"+row+cell+" - floor\n");
			floorPlan.add("f"+row+cell);
			writer.append("b"+boxNumber+" - box\n");
			boxes.add("f"+row+cell);
			boxNumber++;
			cell++;
			break;
		case '*':
			writer.append("f"+row+cell+" - floor\n");
			floorPlan.add("f"+row+cell);
			writer.append("b"+boxNumber+" - box\n");
			boxes.add("f"+row+cell);
			goal.add("f"+row+cell+" ");
			writer.append("Dest"+destNumber+" - destination\n");
			destNumber++;
			boxNumber++;
			cell++;
			break;
		case '#':
			cell++;
			break;
		default:
			break;
		}
		}
		}
		
	}
	writer.append(")\n");
}

public void createInit() throws IOException {
	writer.append("(:init\n");
	String cell;
	String toCompare;
	for (int i=0;i<floorPlan.size();i++) {
		cell=floorPlan.get(i);
		if(boxes.contains(cell)) {
			writer.append("(on b"+boxes.size()+" "+cell+")\n(withbox "+cell+")\n");
			boxes.remove(cell);
		}
		if(!boxes.contains(cell)&&!goal.contains(cell) && !guardPosition.equals(cell))
			writer.append("(empty "+cell+")\n");
		if(guardPosition.equals(cell))
			writer.append("on g "+cell);
		int right=Integer.parseInt(cell.substring(1));
		int down=right+10;
		right++;
		toCompare="f"+right;
		if(floorPlan.contains(toCompare))
			writer.append("(path "+cell+" "+toCompare+" r)\n");
		toCompare="f"+down;
		if(floorPlan.contains(toCompare))
			writer.append("(path "+cell+" "+toCompare+" d)\n");
		
	}
	writer.append(")\n");
}

public void createGoal() throws IOException{
	writer.append("  (:goal (and\n");
	for(String d : goal) {
		writer.append("(withbox "+d+")\n");
	}
	writer.append("\n    ))\n)");
}

public void createPDDL() {
	try {
		createHeader();
		createObjects();
		createInit();
		createGoal();
		writer.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public static void main(String[] args) {
	JSonToPDDL jst= new JSonToPDDL("test1.json");
	jst.createPDDL();
	
}
}
