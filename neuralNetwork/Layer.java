package neuralNetwork;

import java.util.ArrayList;

public class Layer {
	
	private ArrayList<Node> nodes;
	
	public void addNode( Node n ){ nodes.add(n); }
	
	public Layer( int numOfNodes ){ nodes = new ArrayList<>(numOfNodes); }
	
	public int size(){ return nodes.size(); }
	
	public Node getNode( int i ){ return nodes.get(i); }
	
	
}
