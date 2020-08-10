import java.util.Scanner;
public class ConnectFour
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //Player chooses the height and length of the board
        System.out.print("What would you like the height of the board to be? ");
        int boardHeight = input.nextInt();

        System.out.print("What would you like the length of the board to be? ");
        int boardLength = input.nextInt();

        //The board is initialized and printed
        char[][] board = new char[boardHeight][boardLength];
        int boardTotal = boardHeight * boardLength; //Total number of spaces (for tie section)
        initializeBoard(board);
        printBoard(board);

        System.out.println("\nPlayer 1: x");
        System.out.println("Player 2: o");

        //If a player wins, the program ends
        boolean gameContinue = true;
        while(gameContinue)
        {
            int playerTurn = 0;
            int playerColumn;

            for(int i = 1; i <= boardTotal; i++)
            {

                if(playerTurn == 0)
                {
                    //Player 1 chooses column
                    System.out.print("\nPlayer 1: Which column would you like to choose? ");
                    playerColumn = input.nextInt();

                    //Board is updated and printed
                    int rowNum = insertChip(board, playerColumn, 'x');
                    printBoard(board);

                    //Check if Player 1 wins
                    checkIfWinner(board, playerColumn, rowNum, 'x');
                    boolean methodAnswer = checkIfWinner(board, playerColumn, rowNum, 'x');
                    if(methodAnswer)
                    {
                        gameContinue = false;
                        System.out.println();
                        System.out.println("Player 1 won the game!");
                        break;
                    }

                    playerTurn++; //Moves to player 2
                }

                else if(playerTurn == 1)
                {
                    //Player 2 chooses column
                    System.out.print("\nPlayer 2: Which column would you like to choose? ");
                    playerColumn = input.nextInt();

                    //Board is updated and printed
                    int rowNum = insertChip(board, playerColumn, 'o');
                    printBoard(board);

                    //Check if Player 2 wins
                    checkIfWinner(board, playerColumn, rowNum, 'o');
                    boolean methodAnswer = checkIfWinner(board, playerColumn, rowNum, 'o');
                    if(methodAnswer)
                    {
                        gameContinue = false;
                        System.out.println();
                        System.out.println("Player 2 won the game!");
                        break;
                    }

                    playerTurn--; //Moves to player 1
                }

                //Checks if all spaces are filled, tie
                if(i == boardTotal)
                {
                    gameContinue = false;
                    System.out.println("\nDraw. Nobody wins.");
                    break;
                }
            }
        }

    }

    /**
     * Method that initializes the array
     * @param array name of the array
     */
    public static void initializeBoard(char[][] array)
    {
        //Loops through each row and column
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                array[i][j] = '-';
            }

        }
    }

    /**
     * Method that prints the array
     * @param array name of the array
     */
    public static void printBoard(char[][] array)
    {
        //Loops through each row and column
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.print(array[array.length - i - 1][j]);
            }
            System.out.println();

        }

    }

    /**
     * Method that inputs a chip into the array
     * @param array name of the array
     * @param col column the chip was inputted into
     * @param chipType type of chip, x or o
     * @return the row number the chip was inputted into
     */
    public static int insertChip(char[][] array, int col, char chipType)
    {
        int response = 0;

        //Loops through each row
        for(int i = 0; i < array.length; i++)
        {
            if(array[i][col] == '-')
            {
                array[i][col] = chipType; //Replaces blank space with the chip
                response = i; //Returns row number
                break;
            }
        }

        return response;
    }

    /**
     * Method that checks if a player wins
     * @param array name of the array
     * @param col column the previous chip was inputted into
     * @param row row the previous chip was inputted into
     * @param chipType type of chip, x or o
     * @return true if a row or column  have four of the same chipType in a row. Otherwise false
     */
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
    {
        boolean response = false;
        int colAmount = 0;
        int rowAmount = 0;

        //Loops through each row, vertical victory
        for(int i = 0; i < array.length; i++)
        {
            //If 4 in a row, break
            if(array[i][col] == chipType)
            {
                colAmount++;
                if(colAmount == 4)
                {
                    break;
                }
            }
            //Resets if not in a row
            else if(array[i][col] != chipType)
            {
                colAmount = 0;
            }

        }

        //Loops through each column, horizontal victory
        for(int j = 0; j < array[row].length; j++)
        {
            //If 4 in a row, break
            if(array[row][j] == chipType)
            {
                rowAmount++;
                if(rowAmount == 4)
                {
                    break;
                }
            }
            //Resets if not in a row
            else if(array[row][j] != chipType)
            {
                rowAmount = 0;
            }
        }

        //Victory condition
        if(colAmount == 4 || rowAmount == 4)
        {
            response = true;
        }

        return response;
    }
}