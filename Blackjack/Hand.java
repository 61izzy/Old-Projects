import java.util.ArrayList;

public class Hand {
  ArrayList<String> cards = new ArrayList<String>();
  int handValue = 0, aceCount = 0, cardNumber = 0;

  public void getCard(String card) {
    cards.add(card);
    handValue += getValue(card);
    ace(card);
    cardNumber++;
  }

  public int getValue(String card) {
    if (card.charAt(1) == 'J' || card.charAt(1) == 'Q' || card.charAt(1) == 'K') {
      return 10;
    }
    else if (card.charAt(1) == 'A') {
      return 11;
    }
    else {
      if (Integer.parseInt(String.valueOf(card.charAt(1))) == 1) {
        return 10;
      }
      else {
        return Integer.parseInt(String.valueOf(card.charAt(1)));
      }
    }
  }

  public void ace(String card) {
    if (card.charAt(1) == 'A') {
      aceCount++;
    }
    if (handValue > 21 && aceCount > 0) {
      handValue -= 10;
      aceCount--;
    }
  }

  public void displayCards(String person) {
    System.out.println("\n" + person + " cards:");
    System.out.println(cards);
    System.out.println(person + " hand value: " + handValue);
  }
}