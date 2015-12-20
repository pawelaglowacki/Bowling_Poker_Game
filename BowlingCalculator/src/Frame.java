
public class Frame {
	
	private int	 	ball = 1;
	private int 	score = 0;
	private boolean spare = false;
	private boolean strike = false;
	
	public void add_pins(int pins)
	{
		score += pins;
	}
		
	public int getBallNumber() {
		return ball;
	}
	
	public void setBallNumber(int ball) {
		this.ball = ball;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isSpare() {
		return spare;
	}
	public void setSpare(boolean spare) {
		this.spare = spare;
	}
	public boolean isStrike() {
		return strike;
	}
	public void setStrike(boolean strike) {
		this.strike = strike;
	}
}