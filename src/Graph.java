import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Graph {
	private static int nextIndex = 0;
	private ArrayList<Node> nodes;
	private Map<Integer, ArrayList<Edge>> edges;
	
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new LinkedHashMap<Integer,ArrayList<Edge>>();
		
	}
	
	public Node getNode(int idx) {
		return nodes.get(idx);
	}
	
	public Edge getEdge(int from, int to) {
		if(edges.containsKey(from)) {
			ArrayList<Edge> from_Edges = edges.get(from);
			
			for(int i =0;i<from_Edges.size();i++) {
				
				if(from_Edges.get(i).getTo() == to) {
					
					return from_Edges.get(i);
				}
			}
		}
		return null;
	}
	
	public void addNode(Node node) {
		
		if(validIndex(node.getIndex())) {
			nodes.add(node);
			edges.put(node.getIndex(),new ArrayList<Edge>());
			nextIndex++;
		}
	}
	
	public void addEdge(Edge edge) {
		if(validIndex(edge.getTo())&&validIndex(edge.getFrom())) {
			if(getEdge(edge.getFrom(),edge.getTo())== null) {
				edges.get(edge.getFrom()).add(edge);
			}
		}
	}
	
	public ArrayList<Edge> getEdges(int node) {
		return edges.get(node);
	}
	
	
	public boolean validIndex(int idx) {
		return (idx>=0&&idx<=nextIndex);
	}
	
	public int numNodes() {
		return nodes.size();
	}
	
	public void redraw(Graphics g) {
		for(Map.Entry<Integer, ArrayList<Edge>> entry : edges.entrySet()) {
			ArrayList<Edge> val = entry.getValue();
			 for( Edge edge : val) {
				 edge.drawEdge(getNode(edge.getFrom()).getX()+10, getNode(edge.getFrom()).getY()+10, getNode(edge.getTo()).getX()+10, getNode(edge.getTo()).getY()+10, "normal", g);
			 }
		}
	}
	
	public static int getNextIndex() {
		return nextIndex;
	}
	
}
