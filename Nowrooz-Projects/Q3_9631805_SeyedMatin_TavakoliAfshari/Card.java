public class Card {
    /**
     * Simulating a game card!
     *
     * @author Matin Tavakoli
     * ANSI codes are for a better display!
     * parameters are explained in the comments!
     */
    private Animal animal;
    private int isOnTheTable;//Is the card in the game(2),in the players/computers hands(1) or has been eliminated by the opponent(0)!
    private String animalNameLineDisplay;//Displaying the animal's name in a line!
    private String animalAttackMethod1LineDisplay;//Displaying the animal's 1st attacking method in a line!
    private String animalAttackMethod2LineDisplay;//Displaying the animal's 2nd attacking method in a line!
    private String animalEnergyLineDisplay;//Displaying the animal's energy in a line!
    private String animalHealthLineDisplay;//Displaying the animal's health in a line!
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Card(Animal animal) {
        this.animal = animal;
        isOnTheTable = 2;
        if (animal.getName() != null) {//Just to bring it in a separate section!
            if (animal.getName().length() == 3)
                animalNameLineDisplay = "|     " + animal.getName() + "    |";
            else if (animal.getName().length() == 4)
                animalNameLineDisplay = "|    " + animal.getName() + "    |";
            else if (animal.getName().length() == 5)
                animalNameLineDisplay = "|    " + animal.getName() + "   |";
            else if (animal.getName().length() == 6)
                animalNameLineDisplay = "|   " + animal.getName() + "   |";
            else if (animal.getName().length() == 7)
                animalNameLineDisplay = "|   " + animal.getName() + "  |";
            else if (animal.getName().length() == 8)
                animalNameLineDisplay = "|  " + animal.getName() + "  |";
        }
        if (animal.getAttackMethod1() != null) {//Just to bring it in a separate section!
            if (animal.getAttackMethod1().equals("Kill") || animal.getAttackMethod1().equals("Bite")) {//4 letters!
                if (animal.getAttackMethod1Amount() >= 100)
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + "  |" + ANSI_RESET;
                else
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "|   " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + "  |" + ANSI_RESET;

            } else if (animal.getAttackMethod1().equals("Wound")) {//5 letters!
                if (animal.getAttackMethod1Amount() >= 100)
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + " |" + ANSI_RESET;
                else
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + "  |" + ANSI_RESET;
            } else if (animal.getAttackMethod1().equals("Injure") || animal.getAttackMethod1().equals("Attack")) {//6 letters!
                if (animal.getAttackMethod1Amount() >= 100)
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "| " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + " |" + ANSI_RESET;
                else
                    animalAttackMethod1LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod1() + ":" + animal.getAttackMethod1Amount() + " |" + ANSI_RESET;
            }
        }
        if (animal.getAttackMethod2() != null) {
            if (animal.getAttackMethod2().equals("Kill"))
                animalAttackMethod2LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod2() + ":" + animal.getAttackMethod2Amount() + "  |" + ANSI_RESET;//No amount "if's" because in every type their either all a 3 digit number or a 2 digit number!
            else if (animal.getAttackMethod2().equals("Wound"))
                animalAttackMethod2LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod2() + ":" + animal.getAttackMethod2Amount() + " |" + ANSI_RESET;
            else if (animal.getAttackMethod2().equals("Attack"))
                animalAttackMethod2LineDisplay = ANSI_PURPLE + "|  " + animal.getAttackMethod2() + ":" + animal.getAttackMethod2Amount() + " |" + ANSI_RESET;
        } else if (animal.getAttackMethod2() == null)
            animalAttackMethod2LineDisplay = "|            |";
        if (animal.getEnergy() >= 0) {
            if (animal.getEnergy() == 1000)//Only the Lion's energy is 1000!
                animalEnergyLineDisplay = ANSI_BLUE + "| " + "Energy:" + animal.getEnergy() + "|" + ANSI_RESET;
            else if (animal.getEnergy() < 1000 && animal.getEnergy() >= 100)
                animalEnergyLineDisplay = ANSI_BLUE + "| " + "Energy:" + animal.getEnergy() + " |" + ANSI_RESET;
            else if (animal.getEnergy() < 100 && animal.getEnergy() >= 10)
                animalEnergyLineDisplay = ANSI_BLUE + "|  " + "Energy:" + animal.getEnergy() + " |" + ANSI_RESET;
            else if (animal.getEnergy() < 10 && animal.getEnergy() >= 0)
                animalEnergyLineDisplay = ANSI_BLUE + "|  " + "Energy:" + animal.getEnergy() + "  |" + ANSI_RESET;
        }
        if (animal.getHealth() >= 0 || animal.getHealth() < 0) {//positive & negative!
            if (animal.getHealth() > 0) {
                if (animal.getHealth() >= 1000)
                    animalHealthLineDisplay = ANSI_GREEN + "| " + "Health:" + animal.getHealth() + "|" + ANSI_RESET;
                else if (animal.getHealth() < 1000 && animal.getHealth() >= 100)
                    animalHealthLineDisplay = ANSI_GREEN + "| " + "Health:" + animal.getHealth() + " |" + ANSI_RESET;
                else if (animal.getHealth() < 100)
                    animalHealthLineDisplay = ANSI_GREEN + "|  " + "Health:" + animal.getHealth() + " |" + ANSI_RESET;
            } else if (animal.getHealth() <= 0)
                animalHealthLineDisplay = ANSI_RED + "|  " + "Health:" + animal.getHealth() + "  |" + ANSI_RESET;//We will display it as 0 health!
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getIsOnTheTable() {
        return isOnTheTable;
    }

    public void setIsOnTheTable(int isOnTheTable) {
        this.isOnTheTable = isOnTheTable;
    }

    public String getAnimalNameLineDisplay() {
        return animalNameLineDisplay;
    }

    public String getAnimalAttackMethod1LineDisplay() {
        return animalAttackMethod1LineDisplay;
    }

    public String getAnimalAttackMethod2LineDisplay() {
        return animalAttackMethod2LineDisplay;
    }

    public String getAnimalEnergyLineDisplay() {
        return animalEnergyLineDisplay;
    }

    public String getAnimalHealthLineDisplay() {
        return animalHealthLineDisplay;
    }
}
