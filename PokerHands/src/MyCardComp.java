import java.util.Comparator;

public class MyCardComp implements Comparator<Card>{

@Override
public int compare(Card o1, Card o2) {
	
	int result = 0;
	if(o1.cardValue() > o2.cardValue()) result = -1;
	if(o1.cardValue() <= o2.cardValue()) result = 1;
	return result;
}

}
