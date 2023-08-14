import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        String filePath = getInfo("Enter file path: ");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int totalSum = 0;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(" ");
                int[] array = new int[numbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    array[i] = Integer.parseInt(numbers[i]);

                }
                printArray(array);

                int min = getMin(array);
                int max = getMax(array);
                int sum = getSum(array);

                System.out.println("Min: " + min);
                System.out.println("Max: " + max);
                System.out.println("Sum: " + sum);

                totalSum += sum;
            }
            System.out.println("Total sum of all arrays: " + totalSum);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getInfo(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    private static int getSum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    private static int getMax(int[] array) {
        int max = array[0];

        for (int num : array) {
            if (max < num) {
                max = num;
            }
        }

        return max;
    }

    private static int getMin(int[] array) {
        int min = array[0];
        for (int num : array) {
            //System.out.println(num + " ");

            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static void printArray(int[] array) {
        System.out.print("Array: ");

        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
