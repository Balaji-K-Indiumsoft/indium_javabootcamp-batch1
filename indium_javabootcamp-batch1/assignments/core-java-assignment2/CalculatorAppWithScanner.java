import java.util.Scanner;

public class CalculatorAppWithScanner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Calculator App!");
        System.out.println("-------------------------------");

        char operator;
        double number1, number2, result;

        System.out.print("Enter first number: ");
        number1 = input.nextDouble();

        System.out.print("Enter an operator (+, -, *, /): ");
        operator = input.next().charAt(0);

        System.out.print("Enter second number: ");
        number2 = input.nextDouble();

        switch (operator) {
            case '+':
                result = number1 + number2;
                System.out.println("Result: " + number1 + " + " + number2 + " = " + result);
                break;
            case '-':
                result = number1 - number2;
                System.out.println("Result: " + number1 + " - " + number2 + " = " + result);
                break;
            case '*':
                result = number1 * number2;
                System.out.println("Result: " + number1 + " * " + number2 + " = " + result);
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                    System.out.println("Result: " + number1 + " / " + number2 + " = " + result);
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                }
                break;
            default:
                System.err.println("Error: Invalid operator.");
                break;
        }

        System.out.println("-------------------------------");
        System.out.println("Thank you for using the Calculator App!");
        input.close();
    }
}
