
package question;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;  
import java.util.*;

public class MarketNavigator
{  
	
	/* Method that gives the distance between two points */
	public static double distanceFinder(int x1, int y1, int x2, int y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	
	// I use the paths method to calculate all permutations 
	public static void paths(int[] index, ArrayList<ArrayList<Integer>> permutations, int a) {
		if (a == index.length){									// When iterator a equals length of given list, program adds the new permutation to a list
			ArrayList<Integer> listIndex = new ArrayList<>();
			for (int el:index) {
				listIndex.add(el);
			}
			permutations.add(listIndex);
		} else {	
			for (int i = a; i < index.length; i++) {		// Program changes elements of list and create permutations
				int temp = index[i];
				index[i] = index[a];
				index[a] = temp;
				paths(index, permutations, a+1);
				temp = index[i];					// After each recursive call, program convert the list into the first version
				index[i] = index[a];
				index[a] = temp;
			}
		}
	}



	public static int pathFinder(String filename) {

		/* Find the smallestTotalDistance */
		double smallestTotalDistance = 0;

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

		ArrayList<String> locations = new ArrayList<>();	// I create an array list to store string which is in the file
		File file = new File(filename);			
		Scanner readFile;		// Reading file with scanner object in try catch block
		try {
			readFile = new Scanner(file);
			while (readFile.hasNextLine()) {		// I store strings that is in the file with a while loop
				locations.add(readFile.nextLine());
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		
		ArrayList<House> houses = new ArrayList<>();	// I create another array list with house class to store houses

		
		for (String el : locations) {	// I convert each line into a house object
			String[] place = el.split(" ");	// First i split each house
			String placeName = place[0];	// Then I match each attributes with corresponding string part
			int placeX = Integer.valueOf(place[1]);
			int placeY = Integer.valueOf(place[2]);
			House i = new House(placeName, placeX, placeY);  // I create house objects and
			houses.add(i);                                   // I store them

		}


		int[] indexes = new int[houses.size()-1];		// To permute houses I take their index with for loop
		for (int i = 1, j = 0; i < houses.size(); i++, j++) {	// I initialize i from 1 and I add 0 s to the beginning and end
			indexes[j] = i;
		}

		ArrayList<ArrayList<Integer>> permutations = new ArrayList<>(); // I create another array list to store index permutations
		paths(indexes, permutations, 0);		// I apply permutation method

		for (int k = 0; k < permutations.size(); k++) {		// And I add 0 s because we want to begin and end with Migros
															// And we know Migros index is always 0
			permutations.get(k).add(0);
			permutations.get(k).add(0, 0);
		}

		for (ArrayList<Integer> elements:permutations) {	// For each path(permutation) I check the path length and I update it
			double sum = 0;
			for (int i = 0; i<houses.size(); i++) {
				int firstDesX = houses.get(elements.get(i)).getX();		// I take index of house in the permutation list and x and y coordinates with get methods
				int firstDesY = houses.get(elements.get(i)).getY();
				int secondDesX = houses.get(elements.get(i+1)).getX();
				int secondDesY = houses.get(elements.get(i+1)).getY();
				sum += distanceFinder(firstDesX, firstDesY, secondDesX, secondDesY);	// And I apply distanceFinder method each of them

			}
			if (smallestTotalDistance == 0 || sum < smallestTotalDistance) {	// And updating smallest distance when there is smaller distance or distance is 0
				smallestTotalDistance = sum;

			}


		}



		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
		
		int roundedValue = (int) Math.round(smallestTotalDistance);
		return roundedValue;
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		/* This part is for you to test your method, no points will be given from here */
		String path = MarketNavigator.class.getProtectionDomain().getCodeSource().getLocation().getPath() + File.separator + ".." + File.separator+"coordinates.txt";
		int distance = pathFinder("coordinates.txt");
		System.out.println("Smallest distance:" + distance);
	}
	
}  

