
public class Node {

	private int index;
	private int x;
	private int y;
	private double toTarget;
	protected double f = 0;
	protected double g;
	protected Node parent;
	
	public Node(int idx,int x,int y) {
		
		index = idx;
		this.x = x;
		this.y = y;
	
	}
	
	public int getIndex() {
		
		return index;
	}
	
	public void countToTarget(Node n) {
		toTarget = Math.sqrt(Math.pow((x - n.getX()), 2)+Math.pow((y-n.getY()), 2));
	}
	
	public double getToTarget() {
		return toTarget;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		return y;
	}
	

}
