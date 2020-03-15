package mmmlpmsw.comp_math.lab2;

import mmmlpmsw.comp_math.lab2.Riemann_sum.Algorithm;
import mmmlpmsw.comp_math.lab2.Riemann_sum.functions.*;

import static mmmlpmsw.comp_math.lab2.utils.Utilities.colorize;

import java.io.*;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
    Algorithm algorithm;

    public InputReader() {
        scanner = new Scanner(System.in);
        algorithm = new Algorithm(new OutputCombiner());
    }
    public void process() throws IOException {
        System.out.println(getHelp());
        while (true) {
            try {
                System.out.print("Enter the command >>> ");
                String response = processCommand(getNextCommand());
                System.out.println(response);
            } catch (InputParseException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println( ("Incorrect, try again."));
            }
        }
    }

    private String processCommand(String request) throws InputParseException {
        if (request == null) {
            System.exit(0);
        }
        request = request.trim();
        UserCommand command = divideCommand(request);

        switch (command.name) {
            case "exit":
                System.exit(0);
            case "choose": {
                Function function;
                double lowLimit = 0, highLimit = 0;
                switch (command.value) {
                    case "1":
                        function = new SimpleHyperbola();
                        break;
                    case "2":
                        function = new SimpleSquareRoot();
                        break;
                    case "3":
                        function = new SimpleStraightLine();
                        break;
                    case "4":
                        function = new LinearFractionalFunction();
                        break;
                    default:
                        return "";
                }
                System.out.print("Enter the lower limit of the integration >>> ");
                boolean error = true;
                while (error) {
                    String low = scanner.nextLine();
                    low = low.replace(",", ".");
                    if (tryParse(low)) {
                        error = false;
                        lowLimit = Double.parseDouble(low);
                    } else {
                        error = true;
                        System.out.print("Incorrect. Enter the lower limit of the integration >>> ");
                    }
                }
                System.out.print("Enter the higher limit of the integration >>> ");
                error = true;
                while (error) {
                    String high = scanner.nextLine();
                    high = high.replace(",", ".");
                    if (tryParse(high)) {
                        error = false;
                        highLimit = Double.parseDouble(high);
                    } else {
                        error = true;
                        System.out.print("Incorrect. Enter the higher limit of the integration >>> ");
                    }
                }
                System.out.print("Enter the accuracy >>> ");
                error = true;
                double userAccuracy = 0;
                while (error) {
                    String accuracy = scanner.nextLine();
                    accuracy = accuracy.replace(",", ".");
                    if (tryParse(accuracy)) {
                        error = false;
                        userAccuracy = Double.parseDouble(accuracy);
                    } else {
                        error = true;
                        System.out.print("Incorrect. Enter the accuracy >>> ");
                    }
                }

                if (lowLimit > highLimit) {
                    double tmp = lowLimit;
                    lowLimit = highLimit;
                    highLimit = tmp;
                }

                algorithm.calculate(function, lowLimit, highLimit, userAccuracy);
                return "";
            }

            case "help":
                return getHelp();

            default:
                return "No such command: " + command.name + " please, use 'help;' to know, how to use it.";
        }
    }

    private UserCommand divideCommand(String request) {
        int spacePosition = request.indexOf(' ');
        if (spacePosition == -1) {return new UserCommand(request, null);}
        else return new UserCommand(request.substring(0, spacePosition), request.substring(spacePosition+1));
    }

    private String getNextCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        boolean inString = false;
        loop: do {
            String raw = reader.readLine();
            if (raw == null) return null;
            char[] data = raw.toCharArray();
            for (char current : data) {
                if (current != ';' || inString) {  builder.append(current); }
                if (current == '"') { inString = !inString;}
                if (current == ';' && !inString) break loop;
            }
        } while (true);
        return builder.toString();
    }

    private String getHelp() {
        return colorize( "To choose function enter '[[BLUE]]choose <number of function>;[[RESET]]'," +
                "\n you choose from some functions:" +
                "\n 1) " + new SimpleHyperbola().toString() +
                "\n 2) " + new SimpleSquareRoot().toString() +
                "\n 3) " + new SimpleStraightLine().toString() +
                "\n 4) " + new LinearFractionalFunction().toString() +
                "\nto exit enter '[[BLUE]]exit;[[RESET]]'" +
                "\nto get help use '[[BLUE]]help;[[RESET]]'. All commands must end in '[[BRIGHT_CYAN]];[[RESET]]'.");
    }

    private boolean tryParse (String string) throws InputParseException {
        try {
            Double number = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static class UserCommand {
        String name;
        String value;

        UserCommand (String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}