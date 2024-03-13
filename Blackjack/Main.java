//Description: A very badly programmed game of blackjack
//Author: Kevin Yang
//Date: July 14, 2021
//Edit date: December 4, 2021

import java.util.*;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int cardDealt, balance = 100000, total, wager;
    char status, gameStatus;
    Deck deck = new Deck();
    Hand dealer;
    ArrayList<Hand> player = new ArrayList<Hand>();
    ArrayList<Integer> splitsMade = new ArrayList<Integer>();
    ArrayList<Integer> wagers = new ArrayList<Integer>();
    ArrayList<String> splitCards = new ArrayList<String>();

    System.out.print("Welcome to blackjack! (press enter) ");
    scanner.nextLine();

    System.out.println("\nYour balance is $" + balance + ".");

    do {
      wager = 0;
      cardDealt = 0;
      player.add(new Hand());
      dealer = new Hand();
      splitsMade.add(0);

      System.out.print("\nPlease enter your wager: $");
      do {
        wager = inputValidation();
        if (wager > 10000 || balance - wager < 0) {
          System.out.print("Please enter a smaller amount: $");
        }
        if (wager <= 0) {
          System.out.print("Please enter a bigger amount: $");
        }
      }
      while (wager > 10000 || balance - wager < 0 || wager <= 0);

      wagers.add(wager);

      for (int n = 0; n < 2; n++){
        player.get(0).getCard(deck.deck[cardDealt]);
        cardDealt++;
      } 

      dealer.getCard(deck.deck[cardDealt]);
      cardDealt += 2;
      dealer.cards.add("?");

      if (player.get(0).handValue < 21) {
        dealer.displayCards("Dealer");
        player.get(0).displayCards("Your");
      }
      else {
        scanner.nextLine();
      }

      for (int i = 0; i < player.size(); i++) {
        total = 0;
        for (int x = 0; x < wagers.size(); x++) {
          total += wagers.get(x);
        }
        total += splitCards.size() * wager;

        if (player.get(i).handValue >= 21 || splitsMade.get(i) == 2 && player.get(i).cards.get(0).charAt(1) == 'A') {
          gameStatus = 'X';
        }
        else if (splitsMade.get(i) < 2 && player.get(i).cardNumber == 2 && player.get(i).getValue(player.get(i).cards.get(0)) == player.get(i).getValue(player.get(i).cards.get(1)) && balance - (total + wager) >= 0) {
          System.out.print("\nEnter your option (\'W\' to split, \'X\' to stand, \'Y\' to hit, \'Z\' to double): ");
          gameStatus = scanner.next().toUpperCase().charAt(0);
          while (gameStatus != 'W' && gameStatus != 'X' && gameStatus != 'Y' && gameStatus != 'Z') {
            System.out.print("Please enter a valid option (\'W\' to split, \'X\' to stand, \'Y\' to hit, \'Z\' to double): ");
            gameStatus = scanner.next().toUpperCase().charAt(0);
          }
          scanner.nextLine();
        }
        else if (player.get(i).cardNumber == 2 && balance - (total + wager) >= 0) {
          System.out.print("\nEnter your option (\'X\' to stand, \'Y\' to hit, \'Z\' to double): ");
          gameStatus = scanner.next().toUpperCase().charAt(0);
          while (gameStatus != 'X' && gameStatus != 'Y' && gameStatus != 'Z') {
            System.out.print("Please enter a valid option (\'X\' to stand, \'Y\' to hit, \'Z\' to double): ");
            gameStatus = scanner.next().toUpperCase().charAt(0);
          }
          scanner.nextLine();
        }
        else {
          System.out.print("\nEnter your option (\'X\' to stand, \'Y\' to hit): ");
          gameStatus = scanner.next().toUpperCase().charAt(0);
          while (gameStatus != 'X' && gameStatus != 'Y') {
            System.out.print("Please enter a valid option (\'X\' to stand, \'Y\' to hit): ");
            gameStatus = scanner.next().toUpperCase().charAt(0);
          }
          scanner.nextLine();
        }
        
        if (gameStatus == 'W') {
          splitsMade.set(i, splitsMade.get(i) + 1);
          splitCards.add(player.get(i).cards.get(1));
          player.get(i).handValue -= player.get(i).getValue(player.get(i).cards.get(1));
          player.get(i).cards.remove(1);
          player.get(i).cardNumber--;
          if (player.get(i).cards.get(0).charAt(1) == 'A') {
            splitsMade.set(i, 2);
            player.get(i).handValue += 10;
          }
        }
        if (gameStatus == 'W' || gameStatus == 'Y' || gameStatus == 'Z') {
          player.get(i).getCard(deck.deck[cardDealt]);
          cardDealt++;
          if (gameStatus == 'Z') {
            wagers.set(i, wagers.get(i) * 2);
          }
        }

        if (player.size() > 1 || splitCards.size() > 0) {
          System.out.println("\nHand " + (i + 1) + ":");
        }
        else {
          dealer.displayCards("Dealer");
        }
        player.get(i).displayCards("Your");

        if (player.get(i).handValue > 21) {
          System.out.println("\nBust!");
        }
        if (player.get(i).handValue >= 21 || player.get(i).handValue < 21 && (gameStatus == 'X' || gameStatus == 'Z')) {
          System.out.print("\nHit enter to continue ");
          scanner.nextLine();
        }

        if ((gameStatus == 'W' || gameStatus == 'Y') && player.get(i).handValue < 21) {
          i--;
        }
        else if (gameStatus == 'X' || gameStatus == 'Z' || player.get(i).handValue >= 21) {
          if (splitCards.size() > 0) {
            wagers.add(wager);
            splitsMade.add(1);
            player.add(new Hand());
            player.get(i + 1).getCard(splitCards.get(splitCards.size() - 1));
            splitCards.remove(splitCards.size() - 1);
            player.get(i + 1).getCard(deck.deck[cardDealt]);
            cardDealt++;
            if (player.get(i + 1).handValue < 21) {
              System.out.println("\nHand " + (i + 2) + ":");
              player.get(i + 1).displayCards("Your");
            }
            if (player.get(i + 1).cards.get(0).charAt(1) == 'A') {
              splitsMade.set(i + 1, 2);
            }
          }
        }
      }

      if (player.size() == 1 && player.get(0).handValue <= 21 || player.size() > 1) {
        dealer.cards.remove(1);
        dealer.getCard(deck.deck[3]);
        dealer.displayCards("Dealer");
        if (player.size() == 1) {
          player.get(0).displayCards("Your");
        }
      }
      if (player.size() == 1 && player.get(0).handValue == 21 && player.get(0).cardNumber == 2) {
        System.out.print("\nHit enter to continue ");
        scanner.nextLine();
      }
      else if (player.size() > 1 || player.get(0).handValue < 21 || player.get(0).handValue == 21 && player.get(0).cardNumber > 2) {
        while (dealer.handValue < 17) {
          System.out.print("\nHit enter for dealer to hit ");
          scanner.nextLine();

          dealer.getCard(deck.deck[cardDealt]);
          cardDealt++; 

          dealer.displayCards("Dealer");
          if (player.size() == 1) {
            player.get(0).displayCards("Your");
          }
        }
        if (dealer.handValue > 21) {
          System.out.println("\nDealer busts. ");
        }
        System.out.print("\nHit enter to continue ");
        scanner.nextLine();
      }

      for (int i = 0; i < player.size(); i++) {
        if (player.size() > 1) {
          System.out.println("\nHand " + (i + 1) + ":");
        }
        dealer.displayCards("Dealer");
        player.get(i).displayCards("Your");

        if (player.get(i).handValue == 21 && player.get(i).cardNumber == 2 && player.size() == 1 && dealer.handValue < 21) {
          System.out.println("\nBlackjack!");
        }

        if (player.get(i).handValue > 21 || dealer.handValue > player.get(i).handValue && dealer.handValue <= 21 || dealer.handValue == 21 && dealer.cardNumber == 2 && (player.get(i).handValue < 21 || player.get(i).cardNumber > 2)) {
          balance -= wagers.get(i);
          System.out.println("\nYou lose.");
        }
        else if (dealer.handValue > 21 || dealer.handValue < player.get(i).handValue && player.get(i).handValue <= 21) {
          balance += wagers.get(i);
          System.out.println("\nYou win!");
        }
        else if (dealer.handValue == player.get(i).handValue) {
          System.out.println("\nPush!");
          System.out.println("\nIt's a tie!");
        }

        System.out.print("\nYour balance is $" + balance + ". ");

        if (player.size() > 1 && i != player.size() - 1) {
          System.out.println();
          System.out.print("\nHit enter to continue ");
          scanner.nextLine();
        }
      }

      player.clear();
      splitsMade.clear();
      wagers.clear();
      splitCards.clear();

      if (balance <= 0) {
        status = 'N';
      }
      else {
        System.out.print("Would you like to go again (Y/N)? ");
        status = scanner.next().toUpperCase().charAt(0);
        while (status != 'Y' && status != 'N') {
          System.out.print("Please enter \'Y\' or \'N\': ");
          status = scanner.next().toUpperCase().charAt(0);
        }
      }

      deck.shuffle(cardDealt);
    }
    while (status == 'Y');

    if (balance > 0) {
      System.out.println("\nYou left with a balance of $" + balance);
    }
    else {
      System.out.println("Better luck next time!");
    }
    
    System.out.println("\nThanks for playing!");
  }

  public static int inputValidation() {
    boolean isValid;
    int wager = 0;
    
    do {
      isValid = true;
      try {
        wager = scanner.nextInt();
      }
      catch (Exception e) {
        isValid = false;
        scanner.nextLine();
        System.out.print("Invalid input! Please enter an integer for yor wager amount: $");
      }
    }
    while (!isValid);

    return wager;
  }
}