package philosophers_stone;

import java.util.ArrayList;
import java.util.Hashtable;

public class PhilosophersStoneUtilities {
	
	public static void tag(PhilosophersStone stone, String tag) {
		
		tag = formatTag(tag);
		
		if(!isTagged(stone, tag))
			stone.tags.add(tag);
	}
	
	public static void removeTag(PhilosophersStone stone, String tag) {
		
		tag = formatTag(tag);
		
		for(int i = 0; i < stone.tags.size(); i++) {
			
			if(stone.tags.get(i).equals(tag)) {
				
				stone.tags.remove(i);
				
				return;
			}
		}
	}
	
	public static ArrayList<String> getTags(PhilosophersStone stone) {
		return stone.tags;
	}
	
	public static boolean isTagged(PhilosophersStone stone, String tag) {
		
		tag = formatTag(tag);
		
		for(int i = 0; i < stone.tags.size(); i++) {
			
			if(formatTag(stone.tags.get(i)).equals(tag))
				return true;
		}
		
		return false;
	}

	public static void publiclyConnect(PhilosophersStone stone, PhilosophersStone connection) {
	
		if(isPubliclyConnected(stone, connection))
			return;
	
		stone.publicConnections.add(connection);
	}

	public static void privatelyConnect(PhilosophersStone stone, PhilosophersStone connection) {
	
		if(isPrivatelyConnected(stone, connection))
			return;
	
		stone.privateConnections.add(connection);
	}
	
	public static void publiclyConnectMutually(PhilosophersStone stone, PhilosophersStone connection) {
		publiclyConnect(stone, connection);
		publiclyConnect(connection, stone);
	}
	
	public static void privatelyConnectMutually(PhilosophersStone stone, PhilosophersStone connection) {
		privatelyConnect(stone, connection);
		privatelyConnect(connection, stone);
	}
	
	public static void disconnect(PhilosophersStone stone, PhilosophersStone connection) {
	
		int index = getIndexOfPublicConnection(stone, connection);
	
		if(index != -1) {
	
			stone.publicConnections.remove(index);
	
			return;
		}
	
		index = getIndexOfPrivateConnection(stone, connection);
	
		if(index != -1) {
	
			stone.privateConnections.remove(index);
	
			return;
		}
	}
	
	public static void disconnectMutually(PhilosophersStone stone, PhilosophersStone connection) {
		disconnect(stone, connection);
		disconnect(connection, stone);
	}
	
	public static void destroy(PhilosophersStone stone) {
		
		for(int i = 0; i < stone.publicConnections.size(); i++) {
			
			disconnectMutually(stone, stone.publicConnections.get(i));
			
			i--;
		}
		
		for(int i = 0; i < stone.privateConnections.size(); i++) {
			
			disconnectMutually(stone, stone.privateConnections.get(i));
			
			i--;
		}
	}
	
	public static boolean isConnected(PhilosophersStone stone, PhilosophersStone connection) {
		return isPubliclyConnected(stone, connection) || isPrivatelyConnected(stone, connection);
	}
	
	public static boolean isPubliclyConnected(PhilosophersStone stone, PhilosophersStone connection) {
	
		for(int i = 0; i < stone.publicConnections.size(); i++) {
	
			if(stone.publicConnections.get(i) == connection)
				return true;
		}
	
		return false;
	}
	
	public static boolean isPrivatelyConnected(PhilosophersStone stone, PhilosophersStone connection) {
	
		for(int i = 0; i < stone.privateConnections.size(); i++) {
	
			if(stone.privateConnections.get(i) == connection)
				return true;
		}
	
		return false;
	}
	
	public static ArrayList<PhilosophersStone> getConnections(PhilosophersStone stone) {
		
		ArrayList<PhilosophersStone> connections = new ArrayList<PhilosophersStone>();
		
		connections.addAll(stone.publicConnections);
		connections.addAll(stone.privateConnections);
		
		return connections;
	}
	
	public static ArrayList<PhilosophersStone> getPublicConnections(PhilosophersStone stone) {
		return stone.publicConnections;
	}
	
	public static ArrayList<PhilosophersStone> getPrivateConnections(PhilosophersStone stone) {
		return stone.privateConnections;
	}
	
	public static ArrayList<PhilosophersStone> get(PhilosophersStone stone, String... tags) {
	
		ArrayList<String> getTags = new ArrayList<String>();
		
		for(String string : tags)
			getTags.add(string);
	
		return get(stone, getTags);
	}
	
	public static ArrayList<PhilosophersStone> get(PhilosophersStone stone, ArrayList<String> tags) {
		
		if(tags == null)
			return new ArrayList<PhilosophersStone>();
		
		tags = formatTags(tags);
		
		Hashtable<Integer, PhilosophersStone> path = new Hashtable<Integer, PhilosophersStone>();
		ArrayList<PhilosophersStone> stones = new ArrayList<PhilosophersStone>();
	
		getTraverse(stone, true, tags, path, stones);
	
		return stones;
	}
	
	public static ArrayList<PhilosophersStone> getAtlas(PhilosophersStone stone) {
	
		Hashtable<Integer, PhilosophersStone> path = new Hashtable<Integer, PhilosophersStone>();
		ArrayList<PhilosophersStone> stones = new ArrayList<PhilosophersStone>();
	
		getTraverse(stone, true, null, path, stones);
	
		return stones;
	}
	
