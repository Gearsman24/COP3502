import java.util.Scanner;
public class PakuriProgram
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Pakudex pakudex;

        //Display Welcome Message
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        //Prompt for / read Pakudex capacity and confirm
        int capacity = 0;
        String txtCapacity;
        while(capacity <= 0)
        {
            try
            {
                System.out.print("Enter max capacity of the Pakudex: ");
                txtCapacity = input.next();
                capacity = Integer.parseInt(txtCapacity);

                if(capacity < 0)
                {
                    throw new Exception();
                }


            }
            catch(Exception e)
            {
                System.out.println("Please enter a valid size.");
            }
        }

        System.out.println("The Pakudex can hold " + capacity + " species of Pakuri.");
        pakudex = new Pakudex(capacity);


        boolean continueApplication = true;
        while(continueApplication)
        {
            //Display menu
            System.out.println("\nPakudex Main Menu");
            System.out.println("-----------------");
            System.out.println("1. List Pakuri");
            System.out.println("2. Show Pakuri");
            System.out.println("3. Add Pakuri");
            System.out.println("4. Evolve Pakuri");
            System.out.println("5. Sort Pakuri");
            System.out.println("6. Exit\n");

            //Prompt for input
            System.out.print("What would you like to do? ");
            String txtResponse = input.next();
            int response = -1;

            try
            {
                response = Integer.parseInt(txtResponse);
            }
            catch (Exception e)
            {

            }

            if(response < 1 || response > 6)
            {
                System.out.println("Unrecognized menu selection!");
            }

            //Options

            //Listing Pakuri
            if(response == 1)
            {

                    String[] speciesArray = pakudex.getSpeciesArray();

                    if(speciesArray == null)
                    {
                        System.out.println("No Pakuri in Pakudex yet!");
                        continue;
                    }

                    System.out.println("Pakuri In Pakudex:");
                    int pakudexSize = pakudex.getSize();
                    for(int i = 0; i < pakudexSize; ++i)
                    {
                        String species = speciesArray[i];
                        System.out.println((i+1) +". " + species);
                    }

            }

            //Show Pakuri
            else if(response == 2)
            {
                System.out.print("Enter the name of the species to display: ");
                String species = input.next();

                    int[] stats = pakudex.getStats(species);

                    if(stats == null)
                    {
                        System.out.println("Error: No such Pakuri!");
                        continue;
                    }

                    System.out.println("\nSpecies: " + species);
                    System.out.println("Attack: " + stats[0]);
                    System.out.println("Defense: " + stats[1]);
                    System.out.println("Speed: " + stats[2] + "\n");
            }

            //Add Pakuri
            else if(response == 3)
            {
                //Exception if the Pakudex is full
                int size = pakudex.getSize();
                if(size == capacity)
                {
                    System.out.println("Error: Pakudex is full!");
                    continue;
                }

                System.out.print("Enter the name of the species to add: ");
                String species = input.next();

                    boolean methodAnswer = pakudex.addPakuri(species);
                    if(methodAnswer)
                    {
                        System.out.println("Pakuri species " + species + " successfully added!");
                    }
                    else
                    {
                        System.out.println("Error: Pakudex already contains this species!");
                    }
            }

            //Evolve Pakuri
            else if(response == 4)
            {
                System.out.print("Enter the name of the species to evolve: ");
                String species = input.next();

                    boolean methodAnswer = pakudex.evolveSpecies(species);
                    if(methodAnswer)
                    {
                        System.out.println(species + " has evolved!");
                    }
                    else
                    {
                        System.out.println("Error: No such Pakuri!");
                    }
            }

            //Sort Pakuri
            else if(response == 5)
            {
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");
            }

            //Exit
            else if(response == 6)
            {
                continueApplication = false;
                System.out.println("Thanks for using Pakudex! Bye!");
            }

        }
    }



}
