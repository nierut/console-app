import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String parameterOne = "";
        String parameterTwo = "";
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
            int[] numbers = getNumbersFromFile(parameterOne);
        } else {
            if (isValidNumber(parameterOne)) {
                System.out.println("get numbers from standard input");
                int[] numbers = getNumbersFromStdInput();
            } else {
                System.out.println("First parameter is not valid, must be positive integer or path to text file");
                int[] numbers = new int[1];
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

    public static int[] getNumbersFromFile(String filePath) {
        return null;
    }

    public static int[] getNumbersFromStdInput() {
        return null;
    }

    public static List<Integer> processNumbers(int[] numbers) {
        List<Integer> result = new ArrayList<>();
        if(arrayIsEven(numbers)) {
            for(int i = 0; i < numbers.length;i++) {
                if(numbers[i] % 2 == 0) {
                    result.add(numbers[i]);
                }
            }
        } else {
            for(int i = 0; i < numbers.length;i++) {
                if(numbers[i] % 2 != 0) {
                    result.add(numbers[i]);
                }
            }
        }
        return result;
    }

    public static boolean arrayIsEven(int[] numbers) {
        return numbers.length % 2 == 0;
    }
}
