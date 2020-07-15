import java.util.Random;

public class Computer {
    /**
     * Simulating a quite intelligent computer!
     *
     * @author Matin Tavakoli
     * parameters are explained in the comments!
     */
    Card cards[] = new Card[10];
    private int numberOfCards;//The number of cards the computer has!(from 0 to 10)
    private int animalsThatWound;//The number of animals that wound the opposition's animals!
    private int animalsThatKill;//The number of animals that kill the opposition's animals!
    private int animalsThatInjure;//The number of animals that injure the opposition's animals!
    private int animalsThatAttack;//The number of animals that can attack at the opposition's animals!
    private int animalsThatBite;//The number of animals that can bite the opposition's animals!

    public Computer() {
        numberOfCards = 0;//Because at the beginning of the game,the computer has no cards!
        animalsThatWound = 0;//Because at the beginning of the game,the computer has no cards.Therefore no animals!
        animalsThatKill = 0;//Because at the beginning of the game,the computer has no cards.Therefore no animals!
        animalsThatInjure = 0;//Because at the beginning of the game,the computer has no cards.Therefore no animals!
        animalsThatAttack = 0;//Because at the beginning of the game,the computer has no cards.Therefore no animals!
        animalsThatBite = 0;//Because at the beginning of the game,the computer has no cards.Therefore no animals!
    }

    public int getNumberOfCards() {
        int temp = numberOfCards;
        for (int i = 0; i < temp; i++) {
            if (cards[i].getIsOnTheTable() == 0)
                numberOfCards--;//This means that that card does not count as the computers cards
        }
        return numberOfCards;
    }

    public Card[] getCards() {
        return cards;
    }

    public int getAnimalsThatWound() {
        return animalsThatWound;
    }

    public void setAnimalsThatWound(int animalsThatWound) {
        this.animalsThatWound = animalsThatWound;
    }

    public int getAnimalsThatKill() {
        return animalsThatKill;
    }

    public void setAnimalsThatKill(int animalsThatKill) {
        this.animalsThatKill = animalsThatKill;
    }

    public int getAnimalsThatInjure() {
        return animalsThatInjure;
    }

    public void setAnimalsThatInjure(int animalsThatInjure) {
        this.animalsThatInjure = animalsThatInjure;
    }

    public int getAnimalsThatAttack() {
        return animalsThatAttack;
    }

    public void setAnimalsThatAttack(int animalsThatAttack) {
        this.animalsThatAttack = animalsThatAttack;
    }

    public int getAnimalsThatBite() {
        return animalsThatBite;
    }

    public void setAnimalsThatBite(int animalsThatBite) {
        this.animalsThatBite = animalsThatBite;
    }

    public void addCard(Card card) {
        cards[numberOfCards] = new Card(card.getAnimal());//We won't check if the number of cards is more than 10 because in the main,we get card numbers from the user in a 10 loop "for"!
        numberOfCards++;//Because a card has just been added!
    }

    public void removeMethodForComputer(Card card) {//removing that method from our list of animals if a card has been eliminated
        if (card.getAnimal().getAttackMethod2() != null) {
            if (card.getAnimal().getAttackMethod1().equals("Bite") || card.getAnimal().getAttackMethod2().equals("Bite"))
                animalsThatBite--;
            else if (card.getAnimal().getAttackMethod1().equals("Kill") || card.getAnimal().getAttackMethod2().equals("Kill"))
                animalsThatKill--;
            else if (card.getAnimal().getAttackMethod1().equals("Wound") || card.getAnimal().getAttackMethod2().equals("Wound"))
                animalsThatWound--;
            else if (card.getAnimal().getAttackMethod1().equals("Attack") || card.getAnimal().getAttackMethod2().equals("Attack"))
                animalsThatAttack--;
            else if (card.getAnimal().getAttackMethod1().equals("Injure") || card.getAnimal().getAttackMethod2().equals("Injure"))
                animalsThatInjure--;
        } else {
            if (card.getAnimal().getAttackMethod1().equals("Bite"))
                animalsThatBite--;
            else if (card.getAnimal().getAttackMethod1().equals("Kill"))
                animalsThatKill--;
            else if (card.getAnimal().getAttackMethod1().equals("Wound"))
                animalsThatWound--;
            else if (card.getAnimal().getAttackMethod1().equals("Attack"))
                animalsThatAttack--;
            else if (card.getAnimal().getAttackMethod1().equals("Injure"))
                animalsThatInjure--;
        }
    }

