package neuralNetwork;

public class Node {
	
	private double[] weights; // weights between this node and the previous
	
	private final int BIAS = 1;
	
	private double output;
	
	public Node( int numbOfInputs ){
		weights = new double[numbOfInputs];
		initializeWeights();
	}
	
	private void initializeWeights(){
		for ( int i = 0 ; i < weights.length ; i++ ){
			weights[i] = Math.random() * 2 - 1;
		}
		
		
	}
	
	
	
	
	
	

}
