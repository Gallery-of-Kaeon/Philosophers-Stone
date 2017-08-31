package philosophers_stone;

import java.util.ArrayList;

public class PhilosophersStone implements Cloneable {
	
	public ArrayList<PhilosophersStone> publicConnections = new ArrayList<PhilosophersStone>();
	public ArrayList<PhilosophersStone> privateConnections = new ArrayList<PhilosophersStone>();
	
	public ArrayList<String> tags = new ArrayList<String>();
	
	public Object onCall(ArrayList<Object> packet) {
		return null;
	}
	
	public Object clone() {
		
		try {
			return super.clone();
		}
		
		catch (Exception exception) {
			return new PhilosophersStone();
		}
	}
}