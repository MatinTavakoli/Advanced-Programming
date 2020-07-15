public class BattleShip {
    /**
     * Simulating a BattleShip!!
     *
     * @author matin tavakoli
     * @param length the battleship's length!(from 2 to 5)
     * @param startingPointRow the row that the battleShip starts from!(from 0 to 9)
     * @param startingPointColumn the column that the battleShip starts from!(from 0 to 9)
     * @param direction the direction that the battleship faces!
     */
    private int length;
    private int startingPointRow;//When we want the user to put a battleship,We want him to pick a row so the battleship starts from that row!
    private int startingPointColumn;//When we want the user to put a battleship,We want him to pick a column so the battleship starts from that column!
    private String direction;//After our user piced his square,he then decides which direction should his battleship face!(Up,Down,Left or Right!)

    public BattleShip(int length, int startingPointRow, int startingPointColumn, String direction) {
        this.length = length;
        this.startingPointRow = startingPointRow;
        this.startingPointColumn = startingPointColumn;
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    public int getStartingPointRow() {
        return startingPointRow;
    }

    public int getStartingPointColumn() {
        return startingPointColumn;
    }

    public String getDirection() {
        return direction;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStartingPointRow(int startingPointRow) {
        this.startingPointRow = startingPointRow;
    }

    public void setStartingPointColumn(int startingPointColumn) {
        this.startingPointColumn = startingPointColumn;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isShipValid() {
        if (direction.equals("Up") || direction.equals("UP") || direction.equals("Down") || direction.equals("DOWN") || direction.equals("Left") || direction.equals("LEFT") || direction.equals("Right") || direction.equals("RIGHT")) {
            if (direction.equals("Up") || direction.equals("UP")) {
                if (startingPointRow - (length - 1) < 0) {
                    System.out.println("According to your starting row and direction,we can't place this battleship on the board.Please make another battleship!");
                    return false;
                }
            } else if (direction.equals("Down") || direction.equals("DOWN")) {
                if (startingPointRow + (length - 1) > 9) {
                    System.out.println("According to your starting row and direction,we can't place this battleship on the board.Please make another battleship!");
                    return false;
                }
            } else if (direction.equals("Left") || direction.equals("LEFT")) {
                if (startingPointColumn - (length - 1) < 0) {
                    System.out.println("According to your starting column and direction,we can't place this battleship on the board.Please make another battleship!");
                    return false;
                }
            } else if (direction.equals("Right") || direction.equals("RIGHT")) {
                if (startingPointColumn + (length - 1) > 9) {
                    System.out.println("According to your starting column and direction,we can't place this battleship on the board.Please make another battleship!");
                    return false;
                }
            }
        } else {
            System.out.println("You're direction is invalid.Please make another battleship!");
            return false;
        }
        return true;//That's in case nothing goes wrong!
    }
}
