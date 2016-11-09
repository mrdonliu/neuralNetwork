package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private double[] weights; // weights between this node and the previous
	
	private int bias = 1;
	
	private double output;
	
	private List<Double> input; // the output of the last nodes into this one
	
	
	public double getOutput(){ return output; }
	public double[] getWeights(){ return weights; }
	
	public Node( int inputSize ){
		weights = new double[inputSize];
		initializeWeights();
	}
	
	/**
	 * weights are randomly selected from -1 to 1 when the node is created
	 */
	private void initializeWeights(){
		for ( int i = 0 ; i < weights.length ; i++ ){
			weights[i] = Math.random() * 2 - 1;
		}
		
		
	}
	/**
	 * Loads an arraylist of inputs into this node. An output is computed. 
	 * @param list
	 */
	public void loadInputs( List<Double> input ){
		this.input = input;
		double sum = 0;
		for ( int i = 0 ; i < input.size() ; i++ ){
			
			sum = input.get(i) * weights[i];
		}
		
		output = sigmoidFunction(sum + bias);
		
		
	}
	

	private double sigmoidFunction( double num ){
		return 1 / ( 1 + Math.exp(-1 * num) );
	}
	
	public void updateWeights( double rate , double deltaK ){
		for( int i = 0 ; i < weights.length ; i++ ){
			weights[i] += ( -1 * rate * deltaK * input.get(i) );
		}
	}
	
	public double getWeight( int i ){ return weights[i]; }
	
	// public void updateBias(){}	
	
	
	

	
	
	
	
	

}
