import java.util.Random;

public class Computer {
    /**
     * Simulating a Computer!!
     *
     * @author matin tavakoli
     * @param board the Computer's board that he plays on!
     * @param destroyedShips the number of the computer's battleships that are destroyed!(from 0 to 5)
     */
    private Board board;//The computer has two boards(Which are both initialized in the Board class!)
    private int destroyedShips;//The computer has a number of battleships that are destroyed(it can be from 0 to 5)

    public Computer(Board board) {
        this.board = board;
        destroyedShips = 0;
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

    public boolean computerAddShip(Computer computer, BattleShip battleShip) {
        int i = battleShip.getStartingPointRow();
        int j = battleShip.getStartingPointColumn();
        for (int k = 0; k < battleShip.getLength(); k++) {
            if (battleShip.getDirection().equals("Up") || battleShip.getDirection().equals("UP"))
                if (computer.getBoard().getBoardElements()[0][i - k][j] != 1)
                    computer.getBoard().getBoardElements()[0][i - k][j] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        computer.getBoard().getBoardElements()[0][i - m][j] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    return false;
                }
            else if (battleShip.getDirection().equals("Down") || battleShip.getDirection().equals("DOWN"))
                if (computer.getBoard().getBoardElements()[0][i + k][j] != 1)
                    computer.getBoard().getBoardElements()[0][i + k][j] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        computer.getBoard().getBoardElements()[0][i + m][j] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    return false;
                }
            else if (battleShip.getDirection().equals("Left") || battleShip.getDirection().equals("LEFT"))
                if (computer.getBoard().getBoardElements()[0][i][j - k] != 1)
                    computer.getBoard().getBoardElements()[0][i][j - k] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        computer.getBoard().getBoardElements()[0][i][j - m] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    return false;
                }
            else if (battleShip.getDirection().equals("Right") || battleShip.getDirection().equals("RIGHT"))
                if (computer.getBoard().getBoardElements()[0][i][j + k] != 1)
                    computer.getBoard().getBoardElements()[0][i][j + k] = 1;//We explained what "1" means at the beginning of this class!
                else {
                    for (int m = 0; m < k; m++) {
                        computer.getBoard().getBoardElements()[0][i][j + m] = 0;//returning the one's that were incorrectly occupied to it's previous state!
                    }
                    return false;
                }
        }
        return true;//In case nothing goes wrong!
    }

    public boolean isComputersShipDestroyed(Computer computer, BattleShip battleShip) {
        int i = battleShip.getStartingPointRow();
        int j = battleShip.getStartingPointColumn();
        for (int k = 0; k < battleShip.getLength(); k++) {
            if (battleShip.getDirection().equals("Up") || battleShip.getDirection().equals("UP"))
                if (computer.getBoard().getBoardElements()[0][i - k][j] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Down") || battleShip.getDirection().equals("DOWN"))
                if (computer.getBoard().getBoardElements()[0][i + k][j] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Left") || battleShip.getDirection().equals("LEFT"))
                if (computer.getBoard().getBoardElements()[0][i][j - k] != 2)//That means there not damaged!
                    return false;
            if (battleShip.getDirection().equals("Right") || battleShip.getDirection().equals("RIGHT"))
                if (computer.getBoard().getBoardElements()[0][i][j + k] != 2)//That means there not damaged!
                    return false;
        }
        return true;//That's in case nothing goes wrong!
    }

    public CannonBall optimumTarget(Computer computer, int number) {
        boolean flag = false;//To show us whether we had successful strikes or not!
        CannonBall optimumCannonBall = new CannonBall(Math.abs(new Random().nextInt() % 10), Math.abs(new Random().nextInt() % 10));//Just so it doesn't give us errors for not initializing!
        if (number == 0)
            optimumCannonBall = new CannonBall(Math.abs(new Random().nextInt() % 10), Math.abs(new Random().nextInt() % 10));//Just hit randomly!
        else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (computer.getBoard().getBoardElements()[1][i][j] == 3) {//A successful strike!
                        flag = true;//We know that we striked correctly at least once!
                    }
                }
            }
            int m = Math.abs(new Random().nextInt() % 10);
            int n = Math.abs(new Random().nextInt() % 10);//We're doing this to make our computer's artificial intelligence a bit unpredictable(Because in the previous "2 for's" it was obvious that our procedure starts from the top corner and ends at the bottom corner!
            if (computer.getBoard().getBoardElements()[1][m][n] == 3) {//We check all 4 sides of this square!
                for (int j = 1; j < 10 - m; j++) {
                    if (computer.getBoard().getBoardElements()[1][m + j][n] == 0) {
                        optimumCannonBall = new CannonBall(m + j, n);
                        j = 10 - m;
                        break;
                    } else if (computer.getBoard().getBoardElements()[1][m + j][n] == 2)
                        for (int k = 1; k < m; k++) {
                            if (computer.getBoard().getBoardElements()[1][m - k][n] == 0) {
                                optimumCannonBall = new CannonBall(m - k, n);
                                k = m;
                                j = 10 - m;
                                break;
                            } else if (computer.getBoard().getBoardElements()[1][m - k][n] == 2)
                                for (int l = 0; l < 10 - n; l++) {
                                    if (computer.getBoard().getBoardElements()[1][m][n + l] == 0) {
                                        optimumCannonBall = new CannonBall(m, n + l);
                                        l = 10 - n;
                                        k = m;
                                        j = 10 - m;
                                        break;
                                    } else if (computer.getBoard().getBoardElements()[1][m][n + l] == 2)
                                        for (int o = 0; o < n; o++) {
                                            if (computer.getBoard().getBoardElements()[1][m][n - o] == 0) {
                                                optimumCannonBall = new CannonBall(m, n - o);
                                                o = n;
                                                l = 10 - n;
                                                k = m;
                                                j = 10 - m;
                                                break;
                                            }
                                        }
                                }
                        }
                }
            }
        }
        if (flag == false)//This means that we have nothing to track yet!
            optimumCannonBall = new CannonBall(Math.abs(new Random().nextInt() % 10), Math.abs(new Random().nextInt() % 10));//Just hit randomly!
        return optimumCannonBall;
    }

    public int computerShoot(CannonBall cannonBall, Computer computer, Player player) {//A player(me) shoots at another player(opponent) with an ordinary cannonBall!
        //Why int? '1' for hitting the target,'0' for hitting an empty square & '-1' for not hitting the target(or empty square)!
        int i = cannonBall.getRow();
        int j = cannonBall.getColumn();
        if (player.getBoard().getBoardElements()[0][i][j] == 4) {
            return -1;
        } else if (player.getBoard().getBoardElements()[0][i][j] == 2) {
            System.out.println("You damaged this part before!");
            return -1;
        } else if (player.getBoard().getBoardElements()[0][i][j] == 0) {
            computer.getBoard().getBoardElements()[1][i][j] = 4;//You missed!
            player.getBoard().getBoardElements()[0][i][j] = 4;//The opponent was lucky that you missed!
            return 0;
        } else if (player.getBoard().getBoardElements()[0][i][j] == 1) {
            computer.getBoard().getBoardElements()[1][i][j] = 3;
            player.getBoard().getBoardElements()[0][i][j] = 2;
            System.out.println("The computer hits the target!!");
            return 1;//It hits the target.So we return true to have another shot!
        }
        return -1;//In case it didn't hit the target or an empty square(doesn't return 1 or 0)
    }

    public int whoWins(Computer computer, Player player) {
        if (computer.getDestroyedShips() == 5)//This means that all my battleships are destroyed!
            return 1;//The player wins!
        else if (player.getDestroyedShips() == 5)//This means that all the oponnent's battleships are destroyed!
            return -1;//The computer wins!
        else
            return 0;//This means that the game is not over yet!
    }
}