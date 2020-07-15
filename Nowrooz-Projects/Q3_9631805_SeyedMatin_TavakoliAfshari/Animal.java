public class Animal {
    /**
     * Simulating an animal!
     *
     * @author Matin Tavakoli
     * parameters are explained in the comments!
     */
    private String name;//The animal's name!(Lion,Bear etc.)
    private String attackMethod1;//The first way that our animal attacks!
    private int attackMethod1Amount;//The amount of damage it causes!
    private String attackMethod2;//The second way that our animal wants attacks!
    private int attackMethod2Amount;//The amount of damage it causes!
    private int energy;//The amount of energy the animal has!
    private int health;//The amount of health the animal has!

    public Animal(String name) {
        if (name.equals("Lion")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 150;
            attackMethod2 = "Kill";
            attackMethod2Amount = 500;
            energy = 1000;
            health = 900;
        } else if (name.equals("Bear")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 130;
            attackMethod2 = "Kill";
            attackMethod2Amount = 600;
            energy = 900;
            health = 850;
        } else if (name.equals("Tiger")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 120;
            attackMethod2 = "Kill";
            attackMethod2Amount = 650;
            energy = 850;
            health = 850;
        } else if (name.equals("Vulture")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 100;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 600;
            health = 350;
        } else if (name.equals("Fox")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 90;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 600;
            health = 400;
        } else if (name.equals("Elephant")) {
            this.name = name;
            attackMethod1 = "Injure";
            attackMethod1Amount = 70;
            attackMethod2 = "Attack";
            attackMethod2Amount = 50;
            energy = 500;
            health = 1200;
        } else if (name.equals("Wolf")) {
            this.name = name;
            attackMethod1 = "Kill";
            attackMethod1Amount = 700;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 700;
            health = 450;
        } else if (name.equals("Boar")) {
            this.name = name;
            attackMethod1 = "Injure";
            attackMethod1Amount = 80;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 500;
            health = 1100;
        } else if (name.equals("Hippo")) {
            this.name = name;
            attackMethod1 = "Attack";
            attackMethod1Amount = 110;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 360;
            health = 1000;
        } else if (name.equals("Cow")) {
            this.name = name;
            attackMethod1 = "Attack";
            attackMethod1Amount = 90;
            attackMethod2 = "Wound";
            attackMethod2Amount = 100;
            energy = 400;
            health = 750;
        } else if (name.equals("Rabbit")) {
            this.name = name;
            attackMethod1 = "Bite";
            attackMethod1Amount = 80;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 350;
            health = 200;
        } else if (name.equals("Turtle")) {
            this.name = name;
            attackMethod1 = "Bite";
            attackMethod1Amount = 200;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
            energy = 230;
            health = 350;
        }
    }

    public Animal(String name, int health, int energy) {//Making a new costructor for when i want to change my health(the card that has been attacked!)
        this.health = health;
        this.energy = energy;
        if (name.equals("Lion")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 150;
            attackMethod2 = "Kill";
            attackMethod2Amount = 500;
        } else if (name.equals("Bear")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 130;
            attackMethod2 = "Kill";
            attackMethod2Amount = 600;
        } else if (name.equals("Tiger")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 120;
            attackMethod2 = "Kill";
            attackMethod2Amount = 650;
        } else if (name.equals("Vulture")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 100;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Fox")) {
            this.name = name;
            attackMethod1 = "Wound";
            attackMethod1Amount = 90;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Elephant")) {
            this.name = name;
            attackMethod1 = "Injure";
            attackMethod1Amount = 70;
            attackMethod2 = "Attack";
            attackMethod2Amount = 50;
        } else if (name.equals("Wolf")) {
            this.name = name;
            attackMethod1 = "Kill";
            attackMethod1Amount = 700;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Boar")) {
            this.name = name;
            attackMethod1 = "Injure";
            attackMethod1Amount = 80;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Hippo")) {
            this.name = name;
            attackMethod1 = "Attack";
            attackMethod1Amount = 110;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Cow")) {
            this.name = name;
            attackMethod1 = "Attack";
            attackMethod1Amount = 90;
            attackMethod2 = "Wound";
            attackMethod2Amount = 100;
        } else if (name.equals("Rabbit")) {
            this.name = name;
            attackMethod1 = "Bite";
            attackMethod1Amount = 80;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        } else if (name.equals("Turtle")) {
            this.name = name;
            attackMethod1 = "Bite";
            attackMethod1Amount = 200;
            attackMethod2 = null;//It doesn't have a second method!
            attackMethod2Amount = 0;//It doesn't have a second method!
        }
    }

    public String getName() {
        return name;
    }

    public String getAttackMethod1() {
        return attackMethod1;
    }

    public int getAttackMethod1Amount() {
        return attackMethod1Amount;
    }

    public String getAttackMethod2() {
        return attackMethod2;
    }

    public int getAttackMethod2Amount() {
        return attackMethod2Amount;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHealth() {
        return health;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
