public class Table {
    /**
     * Simulating a table that we play on!
     *
     * @author Matin Tavakoli
     * ANSI codes are for a better display!
     * parameters are explained in the comments!
     */
    private Card[][][] dealCards = new Card[2][3][10];//The cards that the dealer(the computer)gives to the players/(player & computer)(The only reason we define part of it as "[3][10]" is that it doesn't get mistaken with the game cards!)
    private Card[][] gameCards = new Card[2][10];//The cards that the players/(player & computer) have at the beginning of the game after picking 10 dealing cards!
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Table(Card[][][] dealCards) {//The Table when we're dealing!
        this.dealCards = dealCards;
    }

    public Table(Card[][] gameCards) {//The table when we're playing the game!(after the players/(player & computer) pick their cards)
        this.gameCards = gameCards;
    }

    public Card[][][] getDealCards() {
        return dealCards;
    }

    public Card[][] getGameCards() {
        return gameCards;
    }

    public void display1(Player player) {//Displaying the deal cards on the table!
        //Why displaying for 1 player/computer?Becuase 30 cards takes a lot of space!So we let player1 pick first & then player2/computer!
        System.out.println("Player #" + (player.getNumber()));
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 10; k++) {
                if (dealCards[player.getNumber() - 1][j][k].getIsOnTheTable() == 2) {
                    if (10 * j + k >= 9)
                        System.out.print(" _____" + (10 * j + (k + 1)) + "_____  ");
                    else
                        System.out.print(" _____" + (10 * j + (k + 1)) + "______  ");
                } else if (dealCards[player.getNumber() - 1][j][k].getIsOnTheTable() == 0) {
                    if (10 * j + k >= 9)
                        System.out.print(" _____" + ANSI_RED + (10 * j + (k + 1)) + ANSI_RESET + "_____  ");//this means that it has been picked before!
                    else
                        System.out.print(" _____" + ANSI_RED + (10 * j + (k + 1)) + ANSI_RESET + "______  ");//this means that it has been picked before!
                }
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[player.getNumber() - 1][j][k].getAnimalNameLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[player.getNumber() - 1][j][k].getAnimalAttackMethod1LineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[player.getNumber() - 1][j][k].getAnimalAttackMethod2LineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[player.getNumber() - 1][j][k].getAnimalEnergyLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[player.getNumber() - 1][j][k].getAnimalHealthLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|____________| ");
            }
            System.out.println();//Printing a line between each 10 cards!
            System.out.println();//Printing a line between each 10 cards!
        }
    }

    public void computerDisplay1(Computer computer) {//Displaying the deal cards on the table(for the computer!)
        //Why displaying for 1 player/computer?Because 30 cards takes a lot of space!So we let player1 pick first & then player2/the computer!
        System.out.println("Computer!");
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 10; k++) {
                if (dealCards[1][j][k].getIsOnTheTable() == 2) {
                    if (10 * j + k >= 9)
                        System.out.print(" _____" + (10 * j + (k + 1)) + "_____  ");
                    else
                        System.out.print(" _____" + (10 * j + (k + 1)) + "______  ");
                } else if (dealCards[1][j][k].getIsOnTheTable() == 0) {
                    if (10 * j + k >= 9)
                        System.out.print(" _____" + ANSI_RED + (10 * j + (k + 1)) + ANSI_RESET + "_____  ");//this means that it has been picked before!
                    else
                        System.out.print(" _____" + ANSI_RED + (10 * j + (k + 1)) + ANSI_RESET + "______  ");//this means that it has been picked before!
                }
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[1][j][k].getAnimalNameLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[1][j][k].getAnimalAttackMethod1LineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[1][j][k].getAnimalAttackMethod2LineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[1][j][k].getAnimalEnergyLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print(dealCards[1][j][k].getAnimalHealthLineDisplay() + " ");
            }
            System.out.println();
            for (int k = 0; k < 10; k++) {
                System.out.print("|____________| ");
            }
            System.out.println();//Printing a line between each 10 cards!
            System.out.println();//Printing a line between each 10 cards!
        }
    }

    public void display2() {//Displaying the game cards on the table!
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                System.out.println("Player #1");
            else
                System.out.println("Player #2/Computer");
            for (int j = 0; j < 10; j++) {
                if (gameCards[i][j].getIsOnTheTable() == 2) {//Is it on the table?
                    if (j != 9)
                        System.out.print(" ______" + (j + 1) + "_____  ");
                    else
                        System.out.print(" _____" + (j + 1) + "_____  ");
                } else if (gameCards[i][j].getIsOnTheTable() == 1) {
                    if (j != 9)
                        System.out.print(" ______" + ANSI_GREEN + (j + 1) + ANSI_RESET + "_____  ");//this means that it has been picked before!
                    else
                        System.out.print(" _____" + ANSI_GREEN + (j + 1) + ANSI_RESET + "______  ");//this means that it has been picked before!
                } else if (gameCards[i][j].getIsOnTheTable() == 0) {
                    if (j != 9)
                        System.out.print(" ______" + ANSI_RED + (j + 1) + ANSI_RESET + "_____  ");//this means that it has been picked before!
                    else
                        System.out.print(" _____" + ANSI_RED + (j + 1) + ANSI_RESET + "______  ");//this means that it has been picked before!
                }
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(gameCards[i][j].getAnimalNameLineDisplay() + " ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(gameCards[i][j].getAnimalAttackMethod1LineDisplay() + " ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(gameCards[i][j].getAnimalAttackMethod2LineDisplay() + " ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("|            | ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(gameCards[i][j].getAnimalEnergyLineDisplay() + " ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print(gameCards[i][j].getAnimalHealthLineDisplay() + " ");
            }
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("|____________| ");
            }
            System.out.println();
            System.out.println();
            if (i == 0) {
                System.out.println();
                System.out.println("               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~     ");//Printing something between the two set of cards!
                System.out.println();
            }
        }
    }

    public int whoWinsMode1(Player player1, Player player2) {//When the user selects 2 players!
        if (player2.getNumberOfCards() == 0)
            return 1;
        else if (player1.getNumberOfCards() == 0)
            return -1;
        else
            return 0;//This means that the game is still not over!
    }

    public int whoWinsMode2(Player player, Computer computer) {//When the user selects 1 player!(with the pc)
        if (computer.getNumberOfCards() == 0)
            return 1;
        else if (player.getNumberOfCards() == 0)
            return -1;
        else
            return 0;//This means that the game is still not over!
    }
}
