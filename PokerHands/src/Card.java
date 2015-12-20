import java.util.Comparator;

public class Card implements Comparator<Card>{
	
private String card;
private int value;

public Card(String x)
{
	card = x;
	value = cardValue();
}

public Card(Card x)
{
	card = x.card;
	value = x.value;
}

public int cardValue()
{
	int result=0;
	char firstCharacter = card.charAt(0);
	result = Character.getNumericValue(firstCharacter);
	if(firstCharacter == 'T') result = 10;
	if(firstCharacter == 'J') result = 11;
	if(firstCharacter == 'Q') result = 12;
	if(firstCharacter == 'K') result = 13;
	if(firstCharacter == 'A') result = 14;
							
	return result;
}

public int cardColorValue()
{
	int result = 0;
	char firstCharacter = card.charAt(1);
	if(firstCharacter == 'S') result = 1;
	if(firstCharacter == 'H') result = 2;
	if(firstCharacter == 'D') result = 3;
	if(firstCharacter == 'C') result = 4;							

	return result;	
}

@Override
public int compare(Card o1, Card o2) {
	
	int result = 0;
	if(o1.cardValue() > o2.cardValue()) result = 1;
	if(o1.cardValue() <= o2.cardValue()) result = -1;
	return result;
}

public void display() {
System.out.println(card);	
}

@Override
public String toString() {
	return card;
}


}
