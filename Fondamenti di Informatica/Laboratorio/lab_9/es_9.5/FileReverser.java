import java.util.Scanner;
import java.io.*;

public class FileReverser {
    public static void main(String[] args) {
        String output = "";
        try (FileReader file = new FileReader(args[0]); Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                output += reverseString(input.nextLine()) + '\n';
            }
        } catch (IOException e) {
            System.out.println("Exception in getting the input file!");
            System.exit(1);
        }
        try (FileWriter file = new FileWriter(args[1])) {
            file.write(output);
        } catch (IOException e) {
            System.out.println("Exception in getting the output file!");
            System.exit(1);
        }
    }

    public static String reverseString(String input) {
        String output = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            output += input.charAt(i);
        }
        return output;
    }
}