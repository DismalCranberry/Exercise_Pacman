import java.util.Scanner;

public class PacmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = Integer.parseInt(scanner.nextLine());

        char[][] grid = new char[N][N];
        int pacmanRow = -1, pacmanCol = -1;
        int totalStars = 0;
        int health = 100;
        boolean freezerActive = false;

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'P') {
                    pacmanRow = i;
                    pacmanCol = j;
                } else if (grid[i][j] == '*') {
                    totalStars++;
                }
            }
        }

        int uncollectedStars = totalStars;
        boolean gameEnded = false;

        while (uncollectedStars > 0) {
            String command = scanner.nextLine();
            if (command.equals("END")) {
                break;
            }

            int oldRow = pacmanRow;
            int oldCol = pacmanCol;
            grid[oldRow][oldCol] = '-';

            switch (command) {
                case "up":
                    pacmanRow = (pacmanRow - 1 + N) % N;
                    break;
                case "down":
                    pacmanRow = (pacmanRow + 1) % N;
                    break;
                case "left":
                    pacmanCol = (pacmanCol - 1 + N) % N;
                    break;
                case "right":
                    pacmanCol = (pacmanCol + 1) % N;
                    break;
            }

            char currentCell = grid[pacmanRow][pacmanCol];
            if (currentCell == '-') {
                grid[pacmanRow][pacmanCol] = 'P';
            } else if (currentCell == '*') {
                grid[pacmanRow][pacmanCol] = 'P';
                uncollectedStars--;
            } else if (currentCell == 'G') {
                if (freezerActive) {
                    freezerActive = false;
                    grid[pacmanRow][pacmanCol] = 'P';
                } else {
                    health -= 50;
                    grid[pacmanRow][pacmanCol] = '-';
                    if (health <= 0) {
                        System.out.println("Game over! Pacman last coordinates [" + pacmanRow + "," + pacmanCol + "]");
                        gameEnded = true;
                        break;
                    }
                }
            } else if (currentCell == 'F') {
                freezerActive = true;
                grid[pacmanRow][pacmanCol] = 'P';
            }
        }

        if (!gameEnded && uncollectedStars == 0) {
            System.out.println("Pacman wins! All the stars are collected.");
        } else if (!gameEnded) {
            System.out.println("Pacman failed to collect all the stars.");
        }

        System.out.println("Health: " + health);
        if (uncollectedStars > 0) {
            System.out.println("Uncollected stars: " + uncollectedStars);
        }

        grid[pacmanRow][pacmanCol] = 'P';
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
