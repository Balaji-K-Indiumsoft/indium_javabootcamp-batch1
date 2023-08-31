

public class CalculatorWithCommandLine {

	public static void main(String[] args) {
		
		if(args.length!=3) {
			System.out.println("Using java CalculatorWithMethods <value1> <value2> <operation>");
		}
		
		double value1 =Double.parseDouble(args[0]);
		double value2 =Double.parseDouble(args[1]);
		String operation = args[2];
		switch(operation) {
		case "add":;
			System.out.println("Result of addition: "+ (value1 +value2));
			break;
		case "sub":
			System.out.println("Result of Subration: " + (value1 - value2));
			break;
		case "mul":
			System.out.println("Result of Multiplication: " + (value1 * value2));
			break;
		case "div":
			if(value2 == 0) {
				System.out.println("Invalid Input");
			}else {
			System.out.println("Result of Division: " + (value1 / value2));
			}
			break;
		default:
			System.out.println("Given value is Invalid");
		}
		
	}
	
}

