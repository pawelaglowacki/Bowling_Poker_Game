import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.Test;

public class PokerHandsTest {

	private PokerHands poker;
	private hand player1;
	private hand player2;

	@Before
	public void setUp() {
		poker = new PokerHands();
	}

	@Test
	public void testRound() throws FileNotFoundException {

		int result = poker.play();
		Assert.assertEquals(376, result);
	}

	@Test
	public void testHighCard() throws FileNotFoundException {
		// given

		List<String> cards1 = Arrays.asList("KC", "KC", "3S", "JC", "KD");
		List<String> cards2 = Arrays.asList("2C", "8D", "AH", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testHighCardDrawHighCard() throws FileNotFoundException {
		// given

		List<String> cards1 = Arrays.asList("AC", "KC", "3S", "JC", "KD");
		List<String> cards2 = Arrays.asList("2C", "8D", "AH", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testPairAgainstAce() throws FileNotFoundException {
		// given

		List<String> cards1 = Arrays.asList("KC", "KC", "3S", "JC", "KD");
		List<String> cards2 = Arrays.asList("2C", "QD", "AH", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testHCAgainstHC() throws FileNotFoundException {
		// given

		List<String> cards1 = Arrays.asList("KC", "AC", "3S", "JC", "4D");
		List<String> cards2 = Arrays.asList("2C", "QD", "AH", "KS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testStraightAgainstFlush() {
		// given

		List<String> cards1 = Arrays.asList("2C", "3C", "4S", "5C", "6D");
		List<String> cards2 = Arrays.asList("2S", "QS", "AS", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(4, player1.getPatternValue());
		Assert.assertEquals(5, player2.getPatternValue());
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testHCAgainstPair() {
		// given

		List<String> cards1 = Arrays.asList("AC", "3C", "4S", "5C", "6D");
		List<String> cards2 = Arrays.asList("2S", "QS", "AD", "3S", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(0, player1.getPatternValue());
		Assert.assertEquals(1, player2.getPatternValue());
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testPairAgainstThree() {
		// given

		List<String> cards1 = Arrays.asList("AC", "3C", "AS", "5C", "6D");
		List<String> cards2 = Arrays.asList("2S", "QS", "2D", "3S", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, player1.getPatternValue());
		Assert.assertEquals(3, player2.getPatternValue());
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testPair() {
		// given

		List<String> cards1 = Arrays.asList("AC", "KC", "3S", "3C", "2D");
		List<String> cards2 = Arrays.asList("QC", "QD", "8H", "JS", "4S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		player1.findPatterns();
		player2.findPatterns();
		int result = player1.compareTo(player2);
		// then

		Assert.assertEquals(-1, result);
	}

	@Test
	public void shouldGetProperPatternValueForHighestCard() {
		// given

		List<String> cards1 = Arrays.asList("KC", "QD", "TS", "JC", "4D");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(0, result);
	}

	@Test
	public void shouldGetProperPatternValueForPair() {
		// given

		List<String> cards1 = Arrays.asList("KC", "3D", "TS", "3C", "AD");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void shouldGetProperPatternValueFor2Pairs() {
		// given

		List<String> cards1 = Arrays.asList("3S", "3D", "TS", "2C", "TD");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(2, result);
	}

	@Test
	public void shouldGetProperPatternValueFor3() {
		// given

		List<String> cards1 = Arrays.asList("3S", "3D", "TS", "3C", "QD");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(3, result);
	}

	@Test
	public void shouldGetProperPatternValueForFull() {
		// given

		List<String> cards1 = Arrays.asList("3S", "3D", "TS", "3C", "TD");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(6, result);
	}

	@Test
	public void shouldGetProperPatternValueForFourOfAKind() {
		// given

		List<String> cards1 = Arrays.asList("2S", "2D", "2C", "3C", "2H");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(7, result);
	}

	@Test
	public void shouldGetProperPatternValueForStraight() {
		// given

		List<String> cards1 = Arrays.asList("2S", "3D", "4C", "5C", "6H");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(4, result);
	}

	@Test
	public void shouldGetProperPatternValueForFlush() {
		// given

		List<String> cards1 = Arrays.asList("2D", "3D", "4D", "5D", "7D");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(5, result);
	}

	@Test
	public void shouldGetProperPatternValueForStraightFlush() {
		// given

		List<String> cards1 = Arrays.asList("2D", "3D", "4D", "5D", "6D");

		player1 = new hand(cards1);
		// when
		player1.findPatterns();
		int result = player1.getPatternValue();
		// then
		Assert.assertEquals(8, result);
	}

	@Test
	public void testHighCards() {
		// given

		List<String> cards1 = Arrays.asList("AC", "KC", "3S", "JC", "5D");
		List<String> cards2 = Arrays.asList("2C", "8D", "AH", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testPairs() {
		// given

		List<String> cards1 = Arrays.asList("KD", "KC", "3S", "JC", "KD");
		List<String> cards2 = Arrays.asList("AC", "8D", "AH", "QS", "AS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testPairsDraw() {
		// given

		List<String> cards1 = Arrays.asList("KD", "KC", "3S", "JC", "KD");
		List<String> cards2 = Arrays.asList("KC", "8D", "AH", "QS", "KS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testTwoPair() {
		// given

		List<String> cards1 = Arrays.asList("KD", "KC", "JS", "JC", "KD");
		List<String> cards2 = Arrays.asList("KC", "8D", "AH", "AS", "KS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testTwoPair221() {
		// given

		List<String> cards1 = Arrays.asList("KD", "KC", "JS", "JC", "TD");
		List<String> cards2 = Arrays.asList("KC", "KD", "JH", "JS", "5S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testTwoPair212() {
		// given

		List<String> cards1 = Arrays.asList("KD", "KC", "TS", "JC", "TD");
		List<String> cards2 = Arrays.asList("KC", "KD", "TH", "QS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testTwoPair122() {
		// given

		List<String> cards1 = Arrays.asList("QD", "QC", "TS", "AC", "TD");
		List<String> cards2 = Arrays.asList("QC", "QD", "TH", "KS", "TS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testTwoPairDraw() {
		// given

		List<String> cards1 = Arrays.asList("KD", "TC", "JS", "JC", "KD");
		List<String> cards2 = Arrays.asList("KC", "8D", "JH", "JS", "KS");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
		Assert.assertEquals(2, player1.getPatternValue());

	}

	@Test
	public void testThree() {
		// given

		List<String> cards1 = Arrays.asList("KS", "KC", "AS", "2C", "KD");
		List<String> cards2 = Arrays.asList("2C", "2D", "JH", "JS", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testThreeDraw() {
		// given

		List<String> cards1 = Arrays.asList("2D", "2C", "2S", "JC", "KD");
		List<String> cards2 = Arrays.asList("2C", "2D", "JH", "QS", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(1, result);
	}

	@Test
	public void testFull() {
		// given

		List<String> cards1 = Arrays.asList("2D", "2C", "2S", "JC", "JD");
		List<String> cards2 = Arrays.asList("2C", "2D", "KH", "KS", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testFour() {
		// given

		List<String> cards1 = Arrays.asList("2D", "2C", "2S", "2H", "JD");
		List<String> cards2 = Arrays.asList("3C", "3D", "3H", "KS", "3S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testFourDraw() {
		// given

		List<String> cards1 = Arrays.asList("2D", "2C", "2S", "2H", "JD");
		List<String> cards2 = Arrays.asList("2C", "2D", "2H", "KS", "2S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testStrike() {
		// given

		List<String> cards1 = Arrays.asList("2D", "3C", "4S", "5H", "6D");
		List<String> cards2 = Arrays.asList("7C", "6D", "5H", "3S", "4S");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testFlush() {
		// given

		List<String> cards1 = Arrays.asList("2D", "3D", "7D", "5D", "6D");
		List<String> cards2 = Arrays.asList("TD", "6D", "7D", "3D", "4D");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

	@Test
	public void testStraightFlush() {
		// given

		List<String> cards1 = Arrays.asList("2D", "3D", "4D", "5D", "6D");
		List<String> cards2 = Arrays.asList("7D", "6D", "5D", "3D", "4D");

		player1 = new hand(cards1);
		player2 = new hand(cards2);
		// when
		int result = player1.compareTo(player2);
		// then
		Assert.assertEquals(-1, result);
	}

}