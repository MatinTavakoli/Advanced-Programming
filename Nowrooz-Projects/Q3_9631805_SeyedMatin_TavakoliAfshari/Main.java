import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * Simulating a jungle like environment!
     *
     * @author Matin Tavakoli
     * ANSI codes are for a better display!
     * parameters are explained in the comments
     */
    public static void main(String[] args) {
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Jungle!!");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        for (int a = 0; a < 1; a++) {
            System.out.println("Please Press '1' if you want to play with another player & '2' id you want to play with the computer!");
            String mode1 = sc.next();
            if (!mode1.equals("1") && !mode1.equals("2")) {
                System.out.println("You're mode is neither '1' nor '2'.Please try again!");
                a--;//This goes on until the user gives us 1 or 2.
            } else if (mode1.equals("1")) {
                Player player[] = new Player[2];
                player[0] = new Player(1);
                player[1] = new Player(2);
                Card[][][] dealCards = new Card[2][3][10];//When dealing,we have two sets of 30 cards.One set for player1 and the other set for player2!
                Card[][] gameCards = new Card[2][10];//When the game starts,we have two sets of 10 cards.One set for player1 and the other set for player2!
                Animal[][][] animals = new Animal[2][3][10];//We have two sets of 30 cards.One for player1 and the other one for player2!
                int numberOfAnimals[][] = new int[2][12];//For example if player1 has 2 lions so numberOfAnimals[0][0]=2
                Table table;//Making a table to display our cards!
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 12; j++) {
                        numberOfAnimals[i][j] = 0;//Because at the beginning of the game,none or the player has any cards.Therefore,no animals!
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 10; k++) {
                            for (int l = 0; l < 1; l++) {
                                int cardNumber = Math.abs(new Random().nextInt() % 12) + 1;//Making a random number so we can choose an animal!
                                if (cardNumber == 1) {
                                    if (numberOfAnimals[i][0] < 5) {
                                        animals[i][j][k] = new Animal("Lion");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][0]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 2) {
                                    if (numberOfAnimals[i][1] < 5) {
                                        animals[i][j][k] = new Animal("Bear");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][1]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 3) {
                                    if (numberOfAnimals[i][2] < 5) {
                                        animals[i][j][k] = new Animal("Tiger");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][2]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 4) {
                                    if (numberOfAnimals[i][3] < 5) {
                                        animals[i][j][k] = new Animal("Vulture");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][3]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 5) {
                                    if (numberOfAnimals[i][4] < 5) {
                                        animals[i][j][k] = new Animal("Fox");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][4]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 6) {
                                    if (numberOfAnimals[i][5] < 5) {
                                        animals[i][j][k] = new Animal("Elephant");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][5]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 7) {
                                    if (numberOfAnimals[i][6] < 5) {
                                        animals[i][j][k] = new Animal("Wolf");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][6]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 8) {
                                    if (numberOfAnimals[i][7] < 5) {
                                        animals[i][j][k] = new Animal("Boar");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][7]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 9) {
                                    if (numberOfAnimals[i][8] < 5) {
                                        animals[i][j][k] = new Animal("Hippo");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][8]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 10) {
                                    if (numberOfAnimals[i][9] < 5) {
                                        animals[i][j][k] = new Animal("Cow");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][9]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 11) {
                                    if (numberOfAnimals[i][10] < 5) {
                                        animals[i][j][k] = new Animal("Rabbit");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][10]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 12) {
                                    if (numberOfAnimals[i][11] < 5) {
                                        animals[i][j][k] = new Animal("Turtle");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][11]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                }
                            }
                        }
                    }
                }
                table = new Table(dealCards);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        table.display1(player[i]);
                        for (int k = 0; k < 1; k++) {
                            if (player[i].getNumberOfCards() != 0 && player[i].getNumberOfCards() != 10)
                                System.out.println("You still have " + (10 - player[i].getNumberOfCards()) + " card(s) left!");
                            System.out.println("Player #" + (i + 1) + ".Please select a number from 1 to 30!(excluding the ones that you already picked)");
                            String cardNumberString = sc.next();//cardNumber is the number of the card the user wants to select!
                            if (cardNumberString != null && cardNumberString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                int cardNumber = Integer.parseInt(cardNumberString);
                                if (cardNumber >= 1 && cardNumber <= 30) {
                                    if (cardNumber != 10 && cardNumber != 20 && cardNumber != 30) {
                                        if (dealCards[i][cardNumber / 10][cardNumber % 10 - 1].getIsOnTheTable() == 2) {//If it is on the table...
                                            player[i].addCard(dealCards[i][cardNumber / 10][cardNumber % 10 - 1]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                            dealCards[i][cardNumber / 10][cardNumber % 10 - 1].setIsOnTheTable(0);//Because this card is no longer on the board!
                                        } else {
                                            System.out.println("That card has been selected before,Please try again!");
                                            k--;//This goes on until the user picks a card which he hasn't picked yet!)
                                        }
                                    } else {
                                        if (dealCards[i][cardNumber / 10 - 1][9].getIsOnTheTable() == 2) {//If it is on the table...
                                            player[i].addCard(dealCards[i][cardNumber / 10 - 1][9]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                            dealCards[i][cardNumber / 10 - 1][9].setIsOnTheTable(0);//Because this card is no longer on the board!
                                        } else {
                                            System.out.println("That card has been selected before,Please try again!");
                                            k--;//This goes on until the user picks a card which he hasn't picked yet!)
                                        }
                                    }
                                } else {
                                    System.out.println("The number you entered is out of bounds.Please try again!");
                                    k--;
                                }
                            } else {
                                System.out.println("You're input is not an integer.Please try again!");
                                k--;
                            }
                        }
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        gameCards[i][j] = new Card(player[i].cards[j].getAnimal());//With this trick,our isOnTheTable switch's back to true(because we are making new objects and sending the player's cards details to the gameCards constructor!)
                    }
                }
                table = new Table(gameCards);//Our table has a new form!
                table.display2();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (player[i].getCards()[j].getAnimal().getName().equals("Lion")) {
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                            player[i].setAnimalsThatKill(player[i].getAnimalsThatKill() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Bear")) {
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                            player[i].setAnimalsThatKill(player[i].getAnimalsThatKill() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Tiger")) {
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                            player[i].setAnimalsThatKill(player[i].getAnimalsThatKill() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Vulture")) {
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Fox")) {
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Elephant")) {
                            player[i].setAnimalsThatInjure(player[i].getAnimalsThatInjure() + 1);
                            player[i].setAnimalsThatAttack(player[i].getAnimalsThatAttack() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Wolf")) {
                            player[i].setAnimalsThatKill(player[i].getAnimalsThatKill() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Boar")) {
                            player[i].setAnimalsThatInjure(player[i].getAnimalsThatInjure() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Hippo")) {
                            player[i].setAnimalsThatAttack(player[i].getAnimalsThatAttack() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Cow")) {
                            player[i].setAnimalsThatAttack(player[i].getAnimalsThatAttack() + 1);
                            player[i].setAnimalsThatWound(player[i].getAnimalsThatWound() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Rabbit")) {
                            player[i].setAnimalsThatBite(player[i].getAnimalsThatBite() + 1);
                        } else if (player[i].getCards()[j].getAnimal().getName().equals("Turtle")) {
                            player[i].setAnimalsThatBite(player[i].getAnimalsThatBite() + 1);
                        }

                    }
                }
                for (int i = 0; i < 2; i++) {
                    System.out.println(ANSI_CYAN + "Player #" + (i + 1) + "!    You have " + player[i].getAnimalsThatBite() + " animal(s) that Bite(s)" + ", " + player[i].getAnimalsThatKill() + " animal(s) that Kill(s)" + ", " + player[i].getAnimalsThatWound() + " animal(s) that Wound(s)" + ", " + player[i].getAnimalsThatAttack() + " animal(s) that Attack(s)" + " & " + player[i].getAnimalsThatInjure() + " animal(s) that Injure(s)." + ANSI_RESET);
                }
                System.out.println();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
                int respawn[] = new int[2];//We have two players that respawn!
                for (int i = 0; i < 2; i++) {
                    respawn[i] = 0;//At the beginning of the game,we have not used any respawns yet!
                }
                table.display2();
                for (int j = 0; j != -1; j++) {//At the end of the game,we will turn it to -1!
                    for (int i = 0; i < 2; i++) {
                        ArrayList<Card> attackingCards = new ArrayList<>();//Every round,one of the players picks a certain number of cards to attack.we use an arraylist to keep those cards!
                        ArrayList<String> attackingCardsMethods = new ArrayList<>();//Every round,one of the players picks a certain number of cards to attack.we use an arraylist to keep there attack methods!
                        ArrayList<Integer> attackingCardsMethodsEnergy = new ArrayList<>();
                        ArrayList<Integer> attackingCardsNumbers = new ArrayList<>();
                        int totalAttackEnergy = 0;//The amount of energy that is summed each round!
                        for (int f = 0; f < 1; f++) {
                            System.out.println("Player #" + (i + 1) + "!Please enter '1' if you want to attack or '2' if you want to respawn one of your cards");
                            String mode2 = sc.next();
                            if (mode2.equals("1") || mode2.equals("2")) {
                                if (mode2.equals("1")) {
                                    System.out.println("You can select a number of cards to attack to one of your opponent's cards.Don't forget that your animals must damage your opponent's card in one way(This means that an animal can't injure your opponent's card and the other one bites it!)");
                                    for (int k = 0; k < 1; k++) {
                                        System.out.println("Please determine the number of cards you want to select to attack to one of your opponent's card!(A number from 1 to the number of cards you have on the table)");
                                        String integerString = sc.next();
                                        if (integerString != null && integerString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                            int integer = Integer.parseInt(integerString);//An integer that the user gives us so he can determine the number of cards he/she wants to select to attack with!
                                            if (integer >= 1 && integer <= player[i].getNumberOfCards()) {
                                                if (integer > player[i].getAnimalsThatBite() && integer > player[i].getAnimalsThatKill() && integer > player[i].getAnimalsThatWound() && integer > player[i].getAnimalsThatAttack() && integer > player[i].getAnimalsThatInjure()) {
                                                    System.out.println("With this amount of cards,you can't organize an attack for your animals.Please try again!");
                                                    k--;
                                                } else {
                                                    System.out.println("Alright!");
                                                    try {
                                                        Thread.sleep(1000);
                                                    } catch (Exception e) {

                                                    }
                                                    for (int l = 0; l < integer; l++) {//Getting card numbers from the user l times!(the amount of cards(or animals) he wants to attack with)
                                                        table.display2();
                                                        if (attackingCards.size() != 0 && attackingCards.size() != integer)
                                                            System.out.println("You still have " + (integer - attackingCards.size()) + " card(s) left to select!");
                                                        System.out.println("Please select a card : ");
                                                        String cardNumberString = sc.next();
                                                        if (cardNumberString != null && cardNumberString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                                            int cardNumber = Integer.parseInt(cardNumberString);//The number of the card the user selects!
                                                            if (cardNumber <= 10 && cardNumber >= 1) {
                                                                if (player[i].cards[cardNumber - 1].getIsOnTheTable() == 2) {
                                                                    System.out.println("Alright!");
                                                                    try {
                                                                        Thread.sleep(1000);
                                                                    } catch (Exception e) {

                                                                    }
                                                                    for (int m = 0; m < 1; m++) {
                                                                        System.out.println("Now determine how you want to damage!");
                                                                        String methodString = sc.next();
                                                                        if (methodString.equals("Bite") || methodString.equals("Kill") || methodString.equals("Wound") || methodString.equals("Attack") || methodString.equals("Injure")) {
                                                                            if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                                                                if (methodString.equals("Bite")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Bite") || player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Bite")) {
                                                                                        if (player[i].getAnimalsThatBite() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Bite");
                                                                                            boolean flag = true;//This flag determins whether our cards method is like the other cards ore not!
                                                                                            if (attackingCardsMethods.get(0) != "Bite") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {//So if nothing wrong happens,our cards will be joined!
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a bite method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Kill")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Kill") || player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Kill")) {
                                                                                        if (player[i].getAnimalsThatKill() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Kill");
                                                                                            boolean flag = true;//This flag determins whether our cards method is like the other cards ore not!
                                                                                            if (attackingCardsMethods.get(0) != "Kill") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {//So if nothing wrong happens,our cards will be joined!
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                if (attackingCards.get(l).getAnimal().getName().equals("Wolf"))//Only wolves have a first method kill
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                else
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a kill method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Wound")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Wound") || player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Wound")) {
                                                                                        if (player[i].getAnimalsThatWound() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Wound");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Wound") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                if (attackingCards.get(l).getAnimal().getName().equals("Cow"))//Because only cows have a second method wound
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                else
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a wound method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Attack")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Attack") || player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Attack")) {
                                                                                        if (player[i].getAnimalsThatAttack() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Attack");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Attack") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                if (attackingCards.get(l).getAnimal().getName().equals("Elephant"))//Because only elephants have a second method attack!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                else
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have an attack method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Injure")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure") || player[i].cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Injure")) {
                                                                                        if (player[i].getAnimalsThatInjure() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Injure");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Injure") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());//Because injure is always a first damage method!
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have an injure method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (methodString.equals("Bite")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Bite")) {
                                                                                        if (player[i].getAnimalsThatBite() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Bite");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Bite") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a bite method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {
                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Kill")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Kill")) {
                                                                                        if (player[i].getAnimalsThatKill() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Kill");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Kill") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a kill method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Wound")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Wound")) {
                                                                                        if (player[i].getAnimalsThatWound() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Wound");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Wound") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have a wound method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Attack")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Attack")) {
                                                                                        if (player[i].getAnimalsThatAttack() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Attack");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Attack") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag == true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have an attack method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else if (methodString.equals("Injure")) {
                                                                                    if (player[i].cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure")) {
                                                                                        if (player[i].getAnimalsThatInjure() >= integer) {
                                                                                            attackingCardsMethods.add(l, "Injure");
                                                                                            boolean flag = true;
                                                                                            if (attackingCardsMethods.get(0) != "Injure") {//Because we are always checking with the first card we selected!
                                                                                                attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                flag = false;
                                                                                                System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                for (int o = 0; o < 1; o++) {
                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        o--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (flag = true) {
                                                                                                player[i].cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());//Because injure is always a first damage method!
                                                                                                totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        System.out.println("That animal doesn't have an injure method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                        for (int n = 0; n < 1; n++) {

                                                                                            System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                            String change = sc.next();
                                                                                            if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                if (change.equals("1")) {
                                                                                                    k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                    l = integer;//So we can get out of the l loop!
                                                                                                } else if (change.equals("2"))
                                                                                                    l--;
                                                                                                else if (change.equals("3"))
                                                                                                    m--;
                                                                                            } else {
                                                                                                System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                n--;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        } else {
                                                                            System.out.println("Your input is not valid.Please try again!");
                                                                            m--;
                                                                        }
                                                                    }
                                                                } else if (player[i].cards[cardNumber - 1].getIsOnTheTable() == 1) {
                                                                    System.out.println("That card has been selected for the attack before.Please try again!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (Exception e) {

                                                                    }
                                                                    l--;
                                                                } else if (player[i].cards[cardNumber - 1].getIsOnTheTable() == 0) {
                                                                    System.out.println("That card has been eliminated before.Please try again!");
                                                                    try {
                                                                        Thread.sleep(1500);
                                                                    } catch (Exception e) {

                                                                    }
                                                                    l--;
                                                                }
                                                            } else {
                                                                System.out.println("You're number is out of bounds.Please try again!");
                                                                l--;
                                                            }
                                                        } else {
                                                            System.out.println("You're input is not valid.Please try again!");
                                                            l--;
                                                        }
                                                    }
                                                    if (k != -1) {//We're doing this so we can skip these two sections if the user wants to select his cards again!(In case the "if" becomes false)
                                                        for (int m = 0; m < 1; m++) {//Every time that bottom if is correct,we must examine our cards again!
                                                            for (int l = 0; l < attackingCards.size(); l++) {
                                                                if (attackingCards.get(l).getAnimal().getEnergy() < (totalAttackEnergy / attackingCards.size())) {
                                                                    System.out.println("We can't let you attack.Because one of your animals(card #" + (l + 1) + "in your hand) is two weak to join you!We will put that card aside for you");
                                                                    totalAttackEnergy -= attackingCardsMethodsEnergy.get(l);
                                                                    attackingCardsMethodsEnergy.remove(l);
                                                                    attackingCardsNumbers.remove(l);
                                                                    attackingCards.remove(l);
                                                                    attackingCardsMethods.remove(l);
                                                                    if (attackingCards.size() == 0) {
                                                                        System.out.println("Unfortunately none of the cards you selected can attack.Try selecting again!");
                                                                        k--;
                                                                    }
                                                                    m--;

                                                                }
                                                            }
                                                        }//Now we can attack!
                                                        System.out.println("Excellent!");
                                                        try {
                                                            Thread.sleep(1500);
                                                        } catch (Exception e) {

                                                        }
                                                        table.display2();
                                                        for (int n = 0; n < 1; n++) {
                                                            System.out.println("Select the card you want to attack to!");
                                                            String opponentCardString = sc.next();
                                                            if (opponentCardString.equals("1") || opponentCardString.equals("2") || opponentCardString.equals("3") || opponentCardString.equals("4") || opponentCardString.equals("5") || opponentCardString.equals("6") || opponentCardString.equals("7") || opponentCardString.equals("8") || opponentCardString.equals("9") || opponentCardString.equals("10")) {
                                                                int opponentCard = Integer.parseInt(opponentCardString);
                                                                if (player[-i + 1].cards[opponentCard - 1].getIsOnTheTable() != 0) {
                                                                    int opponentsInitialHealth = gameCards[-i + 1][opponentCard - 1].getAnimal().getHealth();//Why (-i+1)?Because for y=-x+1 f(0)=2 & f(1)=0!
                                                                    int opponentsFinalHealth = opponentsInitialHealth - totalAttackEnergy;//The amount of health after the total energy has been subtracted!
                                                                    gameCards[-i + 1][opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card!
                                                                    if (gameCards[-i + 1][opponentCard - 1].getAnimal().getHealth() < 0) {
                                                                        gameCards[-i + 1][opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the gamecards)
                                                                    }
                                                                    player[-i + 1].cards[opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card
                                                                    if (player[-i + 1].cards[opponentCard - 1].getAnimal().getHealth() < 0)
                                                                        player[-i + 1].cards[opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the player's card)
                                                                    Animal newAnimal1 = new Animal(gameCards[-i + 1][opponentCard - 1].getAnimal().getName(), opponentsFinalHealth, gameCards[-i + 1][opponentCard - 1].getAnimal().getEnergy());//Making a new animal after it's health is decreased!
                                                                    Card newCard1 = new Card(newAnimal1);//Making a new card for that animal
                                                                    gameCards[-i + 1][opponentCard - 1] = newCard1;//Putting it in it's place!
                                                                    player[-i + 1].cards[opponentCard - 1] = newCard1;//Putting it in it's place!
                                                                    for (int l = 0; l < attackingCards.size(); l++) {
                                                                        int attackersInitalEnergy = attackingCards.get(l).getAnimal().getEnergy();
                                                                        int attackersFinalEnergy = attackersInitalEnergy - (totalAttackEnergy / attackingCards.size());//The amount of energy after subtracting 1/(attackingcards.size) of the totalAttackEnergy from the attackers initial energy!
                                                                        gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it our the card!
                                                                        player[i].cards[attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it to our card!
                                                                        player[i].removeMethod(player[i].cards[attackingCardsNumbers.get(l) - 1]);//Removing it from for example animalsThatKill!
                                                                        Animal newAnimal2 = new Animal(gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getName(), gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getHealth(), attackersFinalEnergy);//Making a new animal after it's health is decreased!
                                                                        Card newCard2 = new Card(newAnimal2);//Making a new card for that animal
                                                                        gameCards[i][attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                                                        player[i].cards[attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                                                        gameCards[i][attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!
                                                                        player[i].cards[attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!

                                                                    }
                                                                    table = new Table(gameCards);
                                                                    table.display2();
                                                                    if (table.whoWinsMode1(player[i], player[-i + 1]) == 1) {
                                                                        System.out.println("Player #" + (i + 1) + "wins!!");
                                                                        j = -2;
                                                                        i = 2;//So it jumps out of the loop!
                                                                        break;//Why j=-2?So it jumps out of the game!
                                                                    }
                                                                } else {
                                                                    System.out.println("That card has been eliminated before.Please try again!");
                                                                    n--;
                                                                }
                                                            } else {
                                                                System.out.println("Your input is not an integer from 1 to 10.Please try again!");
                                                                n--;
                                                            }
                                                        }
                                                    } else {
                                                        continue;//We're doing this so we can go back to selecting the number of our cards!(when k=-1 & at the the end of the for k++==> k=0 and we're back at the starting point!)
                                                    }
                                                }
                                            } else {
                                                System.out.println("Your number is out of bounds.Please try again!");
                                                k--;
                                            }
                                        } else {
                                            System.out.println("Your input is not an integer.Please try again!");
                                            k--;
                                        }
                                    }
                                } else {
                                    if (respawn[i] != 3) {
                                        for (int l = 0; l < 1; l++) {
                                            System.out.println("Please enter the number of the card you want to respawn!");
                                            String cardNumberString = sc.next();
                                            if (cardNumberString.equals("1") || cardNumberString.equals("2") || cardNumberString.equals("3") || cardNumberString.equals("4") || cardNumberString.equals("5") || cardNumberString.equals("6") || cardNumberString.equals("7") || cardNumberString.equals("8") || cardNumberString.equals("9") || cardNumberString.equals("10")) {
                                                int cardNumber = Integer.parseInt(cardNumberString);
                                                if (player[i].cards[cardNumber - 1].getIsOnTheTable() != 0) {
                                                    if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Lion")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 1000) {
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(1000);
                                                            respawn[i]++;
                                                        } else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Bear")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 900)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(900);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Tiger")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 850)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(850);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Vulture")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 600)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(600);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Fox")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 600)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(600);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Elephant")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 500)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(500);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Wolf")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 700)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(700);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Boar")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 500)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(500);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Hippo")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 360)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(360);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Cow")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 400)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(400);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Rabbit")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 350)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(350);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    } else if (player[i].cards[cardNumber - 1].getAnimal().getName().equals("Turtle")) {
                                                        if (player[i].cards[cardNumber - 1].getAnimal().getEnergy() != 230)
                                                            player[i].cards[cardNumber - 1].getAnimal().setEnergy(230);
                                                        else {
                                                            System.out.println("You're animal is already at maximum health!");
                                                            for (int m = 0; m < 1; m++) {
                                                                System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                String change = sc.next();
                                                                if (change.equals("1") || change.equals("2")) {
                                                                    if (change.equals("1"))
                                                                        l--;
                                                                    else
                                                                        f--;
                                                                } else {
                                                                    System.out.println("You're input is not correct.Please try again!");
                                                                    m--;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    respawn[i]++;//This means that we used one of our respawns!
                                                } else {
                                                    System.out.println("You can't respawn a card that has been eliminated before.Please try again!");
                                                    l--;
                                                }
                                            } else {
                                                System.out.println("You're input is not an integer from 1 to 10.Please try again!");
                                                l--;
                                            }
                                        }
                                    } else {
                                        System.out.println("You don't have any respawns left.Please go back and attack!");
                                        f--;//This goes on until the user goes back and attacks!
                                    }
                                }
                            } else {
                                System.out.println("You're mode is neither '1' nor '2'.Please try again!");
                                f--;
                            }
                        }
                    }
                }
            } else if (mode1.equals("2")) {
                Player player1 = new Player(1);
                Computer computer = new Computer();
                Card[][][] dealCards = new Card[2][3][10];//When dealing,we have two sets of 30 cards.One set for player1 and the other set for player2!
                Card[][] gameCards = new Card[2][10];//When the game starts,we have two sets of 10 cards.One set for player1 and the other set for player2!
                Animal[][][] animals = new Animal[2][3][10];//We have two sets of 30 cards.One for player1 and the other one for player2!
                int numberOfAnimals[][] = new int[2][12];//For example if player1 has 2 lions so numberOfAnimals[0][0]=2
                Table table;//Making a table to display our cards!
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 12; j++) {
                        numberOfAnimals[i][j] = 0;//Because at the beginning of the game,none or the player has any cards.Therefore,no animals!
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 10; k++) {
                            for (int l = 0; l < 1; l++) {
                                int cardNumber = Math.abs(new Random().nextInt() % 12) + 1;//Making a random number so we can choose an animal!
                                if (cardNumber == 1) {
                                    if (numberOfAnimals[i][0] < 5) {
                                        animals[i][j][k] = new Animal("Lion");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][0]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 2) {
                                    if (numberOfAnimals[i][1] < 5) {
                                        animals[i][j][k] = new Animal("Bear");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][1]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 3) {
                                    if (numberOfAnimals[i][2] < 5) {
                                        animals[i][j][k] = new Animal("Tiger");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][2]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 4) {
                                    if (numberOfAnimals[i][3] < 5) {
                                        animals[i][j][k] = new Animal("Vulture");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][3]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 5) {
                                    if (numberOfAnimals[i][4] < 5) {
                                        animals[i][j][k] = new Animal("Fox");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][4]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 6) {
                                    if (numberOfAnimals[i][5] < 5) {
                                        animals[i][j][k] = new Animal("Elephant");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][5]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 7) {
                                    if (numberOfAnimals[i][6] < 5) {
                                        animals[i][j][k] = new Animal("Wolf");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][6]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 8) {
                                    if (numberOfAnimals[i][7] < 5) {
                                        animals[i][j][k] = new Animal("Boar");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][7]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 9) {
                                    if (numberOfAnimals[i][8] < 5) {
                                        animals[i][j][k] = new Animal("Hippo");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][8]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 10) {
                                    if (numberOfAnimals[i][9] < 5) {
                                        animals[i][j][k] = new Animal("Cow");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][9]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 11) {
                                    if (numberOfAnimals[i][10] < 5) {
                                        animals[i][j][k] = new Animal("Rabbit");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][10]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                } else if (cardNumber == 12) {
                                    if (numberOfAnimals[i][11] < 5) {
                                        animals[i][j][k] = new Animal("Turtle");
                                        dealCards[i][j][k] = new Card(animals[i][j][k]);
                                        numberOfAnimals[i][11]++;
                                    } else
                                        l--;//This goes on until we pick an animal that hasn't been used more than 5 times!
                                }
                            }
                        }
                    }
                }
                table = new Table(dealCards);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == 0) {//Player's block!
                            table.display1(player1);
                            for (int k = 0; k < 1; k++) {
                                if (player1.getNumberOfCards() != 0 && player1.getNumberOfCards() != 10)
                                    System.out.println("You still have " + (10 - player1.getNumberOfCards()) + " card(s) left!");
                                System.out.println("Player #" + (i + 1) + ".Please select a number from 1 to 30!(excluding the ones that you already picked)");
                                String cardNumberString = sc.next();//cardNumber is the number of the card the user wants to select!
                                if (cardNumberString != null && cardNumberString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                    int cardNumber = Integer.parseInt(cardNumberString);
                                    if (cardNumber >= 1 && cardNumber <= 30) {
                                        if (cardNumber != 10 && cardNumber != 20 && cardNumber != 30) {
                                            if (dealCards[i][cardNumber / 10][cardNumber % 10 - 1].getIsOnTheTable() == 2) {//If it is on the table...
                                                player1.addCard(dealCards[i][cardNumber / 10][cardNumber % 10 - 1]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                                dealCards[i][cardNumber / 10][cardNumber % 10 - 1].setIsOnTheTable(0);//Because this card is no longer on the board!
                                            } else {
                                                System.out.println("That card has been selected before,Please try again!");
                                                k--;//This goes on until the user picks a card which he hasn't picked yet!)
                                            }
                                        } else {
                                            if (dealCards[i][cardNumber / 10 - 1][9].getIsOnTheTable() == 2) {//If it is on the table...
                                                player1.addCard(dealCards[i][cardNumber / 10 - 1][9]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                                dealCards[i][cardNumber / 10 - 1][9].setIsOnTheTable(0);//Because this card is no longer on the board!
                                            } else {
                                                System.out.println("That card has been selected before,Please try again!");
                                                k--;//This goes on until the user picks a card which he hasn't picked yet!)
                                            }
                                        }
                                    } else {
                                        System.out.println("The number you entered is out of bounds.Please try again!");
                                        k--;
                                    }
                                } else {
                                    System.out.println("You're input is not an integer.Please try again!");
                                    k--;
                                }
                            }
                        } else if (i == 1) {//Computer's block!
                            table.computerDisplay1(computer);
                            if (computer.getNumberOfCards() != 0 && computer.getNumberOfCards() != 10)
                                System.out.println("The computer has " + (10 - computer.getNumberOfCards()) + " card(s) left!");
                            try {
                                Thread.sleep(2500);
                            } catch (Exception e) {

                            }
                            int cardNumber = computer.optimumCardSelect(table);//Making an optimum decision on selecting a card!
                            if (cardNumber != 10 && cardNumber != 20 && cardNumber != 30) {
                                computer.addCard(dealCards[i][cardNumber / 10][cardNumber % 10 - 1]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                dealCards[i][cardNumber / 10][cardNumber % 10 - 1].setIsOnTheTable(0);//Because this card is no longer on the board!
                            } else {
                                computer.addCard(dealCards[i][cardNumber / 10 - 1][9]);//In our addCard method,we make new objects and put the dealCards details in the constructor.This way,we will switch our cards isOnTheTable back to "true"(for the next round!)
                                dealCards[i][cardNumber / 10 - 1][9].setIsOnTheTable(0);//Because this card is no longer on the board!
                            }
                            if (j == 9) {
                                table.computerDisplay1(computer);//To see the computers final move!
                                try {
                                    Thread.sleep(2500);
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == 0)
                            gameCards[i][j] = new Card(player1.cards[j].getAnimal());//With this trick,our isOnTheTable switch's back to true(because we are making new objects and sending the player's/computer's cards details to the gameCards constructor!)
                        else
                            gameCards[i][j] = new Card(computer.cards[j].getAnimal());//With this trick,our isOnTheTable switch's back to true(because we are making new objects and sending the player's cards details to the gameCards constructor!)

                    }
                }
                table = new Table(gameCards);//Our table has a new form!
                table.display2();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == 0) {//Player's block!
                            if (player1.getCards()[j].getAnimal().getName().equals("Lion")) {
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                                player1.setAnimalsThatKill(player1.getAnimalsThatKill() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Bear")) {
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                                player1.setAnimalsThatKill(player1.getAnimalsThatKill() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Tiger")) {
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                                player1.setAnimalsThatKill(player1.getAnimalsThatKill() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Vulture")) {
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Fox")) {
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Elephant")) {
                                player1.setAnimalsThatInjure(player1.getAnimalsThatInjure() + 1);
                                player1.setAnimalsThatAttack(player1.getAnimalsThatAttack() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Wolf")) {
                                player1.setAnimalsThatKill(player1.getAnimalsThatKill() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Boar")) {
                                player1.setAnimalsThatInjure(player1.getAnimalsThatInjure() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Hippo")) {
                                player1.setAnimalsThatAttack(player1.getAnimalsThatAttack() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Cow")) {
                                player1.setAnimalsThatAttack(player1.getAnimalsThatAttack() + 1);
                                player1.setAnimalsThatWound(player1.getAnimalsThatWound() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Rabbit")) {
                                player1.setAnimalsThatBite(player1.getAnimalsThatBite() + 1);
                            } else if (player1.getCards()[j].getAnimal().getName().equals("Turtle")) {
                                player1.setAnimalsThatBite(player1.getAnimalsThatBite() + 1);
                            }
                        } else {//The Computer's block!
                            if (computer.getCards()[j].getAnimal().getName().equals("Lion")) {
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                                computer.setAnimalsThatKill(computer.getAnimalsThatKill() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Bear")) {
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                                computer.setAnimalsThatKill(computer.getAnimalsThatKill() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Tiger")) {
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                                computer.setAnimalsThatKill(computer.getAnimalsThatKill() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Vulture")) {
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Fox")) {
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Elephant")) {
                                computer.setAnimalsThatInjure(computer.getAnimalsThatInjure() + 1);
                                computer.setAnimalsThatAttack(computer.getAnimalsThatAttack() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Wolf")) {
                                computer.setAnimalsThatKill(computer.getAnimalsThatKill() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Boar")) {
                                computer.setAnimalsThatInjure(computer.getAnimalsThatInjure() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Hippo")) {
                                computer.setAnimalsThatAttack(computer.getAnimalsThatAttack() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Cow")) {
                                computer.setAnimalsThatAttack(computer.getAnimalsThatAttack() + 1);
                                computer.setAnimalsThatWound(computer.getAnimalsThatWound() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Rabbit")) {
                                computer.setAnimalsThatBite(computer.getAnimalsThatBite() + 1);
                            } else if (computer.getCards()[j].getAnimal().getName().equals("Turtle")) {
                                computer.setAnimalsThatBite(computer.getAnimalsThatBite() + 1);
                            }
                        }
                    }
                }
                System.out.println(ANSI_CYAN + "Player #" + 1 + "!    You have " + player1.getAnimalsThatBite() + " animal(s) that Bite(s)" + ", " + player1.getAnimalsThatKill() + " animal(s) that Kill(s)" + ", " + player1.getAnimalsThatWound() + " animal(s) that Wound(s)" + ", " + player1.getAnimalsThatAttack() + " animal(s) that Attack(s)" + " & " + player1.getAnimalsThatInjure() + " animal(s) that Injure(s)." + ANSI_RESET);
                System.out.println();
                System.out.println(ANSI_CYAN + "The computer has " + computer.getAnimalsThatBite() + " animal(s) that Bite(s)" + ", " + computer.getAnimalsThatKill() + " animal(s) that Kill(s)" + ", " + computer.getAnimalsThatWound() + " animal(s) that Wound(s)" + ", " + computer.getAnimalsThatAttack() + " animal(s) that Attack(s)" + " & " + computer.getAnimalsThatInjure() + " animal(s) that Injure(s)." + ANSI_RESET);
                System.out.println();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                }
                int respawn[] = new int[2];//We have two players(player & computer) that respawn!
                for (int i = 0; i < 2; i++) {
                    respawn[i] = 0;//At the beginning of the game,we have not used any respawns yet!
                }
                table.display2();
                for (int j = 0; j != -1; j++) {//At the end of the game,we will turn it to -1!
                    for (int i = 0; i < 2; i++) {
                        ArrayList<Card> attackingCards = new ArrayList<>();//Every round,one of the players picks a certain number of cards to attack.we use an arraylist to keep those cards!
                        ArrayList<String> attackingCardsMethods = new ArrayList<>();//Every round,one of the players picks a certain number of cards to attack.we use an arraylist to keep there attack methods!
                        ArrayList<Integer> attackingCardsMethodsEnergy = new ArrayList<>();
                        ArrayList<Integer> attackingCardsNumbers = new ArrayList<>();
                        int totalAttackEnergy = 0;//The amount of energy that is summed each round!
                        if (i == 0) {//Player's Block!
                            for (int f = 0; f < 1; f++) {
                                System.out.println("Player #" + (i + 1) + "!Please enter '1' if you want to attack or '2' if you want to respawn one of your cards");
                                String mode2 = sc.next();
                                if (mode2.equals("1") || mode2.equals("2")) {
                                    if (mode2.equals("1")) {
                                        System.out.println("You can select a number of cards to attack to one of your opponent's cards.Don't forget that your animals must damage your opponent's card in one way(This means that an animal can't injure your opponent's card and the other one bites it!)");
                                        for (int k = 0; k < 1; k++) {
                                            System.out.println("Please determine the number of cards you want to select to attack to one of your opponent's card!(A number from 1 to the number of cards you have on the table)");
                                            String integerString = sc.next();
                                            if (integerString != null && integerString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                                int integer = Integer.parseInt(integerString);//An integer that the user gives us so he can determine the number of cards he/she wants to select to attack with!
                                                if (integer >= 1 && integer <= player1.getNumberOfCards()) {
                                                    if (integer > player1.getAnimalsThatBite() && integer > player1.getAnimalsThatKill() && integer > player1.getAnimalsThatWound() && integer > player1.getAnimalsThatAttack() && integer > player1.getAnimalsThatInjure()) {
                                                        System.out.println("With this amount of cards,you can't organize an attack for your animals.Please try again!");
                                                        k--;
                                                    } else {
                                                        System.out.println("Alright!");
                                                        try {
                                                            Thread.sleep(1000);
                                                        } catch (Exception e) {

                                                        }
                                                        for (int l = 0; l < integer; l++) {//Getting card numbers from the user l times!(the amount of cards(or animals) he wants to attack with)
                                                            table.display2();
                                                            if (attackingCards.size() != 0 && attackingCards.size() != integer)
                                                                System.out.println("You still have " + (integer - attackingCards.size()) + " card(s) left to select!");
                                                            System.out.println("Please select a card : ");
                                                            String cardNumberString = sc.next();
                                                            if (cardNumberString != null && cardNumberString.matches("[-+]?\\d*\\.?\\d+")) {//Checking whether it really is an integer or not!
                                                                int cardNumber = Integer.parseInt(cardNumberString);//The number of the card the user wants to select!
                                                                if (cardNumber <= 10 && cardNumber >= 1) {
                                                                    if (player1.cards[cardNumber - 1].getIsOnTheTable() == 2) {
                                                                        System.out.println("Alright!");
                                                                        try {
                                                                            Thread.sleep(1000);
                                                                        } catch (Exception e) {

                                                                        }
                                                                        for (int m = 0; m < 1; m++) {
                                                                            System.out.println("Now determine how you want to damage!");
                                                                            String methodString = sc.next();
                                                                            if (methodString.equals("Bite") || methodString.equals("Kill") || methodString.equals("Wound") || methodString.equals("Attack") || methodString.equals("Injure")) {
                                                                                if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                                                                    if (methodString.equals("Bite")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Bite") || player1.cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Bite")) {
                                                                                            if (player1.getAnimalsThatBite() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Bite");
                                                                                                boolean flag = true;//This flag determins whether our cards method is like the other cards ore not!
                                                                                                if (attackingCardsMethods.get(0) != "Bite") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {//So if nothing wrong happens,our cards will be joined!
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a bite method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Kill")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Kill") || player1.cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Kill")) {
                                                                                            if (player1.getAnimalsThatKill() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Kill");
                                                                                                boolean flag = true;//This flag determins whether our cards method is like the other cards ore not!
                                                                                                if (attackingCardsMethods.get(0) != "Kill") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {//So if nothing wrong happens,our cards will be joined!
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    if (attackingCards.get(l).getAnimal().getName().equals("Wolf"))//Only wolves have a first method kill
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    else
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a kill method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Wound")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Wound") || player1.cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Wound")) {
                                                                                            if (player1.getAnimalsThatWound() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Wound");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Wound") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    if (attackingCards.get(l).getAnimal().getName().equals("Cow"))//Because only cows have a second method wound
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                    else
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a wound method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Attack")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Attack") || player1.cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Attack")) {
                                                                                            if (player1.getAnimalsThatAttack() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Attack");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Attack") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    if (attackingCards.get(l).getAnimal().getName().equals("Elephant"))//Because only elephants have a second method attack!
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                                                                                    else
                                                                                                        attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have an attack method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Injure")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure") || player1.cards[cardNumber - 1].getAnimal().getAttackMethod2().equals("Injure")) {
                                                                                            if (player1.getAnimalsThatInjure() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Injure");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Injure") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());//Because injure is always a first damage method!
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have an injure method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    if (methodString.equals("Bite")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Bite")) {
                                                                                            if (player1.getAnimalsThatBite() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Bite");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Bite") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a bite method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {
                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Kill")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Kill")) {
                                                                                            if (player1.getAnimalsThatKill() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Kill");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Kill") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a kill method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Wound")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Wound")) {
                                                                                            if (player1.getAnimalsThatWound() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Wound");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Wound") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have a wound method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Attack")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Attack")) {
                                                                                            if (player1.getAnimalsThatAttack() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Attack");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Attack") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag == true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have an attack method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } else if (methodString.equals("Injure")) {
                                                                                        if (player1.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure")) {
                                                                                            if (player1.getAnimalsThatInjure() >= integer) {
                                                                                                attackingCardsMethods.add(l, "Injure");
                                                                                                boolean flag = true;
                                                                                                if (attackingCardsMethods.get(0) != "Injure") {//Because we are always checking with the first card we selected!
                                                                                                    attackingCardsMethods.remove(l);//Is no longer part of the group!
                                                                                                    flag = false;
                                                                                                    System.out.println("The method you used for this card is different from the previous card(s) methods.You can either change the number of cards you want to select,the number of your card or your damage method.");
                                                                                                    for (int o = 0; o < 1; o++) {
                                                                                                        System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                        String change = sc.next();
                                                                                                        if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                            if (change.equals("1")) {
                                                                                                                k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                                l = integer;//So we can get out of the l loop!
                                                                                                            } else if (change.equals("2"))
                                                                                                                l--;
                                                                                                            else if (change.equals("3"))
                                                                                                                m--;
                                                                                                        } else {
                                                                                                            System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                            o--;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (flag = true) {
                                                                                                    player1.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                                                                                    attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                                                                                    attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                                                                                    attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());//Because injure is always a first damage method!
                                                                                                    totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("According to your integer(number of cards you want to select) and damage method,you can't attack!.So change your damage method or change your number or change the number of cards you want to select!");
                                                                                                for (int n = 0; n < 1; n++) {

                                                                                                    System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                    String change = sc.next();
                                                                                                    if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                        if (change.equals("1")) {
                                                                                                            k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                            l = integer;//So we can get out of the l loop!
                                                                                                        } else if (change.equals("2"))
                                                                                                            l--;
                                                                                                        else if (change.equals("3"))
                                                                                                            m--;
                                                                                                    } else {
                                                                                                        System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                        n--;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("That animal doesn't have an injure method.So change your number or change your damage method!(Or change the number of cards you want to select if you want to start from scratch!)");
                                                                                            for (int n = 0; n < 1; n++) {

                                                                                                System.out.println("Press '1' if you want to change the number of cards you want to select, '2' if you want to change your card or '3' if you want to change your damage method");
                                                                                                String change = sc.next();
                                                                                                if (change.equals("1") || change.equals("2") || change.equals("3")) {
                                                                                                    if (change.equals("1")) {
                                                                                                        k = -1;//We're doing this so we can go back to selecting the number of our cards!
                                                                                                        l = integer;//So we can get out of the l loop!
                                                                                                    } else if (change.equals("2"))
                                                                                                        l--;
                                                                                                    else if (change.equals("3"))
                                                                                                        m--;
                                                                                                } else {
                                                                                                    System.out.println("Your input is neither 1 nor 2 & not even 3!Please try again!");
                                                                                                    n--;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                System.out.println("Your input is not valid.Please try again!");
                                                                                m--;
                                                                            }
                                                                        }
                                                                    } else if (player1.cards[cardNumber - 1].getIsOnTheTable() == 1) {
                                                                        System.out.println("That card has been selected for the attack before.Please try again!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (Exception e) {

                                                                        }
                                                                        l--;
                                                                    } else if (player1.cards[cardNumber - 1].getIsOnTheTable() == 0) {
                                                                        System.out.println("That card has been eliminated before.Please try again!");
                                                                        try {
                                                                            Thread.sleep(1500);
                                                                        } catch (Exception e) {

                                                                        }
                                                                        l--;
                                                                    }
                                                                } else {
                                                                    System.out.println("You're number is out of bounds.Please try again!");
                                                                    l--;
                                                                }
                                                            } else {
                                                                System.out.println("You're input is not valid.Please try again!");
                                                                l--;
                                                            }
                                                        }
                                                        if (k != -1) {//We're doing this so we can skip these two sections if the user wants to select his cards again!(In case the "if" becomes false)
                                                            for (int m = 0; m < 1; m++) {//Every time that bottom if is correct,we must examine our cards again!
                                                                for (int l = 0; l < attackingCards.size(); l++) {
                                                                    if (attackingCards.get(l).getAnimal().getEnergy() < (totalAttackEnergy / attackingCards.size())) {
                                                                        System.out.println("We can't let you attack.Because one of your animals(card #" + (l + 1) + "in your hand) is two weak to join you!We will put that card aside for you");
                                                                        totalAttackEnergy -= attackingCardsMethodsEnergy.get(l);
                                                                        attackingCardsMethodsEnergy.remove(l);
                                                                        attackingCardsNumbers.remove(l);
                                                                        attackingCards.remove(l);
                                                                        attackingCardsMethods.remove(l);
                                                                        if (attackingCards.size() == 0) {
                                                                            System.out.println("Unfortunately none of the cards you selected can attack.Try selecting again!");
                                                                            k--;
                                                                        }
                                                                        m--;

                                                                    }
                                                                }
                                                            }//Now we can attack!
                                                            System.out.println("Excellent!");
                                                            try {
                                                                Thread.sleep(1500);
                                                            } catch (Exception e) {

                                                            }
                                                            table.display2();
                                                            for (int n = 0; n < 1; n++) {
                                                                System.out.println("Select the card you want to attack to!");
                                                                String opponentCardString = sc.next();
                                                                if (opponentCardString.equals("1") || opponentCardString.equals("2") || opponentCardString.equals("3") || opponentCardString.equals("4") || opponentCardString.equals("5") || opponentCardString.equals("6") || opponentCardString.equals("7") || opponentCardString.equals("8") || opponentCardString.equals("9") || opponentCardString.equals("10")) {
                                                                    int opponentCard = Integer.parseInt(opponentCardString);
                                                                    if (computer.cards[opponentCard - 1].getIsOnTheTable() != 0) {
                                                                        int opponentsInitialHealth = gameCards[-i + 1][opponentCard - 1].getAnimal().getHealth();//Why (-i+1)?Because for y=-x+1 f(0)=2 & f(1)=0!
                                                                        int opponentsFinalHealth = opponentsInitialHealth - totalAttackEnergy;//The amount of health after the total energy has been subtracted!
                                                                        gameCards[-i + 1][opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card!
                                                                        if (gameCards[-i + 1][opponentCard - 1].getAnimal().getHealth() < 0) {
                                                                            gameCards[-i + 1][opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the gamecards)
                                                                        }
                                                                        computer.cards[opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card
                                                                        if (computer.cards[opponentCard - 1].getAnimal().getHealth() < 0)
                                                                            computer.cards[opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the player's card)
                                                                        Animal newAnimal1 = new Animal(gameCards[-i + 1][opponentCard - 1].getAnimal().getName(), opponentsFinalHealth, gameCards[-i + 1][opponentCard - 1].getAnimal().getEnergy());//Making a new animal after it's health is decreased!
                                                                        Card newCard1 = new Card(newAnimal1);//Making a new card for that animal
                                                                        gameCards[-i + 1][opponentCard - 1] = newCard1;//Putting it in it's place!
                                                                        player1.cards[opponentCard - 1] = newCard1;//Putting it in it's place!
                                                                        for (int l = 0; l < attackingCards.size(); l++) {
                                                                            int attackersInitalEnergy = attackingCards.get(l).getAnimal().getEnergy();
                                                                            int attackersFinalEnergy = attackersInitalEnergy - (totalAttackEnergy / attackingCards.size());//The amount of energy after subtracting 1/(attackingcards.size) of the totalAttackEnergy from the attackers initial energy!
                                                                            gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it our the card!
                                                                            player1.cards[attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it to our card!
                                                                            Animal newAnimal2 = new Animal(gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getName(), gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getHealth(), attackersFinalEnergy);//Making a new animal after it's health is decreased!
                                                                            Card newCard2 = new Card(newAnimal2);//Making a new card for that animal
                                                                            gameCards[i][attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                                                            player1.cards[attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                                                            gameCards[i][attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!
                                                                            player1.cards[attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!
                                                                            player1.removeMethod(player1.cards[attackingCardsNumbers.get(l) - 1]);//Removing it from for example animalsThatKill!
                                                                        }
                                                                        table = new Table(gameCards);
                                                                        table.display2();
                                                                        if (table.whoWinsMode2(player1, computer) == 1) {
                                                                            System.out.println("Player1 wins!!");
                                                                            j = -2;
                                                                            i = 2;//So it jumps out of the loop!
                                                                            break;//Why j=-2?So it jumps out of the game!
                                                                        }
                                                                    } else {
                                                                        System.out.println("That card has been eliminated before.Please try again!");
                                                                        n--;
                                                                    }
                                                                } else {
                                                                    System.out.println("Your input is not an integer from 1 to 10.Please try again!");
                                                                    n--;
                                                                }
                                                            }
                                                        } else {
                                                            continue;//We're doing this so we can go back to selecting the number of our cards!(when k=-1 & at the the end of the for k++==> k=0 and we're back at the starting point!)
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Your number is out of bounds.Please try again!");
                                                    k--;
                                                }
                                            } else {
                                                System.out.println("Your input is not an integer.Please try again!");
                                                k--;
                                            }
                                        }
                                    } else {
                                        if (respawn[i] != 3) {
                                            for (int l = 0; l < 1; l++) {
                                                System.out.println("Please enter the number of the card you want to respawn!");
                                                String cardNumberString = sc.next();
                                                if (cardNumberString.equals("1") || cardNumberString.equals("2") || cardNumberString.equals("3") || cardNumberString.equals("4") || cardNumberString.equals("5") || cardNumberString.equals("6") || cardNumberString.equals("7") || cardNumberString.equals("8") || cardNumberString.equals("9") || cardNumberString.equals("10")) {
                                                    int cardNumber = Integer.parseInt(cardNumberString);
                                                    if (player1.cards[cardNumber - 1].getIsOnTheTable() != 0) {
                                                        if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Lion")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 1000) {
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(1000);
                                                                respawn[i]++;
                                                            } else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Bear")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 900)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(900);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Tiger")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 850)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(850);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Vulture")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 600)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(600);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Fox")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 600)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(600);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Elephant")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 500)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(500);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Wolf")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 700)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(700);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Boar")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 500)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(500);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Hippo")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 360)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(360);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Cow")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 400)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(400);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Rabbit")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 350)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(350);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        } else if (player1.cards[cardNumber - 1].getAnimal().getName().equals("Turtle")) {
                                                            if (player1.cards[cardNumber - 1].getAnimal().getEnergy() != 230)
                                                                player1.cards[cardNumber - 1].getAnimal().setEnergy(230);
                                                            else {
                                                                System.out.println("You're animal is already at maximum health!");
                                                                for (int m = 0; m < 1; m++) {
                                                                    System.out.println("Please press '1' if you want to respawn another animal & '2' if you want to continue the game!");
                                                                    String change = sc.next();
                                                                    if (change.equals("1") || change.equals("2")) {
                                                                        if (change.equals("1"))
                                                                            l--;
                                                                        else
                                                                            f--;
                                                                    } else {
                                                                        System.out.println("You're input is not correct.Please try again!");
                                                                        m--;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        respawn[i]++;//This means that we used one of our respawns!
                                                    } else {
                                                        System.out.println("You can't respawn a card that has been eliminated before.Please try again!");
                                                        l--;
                                                    }
                                                } else {
                                                    System.out.println("You're input is not an integer from 1 to 10.Please try again!");
                                                    l--;
                                                }
                                            }
                                        } else {
                                            System.out.println("You don't have any respawns left.Please go back and attack!");
                                            f--;//This goes on until the user goes back and attacks!
                                        }
                                    }
                                } else {
                                    System.out.println("You're mode is neither '1' nor '2'.Please try again!");
                                    f--;
                                }
                            }
                        } else {//Computer's Block!
                            System.out.println("The computer wants to select between respawning or attacking!");
                            try {
                                Thread.sleep(3000);//The user needs time to read!
                            } catch (Exception e) {

                            }
                            int mode2 = computer.optimumModeChoice(respawn[1]);//Choosing the best strategy
                            if (mode2 == 1) {
                                System.out.println("The computer chooses to attack!");
                                try {
                                    Thread.sleep(3000);
                                } catch (Exception e) {

                                }
                                int integer = computer.optimumAmountOfCards();//The optimum amount of cards!
                                if (integer == 4) {//This means that our animals want to injure
                                    for (int l = 0; l < integer; l++) {
                                        table.display2();
                                        if (attackingCards.size() != 0 && attackingCards.size() != integer)
                                            System.out.println("The computer still has " + (integer - attackingCards.size()) + " card(s) left to select!");
                                        int cardNumber = computer.cardSelect();//The number of the card the computer wants to select!
                                        System.out.println("The computer selected a card!");
                                        try {
                                            Thread.sleep(2000);
                                        } catch (Exception e) {

                                        }
                                        computer.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                        attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                        if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                            if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure"))
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            else
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        } else {
                                            attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        }
                                    }
                                } else if (integer == 3) {
                                    for (int l = 0; l < integer; l++) {
                                        table.display2();
                                        if (attackingCards.size() != 0 && attackingCards.size() != integer)
                                            System.out.println("The computer still has " + (integer - attackingCards.size()) + " card(s) left to select!");
                                        int cardNumber = computer.cardSelect();//The number of the card the computer wants to select!
                                        System.out.println("The computer selected a card!");
                                        try {
                                            Thread.sleep(1000);
                                        } catch (Exception e) {

                                        }
                                        computer.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                        attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                        if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                            if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure"))
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            else
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        } else {
                                            attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        }
                                    }
                                } else if (integer == 2) {
                                    for (int l = 0; l < integer; l++) {
                                        table.display2();
                                        if (attackingCards.size() != 0 && attackingCards.size() != integer)
                                            System.out.println("The computer still has " + (integer - attackingCards.size()) + " card(s) left to select!");
                                        int cardNumber = computer.cardSelect();//The number of the card the computer wants to select!
                                        System.out.println("The computer selected a card!");
                                        try {
                                            Thread.sleep(2000);
                                        } catch (Exception e) {

                                        }
                                        computer.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                        attackingCards.add(l, gameCards[i][cardNumber - 1]);
                                        attackingCardsNumbers.add(l, cardNumber);//Adding it's number to our list!
                                        if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                            if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Injure"))
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            else
                                                attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod2Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        } else {
                                            attackingCardsMethodsEnergy.add(l, attackingCards.get(l).getAnimal().getAttackMethod1Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(l);//Adding to our total energy!
                                        }
                                    }
                                } else if (integer == 1) {
                                    table.display2();
                                    System.out.println("The computer wants to attack with 1 card!");
                                    int cardNumber = computer.cardSelect();//The number of the card the computer wants to select!
                                    System.out.println("The computer selected a card!");
                                    try {
                                        Thread.sleep(2000);
                                    } catch (Exception e) {

                                    }
                                    computer.cards[cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                    gameCards[i][cardNumber - 1].setIsOnTheTable(1);//It becomes a selected card!
                                    attackingCards.add(0, gameCards[i][cardNumber - 1]);
                                    attackingCardsNumbers.add(0, cardNumber);//Adding it's number to our list!
                                    if (computer.getAnimalsThatKill() == 1) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                            if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod1().equals("Kill"))
                                                attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod1Amount());
                                            else
                                                attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod2Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(0);//Adding to our total energy!
                                        } else {
                                            attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod1Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(0);//Adding to our total energy!
                                        }
                                    } else {
                                        if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod2() != null) {
                                            if (computer.cards[cardNumber - 1].getAnimal().getAttackMethod1Amount() < computer.cards[cardNumber - 1].getAnimal().getAttackMethod2Amount())
                                                attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod1Amount());//Picking the lighter attack!
                                            else
                                                attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod2Amount());//Picking the lighter attack!
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(0);//Adding to our total energy!
                                        } else {
                                            attackingCardsMethodsEnergy.add(0, attackingCards.get(0).getAnimal().getAttackMethod1Amount());
                                            totalAttackEnergy += attackingCardsMethodsEnergy.get(0);//Adding to our total energy!
                                        }
                                    }
                                }//Now the computer can attack!
                                System.out.println("The computer is ready to attack to one of your cards!");
                                try {
                                    Thread.sleep(2500);
                                } catch (Exception e) {

                                }
                                table.display2();
                                for (int n = 0; n < 1; n++) {
                                    int opponentCard = computer.optimumAttackTarget(totalAttackEnergy, player1);
                                    int opponentsInitialHealth = gameCards[0][opponentCard - 1].getAnimal().getHealth();
                                    int opponentsFinalHealth = opponentsInitialHealth - totalAttackEnergy;//The amount of health after the total energy has been subtracted!
                                    gameCards[0][opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card!
                                    if (gameCards[0][opponentCard - 1].getAnimal().getHealth() < 0) {
                                        gameCards[0][opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the gamecards)
                                    }
                                    player1.cards[opponentCard - 1].getAnimal().setHealth(opponentsFinalHealth);//Applying it to our card
                                    if (player1.cards[opponentCard - 1].getAnimal().getHealth() < 0)
                                        player1.cards[opponentCard - 1].setIsOnTheTable(0);//This means that that card is eliminated from the game!(for the player's card)
                                    Animal newAnimal1 = new Animal(gameCards[-i + 1][opponentCard - 1].getAnimal().getName(), opponentsFinalHealth, gameCards[-i + 1][opponentCard - 1].getAnimal().getEnergy());//Making a new animal after it's health is decreased!
                                    Card newCard1 = new Card(newAnimal1);//Making a new card for that animal
                                    gameCards[-i + 1][opponentCard - 1] = newCard1;//Putting it in it's place!
                                    computer.cards[opponentCard - 1] = newCard1;//Putting it in it's place!
                                    for (int l = 0; l < attackingCards.size(); l++) {
                                        int attackersInitalEnergy = attackingCards.get(l).getAnimal().getEnergy();
                                        int attackersFinalEnergy = attackersInitalEnergy - (totalAttackEnergy / attackingCards.size());//The amount of energy after subtracting 1/(attackingcards.size) of the totalAttackEnergy from the attackers initial energy!
                                        gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it our the card!
                                        computer.cards[attackingCardsNumbers.get(l) - 1].getAnimal().setEnergy(attackersFinalEnergy);//Applying it to our card!
                                        Animal newAnimal2 = new Animal(gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getName(), gameCards[i][attackingCardsNumbers.get(l) - 1].getAnimal().getHealth(), attackersFinalEnergy);//Making a new animal after it's health is decreased!
                                        Card newCard2 = new Card(newAnimal2);//Making a new card for that animal
                                        gameCards[i][attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                        computer.cards[attackingCardsNumbers.get(l) - 1] = newCard2;//Putting it in it's place!
                                        gameCards[i][attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!
                                        computer.cards[attackingCardsNumbers.get(l) - 1].setIsOnTheTable(2);//Back to normal!
                                    }
                                    table = new Table(gameCards);
                                    table.display2();
                                    if (table.whoWinsMode2(player1, computer) == -1) {
                                        System.out.println("The Computer wins!!");
                                        j = -2;
                                        i = 2;//So it jumps out of the loop!
                                        break;//Why j=-2?So it jumps out of the game!
                                    }
                                }
                            } else {
                                for (int k = 0; k < 1; k++) {
                                    int cardNumber = computer.optimumRespawnCard();
                                    if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Lion")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 1000) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(1000);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Bear")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 900) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(900);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Tiger")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 850) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(850);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Vulture")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 600) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(600);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Fox")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 600) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(600);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Elephant")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 500) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(500);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Wolf")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 700) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(700);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Boar")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 500) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(500);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Hippo")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 360) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(360);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Cow")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 400) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(400);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Rabbit")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 350) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(350);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    } else if (computer.cards[cardNumber - 1].getAnimal().getName().equals("Turtle")) {
                                        if (computer.cards[cardNumber - 1].getAnimal().getEnergy() != 230) {
                                            computer.cards[cardNumber - 1].getAnimal().setEnergy(230);
                                        } else {
                                            k--;//This goes on until our random respawin is correct!(Because in the other cases,this will not happen!)
                                        }
                                    }
                                    respawn[i]++;//This means that we used one of our respawns!
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}