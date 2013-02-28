/*
 * Maze3D.java      Author: Nikita Volodin (127196)
 * CS152            Assignment 5 - Problem #2
 * 
 * Class that finds the path from closest top left corner to
 * further bottop rigth corner in 3-dimensional maze
 */

public class Maze3D {

    final int VISITED = 9;
    final int PATH = 7;
    //maze
    private int[][][] maze = {
        {{1, 1, 1, 1, 0}, // Beginning on close left of this row
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}, // First section
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0}},
        {{1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 1, 0, 0}, // Second section
            {1, 1, 1, 0, 0},
            {0, 1, 0, 1, 0}},
        {{0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 1, 1, 1, 1}, // Middle section
            {1, 1, 0, 1, 0},
            {0, 0, 0, 1, 1}},
        {{0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0}, // Fourth section
            {0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0}},
        {{0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0}, // Last section
            {0, 0, 0, 1, 0},
            {0, 0, 0, 1, 1}} // Goal on far right of this row
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Maze3D labyrinth = new Maze3D();
        System.out.println("Before path: ");
        System.out.println(labyrinth);

        if (labyrinth.solve(0, 0, 0)) {
            System.out.println();
            System.out.println("With path: ");
            System.out.println(labyrinth);
        } else {
            System.out.println("Impossible to solve");
        }


    }

    /**
     * Method that recursively solves maze
     *
     * @param width position of cursor in "width" axis
     * @param height position of cursor in "height" axis
     * @param depth position of cursor in "depth" axis
     * @return TRUE if we went to the current position
     */
    public boolean solve(int width, int height, int depth) {
        boolean done = false;

        if (valid(width, height, depth)) {
            maze[width][height][depth] = VISITED;

            if (width == maze.length - 1 && height == maze[0].length - 1
                    && depth == maze[0][0].length - 1) {
                done = true;
            } else {
                done = solve(width + 1, height, depth); //to the right
                if (!done) {
                    done = solve(width - 1, height, depth); //to the left
                }
                if (!done) {
                    done = solve(width, height + 1, depth); //to the top
                }
                if (!done) {
                    done = solve(width, height - 1, depth); //to the bottom
                }
                if (!done) {
                    done = solve(width, height, depth + 1); //to the deep
                }
                if (!done) {
                    done = solve(width, height, depth - 1); //out of the deep
                }
            }

            if (done) {
                maze[width][height][depth] = PATH;
            }
        }

        return done;
    }

    /**
     * Checks validity of the position
     *
     * @param width position of cursor in "width" axis
     * @param height position of cursor in "height" axis
     * @param depth position of cursor in "depth" axis
     * @return TRUE if we can go to this point
     */
    private boolean valid(int width, int height, int depth) {
        boolean result = false;
        if (width >= 0 && width <= maze.length - 1
                && height >= 0 && height <= maze[width].length - 1
                && depth >= 0 && depth <= maze[width][height].length - 1
                && maze[width][height][depth] == 1) {
            result = true;
        }

        return result;
    }

    /**
     * Returns maze as string
     *
     * @return String representation of maze
     */
    @Override
    public String toString() {
        String result = "";

        for (int j = 0; j < maze[0].length; j++) {
            for (int i = 0; i < maze.length; i++) {
                for (int k = 0; k < maze[i][j].length; k++) {
                    result += maze[i][j][k] + " ";
                }
                result += "\t";
            }
            result += "\n";
        }

        return result;
    }
}
