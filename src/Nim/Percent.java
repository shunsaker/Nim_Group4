package Nim;

import java.io.Serializable;

public class Percent implements Serializable{

	private static final long serialVersionUID = 1;
	private int total, wins;
	
	public Percent(){}
	public Percent(int wins, int total) {
		this.total = total;
		this.wins = wins;
	}
	
	public double getPercent() {
		return (double)wins / total;
	}
	
	public void incrementWin() {
		incrementTotal();
		wins++;
	}
	
	public void incrementTotal() {
		total++;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getWins() {
		return wins;
	}
	
	@Override
	public String toString() {
		return wins + "/" + total;
	}
}
