import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Algorithm {
	
	private Graph graph;
	private Node from;
	private Node to;
	private List<Node> visited;
	
	Algorithm(Graph _graph,Node _from,Node _to){
		graph = _graph;
		from = _from;
		to = _to;
		
		for(int i = 0;i<graph.numNodes();i++) {
			graph.getNode(i).f = 0;
			graph.getNode(i).g = 0;
			graph.getNode(i).parent = null;
			
			graph.getNode(i).countToTarget(to);
		
			System.out.println("The graph num " + graph.getNode(i).getIndex() + " Has distance to goal - "+graph.getNode(i).getToTarget());
		}		
		
		System.out.println("serching");
		
		search();
		
		
		
	}
	
	protected List<Node> printPath() {
		List<Node> path = new ArrayList<Node>();
		
		for(Node node = to;node!=null;node = node.parent) {
			
			path.add(node);
			
		}
		
		Collections.reverse(path);
		
		for(int i =0;i<path.size();i++) {
			
			System.out.println(path.get(i).getIndex());
		}
		
		return path;
	}
	
	public List<Node> getSet(){
		System.out.println("visited");
		for(int i = 0;i<visited.size();i++) {
			System.out.println(visited.get(i).getIndex());
		}
		System.out.println("///////");
		
		return visited;
	}

	public void search() {
		
		Set<Node> explored = new HashSet<Node>();
		visited = new ArrayList<Node>();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
				new Comparator<Node>() {

					@Override
					public int compare(Node i, Node j) {
						if(i.f > j.f) {
						
							return 1;
						}
						
						else if (i.f < j.f) {
							
							return -1;
							
						}
						
						else {
							
							return 0;
							
						}
					}
			
		});
		
		from.g = 0;
		
		queue.add(from);
		
		boolean found = false;
		
		while((!queue.isEmpty())&&(!found)) {
			
			Node current = queue.poll();
			
			System.out.println("CURRENT NODE - " + current.getIndex());
			
			explored.add(current);
			
			if(current.getIndex() == to.getIndex()) {
				
				found = true;
			
			}
			
			for(Edge e : graph.getEdges(current.getIndex())) {

				System.out.println();
				Node child = graph.getNode(e.getTo());
				
				if(!visited.contains(child)) visited.add(child);
				
				System.out.println("child node is - " + child.getIndex());
				System.out.println();
				
				
				double cost = e.getCost();
				
				System.out.println("Cost - " + cost);
				System.out.println();
				
				double temp_g = current.g + cost;
				
				System.out.println("temp_g-_scores - " + temp_g);
				System.out.println();
				
				double temp_f = temp_g + child.getToTarget();
				
				System.out.println("temp_f - " + temp_f);
				System.out.println();
				
				if((explored.contains(child))&&(temp_f>= child.f)) {
					System.out.println("COntinuing");
					continue;
				} else if(!(queue.contains(child))||(temp_f < child.f)) {
					
					child.parent = current;
					
					
					System.out.println("Child.parent - " + child.parent.getIndex());
					child.g = temp_g;
					child.f = temp_f;
					
					if(queue.contains(child)) {
						System.out.println("removing child - " + child.getIndex());
						queue.remove(child);
					}
				
					queue.add(child);
				}
				
			}
			
		}
		
	}
	
}
