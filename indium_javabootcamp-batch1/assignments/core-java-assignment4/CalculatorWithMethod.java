
public class CalculatorWithMethod {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java Calculator <operation> <operand1> <operand2>");
            System.exit(1);
        }

        String operation = args[0];
        double operand1 = Double.parseDouble(args[1]);
        double operand2 = Double.parseDouble(args[2]);
        double result = 0.0;

        switch (operation) {
            case "add":
                result = add(operand1, operand2);
                break;
            case "subtract":
                result = subtract(operand1, operand2);
                break;
            case "multiply":
                result = multiply(operand1, operand2);
                break;
            case "divide":
                try {
                    result = divide(operand1, operand2);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
                break;
            default:
                System.out.println("Invalid operation. Choose from 'add', 'subtract', 'multiply', or 'divide'.");
                System.exit(1);
        }

        System.out.println("Result: " + result);
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static double subtract(double x, double y) {
        return x - y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        if (y == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return x / y;
    }

}