	public static boolean has(PhilosophersStone stone, String... tags) {
	
		ArrayList<String> hasTags = new ArrayList<String>();
		
		for(String string : tags)
			hasTags.add(string);
	
		return has(stone, hasTags);
	}
	
	public static boolean has(PhilosophersStone stone, ArrayList<String> tags) {
		
		tags = formatTags(tags);
		
		Hashtable<Integer, PhilosophersStone> path = new Hashtable<Integer, PhilosophersStone>();
	
		return hasTraverse(stone, true, tags, path);
	}
	
	public static ArrayList<Object> call(PhilosophersStone stone, Object... packet) {
	
		ArrayList<Object> callPacket = new ArrayList<Object>();
		
		for(Object object : packet)
			callPacket.add(object);
	
		return call(stone, callPacket);
	}
	
	public static ArrayList<Object> call(PhilosophersStone stone, ArrayList<Object> packet) {
	
		Hashtable<Integer, PhilosophersStone> path = new Hashtable<Integer, PhilosophersStone>();
		ArrayList<Object> calls = new ArrayList<Object>();
	
		callTraverse(stone, true, packet, path, calls);
	
		return calls;
	}
	
	public static Object onCall(PhilosophersStone stone, Object... packet) {
	
		ArrayList<Object> callPacket = new ArrayList<Object>();
		
		for(Object object : packet)
			callPacket.add(object);
	
		return stone.onCall(callPacket);
	}
	
	public static Object onCall(PhilosophersStone stone, ArrayList<Object> packet) {
		return stone.onCall(packet);
	}
	
	public static int getIndexOfPublicConnection(PhilosophersStone stone, PhilosophersStone connection) {
	
		for(int i = 0; i < stone.publicConnections.size(); i++) {
	
			if(stone.publicConnections.get(i) == connection)
				return i;
		}
	
		return -1;
	}
	
	public static int getIndexOfPrivateConnection(PhilosophersStone stone, PhilosophersStone connection) {
	
		for(int i = 0; i < stone.privateConnections.size(); i++) {
	
			if(stone.privateConnections.get(i) == connection)
				return i;
		}
	
		return -1;
	}
	
	public static void callTraverse(
			PhilosophersStone stone,
			boolean init,
			ArrayList<Object> packet,
			Hashtable<Integer, PhilosophersStone> path,
			ArrayList<Object> calls) {
	
		if(path.get(stone.hashCode()) != null)
			return;
	
		path.put(stone.hashCode(), stone);
	
		Object call = null;
		
		try {
			call = onCall(stone, packet);
		}
		
		catch(Exception exception) {
			
		}
	
		if(call != null)
			calls.add(call);
	
		if(init) {
	
			for(int i = 0; i < stone.privateConnections.size(); i++)
				callTraverse(stone.privateConnections.get(i), false, packet, path, calls);
		}
	
		for(int i = 0; i < stone.publicConnections.size(); i++)
			callTraverse(stone.publicConnections.get(i), false, packet, path, calls);
	}
	
	public static void getTraverse(
			PhilosophersStone stone,
			boolean init,
			ArrayList<String> tags,
			Hashtable<Integer, PhilosophersStone> path,
			ArrayList<PhilosophersStone> stones) {
	
		if(path.get(stone.hashCode()) != null)
			return;
	
		path.put(stone.hashCode(), stone);
	
		if(tags != null) {
			
			if(hasAllTags(stone, tags))
				stones.add(stone);
		}
		
		else
			stones.add(stone);
	
		if(init) {
	
			for(int i = 0; i < stone.privateConnections.size(); i++)
				getTraverse(stone.privateConnections.get(i), false, tags, path, stones);
		}
	
		for(int i = 0; i < stone.publicConnections.size(); i++)
			getTraverse(stone.publicConnections.get(i), false, tags, path, stones);
	}
	
	public static boolean hasTraverse(
			PhilosophersStone stone,
			boolean init,
			ArrayList<String> tags,
			Hashtable<Integer, PhilosophersStone> path) {
	
		if(path.get(stone.hashCode()) != null)
			return false;
	
		path.put(stone.hashCode(), stone);
	
		if(hasAllTags(stone, tags))
			return true;
	
		if(init) {
	
			for(int i = 0; i < stone.privateConnections.size(); i++) {
	
				if(hasTraverse(stone.privateConnections.get(i), false, tags, path))
					return true;
			}
		}
	
		for(int i = 0; i < stone.publicConnections.size(); i++) {
	
			if(hasTraverse(stone.publicConnections.get(i), false, tags, path))
				return true;
		}
	
		return false;
	}
	
	public static boolean hasAllTags(PhilosophersStone stone, ArrayList<String> tags) {
		
		for(int i = 0; i < tags.size(); i++) {
			
			if(!isTagged(stone, tags.get(i)))
				return false;
		}
		
		return true;
	}
	
	public static ArrayList<String> formatTags(ArrayList<String> tags) {
		
		ArrayList<String> formattedTags = new ArrayList<String>();
		
		for(int i = 0; i < tags.size(); i++)
			formattedTags.add(formatTag(tags.get(i)));
		
		return formattedTags;
	}
	
	public static String formatTag(String tag) {
		
		String formattedTag = "";
		
		for(int i = 0; i < tag.length(); i++) {
			
			if(!Character.isWhitespace(tag.charAt(i)))
				formattedTag += tag.charAt(i);
		}
		
		return formattedTag.toLowerCase();
	}
}