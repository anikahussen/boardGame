package project1_boardGame;

public class callPlay {

	public static void main(String[] args) {
		//One player
		gamePlaying g1 = new gamePlaying(1);
		g1.play();
		
		//Two player
		gamePlaying g2 = new gamePlaying(2);
		g2.play();
		
		//Three player
		gamePlaying g3 = new gamePlaying(3);
		g3.play();
		
		//Four player
		gamePlaying g4 = new gamePlaying(4);
		g4.play();
		
		
		//Print Results 
		System.out.println("Player in game               Player A average moves / % winning               Player B average moves / % winning               Player C average moves / % winning               Player D average moves / % winning");
		System.out.print("A                            ");
		g1.results(g1.activePlayers);
		System.out.print("A, B                         ");
		g2.results(g2.activePlayers);
		System.out.print("A, B, C                      ");
		g3.results(g3.activePlayers);
		System.out.print("A, B, C, D                   ");
		g4.results(g4.activePlayers);
		
	}

}
