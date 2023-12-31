package project.snakegame.model;

import java.util.*;

// Represents a game of snake played on a board of size BOARD_ROWS * BOARD_COLS with
// row number increasing from top to bottom and column number increasing from
// left to right.
public class Game {
    public static final int BOARD_COLS = 11;
    public static final int BOARD_ROWS = BOARD_COLS;

    private Random randomNumberGenerator;
    private Snake snake;
    private Food food;

    // EFFECTS: snake is at centre of board and food is at a random cell on board
    public Game() {
        randomNumberGenerator = new Random();
        snake = new Snake(new Screen(BOARD_ROWS / 2, BOARD_COLS / 2));
        food = createFood();
    }

    // REQUIRES: !isOver()
    // MODIFIES: this
    // EFFECTS:  move snake and decay food; if snake head is at food position, eat food and
    //           create new food at random location
    public void update() {
        snake.move();
        food.decay();
        if (canSnakeEat()) {
            snake.eat(food);
            food = createFood();
        }
    }

    public Screen getSnakeHeadPosition() {
        return snake.getPosition();
    }

    public List<Screen> getSnakeBodyPositions() {
        return snake.getBodyPositions();
    }

    public Direction getSnakeDirection() {
        return snake.getDirection();
    }

    public int getSnakeLength() {
        return snake.length();
    }

    public Screen getFoodPosition() {
        return food.getPosition();
    }

    public int getFoodNutritionalValue() {
        return food.getNutritionalValue();
    }

    // MODIFIES: this
    // EFFECTS:  rotate snake head 90 degrees to left
    public void rotateSnakeLeft() {
        snake.rotateLeft();
    }

    // MODIFIES: this
    // EFFECTS:  rotate snake head 90 degrees to right
    public void rotateSnakeRight() {
        snake.rotateRight();
    }

    // EFFECTS: returns true if game is over
    public boolean isOver() {
        return snake.selfIntersects() || !isInBounds(snake.getPosition());
    }

    // EFFECTS: returns true if cell is in bounds of game
    private boolean isInBounds(Screen screen) {
        return screen.getColumn() >= 0 && screen.getColumn() < BOARD_COLS
                && screen.getRow() >= 0 && screen.getRow() < BOARD_ROWS;
    }

    // EFFECTS: returns true if snake can eat
    private boolean canSnakeEat() {
        return snake.getPosition().equals(food.getPosition());
    }

    // EFFECTS:  returns food at random location other than location of snake's head
    private Food createFood() {
        Screen position = randomCell();

        while (position.equals(snake.getPosition())) {
            position = randomCell();
        }

        return new Food(position);
    }

    // EFFECTS: returns a cell at a randomly chosen location on the board
    private Screen randomCell() {
        return new Screen(randomNumberGenerator.nextInt(BOARD_ROWS), randomNumberGenerator.nextInt(BOARD_COLS));
    }
}
