import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {

	static Graph graph = new Graph();
	
	static Node nodeFrom = null;
	static Node nodeTo = null;
	static JTextField txFrom;
	static JTextField txTo;
	static List<Node> path;
	static List<Node> visited;
	
	
	static int[][] nodes;
	
	static int[][] edges;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(1400,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Grapher grapher = new Grapher(700,700);
		nodes = grapher.getNodes(); 
		edges = grapher.getEdges(); 
		
		for(int i = 0;i<nodes.length;i++) {
			
			Node node = new Node(Graph.getNextIndex(),nodes[i][0],nodes[i][1]);
			graph.addNode(node);
		}
		
		JPanel pane = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				for(int i = 0;i<edges.length;i++) {
					int from = edges[i][0];
					int to = edges[i][1];
					double distance = Math.sqrt(Math.pow(graph.getNode(to).getX()-graph.getNode(from).getX(), 2)+Math.pow(graph.getNode(to).getY()-graph.getNode(from).getY(), 2));
					Edge edge = new Edge(from,to,distance);
					graph.addEdge(edge);
				}
				graph.redraw(g);
				if(path!= null) {
					if(path.size()>1){
						for(int i = 1;i<path.size();i++) {
							Node from = path.get(i-1);
							Node to = path.get(i);
							for(int j = 0;j<visited.size();j++) {
								if(!path.contains(visited.get(j))) {
									if(graph.getEdge(from.getIndex(),visited.get(j).getIndex()) != null) {
										graph.getEdge(from.getIndex(),visited.get(j).getIndex()).drawEdge(from.getX()+10,from.getY()+10, visited.get(j).getX()+10,visited.get(j).getY()+10, "visited", g);
									}
								}
							}
							graph.getEdge(from.getIndex(), to.getIndex()).drawEdge(from.getX()+10,from.getY()+10 ,to.getX()+10, to.getY()+10, "path", g);;
						}
					}
				}
			}
		};
		for(int i = 0;i<nodes.length;i++) {
			int idx = i;
			CircleButton circleButton = new CircleButton(idx+"");
			circleButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(nodeFrom == null) {
						nodeFrom = graph.getNode(idx);
						txFrom.setText(nodeFrom.getIndex()+ "");
						txFrom.setBackground(null);
						txTo.setBackground(Color.yellow);
						System.out.println("NodeFrom - " + nodeFrom.getIndex());
					}else if(nodeTo == null) {
						nodeTo = graph.getNode(idx);
						txTo.setText(nodeTo.getIndex()+ "");
						txTo.setBackground(null);
						System.out.println("Node To " + nodeTo.getIndex());
					}else if(nodeFrom != null && nodeTo != null) {
						nodeFrom = graph.getNode(idx);
						txFrom.setText(nodeFrom.getIndex()+ "");
						txTo.setText("");
						txTo.setBackground(Color.yellow);
						System.out.println("NodeFrom - " + nodeFrom.getIndex());
						nodeTo = null;
					}
				}
		});
			pane.setLayout(null);
			circleButton.setBounds(nodes[i][0], nodes[i][1], 20, 20);
			pane.add(circleButton);
		}
		JButton start = new JButton("Start");
		start.setPreferredSize(new Dimension(75,35));
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nodeFrom != null && nodeTo != null) {
					if(path!=null) {
						path.clear();
						visited.clear();
					}
					frame.getContentPane().repaint();
					Algorithm alg = new Algorithm(graph,nodeFrom,nodeTo);
					path = alg.printPath();
					visited = alg.getSet();
					frame.getContentPane().repaint();
				}
			}
		});
		JLabel lab1 = new JLabel("From - ");
		JLabel lab2 = new JLabel("To - ");
		txFrom = new JTextField(10);
		txFrom.setEditable(false);
		txFrom.setBackground(Color.YELLOW);
		txTo = new JTextField(10);
		txTo.setEditable(false);
		JPanel pane2 = new JPanel();
		pane2.setLayout(new FlowLayout());
		pane2.add(lab1);
		pane2.add(txFrom);
		pane2.add(lab2);
		pane2.add(txTo);
		pane2.add(start);
		frame.add(pane,BorderLayout.CENTER);
		frame.add(pane2, BorderLayout.SOUTH);
		frame.setVisible(true);
		

	}

}
