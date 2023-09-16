package project.snakegame.model;

// Represents food.
public class Food {
    public static final int DECAY_AMOUNT = 10;
    public static final int INITIAL_NUTRITIONAL_VALUE = 100;

    private Screen position;
    private int nutritionalValue;

    // EFFECTS: food has INITIAL_NUTRITIONAL_VALUE and is located at given cell
    public Food(Screen position) {
        this.position = position;
        nutritionalValue = INITIAL_NUTRITIONAL_VALUE;
    }

    // EFFECTS: food has given nutritional value and is located at given cell
    public Food(Screen position, int nutritionalValue) {
        this.position = position;
        this.nutritionalValue = nutritionalValue;
    }

    public Screen getPosition() {
        return position;
    }

    // MODIFIES: this
    // EFFECTS:  reduces nutritional value of food by DECAY_AMOUNT; if DECAY_AMOUNT is greater than
    //           current nutritional value of food, nutritional value decays to zero
    public void decay() {
        nutritionalValue = nutritionalValue - DECAY_AMOUNT;

        if (nutritionalValue < DECAY_AMOUNT) {
            nutritionalValue = 0;
        }
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }
}
