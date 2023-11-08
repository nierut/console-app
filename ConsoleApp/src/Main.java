import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            List<Integer> numbers = getNumbersFromFile(parameterOne);
            result = processNumbers(numbers);
        } else {
            if (isValidNumber(parameterOne)) {
                List<Integer> numbers = getNumbersFromStdInput();
                result = processNumbers(numbers);
            } else {
                System.out.println("First parameter is not valid, must be positive integer or path to text file");

            }
        }

        if (parameterTwo.equals("")) {
            System.out.println("The result is:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        } else {
            writeResultToFile(result, parameterTwo);
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
        Path path = Paths.get(filePath);
        List<Integer> numbers = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] splitLine = lines.get(i).split(",");
                for (int j = 0; j < splitLine.length; j++) {
                    numbers.add(Integer.valueOf(splitLine[j]));
                }
            }
        } catch (IOException e) {
            System.out.println("Input file does not exist");
        }
        return numbers;
    }

    public static List<Integer> getNumbersFromStdInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input integers separated by a comma(','): ");
        String input = scanner.nextLine();
        String[] numbersAsStrings = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numbersAsStrings.length; i++) {
            Integer num = Integer.parseInt(numbersAsStrings[i]);
            numbers.add(num);
        }
        return numbers;
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

    public static void writeResultToFile(List<Integer> result, String filePath) {
        Path path = Paths.get(filePath);
        List<String> content = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            content.add(String.valueOf(result.get(i)));
        }
        try {
            Files.write(path, content, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + filePath);
        }
    }
}
