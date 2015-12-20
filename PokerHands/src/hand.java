import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

public class hand {

	private TreeSet<Card> cards = new TreeSet<Card>(new MyCardComp());
	private int patternValue = 0;
	
	public int getPatternValue() {
		return patternValue;
	}

	public void setPatternValue(int patternValue) {
		this.patternValue = patternValue;
	}

	public hand(List<String> karty) {
		for (int i = 0; i < karty.size(); i++)
			cards.add(new Card(karty.get(i)));
	}

	public List<Integer> findPatterns() {
		
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();

		maxValuesToCompare = addIfNotNull(maxValuesToCompare,getHighestCard());		
		maxValuesToCompare = addIfNotNull(maxValuesToCompare,Pair()); // 1
		maxValuesToCompare = addIfNotNull(maxValuesToCompare,TwoPairs()); // 2 

		maxValuesToCompare = addIfNotNull(maxValuesToCompare,ThreeOfAKind()); // 3
		
		if(patternValue==0)
		maxValuesToCompare = addIfNotNull(maxValuesToCompare,Straight()); // 4
		
		maxValuesToCompare = addIfNotNull(maxValuesToCompare,Flush());  //5

		maxValuesToCompare = addIfNotNull(maxValuesToCompare,FullHouse()); // 6

		maxValuesToCompare = addIfNotNull(maxValuesToCompare,FourOfAKind()); // 7
		
		if(patternValue==5)
		maxValuesToCompare = addIfNotNull(maxValuesToCompare,StraightFlush()); //8 

		for(int i = maxValuesToCompare.size(); i < 5 ; i++)
		{
			maxValuesToCompare.add(0);
		}
			return maxValuesToCompare;
	}

	public List<Integer> addIfNotNull(List<Integer> maxValuesToCompare, List<Integer> valuesFromFunction)
	{
		if(valuesFromFunction.size()>0)
			maxValuesToCompare = valuesFromFunction;
		return maxValuesToCompare;
	}
	
	public List<Integer> StraightFlush() {
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();
		
		if(Flush().size()>0 && Straight().size()>0)
		{patternValue = 8;
		maxValuesToCompare=(getHighestCard());
		}
		return maxValuesToCompare;
	}

	public List<Integer> Flush() {
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();
		int color = cards.first().cardColorValue();
		boolean output = true;
		for (Card c : cards) {
			if (c.cardColorValue() != color)
				output = false;
		}
		if(output)
		{
			maxValuesToCompare=(getHighestCard());
			patternValue = 5;
		}
		return maxValuesToCompare;
	}

	public List<Integer> Straight() {

		List<Integer> maxValuesToCompare = new ArrayList<Integer>();

		if (cards.first().cardValue() - cards.last().cardValue() == 4)
		{	
				for(Card c : cards )
				maxValuesToCompare.add(c.cardValue());
				patternValue = 4;
		}
		return maxValuesToCompare;

	}

	public List<Integer> ThreeOfAKind() {

		List<Integer> maxValuesToCompare = new ArrayList<Integer>();

		Iterator<Card> it1 = cards.iterator();
		Iterator<Card> it2 = cards.iterator();
		Card element1 = it1.next();
		Card element2 = it2.next();
		element2 = it2.next();
		element2 = it2.next();

		for (int i = 0; i < 3; i++) {
			if (element1.cardValue() == element2.cardValue())
			{
				maxValuesToCompare.add(element1.cardValue());
				patternValue = 3;
			}
			if (i < 2)
			{	element1 = it1.next();
				element2 = it2.next();
				}
		}
		
		if(maxValuesToCompare.size()>0)
		if(cards.first().cardValue() == maxValuesToCompare.get(0))
			{
			element1 = it1.next();
			maxValuesToCompare.add(element1.cardValue());
			}
		else
		{
			maxValuesToCompare.add(cards.first().cardValue());
		}
		return maxValuesToCompare;

	}

