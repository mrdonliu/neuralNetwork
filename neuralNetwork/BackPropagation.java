package neuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class BackPropagation {
	
	private final int HIDDEN = 0;
	private final int OUTER = 1;
	
	private Layer[] layers;
	
	private double[] maxAttValues;
	
	private List<List<Double>> dataset;
	
	private double totalError;
	
	private double errorThreshold;
	
	private int iterations;
	
	private int bTestFold , eTestFold; // 
	
	private List<Double> targets;
	
	private final double RATE = .7;
	
	public BackPropagation( List<List<Double>> dataset , List<Double> targets , double errorThreshold , double[] maxAttValues ){
		initLayers();
		this.dataset = dataset; 
		this.targets = targets;
		this.errorThreshold = errorThreshold;
		this.maxAttValues = maxAttValues;
		iterations = 0;
		normalize();
		initFolds();
		
	}
	
	private void initLayers(){
		layers = new Layer[2];
		layers[HIDDEN] = new Layer(3);
		layers[OUTER] = new Layer(1);
		for ( int i = 0 ; i < 3 ; i++ ) layers[HIDDEN].addNode( new Node(7) );
		layers[OUTER].addNode(new Node(3));
	}
	
	private void normalize(){
		for ( int i = 0 ; i < dataset.size() ; i++ ){
			for ( int x = 0 ; x < dataset.get(i).size(); x++ ){
				dataset.get(i).set(x, dataset.get(i).get(x) / maxAttValues[x] );
				targets.set(i, targets.get(i) / maxAttValues[x] );
			}
		}
	}
	
	/**
	 * initializes the testing folds for cross validation. During the training interations, the data set
	 * between the beginning of the test fold (bTestFold) and end (eTestFold) will be skipped over. 
	 */
	private void initFolds(){
		                      
		bTestFold = 0;
		eTestFold = 52;
	}
	
	private void updateFolds(){
		
		switch ( bTestFold ){
		case 0: 
			bTestFold = 52;
			eTestFold = 104;
			break;
		case 52: 
			bTestFold = 104;
			eTestFold = 156;
			break;
		case 104: 
			bTestFold = 156;
			eTestFold = 208;
			break;
		case 208: 
			bTestFold = 0;
			eTestFold = 52;
			break;
		}
		
	}
	
	public void begin(){
		boolean convergence = false;
		double[][] deltaK = new double[2][];
		deltaK[HIDDEN] = new double[3];
		deltaK[OUTER] = new double[1];
		//do
		while ( !convergence ){
		for ( int i = 0 ; i < dataset.size() ; i++ ){
			if ( i >= bTestFold && i<= eTestFold ) continue;
			
			ArrayList<Double> hiddenOutputs = new ArrayList<>(); // hold outputs for hidden nodes
			double finalOutput;                                  // hold the one output for outer node
			
			for ( int x = 0 ; x < layers[HIDDEN].size() ; x++ ){
				layers[HIDDEN].getNode(x).loadInputs(dataset.get(i));
				hiddenOutputs.add( layers[HIDDEN].getNode(x).getOutput() );
			}
			layers[OUTER].getNode(0).loadInputs(hiddenOutputs);
			finalOutput = layers[OUTER].getNode(0).getOutput();
			
			deltaK[OUTER][0] = finalOutput * ( 1 - finalOutput ) * ( finalOutput - targets.get(i) );
			for ( int y = 0 ; y < 3 ; y++ ){
				deltaK[HIDDEN][y] = hiddenOutputs.get(y) * (1-hiddenOutputs.get(y)) * 
				layers[OUTER].getNode(0).getWeight(y) * deltaK[OUTER][0];
			}
			
			updateWeights( deltaK[OUTER][0] , deltaK[HIDDEN] , hiddenOutputs ); }			
		
		double currError;
		convergence = ( (currError = testAndCalculateError() )> errorThreshold ) ? false : true;
		iterations++;
		System.out.println("Current iteration: " + iterations + "  Current Error: " + currError);
		//while (testAndCalculateError > errorThreshold )
		}
		
	}
		
		


	public void updateWeights( double outerDeltaK , double [] hiddenDeltaK , ArrayList<Double> hiddenOutputs ){
		for ( int i = 0 ; i < layers[HIDDEN].size(); i++ ){
			layers[HIDDEN].getNode(i).updateWeights(RATE , hiddenDeltaK[i]);
		}
		
		layers[OUTER].getNode(0).updateWeights(RATE, outerDeltaK);
	}
	
	public double testAndCalculateError(){
		double totalError = 0;
		for( int i = bTestFold ; i <= eTestFold ; i++ ){
			ArrayList<Double> hiddenOutputs = new ArrayList<>(); // hold outputs for hidden nodes
			double finalOutput;                                  // hold the one output for outer node
			
			for ( int x = 0 ; x < layers[HIDDEN].size() ; x++ ){
				layers[HIDDEN].getNode(x).loadInputs(dataset.get(i));
				hiddenOutputs.add( layers[HIDDEN].getNode(x).getOutput() );
			}
			layers[OUTER].getNode(0).loadInputs(hiddenOutputs);
			finalOutput = layers[OUTER].getNode(0).getOutput();
			
			totalError += ( (.5) * Math.pow(finalOutput - targets.get(i), 2));
			
		}
		
		return totalError;
	}
}
