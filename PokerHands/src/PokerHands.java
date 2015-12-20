import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PokerHands {

public int play() throws FileNotFoundException
{
	int countPlayer1Wins = 0;

	File file = new File("poker.txt");
	Scanner in = new Scanner(file);

	String line;

	while(in.hasNext())
	{
	line = in.nextLine();
	
	
	String[] parts = line.split(" ");
	
	List<String> cardsForFirstPlayer = new ArrayList<String>();
	List<String> cardsForSecondPlayer = new ArrayList<String>();
	int result;
	
	for(int i = 0; i<5 ; i++)
	{
		cardsForFirstPlayer.add(parts[i]);
	}
	for(int i = 5; i<10 ; i++)
	{
		cardsForSecondPlayer.add(parts[i]);
	}
	
	hand player1 = new hand(cardsForFirstPlayer);
	hand player2 = new hand(cardsForSecondPlayer);

	 
	player1.findPatterns();
    player2.findPatterns();

	result = 0;
	result = player1.compareTo(player2);
	if(result == 1)
	countPlayer1Wins++;
	
	}
	return countPlayer1Wins;
}


public int oneround() throws FileNotFoundException
{

	File file = new File("poker.txt");
	Scanner in = new Scanner(file);

	String line;

	line = in.nextLine();
	System.out.println(line);
	
	
	String[] parts = line.split(" ");
	
	List<String> cardsForFirstPlayer = new ArrayList<String>();
	List<String> cardsForSecondPlayer = new ArrayList<String>();
	
	for(int i = 0; i<5 ; i++)
	{
		cardsForFirstPlayer.add(parts[i]);
	}
	for(int i = 5; i<10 ; i++)
	{
		cardsForSecondPlayer.add(parts[i]);
	}
	
	hand player1 = new hand(cardsForFirstPlayer);
	hand player2 = new hand(cardsForSecondPlayer);

	int result = 0;
	result = player1.compareTo(player2);
	
	return result;

}


}
