

public class PlayerInfo {
	
	private IPlayer player;
	private int gameScore;
	private Pile hand;
	private Pile tricksWon;
	
	public PlayerInfo() {
		// Initialize our player (swapping between implementations of IPlayer would be here).
		this.player = new DummyPlayer();
		this.gameScore = 0;
		this.resetPiles();
	}
	public IPlayer getPlayer() {
		return this.player;
	}
	public int getGameScore() {
		return this.gameScore;
	}
	public void setGameScore(int newScore) {
		this.gameScore = newScore;
	}
	public Pile getHandPile() {
		return this.hand;
	}
	public Pile getTricksWonPile() {
		return this.tricksWon;
	}
	
	public void resetPiles() {
		this.hand = new Pile();
		this.tricksWon = new Pile();
	}
}
