package project1_boardGame;


import java.util.*;



public class Dice {
	Random r = new Random();
	
	ArrayList<Integer> sides = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
	
	
	public int roll() {
		
		int value = sides.get(r.nextInt(sides.size()));
		
		return value;
   		  		 		
    		
    }
		
	
	

}
