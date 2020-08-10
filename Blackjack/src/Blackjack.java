import java.util.Scanner;

public class Blackjack
{
    public static void main(String[] args)
    {
        P1Random rng = new P1Random();
        Scanner input = new Scanner(System.in);

        /*
        1 = Ace
        2-10 = Face Values
        11 = JACK!
        12 = QUEEN!
        13 = KING!

        JACK QUEEN and KING evaluate to 10
         */

        //Statistics
        int gameNumber = 0;
        int playerWins = 0;
        int dealerWins = 0;
        int numTies = 0;
        double percentPlayerWin;

        //While Loop for Successive Games
        boolean continueExecution = true;
        while(continueExecution)
        {
            //Tells the current game number
            gameNumber++;
            System.out.println("START GAME #" + gameNumber + "\n");

            //Initializes the first round
            String choice;
            int playerCard;
            int playerHand = 0;
            int dealerCard;
            int dealerHand = 0;


            //Tells the player's given card and current hand
            for(int i = 0; i < 21; i++) //If they get 21 Aces lol
            {
                playerCard = rng.nextInt(13) + 1; //RNG between 1-13
                if(playerCard == 1)
                {
                    System.out.println("Your card is a ACE!");
                    playerHand = playerHand + 1;
                }
                else if(playerCard >= 2 && playerCard <= 10)
                {
                    System.out.println("Your card is a " + playerCard + "!");
                    playerHand = playerHand + playerCard;
                }
                else if(playerCard == 11)
                {
                    System.out.println("Your card is a JACK!");
                    playerHand = playerHand + 10;
                }
                else if(playerCard == 12)
                {
                    System.out.println("Your card is a QUEEN!");
                    playerHand = playerHand + 10;
                }
                else if(playerCard == 13)
                {
                    System.out.println("Your card is a KING!");
                    playerHand = playerHand + 10;
                }

                System.out.println("Your hand is: " + playerHand +"\n");

                //Checks if the player wins or loses
                if(playerHand == 21)
                {
                    System.out.println("BLACKJACK! You win!\n");
                    playerWins++;
                    break;
                }
                else if(playerHand > 21)
                {
                    System.out.println("You exceeded 21! You lose.\n");
                    dealerWins++;
                    break;
                }


                //Player's Options Can be Repeated for Option 3 but no others
                choice = "3";
                while(!choice.contentEquals("1") || !choice.contentEquals("2") || !choice.contentEquals("4"))
                {
                    System.out.println("1. Get another card");
                    System.out.println("2. Hold hand");
                    System.out.println("3. Print statistics");
                    System.out.println("4. Exit\n");
                    System.out.print("Choose an option: ");

                    //Player's Choice
                    choice = input.next();
                    //Get another card
                    if(choice.contentEquals("1"))
                    {
                        System.out.println();
                        break;
                    }
                    //Hold hand, dealer gets a card
                    else if(choice.contentEquals("2"))
                    {
                        dealerCard = rng.nextInt(11) + 16;
                        dealerHand = dealerHand + dealerCard;

                        System.out.println("\n" + "Dealer's hand: " + dealerHand);
                        System.out.println("Your hand is: " + playerHand + "\n");

                        if(dealerHand > 21)
                        {
                            System.out.println("You win!\n");
                            playerWins++;
                            break;
                        }
                        else if(dealerHand == playerHand)
                        {
                            System.out.println("It's a tie! No one wins!\n");
                            numTies++;
                            break;
                        }
                        else if(dealerHand > playerHand)
                        {
                            System.out.println("Dealer wins!\n");
                            dealerWins++;
                            break;
                        }
                        else
                        {
                            System.out.println("You win!\n");
                            playerWins++;
                            break;
                        }
                    }
                    //Print Statistics
                    else if(choice.contentEquals("3"))
                    {
                        System.out.println();
                        System.out.println("Number of Player wins: " + playerWins);
                        System.out.println("Number of Dealer wins: " + dealerWins);
                        System.out.println("Number of tie games:  " + numTies);
                        int totalGames = gameNumber - 1; //Not including the current game
                        System.out.println("Total # of games played is: " + totalGames);
                        double doublePlayerWins = playerWins/1.0;
                        double doubleGameNumber = gameNumber/1.0;
                        percentPlayerWin = (doublePlayerWins / (doubleGameNumber - 1.0)) * 100;
                        System.out.println("Percentage of Player wins: " + percentPlayerWin + "%\n");
                    }
                    //Close App; breaks out of this while loop
                    else if(choice.contentEquals("4"))
                    {
                        continueExecution = false;
                        break;
                    }
                    else //They picked a stupid number
                    {
                        System.out.println();
                        System.out.println("Invalid input!");
                        System.out.println("Please enter an integer value between 1 and 4.\n");
                    }


                }

                //Breaks into main while loop
                if(choice.contentEquals("4"))
                {
                    break;
                }

                if(choice.contentEquals("2"))
                {
                    break;
                }


            }





        }







    }
}
