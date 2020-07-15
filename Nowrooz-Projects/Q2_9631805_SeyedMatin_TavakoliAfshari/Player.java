import java.util.Random;

public class Player {
    /**
     * Simulating a Player!!
     *
     * @author matin tavakoli
     * @param board the Player's board that he plays on!
     * @param destroyedShips the number of the player's battleships that are destroyed!(from 0 to 5)
     */
    private int number;//For example 1 or 2.
    private Board board;//Every player has two boards(Which are both initialized in the Board class!)
    private int destroyedShips;//Every player has a number of battleships that are destroyed(it can be from 0 to 5)

    public Player(int number, Board board) {
        this.number = number;
        this.board = board;
        destroyedShips = 0;
    }

    public int getNumber() {
        return number;
    }

    public Board getBoard() {
        return board;
    }

    public int getDestroyedShips() {
        return destroyedShips;
    }

    public void setDestroyedShips(int destroyedShips) {
        this.destroyedShips = destroyedShips;
    }

    public boolean addShip(Player player, BattleShip battleShip) {
        int i = battleShip.getStartingPointRow();
        int j = battleShip.getStartingPointColumn();
        for (int k = 0; k < battleShip.getLength(); k++) {
            if (battleShip.getDirection().equals("Up") || battleShip.getDirection().equals("UP"))
                if (player.getBoard().getBoardElements()[0][i - k][j] != 1)
                    player.getBoard().getBoardElements()[0][i - k][j] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        player.getBoard().getBoardElements()[0][i - m][j] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    System.out.println("One of your squares has been occupied before.Please make another battleShip!");
                    return false;
                }
            else if (battleShip.getDirection().equals("Down") || battleShip.getDirection().equals("DOWN"))
                if (player.getBoard().getBoardElements()[0][i + k][j] != 1)
                    player.getBoard().getBoardElements()[0][i + k][j] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        player.getBoard().getBoardElements()[0][i + m][j] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    System.out.println("One of your squares has been occupied before.Please make another battleShip!");
                    return false;
                }
            else if (battleShip.getDirection().equals("Left") || battleShip.getDirection().equals("LEFT"))
                if (player.getBoard().getBoardElements()[0][i][j - k] != 1)
                    player.getBoard().getBoardElements()[0][i][j - k] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        player.getBoard().getBoardElements()[0][i][j - m] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    System.out.println("One of your squares has been occupied be fore.Please make another battleShip!");
                    return false;
                }
            else if (battleShip.getDirection().equals("Right") || battleShip.getDirection().equals("RIGHT"))
                if (player.getBoard().getBoardElements()[0][i][j + k] != 1)
                    player.getBoard().getBoardElements()[0][i][j + k] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        player.getBoard().getBoardElements()[0][i][j + m] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    System.out.println("One of your squares has been occupied before.Please make another battleShip!");
                    return false;
                }
        }
        return true;//In case nothing goes wrong!
    }

    public boolean isShipDestroyed(Player player, BattleShip battleShip) {
        int i = battleShip.getStartingPointRow();
        int j = battleShip.getStartingPointColumn();
        for (int k = 0; k < battleShip.getLength(); k++) {
            if (battleShip.getDirection().equals("Up") || battleShip.getDirection().equals("UP"))
                if (player.getBoard().getBoardElements()[0][i - k][j] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Down") || battleShip.getDirection().equals("DOWN"))
                if (player.getBoard().getBoardElements()[0][i + k][j] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Left") || battleShip.getDirection().equals("LEFT"))
                if (player.getBoard().getBoardElements()[0][i][j - k] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Right") || battleShip.getDirection().equals("RIGHT"))
                if (player.getBoard().getBoardElements()[0][i][j + k] != 2)//That means there not damaged!
                    return false;
        }
        return true;//That's in case nothing goes wrong!
    }

    public int ordinaryShoot(CannonBall cannonBall, Player me, Player opponent) {//A player(me) shoots at another player(opponent) with an ordinary cannonBall!
        //Why int? '1' for hitting the target,'0' for hitting an empty square & '-1' for not hitting the target(or empty square)!
        int i = cannonBall.getRow();
        int j = cannonBall.getColumn();
        if (opponent.getBoard().getBoardElements()[0][i][j] == 4) {
            System.out.println("You missed before!");
            return -1;
        } else if (opponent.getBoard().getBoardElements()[0][i][j] == 2) {
            System.out.println("You damaged this part before!");
            return -1;
        } else if (opponent.getBoard().getBoardElements()[0][i][j] == 0) {
            me.getBoard().getBoardElements()[1][i][j] = 4;//You missed!
            opponent.getBoard().getBoardElements()[0][i][j] = 4;//The opponent was lucky that you missed!
            return 0;
        } else if (opponent.getBoard().getBoardElements()[0][i][j] == 1) {
            me.getBoard().getBoardElements()[1][i][j] = 3;
            opponent.getBoard().getBoardElements()[0][i][j] = 2;
            System.out.println("Bullseye!!");
            return 1;//It hits the target.So we return true to have another shot!
        }
        return -1;//In case it didn't hit the target or an empty square(doesn't return 1 or 0)
    }

    public int wrongAimingShoot(CannonBall cannonBall, Player me, Player opponent) {//A player(me) shoots at another player(opponent) with a wrong aiming cannonBall!
        //Why int? '1' for hitting the target,'0' for hitting an empty square & '-1' for not hitting the target(or empty square)!
        int i = cannonBall.getRow();
        int j = cannonBall.getColumn();
        int k = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1
        int l = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1
        if (opponent.getBoard().getBoardElements()[0][i][j] == 2) {//Aiming at a wrong target!
            System.out.println("You are aiming at somewhere which has been damaged before!");
            return -1;
        } else if (opponent.getBoard().getBoardElements()[0][i + k][j + l] == 2 || opponent.getBoard().getBoardElements()[0][i + k][j + l] == 4) {//You damaged or missed before!
            for (int m = 0; m < 1; m++) {
                k = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1(Making a new random number!)
                l = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1(Making a new random number!)
                if (opponent.getBoard().getBoardElements()[0][i + k][j + l] == 2 || opponent.getBoard().getBoardElements()[0][i + k][j + l] == 4)//This means that you still missed!
                    m--;//This means that we make random numbers until our square is not 2(Is either 0,which means an empty square,Or 1,which means there is a battleship's part there!)
            }
        }
        if (opponent.getBoard().getBoardElements()[0][i + k][j + l] == 0) {//[i+k][j+l] is a 3*3 square!
            //Why "if" instead of "else if"? 1.Because after the procedure of the previous "if",our random element will be 0(empty square) or 1(a part of a battleship). 2.Our shooting process is not over yet and we need to finish it with one of the next two "if's"!
            me.getBoard().getBoardElements()[1][i + k][j + l] = 4;//You missed!
            opponent.getBoard().getBoardElements()[0][i + k][j + l] = 4;//The opponent was lucky that you missed!
            return 0;
        }
        if (opponent.getBoard().getBoardElements()[0][i + k][j + l] == 1) {
            //Why "if" instead of "else if"? 1.Because after the procedure of the previous "if",our random element will be 0(empty square) or 1(a part of a battleship). 2.Our shooting process is not over yet and we need to finish it with one of the next two "if's"!
            me.getBoard().getBoardElements()[1][i + k][j + l] = 3;
            opponent.getBoard().getBoardElements()[0][i + k][j + l] = 2;
            System.out.println("Bullseye!!");
            return 1;//It hits the target.So we return true to have another shot!
        }
        return -1;//In case it didn't hit the target or an empty square(doesn't return 1 or 0)
    }

    public int ordinaryShoot(CannonBall cannonBall, Player me, Computer computer) {//A player(me) shoots at another player(opponent) with an ordinary cannonBall!
        //Why int? '1' for hitting the target,'0' for hitting an empty square & '-1' for not hitting the target(or empty square)!
        int i = cannonBall.getRow();
        int j = cannonBall.getColumn();
        if (computer.getBoard().getBoardElements()[0][i][j] == 4) {
            System.out.println("You missed before!");
            return -1;
        } else if (computer.getBoard().getBoardElements()[0][i][j] == 2) {
            System.out.println("You damaged this part before!");
            return -1;
        } else if (computer.getBoard().getBoardElements()[0][i][j] == 0) {
            me.getBoard().getBoardElements()[1][i][j] = 4;//You missed!
            computer.getBoard().getBoardElements()[0][i][j] = 4;//The opponent was lucky that you missed!
            return 0;
        } else if (computer.getBoard().getBoardElements()[0][i][j] == 1) {
            me.getBoard().getBoardElements()[1][i][j] = 3;
            computer.getBoard().getBoardElements()[0][i][j] = 2;
            System.out.println("Bullseye!!");
            return 1;//It hits the target.So we return true to have another shot!
        }
        return -1;//In case it didn't hit the target or an empty square(doesn't return 1 or 0)
    }

    public int wrongAimingShoot(CannonBall cannonBall, Player me, Computer computer) {//A player(me) shoots at another player(opponent) with a wrong aiming cannonBall!
        //Why int? '1' for hitting the target,'0' for hitting an empty square & '-1' for not hitting the target(or empty square)!
        int i = cannonBall.getRow();
        int j = cannonBall.getColumn();
        int k = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1
        int l = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1
        if (computer.getBoard().getBoardElements()[0][i][j] == 2) {//Aiming at a wrong target!
            System.out.println("You are aiming at somewhere which has been damaged before!");
            return -1;
        } else if (computer.getBoard().getBoardElements()[0][i + k][j + l] == 2 || computer.getBoard().getBoardElements()[0][i + k][j + l] == 4) {//You damaged or missed before!
            for (int m = 0; m < 1; m++) {
                k = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1(Making a new random number!)
                l = Math.abs(new Random().nextInt() % 3) - 1;//Gives a number between -1,0 & 1(Making a new random number!)
                if (computer.getBoard().getBoardElements()[0][i + k][j + l] == 2 || computer.getBoard().getBoardElements()[0][i + k][j + l] == 4)//This means that you still missed!
                    m--;//This means that we make random numbers until our square is not 2(Is either 0,which means an empty square,Or 1,which means there is a battleship's part there!)
            }
        }
        if (computer.getBoard().getBoardElements()[0][i + k][j + l] == 0) {//[i+k][j+l] is a 3*3 square!
            //Why "if" instead of "else if"? 1.Because after the procedure of the previous "if",our random element will be 0(empty square) or 1(a part of a battleship). 2.Our shooting process is not over yet and we need to finish it with one of the next two "if's"!
            me.getBoard().getBoardElements()[1][i + k][j + l] = 4;//You missed!
            computer.getBoard().getBoardElements()[0][i + k][j + l] = 4;//The opponent was lucky that you missed!
            return 0;
        }
        if (computer.getBoard().getBoardElements()[0][i + k][j + l] == 1) {
            //Why "if" instead of "else if"? 1.Because after the procedure of the previous "if",our random element will be 0(empty square) or 1(a part of a battleship). 2.Our shooting process is not over yet and we need to finish it with one of the next two "if's"!
            me.getBoard().getBoardElements()[1][i + k][j + l] = 3;
            computer.getBoard().getBoardElements()[0][i + k][j + l] = 2;
            System.out.println("Bullseye!!");
            return 1;//It hits the target.So we return true to have another shot!
        }
        return -1;//In case it didn't hit the target or an empty square(doesn't return 1 or 0)
    }

    public Player whoWins(Player me, Player opponent) {
        if (me.destroyedShips == 5)//This means that all my battleships are destroyed!
            return opponent;//The opponent wins!
        else if (opponent.destroyedShips == 5)//This means that all the oponnent's battleships are destroyed!
            return me;//I win!
        else
            return null;//This means that the game is not over yet!
    }

    public int whoWins(Player player, Computer computer) {
        if (computer.getDestroyedShips() == 5)//This means that all my battleships are destroyed!
            return 1;//The player wins!
        else if (player.getDestroyedShips() == 5)//This means that all the oponnent's battleships are destroyed!
            return -1;//The computer wins!
        else
            return 0;//This means that the game is not over yet!
    }
}