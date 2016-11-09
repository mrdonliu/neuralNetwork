package neuralNetwork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			List<List<Double>> dataset = new ArrayList<>();
			List<Double> targets = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("machine.data.txt"));
			String line;
			double[] maxAttValues = { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }; // keep track of max attributes for normalization starting
                                                                       // from attribute 2.		
			while ( ( line = br.readLine()) != null ){
				dataset.add( new ArrayList<Double>() ); // each arraylist stores the attributes of a single dataset
				String[] dataValues = line.split(",");
				
				for ( int i = 2 ; i < dataValues.length ; i++ ){ // ignore first two attributes
					double value = (double) Integer.parseInt(dataValues[i]);
					if ( i == 9 ){ // attribute 9 is target value
						targets.add(value);
						continue;
					}
					if ( value > maxAttValues[i-2] ) maxAttValues[i-2] = value; 
					dataset.get(dataset.size()-1).add( value ); // adds attribute into current arraylist
					
					
				}
				
			}
			
			BackPropagation bp = new BackPropagation( dataset , targets , .001 , maxAttValues );
			bp.begin();
			


			/*for ( int i = 0 ; i < dataset.size(); i++ ){
				for ( int x = 0 ; x < dataset.get(i).size(); x++ ){
					System.out.print(dataset.get(i).get(x) + " ");
				}
				
				System.out.println();
			}*/
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
