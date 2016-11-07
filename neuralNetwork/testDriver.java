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
			
			List<List<Integer>> dataset = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("machine.data.txt"));
			String line;
		
			while ( ( line = br.readLine()) != null ){
				dataset.add( new ArrayList<Integer>() );
				String[] dataValues = line.split(",");
				for ( int i = 2 ; i < dataValues.length ; i++ ){
					dataset.get(dataset.size()-1).add(Integer.parseInt(dataValues[i]));
				}
				
			}


			for ( int i = 0 ; i < dataset.size(); i++ ){
				for ( int x = 0 ; x < dataset.get(i).size(); x++ ){
					System.out.print(dataset.get(i).get(x) + " ");
				}
				
				System.out.println();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
