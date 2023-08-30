package core.java.labs;

public class CommandLineTask {

	public static void main(String[] args) {
		
		// Hello Indium Welcome to JavaTraining 100,Sanjay,5000.0,Savings
        // show length of the first argument value
        // convert second argument to lower case
        // print third argument in reverse
        // skip the fourth arg// show the sub string [4,11] of fifth argument
        // split sixth argument by , and show the tokens
		
		if (args.length == 0) {
            System.out.println("Hello Indium Welcome to JavaTraining 100,Sanjay,5000.0,Savings");
            System.exit(0);
        }

        String arg1 = args[0];
        String arg2 = args[1];
        String arg3 = args[2];
        String arg5 = args[4];
        String arg6 = args[5];

      
        System.out.println("Length of arg1: " + arg1.length());

       
        System.out.println("arg2 in lower case: " + arg2.toLowerCase());

        
        StringBuilder reversedArg3 = new StringBuilder(arg3);
        reversedArg3.reverse();
        System.out.println("Reversed arg3: " + reversedArg3);

        String subStrArg5 = arg5.substring(4, 12);
        System.out.println("Substring of arg5: " + subStrArg5);

        
        String[] tokensArg6 = arg6.split(",");
        System.out.println("Tokens in arg6:");
        for (String token : tokensArg6) {
            System.out.println(token);
        }
    }
}
