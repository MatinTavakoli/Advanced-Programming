public class Player {
    /**
     * Simulating a player!
     *
     * @author Matin Tavakoli
     * parameters are explained in the comments!
     */
    private int number;//The player's number!
    Card cards[] = new Card[10];
    private int numberOfCards;//The number of cards the player has!(from 0 to 10)
    private int animalsThatWound;//The number of animals that wound the opposition's animals!
    private int animalsThatKill;//The number of animals that kill the opposition's animals!
    private int animalsThatInjure;//The number of animals that injure the opposition's animals!
    private int animalsThatAttack;//The number of animals that can attack at the opposition's animals!
    private int animalsThatBite;//The number of animals that can bite the opposition's animals!

    public Player(int number) {
        this.number = number;
        numberOfCards = 0;//Because at the beginning of the game,the player has no cards!
        animalsThatWound = 0;//Because at the beginning of the game,the player has no cards.Therefore no animals!
        animalsThatKill = 0;//Because at the beginning of the game,the player has no cards.Therefore no animals!
        animalsThatInjure = 0;//Because at the beginning of the game,the player has no cards.Therefore no animals!
        animalsThatAttack = 0;//Because at the beginning of the game,the player has no cards.Therefore no animals!
        animalsThatBite = 0;//Because at the beginning of the game,the player has no cards.Therefore no animals!
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfCards() {
        int temp = numberOfCards;
        for (int i = 0; i < temp; i++) {
            if (cards[i].getIsOnTheTable() == 0)
                numberOfCards--;//This means that that card does not count as the players cards
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

    public void removeMethod(Card card) {//removing that method from our list of animals if a card has been eliminated
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
}
