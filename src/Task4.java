import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class Task4 {
    public static void main(String[] args) {
        String filePath = getInfo("Enter path: ");

        String numbersString = inputNumbers("Enter numbers separated by spaces: ");

        String[] numbersArray = numbersString.split(" ");

        int[] array = new int[numbersArray.length];
        for (int i = 0; i < numbersArray.length; i++) {
            array[i] = Integer.parseInt(numbersArray[i]);
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            saveOriginArray(writer,array);
            saveEvenNumbers(writer,array);
            saveOddNumbers(writer,array);
            saveReversedArray(writer,array);

            System.out.println("Data saved to the file");



        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
    private static String getInfo(String message)
    {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    private static String inputNumbers(String message) {
        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static void saveReversedArray(BufferedWriter writer, int[] array) throws IOException {
        for (int i = array.length - 1; i >= 0; i--) {
            writer.write(array[i] + " ");
        }
        writer.newLine();
    }

    private static void saveEvenNumbers(BufferedWriter writer, int[]array)throws IOException{

        for(int num  : array)
        {
            if(num%2 == 0)
            {
                writer.write(num + " ");
            }
        }
        writer.newLine();
    }

    private static void saveOddNumbers(BufferedWriter writer, int[]array)throws IOException{

        for(int num  : array)
        {
            if(num%2 != 0)
            {
                writer.write(num + " ");
            }
        }
        writer.newLine();
    }

    private static void saveOriginArray(BufferedWriter writer,int[]array) throws IOException {
        for(int num : array)
        {
            writer.write(num + " ");
        }
        writer.newLine();
    }
}
