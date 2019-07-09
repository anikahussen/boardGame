package project1_boardGame;

import java.util.*;

//class to play the game
public class gamePlaying {
	//how many players
	int playerAmount;
	ArrayList<Player> activePlayers = new ArrayList<Player>();
	
	//initiate with how many players
	public gamePlaying (int playerAmount) {
		this.playerAmount = playerAmount;
	}
	
	
		//method that will play the game 1000 times//
		public void play () {
		
		
	
		DoublyLinkedList<Integer> board = new DoublyLinkedList<Integer>();
		
		//symbolize start and end of the game after header, before footer
		final int START = 0;
		final int END = 0;
	
		//setting the element integers 
		ArrayList<Integer> boardPos = new ArrayList<Integer>(
		      Arrays.asList(START, 5, 10, 8, 10, 7, 5, 9, 10, 6, 7, 10, 6, 5, 8, 9, 5, 10, 5, 9, 6, 8, 7, 10, 6, 8, END)); 
	
		
		//into doubly linked list
		for (int i = 0; i<boardPos.size(); i++) {
			
			board.addLast(boardPos.get(i));

		}	
		
		
		
		
	
		
		//Instantiate Players
		Player p1 = new Player("A");
		Player p2 = new Player("B");
		Player p3 = new Player("C");
		Player p4 = new Player("D");
		
		ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(p1, p2, p3, p4));
		
		
		
		for (int a=0; a<playerAmount; a++) {
			activePlayers.add(players.get(a));
			
		}
		
		
		
		
		//Place player on starting position 
		
		
		for (int i = 0; i<players.size(); i++) {
			board.front(players.get(i));
			
			
		}
		
		
		
		//Instantiate 6-sided dice 
		Dice d1 = new Dice();
		
		
		
		//Initiate Roll
		int roll = 0;
		
		
		
		
		
		//count wins 
		int win=0;
		//Move player RESTRICT from going over 1000 games
		while(p1.getWins()+p2.getWins()+p3.getWins()+p4.getWins() < 1000) {
			
			
			
			
			//playing the game for the amount of players (looping through players)
			for (int p=0; p <playerAmount; p++) {
				//from players arraylist
				Player p0 = players.get(p);
				//add turn count to player
				p0.addTurns(1);
					
				//Roll dice 
				roll = d1.roll();
				
				//if on the board
				if (p0.getPos() + roll< board.size()) {
					//set position for player
					p0.setPos(p0.getPos() + roll);
					//set points for players
					p0.setPoints(p0.getPoints()+board.getScore(p0.getPos()));
					//cheek if the following place he player will go is occupied
					//if it is 
					if (board.checkOccupy(p0.getPos()+roll) == true) {
						//move those occupying players back to 7 spaces
						for (int c=0; c <board.whoOccupy(p0.getPos() + roll).size(); c++) {
							Player r = board.whoOccupy(p0.getPos()+roll).get(c);
							board.move(p0.getPos()+roll, -7, r);	
						}
							
					}
							
						

				}
			//if off the board		
			else {
					
				//check if the player has 44 more points
				if (p0.getPoints() >= 44) {
					//WIN
					p0.setPos(p0.getPos() + roll);
					//add win
					p0.addWins(1);
					//increment win count
					win ++;
							
							
					//if it is 100th win/game 
					if (win % 100 == 0) {
						//draw the board 
						board.drawBoard(activePlayers);	
					}
							
							
								
				
					//set all players back to the front of the board and clear points 		
					for (int r=0; r<players.size(); r++){
						players.get(r).setPos(0);
						players.get(r).setPoints(0);
					}
						board.reset();

					}
						
					//if points are less than 44, just place player back to 0	
					else{
						p0.setPos(0); //Place back at the start
						board.front(p0); //occupy first square
							
							
					}
						
						
							
						
							
						
				}
					
			}		
				
					
					
		
		
			
	
			
	}	
			
		
			
			
	
	}	
			
			
		
		
		//Method to print results (avgs)//
		//takes in the argument of player arraylist (current players)
		public void results (ArrayList <Player>playNow) {
		
			//iterate through current players
			for (int i=0; i<playNow.size(); i++) {
				//store the players' wins	
				double winnings = playNow.get(i).getWins();
				//find percentage
				double result1 = winnings/1000.0 * 100.0;
					
				//store the players' turns 	
				double turnings = playNow.get(i).getTurns();
				//find average
				double result2 = turnings/1000.0;
				//format	
				System.out.printf("%.2f / %.2f ", result2, result1);
				System.out.print("                                    ");
					
		
		}
			
				System.out.println("");
		}
	
	

}



























