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

        List<Integer> result = new ArrayList<>();

        if (parameterOne.endsWith(".txt")) {
            System.out.println("We are going to read a file");
            List<Integer> numbers = getNumbersFromFile(parameterOne);
            result = processNumbers(numbers);
        } else {
            if (isValidNumber(parameterOne)) {
                System.out.println("get numbers from standard input");
                List<Integer> numbers = getNumbersFromStdInput();
                result = processNumbers(numbers);
            } else {
                System.out.println("First parameter is not valid, must be positive integer or path to text file");

            }
        }
        //WE HAVE A RESULT

        //WHERE DO WE PUT IT?

        if(parameterTwo.equals("")) {
            System.out.println("The result is:");
            for(int i = 0; i < result.size();i++) {
                System.out.println(result.get(i));
            }
        } else {
            System.out.println("We are printing into a file");
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

    public static List<Integer> getNumbersFromFile(String filePath) {
        return null;
    }

    public static List<Integer> getNumbersFromStdInput() {
        return null;
    }

    public static List<Integer> processNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        if (arrayIsEven(numbers)) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) % 2 == 0) {
                    result.add(numbers.get(i));
                }
            }
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) % 2 != 0) {
                    result.add(numbers.get(i));
                }
            }
        }
        return result;
    }

    public static boolean arrayIsEven(List<Integer> numbers) {
        return numbers.size() % 2 == 0;
    }
}
