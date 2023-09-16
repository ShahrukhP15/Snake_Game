package project.snakegame.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// jUnit tests for the Cell class
class ScreenTest {
    private Screen testScreen;

    @BeforeEach
    void runBefore() {
        testScreen = new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2);
    }

    @Test
    void testScreenHorizontalCoord() {
        assertEquals(testScreen.getColumn() * Screen.CELL_PIXELS, testScreen.getScreenHorizontalCoord());
    }

    @Test
    void testScreenVerticalCoord() {
        assertEquals(testScreen.getRow() * Screen.CELL_PIXELS, testScreen.getScreenVerticalCoord());
    }

    @Test
    void testEquals() {
        Screen other = new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2);
        assertEquals(testScreen, other);
    }

    @Test
    void testHashCode() {
        Screen other = new Screen(Game.BOARD_ROWS / 2, Game.BOARD_COLS / 2);
        assertEquals(testScreen.hashCode(), other.hashCode());
    }
}