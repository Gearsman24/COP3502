import java.util.Scanner;
import java.lang.Math;

public class SciCalculator
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //Initialization of Variables
        int choice;
        int numCalculations = 0;
        double sumCalculations = 0.0;
        double firstOperand;
        double secondOperand;
        double lastResult = 0.0;
        boolean continueApplication = true;
        boolean optionShow = true;

        //User Chooses an Option
        while(continueApplication)
        {

            if(optionShow)
            {
                System.out.println("Current Result: " + lastResult);
                System.out.println();
                System.out.println("Calculator Menu");
                System.out.println("---------------");
                System.out.println("0. Exit Program");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("5. Exponentiation");
                System.out.println("6. Logarithm");
                System.out.println("7. Display Average \n");
            }

            //The next input must be an integer
            System.out.print("Enter Menu Selection: ");
            choice = input.nextInt();

            //Add
            if(choice == 1)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }


                double result = firstOperand + secondOperand;
                System.out.println();
                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;
            }

            //Subtract
            else if(choice == 2)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }

                double result = firstOperand - secondOperand;
                System.out.println();

                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;
            }

            //Multiply
            else if(choice == 3)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }

                double result = firstOperand * secondOperand;
                System.out.println();
                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;
            }

            //Divide
            else if(choice == 4)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }

                double result = firstOperand / secondOperand;
                System.out.println();
                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;
            }

            //Exponentiation
            else if(choice == 5)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }

                double result = Math.pow(firstOperand, secondOperand);
                System.out.println();
                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;

            }

            //Logarithm
            else if(choice == 6)
            {
                //User inputs values
                System.out.print("Enter first operand: ");
                String first = input.next();
                if(first.contentEquals("RESULT"))
                {
                    firstOperand = lastResult;
                }
                else
                {
                    firstOperand = Double.parseDouble(first);
                }

                System.out.print("Enter second operand: ");
                String second = input.next();
                if(second.contentEquals("RESULT"))
                {
                    secondOperand = lastResult;
                }
                else
                {
                    secondOperand = Double.parseDouble(second);
                }

                double result = Math.log(secondOperand) / Math.log(firstOperand);
                System.out.println();
                sumCalculations = sumCalculations + result;
                numCalculations++;
                lastResult = result;
                optionShow = true;

            }

            //Display Average
            else if(choice == 7)
            {
                optionShow = false;
                if(numCalculations == 0)
                {
                    System.out.println();
                    System.out.println("Error: No calculations yet to average!");
                    System.out.println();
                }
                else
                {
                    System.out.println("Sum of calculations: " + sumCalculations);
                    System.out.println("Number of calculations: " + numCalculations);
                    double aveCalculations = sumCalculations / numCalculations;
                    aveCalculations = aveCalculations * 100;
                    aveCalculations = Math.round(aveCalculations);
                    aveCalculations = aveCalculations / 100;

                    System.out.println("Average of calculations: " + aveCalculations);
                    System.out.println();
                }
            }
            //Exit App
            else if(choice == 0)
            {
                System.out.println();
                System.out.println("Thanks for using this calculator. Goodbye!");
                continueApplication = false;
            }
            //Wrong Input
            if(choice < 0 || choice > 7)
            {
                System.out.println();
                System.out.println("Error: Invalid selection!");
                System.out.println();
                optionShow = false;
            }
        }


    }
}
