
public class Grapher {
	
	private int[][] nodes;
	private int[][] edges;
	
	
	public Grapher(int Hight, int Width) {

		
		nodes = new int [Hight/2][2];
		edges = new int [Hight * 4][2];
		
		int zmienna = 50;
		int z1 = 50;
		
		for(int i = 0;i<Hight/2;i++) {
			nodes[i][0] = z1;
			nodes[i][1] = zmienna;
			if(zmienna == Width) {
				zmienna = 50;
				z1 = z1 + 50;
			}else {
				zmienna = zmienna + 50;
			}
		}
		
		int j = 0;
		for(int i = 0; i < Hight/2;i++ ) {
			if((i+1)<Hight/2) {
				
				if((i+1)%14 != 0) {
					
					edges[j][0] = i;
					edges[j][1] = i+1;
					edges[j+1][0] = i+1;
					edges[j+1][1] = i;
					
				}
			}
			if((i+15)<Hight/2) {
				
				if((i+15)%14 != 0) {
					
					edges[j+2][0] = i;
					edges[j+2][1] = i+15;
					edges[j+3][0] = i+15;
					edges[j+3][1] = i;
				}
			}
			if((i+14)<Hight/2) {
				
				edges[j+4][0] = i;
				edges[j+4][1] = i+14;
				edges[j+5][0] = i+14;
				edges[j+5][1] = i;
			}
			if((i+13)<Hight/2) {
				
				if((i+13 + 1)%14 != 0) {
					
					edges[j+6][0] = i;
					edges[j+6][1] = i+13;
					edges[j+7][0] = i+13;
					edges[j+7][1] = i;
				}
			}
			
			j = j + 8;
			
		}
		
		for(int i = 0; i<edges.length;i++) {
			
			int from = edges[i][0];
			int to = edges[i][1];
			System.out.println("FRom - " + from + " To - " + to);
		}
		
	}
	
	public int[][] getNodes(){
		
		return nodes;
	}
	
	public int[][] getEdges(){
		
		return edges;
	}
	
}
