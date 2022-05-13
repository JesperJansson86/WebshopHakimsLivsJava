/**
 * Created by Hui
 * Date:2022-05-11
 * Time:15:31
 * Copyright:MIT
 */
import java.util.Scanner;
public class Exercise01 {
    public static void main(String[] args) {
        //calculate the grades of three classes, each with five students
        //average score for each class and the average score for all classes
        //count the number of passers in three classes, each class har five students
        Scanner myScanner = new Scanner(System.in);
        for (int i = 1; i <=3; i++) {
            double sum = 0;
            for (int j = 1; j <= 5; j++) {
                System.out.println("Please write in score of class "+ i + " number " + j + " student" + " Score:");
                double Score = myScanner.nextDouble();
                sum += Score;
                System.out.println("Score : " + Score);

            }
            System.out.println("Sum= " + sum + "\nAverage= " + sum/5 );
        }


    }
}
