
public class Main {
    public static void main(String[] args) {
        String parameterOne = "";
        String parameterTwo = "";
        System.out.println("Hello world");
        if (args.length == 0) {
            System.out.println("You have given no parameters");
        } else {
            parameterOne = args[0];
        }
        if (args.length == 2) {
            parameterTwo = args[1];
            if (!parameterTwo.endsWith(".txt")) {
                System.out.println("Second parameter must be path to a text file");
                parameterTwo = "";
            }
        } else if (args.length > 2) {
            System.out.println("You have given more than two parameters");
        }

        if (parameterOne.endsWith(".txt")) {
            System.out.println("We are going to read a file");
        } else {
            if (isValidNumber(parameterOne)) {
                System.out.println("get numbers from standard input");
            } else {
                System.out.println("First parameter is not valid, must be positive integer or path to text file");
            }
        }


    }

    public static boolean isValidNumber(String parameter) {
        try {
            double number = Double.parseDouble(parameter);
            return number > 0 && number == (int) number;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
