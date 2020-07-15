import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mode = 0;//2 players or with the PC!
        String modeString;//We use sc.next().So we need a String type variable for our variables.
        System.out.println("Welcome to the battlefield!!");
        System.out.println("Please press '1' if you want to play with another player and '2' if you want to play with the Computer!");
        for (int i = 0; i < 1; i++) {
            modeString = sc.next();
            if (modeString.equals("" + 1 + "") || modeString.equals("" + 2 + "")) {
                mode = Integer.parseInt(modeString);
            } else {
                System.out.println("You're mode is neither '1' nor '2' ! Please try again!");
                i--;//This goes on until the user enters an integer!
            }
        }
        if (mode == 1) {
            Board b1 = new Board(1);
            Board b2 = new Board(2);
            Player player1 = new Player(1, b1);
            Player player2 = new Player(2, b2);
            BattleShip battleShips[][] = new BattleShip[2][5];
            int shipLengths[][] = new int[2][5];
            String shipLengthsString[][] = new String[2][5];//We use sc.next().So we need a String type variable for our variables.
            int shipStartingPointRows[][] = new int[2][5];//2 for the number of players.5 for the number of battleShips!
            String shipStartingPointRowsString[][] = new String[2][5];//We use sc.next().So we need a String type variable for our variables.
            int shipStartingPointColumns[][] = new int[2][5];
            String shipStartingPointColumnsString[][] = new String[2][5];//We use sc.next().So we need a String type variable for our variables.
            String[][] shipDirections = new String[2][5];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    battleShips[i][j] = new BattleShip(0, 0, 0, "");//Just so it doesn't give us errors for not initializing!
                }
            }
            for (int i = 0; i < 2; i++) {//Two players
                for (int j = 0; j < 5; j++) {//The number of battleShips for every player
                    for (int k = 0; k < 1; k++) {//Just to make sure our ship is valid!

                        System.out.println("Player #" + (i + 1) + "!Please enter battleship #" + (j + 1) + "'s Length(A number from 2 to 5)");
                        shipLengthsString[i][j] = sc.next();
                        if (shipLengthsString[i][j].equals("" + 2 + "") || shipLengthsString[i][j].equals("" + 2 + "") || shipLengthsString[i][j].equals("" + 3 + "") || shipLengthsString[i][j].equals("" + 4 + "") || shipLengthsString[i][j].equals("" + 5 + "")) {
                            shipLengths[i][j] = Integer.parseInt(shipLengthsString[i][j]);
                            break;
                        } else {
                            System.out.println("You're ship's length is not an integer from '2' to '5'!Please try again!");
                            k--;
                        }
                    }
                    for (int k = 0; k < 1; k++) {

                        System.out.println("Player #" + (i + 1) + "!Please enter battleship #" + (j + 1) + "'s Starting Point Row(A number from 0 to 9)");
                        shipStartingPointRowsString[i][j] = sc.next();
                        if (shipStartingPointRowsString[i][j].equals("" + 0 + "") || shipStartingPointRowsString[i][j].equals("" + 1 + "") || shipStartingPointRowsString[i][j].equals("" + 2 + "") || shipStartingPointRowsString[i][j].equals("" + 3 + "") || shipStartingPointRowsString[i][j].equals("" + 4 + "") || shipStartingPointRowsString[i][j].equals("" + 5 + "") || shipStartingPointRowsString[i][j].equals("" + 6 + "") || shipStartingPointRowsString[i][j].equals("" + 7 + "") || shipStartingPointRowsString[i][j].equals("" + 8 + "") || shipStartingPointRowsString[i][j].equals("" + 9 + "")) {
                            shipStartingPointRows[i][j] = Integer.parseInt(shipStartingPointRowsString[i][j]);
                            break;
                        } else {
                            System.out.println("You're ship's row is not an integer from '0' to '9'!Please try again!");
                            k--;
                        }

                    }
                    for (int k = 0; k < 1; k++) {

                        System.out.println("Player #" + (i + 1) + "!Please enter battleship #" + (j + 1) + "'s Starting Point Column(A number from 0 to 9)");
                        shipStartingPointColumnsString[i][j] = sc.next();
                        if (shipStartingPointColumnsString[i][j].equals("" + 0 + "") || shipStartingPointColumnsString[i][j].equals("" + 1 + "") || shipStartingPointColumnsString[i][j].equals("" + 2 + "") || shipStartingPointColumnsString[i][j].equals("" + 3 + "") || shipStartingPointColumnsString[i][j].equals("" + 4 + "") || shipStartingPointColumnsString[i][j].equals("" + 5 + "") || shipStartingPointColumnsString[i][j].equals("" + 6 + "") || shipStartingPointColumnsString[i][j].equals("" + 7 + "") || shipStartingPointColumnsString[i][j].equals("" + 8 + "") || shipStartingPointColumnsString[i][j].equals("" + 9 + "")) {
                            shipStartingPointColumns[i][j] = Integer.parseInt(shipStartingPointColumnsString[i][j]);
                            break;
                        } else {
                            System.out.println("You're ship's column is not an integer from '0' to '9'!Please try again!");
                            k--;
                        }

                    }
                    for (int k = 0; k < 1; k++) {
                        System.out.println("Player #" + (i + 1) + "!Please enter battleship #" + (j + 1) + "'s direction(A word between Up,Down,Left or Right)");
                        shipDirections[i][j] = sc.next();
                        if (shipDirections[i][j].equals("Up") || shipDirections[i][j].equals("UP") || shipDirections[i][j].equals("Down") || shipDirections[i][j].equals("DOWN") || shipDirections[i][j].equals("Left") || shipDirections[i][j].equals("LEFT") || shipDirections[i][j].equals("Right") || shipDirections[i][j].equals("RIGHT")) {
                            battleShips[i][j] = new BattleShip(shipLengths[i][j], shipStartingPointRows[i][j], shipStartingPointColumns[i][j], shipDirections[i][j]);
                            if (i == 1) {
                                if (battleShips[0][j].isShipValid() == false || battleShips[1][j].isShipValid() == false)
                                    k--;//This means that it continues until the user gives a correct ship!
                                else {
                                    boolean add = player2.addShip(player2, battleShips[1][j]);
                                    if (add == false)
                                        j--;

                                }

                            } else {
                                if (battleShips[0][j].isShipValid() == false)
                                    k--;
                                else {
                                    boolean add = player1.addShip(player1, battleShips[0][j]);//Running the method only once!
                                    if (add == false) {//Because the may cross eachother!
                                        j--;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Your direction is in valid.Please try again!");
                            k--;
                        }
                    }
                }
                for (int j = 0; j < 50; j++) {
                    System.out.println();//Clearing lines so that the other player doesn't see anything!
                }
            }
            player1.getBoard().display(player1);
            System.out.println("Player1's board.Now pass to Player2!");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
            }
            for (int i = 0; i < 100; i++) {
                System.out.println();//Clearing lines so that the other player doesn't see anything!
            }

            player2.getBoard().display(player2);
            System.out.println("Player2's board!");
            System.out.println();
            System.out.println("Let the game begin!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            for (int i = 0; i < 100; i++) {
                System.out.println();//Clearing lines so that the other player doesn't see anything!
            }
            int c1 = 0;//Player 1's cannonball mode!(Equals 0 just so it doesn't give us errors for not initializing!)
            int c2 = 0;//Player 2's cannonball mode!(Equals 0 just so it doesn't give us errors for not initializing!)
            String c1String;//We use sc.next().So we need a String type variable for our variables.
            String c2String;//We use sc.next().So we need a String type variable for our variables.

            for (int i = 0; i < 1; i++) {//This for is just to make sure that player1's cannonball mode is initialized correctly!
                System.out.println("Player #" + 1 + ".Please enter '1' for an ordinary cannonball and '2' for a wrong aiming cannonball!");
                c1String = sc.next();
                if (c1String.equals("" + 1 + "") || c1String.equals("" + 2 + "")) {
                    c1 = Integer.parseInt(c1String);
                    for (int j = 0; j < 1; j++) {//This for is just to make sure that player2's cannonball mode is initialized correctly!
                        System.out.println("Player #" + 2 + ".Please enter '1' for an ordinary cannonball and '2' for a wrong aiming cannonball!");
                        c2String = sc.next();
                        if (c2String.equals("" + 1 + "") || c2String.equals("" + 2 + "")) {
                            c2 = Integer.parseInt(c2String);
                        } else {
                            System.out.println("Your cannonball mode is neither '1' nor '2'.Please try again!");
                            j--;
                        }
                    }
                } else {
                    System.out.println("Your cannonball m ode is neither '1' nor '2'.Please try again!");
                    i--;
                }
            }
            for (int k = 0; k < 100; k++) {//They have 100 shots each at the very best!!
                for (int m = 1; m <= 2; m++) {
                    for (int n = 0; n < 1; n++) {//This "for" is for two reasons: 1.to make sure our cannoball is valid. 2.Giving an award if the player hits the target!
                        int i = 0;//Just so it doesn't give us errors for not initializing!
                        int j = 0;//Just so it doesn't give us errors for not initializing!
                        for (int l = 0; l < 1; l++) {
                            System.out.println("Player #" + m + ".Please enter the row of the square you want to shoot at!(A number from 0 to 9)");
                            String rowString = sc.next();
                            if (rowString.equals("" + 0 + "") || rowString.equals("" + 1 + "") || rowString.equals("" + 2 + "") || rowString.equals("" + 3 + "") || rowString.equals("" + 4 + "") || rowString.equals("" + 5 + "") || rowString.equals("" + 6 + "") || rowString.equals("" + 7 + "") || rowString.equals("" + 8 + "") || rowString.equals("" + 9 + ""))
                                i = Integer.parseInt(rowString);
                            else {
                                System.out.println("You're cannonball's row is not an integer from '0' to '9'.Please try again!");
                                l--;
                            }
                        }
                        for (int l = 0; l < 1; l++) {
                            System.out.println("Player #" + m + ".Please enter the column of the square you want to shoot at!(A number from 0 to 9)");
                            String columnString = sc.next();
                            if (columnString.equals("" + 0 + "") || columnString.equals("" + 1 + "") || columnString.equals("" + 2 + "") || columnString.equals("" + 3 + "") || columnString.equals("" + 4 + "") || columnString.equals("" + 5 + "") || columnString.equals("" + 6 + "") || columnString.equals("" + 7 + "") || columnString.equals("" + 8 + "") || columnString.equals("" + 9 + ""))
                                j = Integer.parseInt(columnString);
                            else {
                                System.out.println("You're cannonball's column is not an integer from '0' to '9'.Please try again!");
                                l--;
                            }
                        }
                        CannonBall cannonBall = new CannonBall(i, j);//Making a new cannonball!(Which works commonly for each of the 2 players and 2 cannonball types!)
                        if (m == 1) {//player1's block!
                            if (c1 == 1) {
                                if (cannonBall.isCannonBallValid()) {
                                    int sv = player1.ordinaryShoot(cannonBall, player1, player2);//We do this so we don't run this method several times!
                                    //sv stands for static variable!
                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player1.getBoard().display(player1);
                                            int temp = player2.getDestroyedShips();
                                            player2.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (player2.isShipDestroyed(player2, battleShips[1][s])) {
                                                    player2.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (player2.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the opponent's battleShips has been destroyed!");
                                                        if (player1.whoWins(player1, player2) == player1) {
                                                            System.out.println("All of the opponent's battleShips has been destroyed!");
                                                            System.out.println("Player1 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player1.getBoard().display(player1);
                                            System.out.println("You missed!Pass to Player2!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                            for (int l = 0; l < 100; l++) {
                                                System.out.println();
                                            }
                                        }
                                    } else {
                                        player1.getBoard().display(player1);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until ordinary cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            }
                            if (c1 == 2) {
                                if (cannonBall.isCannonBallValid()) {
                                    int sv = player1.wrongAimingShoot(cannonBall, player1, player2);

                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player1.getBoard().display(player1);
                                            int temp = player2.getDestroyedShips();
                                            player2.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (player2.isShipDestroyed(player2, battleShips[1][s])) {
                                                    player2.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (player2.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the opponent's battleShips has been destroyed!");
                                                        if (player1.whoWins(player1, player2) == player1) {
                                                            System.out.println("All of the opponent's battleShips has been destroyed!");
                                                            System.out.println("Player1 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player1.getBoard().display(player1);
                                            System.out.println("You missed!Pass to Player2!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                            for (int l = 0; l < 100; l++) {
                                                System.out.println();
                                            }
                                        }
                                    } else {
                                        player1.getBoard().display(player1);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until our wrong aiming cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            }
                        } else if (m == 2) {//player2's block!
                            if (c2 == 1) {
                                if (cannonBall.isCannonBallValid()) {

                                    int sv = player2.ordinaryShoot(cannonBall, player2, player1);
                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player2.getBoard().display(player2);
                                            int temp = player1.getDestroyedShips();
                                            player1.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (player1.isShipDestroyed(player1, battleShips[0][s])) {
                                                    player1.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (player1.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the opponent's battleShips has been destroyed!");
                                                        if (player2.whoWins(player2, player1) == player2) {
                                                            System.out.println("All of the opponent's battleShips has been destroyed!");
                                                            System.out.println("Player2 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player2.getBoard().display(player2);
                                            System.out.println("You missed!Pass to Player1!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                            for (int l = 0; l < 100; l++) {
                                                System.out.println();
                                            }
                                        }

                                    } else {
                                        player2.getBoard().display(player2);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until ordinary cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            } else if (c2 == 2) {
                                if (cannonBall.isCannonBallValid()) {

                                    int sv = player2.wrongAimingShoot(cannonBall, player2, player1);
                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player2.getBoard().display(player2);
                                            int temp = player1.getDestroyedShips();
                                            player1.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (player1.isShipDestroyed(player1, battleShips[0][s])) {
                                                    player1.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (player1.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the opponent's battleShips has been destroyed!");
                                                        if (player2.whoWins(player2, player1) == player2) {
                                                            System.out.println("All of the opponent's battleShips has been destroyed!");
                                                            System.out.println("Player2 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player2.getBoard().display(player2);
                                            System.out.println("You missed!Pass to Player1!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                            for (int l = 0; l < 100; l++) {
                                                System.out.println();
                                            }
                                        }
                                    } else {
                                        player2.getBoard().display(player2);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until our wrong aiming cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            }
                        }
                    }
                }
            }
        } else if (mode == 2) {
            Board b1 = new Board(1);
            Board b2 = new Board(2);
            Player player1 = new Player(1, b1);
            Computer computer = new Computer(b2);
            BattleShip battleShips[][] = new BattleShip[2][5];
            int shipLengths[][] = new int[2][5];
            String shipLengthsString[] = new String[5];//We use sc.next().So we need a String type variable for our variables.
            int shipStartingPointRows[][] = new int[2][5];//2 for the number of players.5 for the number of battleShips!
            String shipStartingPointRowsString[] = new String[5];//We use sc.next().So we need a String type variable for our variables.
            int shipStartingPointColumns[][] = new int[2][5];
            String shipStartingPointColumnsString[] = new String[5];//We use sc.next().So we need a String type variable for our variables.
            String[][] shipDirections = new String[2][5];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    battleShips[i][j] = new BattleShip(0, 0, 0, "");//Just so it doesn't give us errors for not initializing!
                }
            }
            for (int j = 0; j < 5; j++) {//The number of battleShips for every player
                for (int k = 0; k < 1; k++) {//Just to make sure our ship is valid!

                    System.out.println("Player #" + 1 + "!Please enter battleship #" + (j + 1) + "'s Length(A number from 2 to 5)");
                    shipLengthsString[j] = sc.next();
                    if (shipLengthsString[j].equals("" + 2 + "") || shipLengthsString[j].equals("" + 2 + "") || shipLengthsString[j].equals("" + 3 + "") || shipLengthsString[j].equals("" + 4 + "") || shipLengthsString[j].equals("" + 5 + "")) {
                        shipLengths[0][j] = Integer.parseInt(shipLengthsString[j]);
                        break;
                    } else {
                        System.out.println("You're ship's length is not an integer from '2' to '5'!Please try again!");
                        k--;
                    }
                }
                for (int k = 0; k < 1; k++) {

                    System.out.println("Player #" + 1 + "!Please enter battleship #" + (j + 1) + "'s Starting Point Row(A number from 0 to 9)");
                    shipStartingPointRowsString[j] = sc.next();
                    if (shipStartingPointRowsString[j].equals("" + 0 + "") || shipStartingPointRowsString[j].equals("" + 1 + "") || shipStartingPointRowsString[j].equals("" + 2 + "") || shipStartingPointRowsString[j].equals("" + 3 + "") || shipStartingPointRowsString[j].equals("" + 4 + "") || shipStartingPointRowsString[j].equals("" + 5 + "") || shipStartingPointRowsString[j].equals("" + 6 + "") || shipStartingPointRowsString[j].equals("" + 7 + "") || shipStartingPointRowsString[j].equals("" + 8 + "") || shipStartingPointRowsString[j].equals("" + 9 + "")) {
                        shipStartingPointRows[0][j] = Integer.parseInt(shipStartingPointRowsString[j]);
                        break;
                    } else {
                        System.out.println("You're ship's row is not an integer from '0' to '9'!Please try again!");
                        k--;
                    }

                }
                for (int k = 0; k < 1; k++) {

                    System.out.println("Player #" + 1 + "!Please enter battleship #" + (j + 1) + "'s Starting Point Column(A number from 0 to 9)");
                    shipStartingPointColumnsString[j] = sc.next();
                    if (shipStartingPointColumnsString[j].equals("" + 0 + "") || shipStartingPointColumnsString[j].equals("" + 1 + "") || shipStartingPointColumnsString[j].equals("" + 2 + "") || shipStartingPointColumnsString[j].equals("" + 3 + "") || shipStartingPointColumnsString[j].equals("" + 4 + "") || shipStartingPointColumnsString[j].equals("" + 5 + "") || shipStartingPointColumnsString[j].equals("" + 6 + "") || shipStartingPointColumnsString[j].equals("" + 7 + "") || shipStartingPointColumnsString[j].equals("" + 8 + "") || shipStartingPointColumnsString[j].equals("" + 9 + "")) {
                        shipStartingPointColumns[0][j] = Integer.parseInt(shipStartingPointColumnsString[j]);
                        break;
                    } else {
                        System.out.println("You're ship's column is not an integer from '0' to '9'!Please try again!");
                        k--;
                    }

                }
                for (int k = 0; k < 1; k++) {
                    System.out.println("Player #" + 1 + "!Please enter battleship #" + (j + 1) + "'s direction(A word between Up,Down,Left or Right)");
                    shipDirections[0][j] = sc.next();
                    if (shipDirections[0][j].equals("Up") || shipDirections[0][j].equals("UP") || shipDirections[0][j].equals("Down") || shipDirections[0][j].equals("DOWN") || shipDirections[0][j].equals("Left") || shipDirections[0][j].equals("LEFT") || shipDirections[0][j].equals("Right") || shipDirections[0][j].equals("RIGHT")) {
                        battleShips[0][j] = new BattleShip(shipLengths[0][j], shipStartingPointRows[0][j], shipStartingPointColumns[0][j], shipDirections[0][j]);
                        battleShips[0][j] = new BattleShip(shipLengths[0][j], shipStartingPointRows[0][j], shipStartingPointColumns[0][j], shipDirections[0][j]);
                        if (battleShips[0][j].isShipValid() == false)
                            k--;
                        else {
                            boolean add = player1.addShip(player1, battleShips[0][j]);
                            if (add == false)
                                j--;
                        }
                    } else {
                        System.out.println("Your direction is invalid.Please try again!");
                        k--;
                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                int j = Math.abs(new Random().nextInt() % 4) + 2;//A random number for the ship's length!(from 2 to 5)
                int k = Math.abs(new Random().nextInt() % 10);//A random number for the ship's row!(from 0 to 9)
                int l = Math.abs(new Random().nextInt() % 10);//A random number for the ship's column!(from 0 to 9)
                shipLengths[1][i] = j;
                shipStartingPointRows[1][i] = k;
                shipStartingPointColumns[1][i] = l;
                boolean add;//For checking our addShip!
                for (int n = 0; n < 1; n++) {

                    int m = Math.abs(new Random().nextInt() % 4);//A random number for the ship's direction!from (0 to 3)
                    if (m == 0)
                        shipDirections[1][i] = "Up";
                    if (m == 1)
                        shipDirections[1][i] = "Down";
                    if (m == 2)
                        shipDirections[1][i] = "Left";
                    if (m == 3)
                        shipDirections[1][i] = "Right";
                    battleShips[1][i] = new BattleShip(shipLengths[1][i], shipStartingPointRows[1][i], shipStartingPointColumns[1][i], shipDirections[1][i]);
                    if (battleShips[1][i].isShipValid()) {
                        add = computer.computerAddShip(computer, battleShips[1][i]);
                        if (add == false)
                            i--;
                    } else
                        n--;//This goes on until the computer's ship has a correct direction
                }
            }//The computer battleShips are ready as well!
            player1.getBoard().display(player1);
            System.out.println("Player1's board!");
            System.out.println();
            System.out.println("Let the game begin!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            int c1 = 0;//Player 1's cannonball mode!(Equals 0 just so it doesn't give us errors for not initializing!)
            String c1String;//We use sc.next().So we need a String type variable for our variables.

            for (int i = 0; i < 1; i++) {//This for is just to make sure that player1's cannonball mode is initialized correctly!
                System.out.println("Player #" + 1 + ".Please enter '1' for an ordinary cannonball and '2' for a wrong aiming cannonball!");
                c1String = sc.next();
                if (c1String.equals("" + 1 + "") || c1String.equals("" + 2 + "")) {
                    c1 = Integer.parseInt(c1String);
                } else {
                    System.out.println("Your cannonball mode is neither '1' nor '2'.Please try again!");
                    i--;
                }
            }
            for (int k = 0; k < 100; k++) {//They have 100 shots each at the very best!!
                for (int m = 1; m <= 2; m++) {
                    if (m == 1) {//player1's block!
                        for (int n = 0; n < 1; n++) {//This "for" is for two reasons: 1.to make sure our cannoball is valid. 2.Giving an award if the player hits the target!
                            int i = 0;//Just so it doesn't give us errors for not initializing!
                            int j = 0;//Just so it doesn't give us errors for not initializing!
                            for (int l = 0; l < 1; l++) {
                                System.out.println("Player #" + m + ".Please enter the row of the square you want to shoot at!(A number from 0 to 9)");
                                String rowString = sc.next();
                                if (rowString.equals("" + 0 + "") || rowString.equals("" + 1 + "") || rowString.equals("" + 2 + "") || rowString.equals("" + 3 + "") || rowString.equals("" + 4 + "") || rowString.equals("" + 5 + "") || rowString.equals("" + 6 + "") || rowString.equals("" + 7 + "") || rowString.equals("" + 8 + "") || rowString.equals("" + 9 + ""))
                                    i = Integer.parseInt(rowString);
                                else {
                                    System.out.println("You're cannonball's row is not an integer from '0' to '9'.Please try again!");
                                    l--;
                                }
                            }
                            for (int l = 0; l < 1; l++) {
                                System.out.println("Player #" + m + ".Please enter the column of the square you want to shoot at!(A number from 0 to 9)");
                                String columnString = sc.next();
                                if (columnString.equals("" + 0 + "") || columnString.equals("" + 1 + "") || columnString.equals("" + 2 + "") || columnString.equals("" + 3 + "") || columnString.equals("" + 4 + "") || columnString.equals("" + 5 + "") || columnString.equals("" + 6 + "") || columnString.equals("" + 7 + "") || columnString.equals("" + 8 + "") || columnString.equals("" + 9 + ""))
                                    j = Integer.parseInt(columnString);
                                else {
                                    System.out.println("You're cannonball's column is not an integer from '0' to '9'.Please try again!");
                                    l--;
                                }
                            }
                            CannonBall cannonBall = new CannonBall(i, j);//Making a new cannonball!(Which works commonly for each of the 2 players and 2 cannonball types!)
                            if (c1 == 1) {
                                if (cannonBall.isCannonBallValid()) {
                                    int sv = player1.ordinaryShoot(cannonBall, player1, computer);//We do this so we don't run this method several times!
                                    //sv stands for static variable!
                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player1.getBoard().display(player1);
                                            int temp = computer.getDestroyedShips();
                                            computer.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (computer.isComputersShipDestroyed(computer, battleShips[1][s])) {
                                                    computer.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (computer.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the computer's battleShips has been destroyed!");
                                                        if (player1.whoWins(player1, computer) == 1) {
                                                            System.out.println("All of the computer's battleShips has been destroyed!");
                                                            System.out.println("Player1 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player1.getBoard().display(player1);
                                            System.out.println("You missed!Pass to the computer!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                        }
                                    } else {
                                        player1.getBoard().display(player1);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until ordinary cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            }
                            if (c1 == 2) {
                                if (cannonBall.isCannonBallValid()) {
                                    int sv = player1.wrongAimingShoot(cannonBall, player1, computer);

                                    if (sv != -1) {
                                        if (sv == 1) {
                                            player1.getBoard().display(player1);
                                            int temp = computer.getDestroyedShips();
                                            computer.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                            for (int s = 0; s < 5; s++) {
                                                if (computer.isComputersShipDestroyed(computer, battleShips[1][s])) {
                                                    computer.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                    if (computer.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                        System.out.println("One of the computer's battleShips has been destroyed!");
                                                        if (player1.whoWins(player1, computer) == 1) {
                                                            System.out.println("All of the computer's battleShips has been destroyed!");
                                                            System.out.println("Player1 wins!!");
                                                            n = 1;//To come out of the loop!
                                                            m = 3;//To come out of the loop!
                                                            k = 101;//To come out of the loop!
                                                            break;
                                                        } else {
                                                            System.out.println("Well done.As a reward,shoot again!");
                                                            n--;//This means that he can shoot again
                                                        }
                                                    }
                                                } else {
                                                    System.out.println("Well done.As a reward,shoot again!");
                                                    n--;//This means that he can shoot again
                                                }
                                            }
                                        } else if (sv == 0) {
                                            player1.getBoard().display(player1);
                                            System.out.println("You missed!Pass to the computer!");
                                            try {
                                                Thread.sleep(2500);
                                            } catch (InterruptedException ex) {
                                            }
                                        }
                                    } else {
                                        player1.getBoard().display(player1);
                                        System.out.println("You have shot their before.Try again!");
                                        n--;//This goes on until our wrong aiming cannon ball hits somewhere(empty or occupied!)
                                    }
                                } else {
                                    System.out.println("You're CannonBall isn't valid.Please try again!");
                                    n--;
                                }
                            }
                        }
                    } else if (m == 2) {//Computer's block!
                        for (int n = 0; n < 1; n++) {//This "for" is for two reasons: 1.to make sure our cannoball is valid. 2.Giving an award if the computer hits the target!
                            CannonBall cannonBall = computer.optimumTarget(computer, k);//Which gives us a cannonball that aims at an optimum target!
                            //k is the number of cannonballs that has been previously shot!
                            if (cannonBall.isCannonBallValid()) {
                                int sv = computer.computerShoot(cannonBall, computer, player1);
                                if (sv != -1) {
                                    if (sv == 1) {
                                        int temp = player1.getDestroyedShips();
                                        player1.setDestroyedShips(0);//setting player2's destroyed battleShips on 0!
                                        for (int s = 0; s < 5; s++) {
                                            if (player1.isShipDestroyed(player1, battleShips[0][s])) {
                                                player1.setDestroyedShips(s + 1);//This means that in every loop,destroyedShips++.
                                                if (player1.getDestroyedShips() > temp) {//This means that one of the battleShips is recently destroyed!
                                                    System.out.println("The computer has destroyed one of your battleShips!");
                                                    if (computer.whoWins(computer, player1) == -1) {
                                                        System.out.println("The computer has destroyed all of your battleShips!");
                                                        System.out.println("Computer wins!!");
                                                        n = 1;//To come out of the loop!
                                                        m = 3;//To come out of the loop!
                                                        k = 101;//To come out of the loop!
                                                        break;
                                                    } else {
                                                        n--;//This means that he can shoot again
                                                    }
                                                }
                                            } else {
                                                n--;//This means that he can shoot again
                                            }
                                        }
                                    } else if (sv == 0) {
                                        System.out.println("The computer missed!Back to Player1!");
                                        try {
                                            Thread.sleep(2500);
                                        } catch (InterruptedException ex) {
                                        }
                                        for (int l = 0; l < 100; l++) {
                                            System.out.println();
                                        }
                                    }

                                } else {
                                    n--;//This goes on until ordinary cannon ball hits somewhere(empty or occupied!)
                                }
                            } else {
                                n--;
                            }
                        }
                    }
                }
            }
        }
    }
}