	public List<Integer> TwoPairs() { 

		List<Integer> maxValuesToCompare = new ArrayList<Integer>();


		Iterator<Card> it1 = cards.iterator();
		Iterator<Card> it2 = cards.iterator();
		Card element1 = it1.next();
		Card element2 = it2.next();
		element2 = it2.next();

		if (element1.cardValue() == element2.cardValue()) // 2 ..
		{
			element1 = it1.next();
			element2 = it2.next();
			element1 = it1.next();
			element2 = it2.next();
			if (element1.cardValue() == element2.cardValue()) // 2 2
			{
				patternValue = 2;
//				System.out.println("Dwojka 2 2 1");
				if(element1.cardValue() > cards.first().cardValue())
				{
					maxValuesToCompare.add(element1.cardValue());
					maxValuesToCompare.add(cards.first().cardValue());
				}else
				{
					maxValuesToCompare.add(cards.first().cardValue());
					maxValuesToCompare.add(element1.cardValue());
				}
				maxValuesToCompare.add(cards.last().cardValue());
			} else
			{
				element1 = it1.next();
				element2 = it2.next();
				if (element1.cardValue() == element2.cardValue()) 
				{
					patternValue = 2;
					if(cards.last().cardValue() > cards.first().cardValue())
					{
						maxValuesToCompare.add(cards.last().cardValue());
						maxValuesToCompare.add(cards.first().cardValue());
					}else
					{
						maxValuesToCompare.add(cards.first().cardValue());
						maxValuesToCompare.add(cards.last().cardValue());
					}
					//TODO: srodkowa cyferke dodaj
					Iterator<Card> it3 = cards.iterator();
					Card element3 = it3.next();
					element3 = it3.next();
					element3 = it3.next();
					maxValuesToCompare.add(element3.cardValue());
					}
			}

		} else
		{
			element1 = it1.next();
			element2 = it2.next();
			if (element1.cardValue() == element2.cardValue()) {
				int tmp = element1.cardValue();
				element1 = it1.next();
				element2 = it2.next();
				element1 = it1.next();
				element2 = it2.next();

				if (element1.cardValue() == element2.cardValue()) 
				{
					patternValue = 2;
					if(element1.cardValue() > tmp)
					{
						maxValuesToCompare.add(element1.cardValue());
						maxValuesToCompare.add(tmp);
					}else
					{
						maxValuesToCompare.add(tmp);
						maxValuesToCompare.add(element1.cardValue());
					}
					maxValuesToCompare.add(cards.first().cardValue());

				}
			}

		}

		return maxValuesToCompare;
	}

	public List<Integer> Pair() {
		
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();
		
		Iterator<Card> it1 = cards.iterator();
		Iterator<Card> it2 = cards.iterator();
		Card element1 = it1.next();
		Card element2 = it2.next();
		element2 = it2.next();
		
		for (int i = 0; i < 4; i++)
		{
			if (element1.cardValue() == element2.cardValue())
			{	
			patternValue = 1;
			maxValuesToCompare.add(element1.cardValue());
			}
			if (i < 3)
			{ 
				element1 = it1.next();
				element2 = it2.next();
				}
		}
		
		Iterator<Card> it3 = cards.iterator();
		Card element3 = it3.next();
		element3 = it3.next(); 
		element3 = it3.next(); 
			
		if(maxValuesToCompare.size()>0)
		{
		if(maxValuesToCompare.get(0) == cards.first().cardValue())
		{maxValuesToCompare.add(element3.cardValue());}
		else
		maxValuesToCompare.add(cards.first().cardValue());
		}
		
		return	maxValuesToCompare;
	
	}

	public List<Integer> FullHouse() {
		
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();		
		Iterator<Card> it = cards.iterator();
		Card element = it.next();
		element = it.next();// second card

		if (cards.first().cardValue() == element.cardValue()) // dwojka
		{
			element = it.next();
			if (element.cardValue() == cards.last().cardValue()) // potem trojka
			{
			patternValue = 6;
			maxValuesToCompare.add(cards.last().cardValue());
			maxValuesToCompare.add(cards.first().cardValue());
			}
		}

		if (element.cardValue() == cards.first().cardValue())// trojka
		{	
			element = it.next();
			if (element.cardValue() == cards.last().cardValue()) // i dwojka
			{
				patternValue = 6;
				maxValuesToCompare.add(cards.first().cardValue());
				maxValuesToCompare.add(cards.last().cardValue());
			}
		}
		return maxValuesToCompare;
	}

	protected List<Integer> FourOfAKind() {
		
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();	
		
		Iterator<Card> it = cards.iterator();
		Card element = it.next();

		element = it.next();
		if (element.cardValue() == cards.last().cardValue())
		{
			maxValuesToCompare.add(cards.last().cardValue());
			maxValuesToCompare.add(cards.first().cardValue());
			patternValue = 7;
		}
			
		element = it.next();
		element = it.next();
		if (element.cardValue() == cards.first().cardValue())
		{
			maxValuesToCompare.add(cards.last().cardValue());
			maxValuesToCompare.add(cards.first().cardValue());
			patternValue = 7;
		}
		
		return maxValuesToCompare;
	}

	public List<Integer> getHighestCard() {
		List<Integer> maxValuesToCompare = new ArrayList<Integer>();
		for(Card c : cards )
		maxValuesToCompare.add(c.cardValue());
		
		return maxValuesToCompare;
	}
	
	public int compareTo(hand opponent)
	{
		int result = 0;
		final int IWIN = 1;
		final int OPPONENT_WINS = -1;
		
		if(this.getPatternValue() > opponent.getPatternValue())
		result = IWIN;
		if(this.getPatternValue() < opponent.getPatternValue())
		result = OPPONENT_WINS;
		
		if(result == 0)
		{
			List<Integer> myCardsValues  = this.findPatterns();
			List<Integer> hisCardsValues = opponent.findPatterns();
			for(int i = 0 ; i < myCardsValues.size() ; i++)
			{	
				if(myCardsValues.get(i) > hisCardsValues.get(i))
				{result = IWIN; break;}
				
				if(myCardsValues.get(i) < hisCardsValues.get(i))
				{result = OPPONENT_WINS; break;}

			}
		}
		return result;
	}

	public void display() {
		System.out.println(cards);
}
	
}