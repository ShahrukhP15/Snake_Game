package project.snakegame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// jUnit tests for Food class
class FoodTest {
    private Food testFood;

    @BeforeEach
    void runBefore() {
        testFood = new Food(new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2));
    }

    @Test
    void testConstructor() {
        assertEquals(new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2), testFood.getPosition());
        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE, testFood.getNutritionalValue());
    }

    @Test
    void testConstructorWithNutritionalValue() {
        Food otherFood = new Food(new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2 ), 50);
        assertEquals(new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2), otherFood.getPosition());
        assertEquals(50, otherFood.getNutritionalValue());
    }

    @Test
    void testDecay() {
        testFood.decay();

        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE - Food.DECAY_AMOUNT, testFood.getNutritionalValue());
    }

    @Test
    void testMultipleDecay() {
        testFood.decay();
        testFood.decay();

        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE - 2 * Food.DECAY_AMOUNT, testFood.getNutritionalValue());
    }

    @Test
    void testExcessiveDecay() {
        final int EXCESSIVE_DECAYS = Food.INITIAL_NUTRITIONAL_VALUE / Food.DECAY_AMOUNT * 2;

        for(int decayCount = 0; decayCount < EXCESSIVE_DECAYS; decayCount++) {
            testFood.decay();
        }

        assertEquals(0, testFood.getNutritionalValue());
    }
}