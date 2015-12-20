
public class Cell {

	private boolean isAlive = false;
	private int 	numOfNeighbours= 0;

	void die()
	{
		isAlive = false;
	}
	
	void getALife()
	{
		isAlive = true;
	}
	
	public int getNumOfNeighbours() {
		return numOfNeighbours;
	}

	public void setNumOfNeighbours(int numOfNeighbours) {
		this.numOfNeighbours = numOfNeighbours;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
}