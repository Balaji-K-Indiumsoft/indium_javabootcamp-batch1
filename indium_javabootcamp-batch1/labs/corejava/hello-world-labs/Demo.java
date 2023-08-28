public class Demo {

    public static void main(String args[]) {

        System.out.println("Hello User");
        try {
            // sleep for 5 mins
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
