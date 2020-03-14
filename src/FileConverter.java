import java.util.Scanner;
/* Pyae Phyo Kyaw - 45238952 */
/**
 * Main class to start all threads
 */
public class FileConverter {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter input file name");
        String inputFileName=input.nextLine();
        System.out.println("Enter output file name");
        String outputFileName=input.nextLine();


        FirstThread ft = new FirstThread(inputFileName);
        Thread t1 = new Thread(ft);
        SecondThread st = new SecondThread();
        Thread t2 = new Thread(st);
        ThirdThread tt = new ThirdThread(outputFileName );
        Thread t3 = new Thread(tt);

        t1.start();
        t2.start();
        t3.start();


    }
}
