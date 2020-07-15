public class Board {
    /**
     * Simulating a battlefields board!!
     *
     * @author matin tavakoli
     * @param boardElements for each player, we have two boards and each board has 100 squares so it's boardElements[2][10][10]
     * @param number the player's number!
     */
    private int[][][] boardElements = new int[2][10][10];//Because for each player, we have two boards.His board an the opponent's board!
    private int number;//Every board has a number!
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";

    //We agree that for:
    //boardElements[][][]=0==>The square is empty!(Your board & the opponent's board)
    //boardElements[][][]=1==>The square is occupied by a part of one of our battleships and is safe and sound!(Your board)
    //boardElements[][][]=2==>The square is occupied by a part of one of our battleships and is damaged!(Your board)
    //boardElements[][][]=3==>The square is occupied by a part of one of the opponent's battleships and you managed to damage it!(Opponent's board)
    //boardElements[][][]=4==>Your shot didn't hit anything!(Opponent's board)
    public Board(int number) {
        this.number = number;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardElements[0][i][j] = 0;
                boardElements[1][i][j] = 0;
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public int[][][] getBoardElements() {
        return boardElements;
    }

    public void display(Player player) {//Every player has a board.But to acces the player number,we need to have a player in it's argument to access it's number!
        if (player.getNumber() == number) {//This means that for Board b1 and Player p1,(b1.getNumber=1) equals (p1.getNumber=1).But p2 can't access b1(And that's what we want!)
            System.out.println("Player #" + player.getNumber() + ":");
            System.out.print("  ");
            for (int i = 0; i < 10; i++) {
                System.out.print("|" + " " + i + " ");
            }
            System.out.print("|");
            System.out.print("    ");//A tab before the second board!
            System.out.print("  ");
            for (int i = 0; i < 10; i++) {
                System.out.print("| " + i + " ");//The first line!
            }
            System.out.println("|");


            for (int i = 0; i < 10; i++) {
                System.out.print("--|");
                for (int j = 0; j < 10; j++) {
                    System.out.print("---+");
                }
                System.out.print("    ");//A tab before the second board!
                System.out.print("--|");
                for (int j = 0; j < 10; j++) {
                    System.out.print("---+");
                }
                System.out.println();
                System.out.print(i + " |");
                for (int j = 0; j < 10; j++) {
                    if (boardElements[0][i][j] == 0)
                        System.out.print("   |");
                    else if (boardElements[0][i][j] == 1)
                        System.out.print(ANSI_BLUE_BACKGROUND + "   " + ANSI_RESET + "|");
                    else if (boardElements[0][i][j] == 2)
                        System.out.print(ANSI_RED_BACKGROUND + "   " + ANSI_RESET + "|");
                    else if (boardElements[0][i][j] == 4)
                        System.out.print(ANSI_YELLOW_BACKGROUND + "   " + ANSI_RESET + "|");//This means that the opponent has missed!
                }
                System.out.print("    ");
                System.out.print(i + " |");
                for (int j = 0; j < 10; j++) {
                    if (boardElements[1][i][j] == 0)
                        System.out.print("   |");
                    if (boardElements[1][i][j] == 3)
                        System.out.print(ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET + "|");
                    if (boardElements[1][i][j] == 4)
                        System.out.print(ANSI_YELLOW_BACKGROUND + "   " + ANSI_RESET + "|");
                }
                System.out.println();//Going to the next line!
            }


            System.out.print("--|");
            for (int j = 0; j < 10; j++) {
                System.out.print("---+");
            }
            System.out.print("    ");//A tab before the second board!
            System.out.print("--|");
            for (int j = 0; j < 10; j++) {
                System.out.print("---+");//The last line!
            }
            System.out.println();
            System.out.println();
            System.out.println("                 My Board                                    Opponent's Board          ");
            System.out.println();//Making space!
        } else
            System.out.println("You have no access to that board!Please try again");
    }
}
