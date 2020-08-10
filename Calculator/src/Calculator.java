import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //User inputs values
        System.out.print("Enter first operand: ");
        String first = input.next();
        double firstOperand = Double.parseDouble(first);
        System.out.print("Enter second operand: ");
        String second = input.next();
        double secondOperand = Double.parseDouble(second);

        //User Chooses an Option
        System.out.println();
        System.out.println("Calculator Menu");
        System.out.println("---------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division \n");
        System.out.print("Which operation do you want to perform? ");
        int choice = input.nextInt();

        //Add
        if(choice == 1)
        {
            double result = firstOperand + secondOperand;
            System.out.println();
            System.out.println("The result of the operation is  " + result + ". Goodbye!");
        }
        //Subtract
        else if(choice == 2)
        {
            double result = firstOperand - secondOperand;
            System.out.println();
            System.out.println("The result of the operation is  " + result + ". Goodbye!");
        }
        //Multiply
        else if(choice == 3)
        {
            double result = firstOperand * secondOperand;
            System.out.println();
            System.out.println("The result of the operation is  " + result + ". Goodbye!");
        }
        //Divide
        else if(choice == 4)
        {
            double result = firstOperand / secondOperand;
            System.out.println();
            System.out.println("The result of the operation is  " + result + ". Goodbye!");
        }
        //Wrong Input
        else
        {
            System.out.println();
            System.out.println("Error: Invalid selection! Terminating program.");
        }

    }

}
