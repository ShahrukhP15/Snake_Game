package project.snakegame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// jUnit tests for SnakeGame class
class GameTest {
    private Game testGame;

    @BeforeEach
    void runBefore() {
        testGame = new Game();
    }

    @Test
    void testIsGameOverMoveRight() {
        setSnakeDirection(Direction.RIGHT);
        Screen snakePosition = testGame.getSnakeHeadPosition();
        int distanceToRightWall = Game.BOARD_COLS - snakePosition.getColumn();

        for(int i = 0; i < distanceToRightWall - 1; i++) {
            testGame.update();
        }

        assertFalse(testGame.isOver());

        testGame.update();

        assertTrue(testGame.isOver());
    }

    @Test
    void testIsGameOverMoveLeft() {
        setSnakeDirection(Direction.LEFT);
        Screen snakePosition = testGame.getSnakeHeadPosition();
        int distanceToLeftWall = snakePosition.getColumn();

        for(int i = 0; i < distanceToLeftWall; i++) {
            testGame.update();
        }

        assertFalse(testGame.isOver());

        testGame.update();

        assertTrue(testGame.isOver());
    }

    @Test
    void testIsGameOverMoveDown() {
        setSnakeDirection(Direction.DOWN);
        Screen snakePosition = testGame.getSnakeHeadPosition();
        int distanceToBottomWall = Game.BOARD_ROWS - snakePosition.getRow();

        for(int i = 0; i < distanceToBottomWall - 1; i++) {
            testGame.update();
        }

        assertFalse(testGame.isOver());

        testGame.update();

        assertTrue(testGame.isOver());


    }

    @Test
    void testIsGameOverMoveUp() {
        setSnakeDirection(Direction.UP);
        Screen snakePosition = testGame.getSnakeHeadPosition();
        int distanceToTopWall = snakePosition.getRow();

        for(int i = 0; i < distanceToTopWall; i++) {
            testGame.update();
        }

        assertFalse(testGame.isOver());

        testGame.update();

        assertTrue(testGame.isOver());
    }

    @Test
    void testIsGameOverSnakeSelfIntersects() {
        while(testGame.getSnakeLength() < 5) {
            eatFood();
        }

        for (int count = 0; count < 3; count++) {
            testGame.rotateSnakeRight();
            testGame.update();
        }

        assertTrue(testGame.isOver());
    }

    @Test
    void testRotateSnakeLeft() {
        setSnakeDirection(Direction.UP);
        testGame.rotateSnakeLeft();
        assertEquals(Direction.LEFT, testGame.getSnakeDirection());
    }

    @Test
    void testRotateSnakeRight() {
        setSnakeDirection(Direction.UP);
        testGame.rotateSnakeRight();
        assertEquals(Direction.RIGHT, testGame.getSnakeDirection());
    }

    @Test
    void testSnakeGrowsWhenEating() {
        while(testGame.getSnakeLength() == 1) {
            eatFood();
        }

        assertTrue(testGame.getSnakeLength() > 1);
        assertEquals(testGame.getSnakeLength(), 1 + testGame.getSnakeBodyPositions().size());
    }

    @Test
    void testNewFoodCreatedWhenEaten() {
        eatFood();
        assertNotEquals(testGame.getFoodPosition(), testGame.getSnakeHeadPosition());
        assertEquals(Food.INITIAL_NUTRITIONAL_VALUE, testGame.getFoodNutritionalValue());
    }

    // MODIFIES: this
    // EFFECTS:  move snake to cell containing food so that food is eaten by snake
    private void eatFood() {
        Screen foodPosition = testGame.getFoodPosition();
        Screen snakePosition = testGame.getSnakeHeadPosition();
        int rowDiff = foodPosition.getRow() - snakePosition.getRow();
        int colDiff = foodPosition.getColumn() - snakePosition.getColumn();

        if (rowDiff > 0) {
            moveSnake(rowDiff, Direction.DOWN);
        }
        else if (rowDiff < 0) {
            moveSnake(-rowDiff, Direction.UP);
        }

        if (colDiff > 0) {
            moveSnake(colDiff, Direction.RIGHT);
        }
        else if (colDiff < 0) {
            moveSnake(-colDiff, Direction.LEFT);
        }
    }

    // MODIFIES: this
    // EFFECTS: update game so that snake moves given number of steps in given direction
    private void moveSnake(int steps, Direction direction) {
        setSnakeDirection(direction);
        for (int i = 0; i < steps; i++)
            testGame.update();
    }

    // MODIFIES: this
    // EFFECTS:  rotates snake until its direction is d
    private void setSnakeDirection(Direction d) {
        while (testGame.getSnakeDirection() != d)
            testGame.rotateSnakeLeft();
    }
}