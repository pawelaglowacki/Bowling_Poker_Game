import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BowlingCalculatorTest {

	private BowlingCalculator calculator;

	@Before
	public void setUp() {
		calculator = new BowlingCalculator();
	}

	@Test
	public void shouldHandleNoRolls() {
		// given

		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(1, frameNumber);
	}
	
	@Test
	public void shouldHandleOneRoll() {
		// given
		calculator.roll(1);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(1, frameNumber);
	}

	@Test
	public void shouldHandleTwoRolls() {
		// given

		calculator.roll(1);
		calculator.roll(2);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(2, frameNumber);
	}

	@Test
	public void shouldHandleStrike() {
		// given

		calculator.roll(10);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(2, frameNumber);
	}

	@Test
	public void shouldHandleStrikeAndRoll() {
		// given
		calculator.roll(10);
		calculator.roll(1);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(2, frameNumber);
	}

	@Test
	public void shouldHandleStrikeAndTwoRolls() {
		// given
		calculator.roll(10);
		calculator.roll(1);
		calculator.roll(1);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(3, frameNumber);
	}

	@Test
	public void shouldHandle18_5and3Strikes() {
		// given
		for (int i = 0; i < 18; i++) {

			calculator.roll(5);
			
		}
		calculator.roll(10);
		calculator.roll(10);
		calculator.roll(10);
		// when
		int frameNumber = calculator.getFrameNumber();
		// then
		Assert.assertEquals(10, frameNumber);
	}
	
	@Test
	public void shouldGetProperScoreFor18_0and3Strikes() {
		// given
		for (int i = 0; i < 18; i++) {

			calculator.roll(0);
			
		} 
		calculator.roll(10);
		calculator.roll(10);
		calculator.roll(10);
		// when
		int frameNumber = calculator.getScore();
		// then
		Assert.assertEquals(30, frameNumber);
	}
	
	@Test
	public void shouldGetProperScoreFor18x0and123() {
		// given
		for(int i=0 ; i<18;i++)
		calculator.roll(0);
		calculator.roll(1);
		calculator.roll(2);
		calculator.roll(3);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(6, result);
	}
	
	@Test
	public void shouldGetProperScoreFor18x0and551() {
		// given
		for(int i=0 ; i<18;i++)
		calculator.roll(0);
		calculator.roll(5);
		calculator.roll(5);
		calculator.roll(1);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(11, result);
	}

	@Test
	public void shouldGetProperScoreFor18x0andX0X() {
		// given
		for(int i=0 ; i<18;i++)
		calculator.roll(0);
		calculator.roll(10);
		calculator.roll(0);
		calculator.roll(10);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(20, result);
	}
	
	
	@Test
	public void shouldGetProperScoreForZatti() {
		// given
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(7);
		calculator.roll(3);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(10);
		calculator.roll(8);
		calculator.roll(1);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(6);
		calculator.roll(1);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(127, result);
	}
	
	@Test
	public void shouldGetProperScoreForNaqib() {
		// given
		calculator.roll(6);
		calculator.roll(3);
		calculator.roll(0);
		calculator.roll(8);
		calculator.roll(8);
		calculator.roll(2);
		calculator.roll(8);
		calculator.roll(0);
		calculator.roll(8);
		calculator.roll(1);
		calculator.roll(10);
		calculator.roll(1);
		calculator.roll(0);
		calculator.roll(5);
		calculator.roll(2);
		calculator.roll(8);
		calculator.roll(2);
		calculator.roll(4);
		calculator.roll(5);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(94, result);
	}
	
	@Test
	public void shouldGetProperScoreForNaiim() {
		// given
		calculator.roll(6);
		calculator.roll(3);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(7);
		calculator.roll(0);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(7);
		calculator.roll(1);
		calculator.roll(0);
		calculator.roll(9);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(10);
		calculator.roll(10);
		calculator.roll(4);
		calculator.roll(1);
		
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(137, result);
	}
	
	@Test
	public void shouldGetProperScoreForZikri() {
		// given
		calculator.roll(10);
		calculator.roll(7);
		calculator.roll(3);
		calculator.roll(7);
		calculator.roll(2);
		calculator.roll(8);
		calculator.roll(1);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(7);
		calculator.roll(2);
		calculator.roll(1);
		calculator.roll(7);
		calculator.roll(7);
		calculator.roll(1);
		calculator.roll(5);
		calculator.roll(1);
		
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(114, result);
	}

	@Test
	public void shouldGetProperScoreForExampleFromPPT() {
		// given
		calculator.roll(10);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(5);
		calculator.roll(5);
		calculator.roll(7);
		calculator.roll(2);
		calculator.roll(10);
		calculator.roll(10);
		calculator.roll(10);
		calculator.roll(9);
		calculator.roll(0);
		calculator.roll(8);
		calculator.roll(2);
		calculator.roll(9);
		calculator.roll(1);
		calculator.roll(10);		
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(187, result);
	}
	
	@Test
	public void shouldGetProperScoreFor12x10() {
		// given
		for(int i=0;i<12;i++)
			calculator.roll(10);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(300, result);
	}
	
	@Test
	public void shouldGetProperScoreFor21x5() {
		// given
		for(int i=0;i<21;i++)
			calculator.roll(5);
		// when
		int result = calculator.getScore();
		// then
		Assert.assertEquals(150, result);
	}
	

	
	@Test
	public void shouldGetProperScore() {
		// given

		// when
		int score = calculator.getScore();
		// then
		Assert.assertEquals(0, score);
	}

	@Test
	public void shouldGetProperScoreWithRolls() {
		// given
		calculator.roll(10);
		// when
		int score = calculator.getScore();
		// then
		Assert.assertEquals(10, score);
	}

	
	@Test
	public void shouldGetProperScoreWithTwoRolls() {
		// given
		calculator.roll(1);
		calculator.roll(2);
		// when
		int score = calculator.getScore();
		// then
		Assert.assertEquals(3, score);
	}

	@Test
	public void shouldGetProperScoreWithStrikeAndTwoRolls() {
		// given
		calculator.roll(10);
		calculator.roll(1);
		calculator.roll(1);
		// when
		int score = calculator.getScore();
		// then
		Assert.assertEquals(14, score);
	}
	
	@Test
	public void shouldNotFinish() {
		// given
		calculator.roll(10);
		calculator.roll(1);
		calculator.roll(1);
		// when
		boolean gameover = calculator.isFinished();
		// then
		Assert.assertEquals(false, gameover);
	}
	
	@Test
	public void shouldDetectFinish() {
		// given
		for(int i=0 ; i< 21 ; i++)
			calculator.roll(5);
		// when
		boolean gameover = calculator.isFinished();
		// then
		Assert.assertEquals(true, gameover);
	}


	@Test
	public void shouldDetectFinish20rolls() {
		// given
		for(int i=0 ; i< 20 ; i++)
			calculator.roll(4);
		// when
		boolean gameover = calculator.isFinished();
		// then
		Assert.assertEquals(true, gameover);
	}

	
}
