package project1_boardGame;



public class Player {
	//player name
	String id = "Player ";
	//keep track of points, position, turns, wins
	int totalPoints = 0;
	int currentPos = 0;
	int turns =0;
	int wins = 0;
	static int boardId = 0;
	
	//initiate with letter ie. A, B
	public Player (String letter) {
		id = id + letter;
		boardId++;
		}
	
	
	//getters and setters for variable use
	public int getPoints() {
		return this.totalPoints;
	}
	
	public void setPoints(int newPoints) {
		this.totalPoints = newPoints;
	}
	
	public int getPos() {
		return this.currentPos;
	}
	
	public void setPos(int newPos) {
		this.currentPos = newPos;
	}
	
	
	public String getidNum() {
		return this.id;
	}
	
	
	public int getboardId() {
		return boardId;
	}
	
	
	public int getTurns() {
		return this.turns;
	}
	
	public void addTurns(int newTurn) {
		this.turns += newTurn;
	}
	
	public int getWins() {
		return this.wins;
	}
	
	public void addWins(int newWins) {
		this.wins += newWins;
	}

	public void reset() {
		this.turns = 0;
		this.wins = 0;
	}

}