    public int optimumCardSelect(Table table) {//Checking whether we have 6 of the top 12 animals each by 2 "for" 's(& returning if it is on the table!)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Lion"))//The first optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Bear"))//The second optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Tiger"))//The third optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Elephant"))//The fourth optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Boar"))//The fifth optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (table.getDealCards()[1][i][j].getAnimal().getName().equals("Hippo"))//The sixth optimum choice
                    if (table.getDealCards()[1][i][j].getIsOnTheTable() == 2)//If we can pick it!
                        return 10 * i + (j + 1);//The cards number!
            }
        }
        for (int i = 0; i < 1; i++) {
            int randomCardNumber = Math.abs(new Random().nextInt() % 30) + 1;//In case we have no other way!(We pick a random number from 1 to 30 randolmy to make it less predictable!)
            if (randomCardNumber != 10 && randomCardNumber != 20 && randomCardNumber != 30) {
                if (table.getDealCards()[1][randomCardNumber / 10][randomCardNumber % 10 - 1].getIsOnTheTable() == 2)
                    return randomCardNumber;
                else
                    i--;
            } else {
                if (table.getDealCards()[1][randomCardNumber / 10 - 1][9].getIsOnTheTable() == 2)
                    return randomCardNumber;
                else
                    i--;
            }
        }
        return Math.abs(new Random().nextInt() % 30) + 1;//This is just so it doesn't give us an error.Because judging the previous section,it will never reach here at all!!
    }

    public int optimumModeChoice(int respawn) {//Choosing the best strategy.Attack or recover?!
        for (int i = 0; i < 10; i++) {
            if (cards[i].getAnimal().getEnergy() < 250)//Because we are using good animals(with high energy bars!),Less than 250 is considered "low energy"!
                if (respawn != 3)
                    return 2;
        }
        return 1;//In case the computer's animals' energies are "OK"!
    }

    public int optimumAmountOfCards() {//Choosing the best amount of cards for our attack!
        if (animalsThatKill >= 2) { //First strategy
            int counter = 0;//Checking the number of animals with enough energy
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Kill") || cards[i].getAnimal().getAttackMethod2().equals("Kill"))
                    if (cards[i].getAnimal().getEnergy() > 500)//500 is the least it must have!
                        counter++;
            }
            if (counter >= 2)//So we have at least 2 enemies with sufficient energy!
                return 2;//Two animals is enough to kill most of the animals in one go!
            else if (counter == 1)
                return 1;//We have 1 animal that kills.So we send it!
        } else if (animalsThatWound >= 3) {//Third strategy
            int counter = 0;
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Wound") || cards[i].getAnimal().getAttackMethod2().equals("Wound"))
                    if (cards[i].getAnimal().getEnergy() > 150)//150 is the least it must have!
                        counter++;
            }
            if (counter >= 3)//So we have at least 2 enemies with sufficient energy!
                return 3;//Wounding with 3 animals is a good strategy
        } else if (animalsThatInjure >= 4) {//Fourth strategy
            int counter = 0;
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Injure") || cards[i].getAnimal().getAttackMethod2().equals("Injure"))
                    if (cards[i].getAnimal().getEnergy() > 110)//110 is the least it must have
                        counter++;
            }
            if (counter >= 4)//So we have at least 2 enemies with sufficient energy!
                return 4;//4 animals that injure is good
        }
        return 1;//In case we can't apply the other strategies!
    }

    public int cardSelect() {
        if (optimumAmountOfCards() == 4) {
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Injure") || cards[i].getAnimal().getAttackMethod2().equals("Injure"))//Finding that card!
                    if (cards[i].getIsOnTheTable() == 2)//Checking whether it is available or not!
                        return i + 1;
            }
        } else if (optimumAmountOfCards() == 3) {
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Wound") || cards[i].getAnimal().getAttackMethod2().equals("Wound"))//Finding that card!
                    if (cards[i].getIsOnTheTable() == 2)//Checking whether it is available or not!
                        return i + 1;
            }
        } else if (optimumAmountOfCards() == 2) {
            for (int i = 0; i < 10; i++) {
                if (cards[i].getAnimal().getAttackMethod1().equals("Kill") || cards[i].getAnimal().getAttackMethod2().equals("Kill"))//Finding that card!
                    if (cards[i].getIsOnTheTable() == 2)//Checking whether it is available or not!
                        return i + 1;
            }
        } else if (optimumAmountOfCards() == 1) {
            if (animalsThatKill == 1) {
                for (int i = 0; i < 10; i++) {
                    if (cards[i].getAnimal().getAttackMethod1().equals("Injure") || cards[i].getAnimal().getAttackMethod2().equals("Injure"))//Finding that card!
                        if (cards[i].getIsOnTheTable() == 2)//Checking whether it is available or not!
                            return i + 1;
                }
            } else {
                int i = Math.abs(new Random().nextInt() % 10) + 1;//Giving a random number to make it less predictable!(than a normal "for")
                if (cards[i].getAnimal().getEnergy() > 200)//It must have at least have 200 energy
                    if (cards[i].getIsOnTheTable() == 2)//Giving a random number
                        return i + 1;
            }
        }
        return Math.abs(new Random().nextInt() % 10) + 1;//Just so it doesn't give us errors for not returning!
    }

    public int optimumAttackTarget(int totalAttackEnergy, Player player) {
        int difference[] = new int[10];//10 results for totalAttackEnergy-opponent's Health
        int counter = 0;//Determine how many of them are smaller than totalAttackEnergy
        int i;
        for (i = 0; i < 10; i++) {//Calculating the differences!
            if (player.cards[i].getIsOnTheTable() == 2)//If it is available...
                if (totalAttackEnergy - player.cards[i].getAnimal().getHealth() >= 0) {
                    difference[i] = totalAttackEnergy - player.cards[i].getAnimal().getHealth();
                    counter++;
                }
        }
        for (i = 0; i < counter; i++) {//Finding the smallest difference!
            int temp;//We want to sort them with the bubble sort method!
            if (difference[i] < difference[i + 1]) {//Shoving the smaller one forward!
                temp = difference[i];
                difference[i] = difference[i + 1];
                difference[i + 1] = temp;
            }
        }
        for (i = 0; i < 10; i++) {//Finding the card!
            if (player.cards[i].getAnimal().getHealth() + difference[counter - 1] == totalAttackEnergy)//If that optimal card is found...
                return (i + 1);
        }
        return Math.abs(new Random().nextInt() % 10) + 1;//This is just because it doesn't give us any errors!
    }

    public int optimumRespawnCard() {//The best card to respawn!
        for (int i = 0; i < 10; i++) {
            if (cards[i].getAnimal().getName().equals("Lion"))
                if (cards[i].getIsOnTheTable() == 2)//If it is available!
                    if (cards[i].getAnimal().getEnergy() < 500)
                        return (i + 1);
        }
        for (int i = 0; i < 10; i++) {
            if (cards[i].getAnimal().getName().equals("Bear"))
                if (cards[i].getIsOnTheTable() == 2)//If it is available!
                    if (cards[i].getAnimal().getEnergy() < 500)
                        return (i + 1);
        }
        for (int i = 0; i < 10; i++) {
            if (cards[i].getAnimal().getName().equals("Tiger"))
                if (cards[i].getIsOnTheTable() == 2)//If it is available!
                    if (cards[i].getAnimal().getEnergy() < 500)
                        return (i + 1);
        }
        for (int i = 0; i < 1; i++) {
            int randomCard = Math.abs(new Random().nextInt() % 10) + 1;//In case our "big guns" have enough energy!(& we use random to make our respawning less predictable for the opponent!)
            if (cards[randomCard - 1].getIsOnTheTable() == 2)
                return randomCard;
            else
                i--;
        }
        return Math.abs(new Random().nextInt() % 10) + 1;//Just so it doesn't give us any errors!
    }
}