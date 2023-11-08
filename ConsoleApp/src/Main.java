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
        if (args.length == 0 || args.length > 2) {
            handleInvalidParameters(args);
        }
        String parameterOne = args[0];
        String parameterTwo = "";

        if (args.length == 2) {
            parameterTwo = args[1];
            if (!parameterTwo.endsWith(".txt")) {
                System.out.println("Second parameter must be path to a text file");
                System.exit(0);
            }
        }

        List<Integer> result = new ArrayList<>();

        if (parameterOne.endsWith(".txt")) {
            result = processFile(parameterOne);
        } else if (isValidNumber(parameterOne)) {
            result = processStdInput();
        } else {
            System.out.println("First parameter is not valid, must be positive integer or path to text file");
            System.exit(0);
        }

        if (parameterTwo.equals("")) {
            printResult(result);
        } else {
            writeResultToFile(result, parameterTwo);
        }
    }


    public static void handleInvalidParameters(String[] args) {
        if (args.length == 0) {
            System.out.println("You have given no parameters");
        } else {
            System.out.println("You have given more than two parameters");
        }
        System.exit(0);
    }

    public static boolean isValidNumber(String parameter) {
        try {
            double number = Double.parseDouble(parameter);
            return number > 0 && number == (int) number;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static List<Integer> processFile(String filePath) {
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
        return processNumbers(numbers);
    }

    public static List<Integer> processStdInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input integers separated by a comma(','): ");
        String input = scanner.nextLine();
        String[] numbersAsStrings = input.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numbersAsStrings.length; i++) {
            Integer num = Integer.parseInt(numbersAsStrings[i]);
            numbers.add(num);
        }
        return processNumbers(numbers);
    }

    public static List<Integer> processNumbers(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        if (numbers.size() % 2 == 0) {
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

    public static void printResult(List<Integer> result) {
        System.out.println("The result is:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
