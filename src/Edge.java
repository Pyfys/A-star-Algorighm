import java.awt.Color;
import java.awt.Graphics;

public class Edge {
	private int from;
	private int to;
	private double cost = 1.0;
	
	public Edge(int n_From,int n_To,double n_Cost) {
		
		from = n_From;
		to = n_To;
		cost = n_Cost;
	}
	
	public int getFrom() {
		return from;
	}
	
	
	public int getTo() {
		return to;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void drawEdge(int x,int y,int x1,int y1,String style,Graphics g) {
		
		switch(style) {
			case "normal":
				g.setColor(Color.gray);
				g.drawLine(x, y, x1, y1);
				break;
			case "path":
				g.setColor(Color.BLUE);
				g.drawLine(x, y, x1, y1);
				break;
			case "visited":
				g.setColor(Color.RED);
				g.drawLine(x, y, x1, y1);
				break;
		}
	}
	
}
