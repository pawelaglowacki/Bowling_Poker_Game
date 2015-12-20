public class GameOfLife {

	private Cell[][] array; 
	int sizeOfBoard = 0;

	public GameOfLife(int size, boolean[][] inputArray)
	{
		sizeOfBoard = size;

		array = new Cell[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				array[i][j] = new Cell();

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				array[i][j].setAlive(inputArray[i][j]);
	}

	public void performIterations(int n) {
		for (int i = 0; i < n; i++)
			Iteration();
	}

	public void Iteration() {
		setNumberOfLivingNeighboursForEachCell();
		LiveOrDie();
	}

	public void setNumberOfLivingNeighboursForEachCell() {
		for (int i = 0; i < sizeOfBoard; i++)
			for (int j = 0; j < sizeOfBoard; j++) {
				setNumberOfLivingNeighboursForOneCell(i, j);
			}
	}

	public void setNumberOfLivingNeighboursForOneCell(int i, int j) {
		int result = countNeighbours(i, j);
		array[i][j].setNumOfNeighbours(result);
	}

	public int countNeighbours(int i, int j) {
		int result = 0;

		for (int x = i - 1; x <= i + 1; x++)
			for (int y = j - 1; y <= j + 1; y++) {
				result += checkNeighbour(x, y);
			}
		if (checkNeighbour(i, j) == 1)
			result--;

		return result;
	}

	public int checkNeighbour(int i, int j) {
		if (i < 0 || j < 0 || i >= sizeOfBoard || j >= sizeOfBoard)
			return 0;
		if (array[i][j].isAlive())
			return 1;
		return 0;
	}

	public void LiveOrDie() {
		for (int i = 0; i < sizeOfBoard; i++)
			for (int j = 0; j < sizeOfBoard; j++) {
				Cell cell = array[i][j]; // alf shift L
				int neighbours = cell.getNumOfNeighbours();
				if (cell.isAlive() && !(neighbours == 2 || neighbours == 3))
					cell.die();
				if (!cell.isAlive() && neighbours == 3)
					cell.getALife();
			}
	}
	
	public boolean getCell(int x, int y)
	{
		return array[x][y].isAlive();
	}
}