public class Deck {
  String[] deck = new String[52];

  public Deck() {
    String[] suit = {"♣", "♦", "♥", "♠"}, card = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    for (int i = 0; i < 52; i++) {
      deck[i] = suit[i/13] + card[i%13];
      int r = (int)(Math.random() * i);
      String temp = deck[r];
      deck[r] = deck[i];
      deck[i] = temp;
    }
  }

  public void shuffle(int cardDealt) {
    String[] bottom = new String[cardDealt];

    for (int i = 0; i < cardDealt; i++) {
      bottom[i] = deck[i];
    } 

    for (int i = 0; i < 52; i++) {
      if (i + cardDealt > 51) {
        deck[i] = bottom[i - (52 - cardDealt)];
      }
      else {
        deck[i] = deck[i + cardDealt];
      }
    }
  }
}