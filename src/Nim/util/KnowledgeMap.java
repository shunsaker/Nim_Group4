package Nim.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.Board;
import model.Percent;

public class KnowledgeMap implements Serializable{
	private static final String SAVE_PATH = "/knowledgeMap.ser";
	private static final String PERFECT_PATH = "perfectAI.ser";
	private static final long serialVersionUID = 1;
	private static boolean allowExperimentation = false;
	private static boolean usePerfectAI = false;
	private static final double LOW_PERCENT_THRESHOLD = .0001;
	
	private Map<Board, Percent> knowledgeMap = new HashMap<Board, Percent>();
	private static KnowledgeMap masterMap;
	private int totalBoardStatesCounted = 0;
	
	private KnowledgeMap(){};
	
	public static void setAI(boolean usePefrect) {
		usePerfectAI = usePefrect;
		if(usePerfectAI) {
			allowExperimentation = false;
		}
		else {
			masterMap = new KnowledgeMap();
		}
	}
	
	public static void toggleExperimentation() {
		allowExperimentation = !allowExperimentation;
	}
	
	public static boolean isExperimentationAllowed() {
		return allowExperimentation;
	}
	
	public static boolean isUsingPerfectAI() {
		return usePerfectAI;
	}
	
	public static KnowledgeMap getInstance() {
		if(masterMap == null) {
			masterMap = new KnowledgeMap();
		}
		return masterMap;
	}
	
	public void add(Board board, int value) {
		assert(board != null);
		totalBoardStatesCounted++;
		Percent p = knowledgeMap.get(board);
		if(p == null) {
			p = new Percent(value, 1);
			knowledgeMap.put(board, p);
		}
		else {
			if(value > 0) {
				p.incrementWin();
			}
			else {
				p.incrementTotal();
			}
		}
	}
	
	public double getValue(Board board) {
		Percent p = knowledgeMap.get(board);
		double percent = 1.1;
		if(p != null && (allowExperimentation ? (double) p.getTotal() / totalBoardStatesCounted > LOW_PERCENT_THRESHOLD : true)) {
			percent = p.getPercent();
		}
		return percent;
	}
	
	public static void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(getPath());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(masterMap);
	         out.close();
	         fileOut.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try{
			FileInputStream fileIn = new FileInputStream(getPath());
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        masterMap = (KnowledgeMap) in.readObject();
	        in.close();
	        fileIn.close();
	    }
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
	    }
	}
	
	public static String getPath() {
		return usePerfectAI ? PERFECT_PATH : SAVE_PATH;
	}
}
