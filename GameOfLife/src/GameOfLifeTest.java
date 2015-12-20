import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameOfLifeTest {

	private GameOfLife board;

	@Before
	public void setUp() {

		boolean tab[][] = new boolean[3][3];
		for (int i = 0; i < 3; i++) {
			tab[0][i] = false;
			tab[1][i] = true;
			tab[2][i] = false;
		}

		board = new GameOfLife(3, tab);
	}

	@Test
	public void ShouldDetectDeadCell() {
		// given
		board.Iteration();

		// when
		boolean result = board.getCell(2, 2);
		// then
		Assert.assertEquals(false, result);
	}

	@Test
	public void ShouldDetectAliveCell() {
		// given
		board.Iteration();

		// when
		boolean result = board.getCell(1, 1);
		// then
		Assert.assertEquals(true, result);
	}

	@Test
	public void ShouldGetALife() {
		// given

		board.Iteration();

		// when
		boolean result = board.getCell(0, 1);
		// then
		Assert.assertEquals(true, result);
	}

	@Test
	public void ShouldKeepLiving() {
		// given

		board.Iteration();

		// when
		boolean result = board.getCell(1, 1);
		// then
		Assert.assertEquals(true, result);
	}

	@Test
	public void ShouldDie() {
		// given

		board.Iteration();

		// when
		boolean result = board.getCell(1, 2);
		// then
		Assert.assertEquals(false, result);
	}

	@Test
	public void ShouldDealWith0Iterations() {
		// given
		board.performIterations(0);

		// when
		boolean result = board.getCell(1, 1);
		// then
		Assert.assertEquals(true, result);
	}

	@Test
	public void ShouldDealWith3Iterations() {
		// given
		board.performIterations(3);

		// when
		boolean result = board.getCell(1, 0);
		// then
		Assert.assertEquals(false, result);
	}

	@Test
	public void ShouldDealWith300Iterations() {
		// given
		board.performIterations(300);

		// when
		boolean result = board.getCell(1, 1);
		// then
		Assert.assertEquals(true, result);
	}

	@Test
	public void ShouldBeZeroOnEdge() {
		// given
		board.performIterations(123);

		// when
		boolean result = board.getCell(2, 2);
		// then
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void ShouldDealWithLargeArray() {
		// given
		boolean arr[][] = new boolean[1000][1000];
		for(int i = 0 ; i < 1000 ; i++)
			for(int j = 0 ; j < 1000 ; j++)
				arr[i][j] = true;
				
		GameOfLife board2 = new GameOfLife(1000, arr);
		
		board2.performIterations(123);

		// when
		boolean result = board.getCell(2, 2);
		// then
		Assert.assertEquals(false, result);
	}

}