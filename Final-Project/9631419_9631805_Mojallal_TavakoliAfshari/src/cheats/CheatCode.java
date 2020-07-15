package cheats;

/**
 * a class that checks whether the user entered the cheat code or not!(objects are made in the "GameState" class)
 */
public class CheatCode {
    private int cheatCodeComboLevel;
    private boolean cheatCodeEntered;

    /**
     * the constructor (simply sets 0 for the combo level & false for cheatCodeEntered)
     */
    public CheatCode() {
        cheatCodeComboLevel=0;
        cheatCodeEntered=false;
    }

    /**
     * the cheatCodeComboLevels getter
     * @return returns cheatCodeComboLevel
     */
    public int getCheatCodeComboLevel() {
        return cheatCodeComboLevel;
    }

    /**
     * a method that upgrades the cheatcodelevel 1 bar
     */
    public void upgradeCheatCodeComboLevel() {
        cheatCodeComboLevel++;
    }

    /**
     * the cheatCodeLevels setter
     * @param cheatCodeComboLevel the desired cheatCodeLevel
     */
    public void setCheatCodeComboLevel(int cheatCodeComboLevel) {
        this.cheatCodeComboLevel = cheatCodeComboLevel;
    }

    /**
     * checks whether the user entered the cheat code or not
     * @return returns true if he/she did & false if he/she didn't
     */
    public boolean isCheatCodeEntered() {
        return cheatCodeEntered;
    }

    /**
     * the cheatCodes setter
     * @param cheatCodeEntered changes the cheatCode
     */
    public void setCheatCodeEntered(boolean cheatCodeEntered) {
        this.cheatCodeEntered = cheatCodeEntered;
    }
}
