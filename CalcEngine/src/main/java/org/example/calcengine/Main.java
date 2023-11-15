package org.example.calcengine;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if(args.length == 0) {
           performCalculations();
        } else if (args.length == 1 && args[0].equals("interaction")) {
            executeInteraction();
        } else if (args.length == 3)
            performOperations(args);
            else
                System.out.println("please provide an operation code and 2 numeric values.");
    }

    //uses series of parallel arrays
    private static void performCalculations() {
        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation(MathOperation.DIVIDE, 100.0d, 50.0d);
        equations[1] = new MathEquation(MathOperation.ADD, 25.0d, 92.0d);
        equations[2] = new MathEquation(MathOperation.SUBTRACT, 225.0d, 17.0d);
        equations[3] = new MathEquation(MathOperation.MULTIPLY, 11.0d, 3.0d);

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.println(equation);
        }
        System.out.println("Average Result = " + MathEquation.getAverageResult());

      //  useOverloads();

    }

    static void useOverloads() {

        System.out.println();
        System.out.println("Using execute overloads");
        System.out.println();

        MathEquation equationOverload = new MathEquation(MathOperation.DIVIDE);
        double leftDouble = 9.0d;
        double rightDouble = 4.0d;
        equationOverload.execute(leftDouble, rightDouble);
        System.out.println("Overload result with doubles: " + equationOverload.getResult());

        int leftInt = 9;
        int rightInt = 4;
        equationOverload.execute(leftInt, rightInt);
        System.out.println("Overload result with Ints: " + equationOverload.getResult());
    }

    static void executeInteraction() {
        System.out.println("Enter an operation and two numbers.");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        performOperations(parts);
    }

    private static void performOperations(String[] parts) {
       MathOperation opCode= MathOperation.valueOf(parts[0].toUpperCase());
       double leftVal = valueFromWord(parts[1]);
       double rightVal = valueFromWord(parts[2]);
       MathEquation equation = new MathEquation(opCode,leftVal,rightVal);
       equation.execute();
       System.out.println(equation);
    }

    static double valueFromWord(String word) {
        String[] numberWords = {
           "zero", "one", "two", "three","four",
           "five", "six", "seven", "eight", "nine"
        };
        boolean isValueSet = false;
        double value = 0d;
        for(int i = 0; i < numberWords.length; i++) {
            if(word.equals(numberWords[i])) {
              value = i;
              isValueSet = true;
              break;
            }
        }
        if(!isValueSet) {
            value = Double.parseDouble(word);
        }
        return value;
    }



}