import java.util.Locale;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static int fstudents(Scanner sc){
        while(true) {
            System.out.println("How many students do you want to enroll? Write an integer number");
            try {
                int students = sc.nextInt();
                sc.nextLine();
                return students;
            } catch (InputMismatchException e) {
                System.out.println("Please write an integer number");
                sc.nextLine();
                continue;
            }
        }
    }
    public static String[] fnames( Scanner sc, int i, String[] names){
        System.out.println("What is the name of the student " +(i+1)+"?");
        names[i] = sc.nextLine();
        return names;
    }
    public static double[] fmarks( int i, Scanner sc, String[] names, double[] marks){
        while(true) {
            System.out.println("Which is the mark of " + names[i] + "? You can write decimals using .");
            try {
                marks[i] = sc.nextDouble();
                sc.nextLine();
                return marks;
            } catch (InputMismatchException e) {
                System.out.println("Please, write a valid number, using decimals with the separator .");
                sc.nextLine();
                continue;
            }
        }
    }
    public static boolean freview(String[] students, double[] marks, int i, Scanner sc){
        while(true) {
            System.out.println("Is correct the name " + students[i] + " and the mark " + marks[i] + "? Answer with yes to continue or no to rewrite the student " + (i + 1));
            String review = sc.nextLine();
            if (review.equalsIgnoreCase("yes")) {
                System.out.println("Ok, going to the next student...");
                return false;
            } else if (review.equalsIgnoreCase("no")) {
                System.out.println("Ok, coming back to rewrite...");
                return true;
            } else {
                System.out.println("Please, answer with yes or no");
                continue;
            }
        }
    }
    public static double faveragemark(double[] marks, int students){
        double sum = 0;
        for(double i : marks){
            sum += i;
        }
        sum = sum / students;
        return sum;
    }
    public static String fhighermark(String [] names, double[] marks){
        double mark = 0;
        int person = 0;
        for (int i=0; i < marks.length; i++ ){
        if (marks[i]> mark){
         mark = marks[i];
         person = i;
        }
        }
        return names [person]+" with " + String.format("%.2f",mark);
    }
    public static boolean fend(Scanner sc){
        while(true) {
            System.out.println("Do you want to do another list? Answer with yes or no");
            String mainloop = sc.nextLine();
            if (mainloop.equalsIgnoreCase("yes")) {
                System.out.println("Perfect, coming back to the beginning");
                return true;
            } else if (mainloop.equalsIgnoreCase("no")) {
                System.out.println("Perfect, thank you for use our services!");
                return false;
            } else {
                System.out.println("Please, answer with yes or no the question");
                continue;
            }
        }
    }
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        System.out.println("Welcome to the Students/Marks notebook!");
        while(loop) {
            int students = fstudents(sc);
            String[] names = new String[students];
            double[] marks = new double[students];
            for (int i = 0; i < students; i++) {
                boolean loop1 = true;
                while(loop1) {
                    names = fnames(sc, i, names);
                    marks = fmarks(i, sc, names, marks);
                    loop1 = freview(names, marks, i, sc);
                }
            }
            double sum = faveragemark(marks, students);
            String higher = fhighermark(names, marks);
            System.out.println("List with all students and their marks:");
            for (int o = 0; o < names.length && o < marks.length; o++) {
                System.out.println((o + 1) + "-" + names[o] + "-" + String.format("%.2f", marks[o]));
            }
            System.out.println("The average mark between all students is: " + String.format("%.2f", sum));
            System.out.println("The highest mark is for " + higher + ", congratulations!");
            loop = fend(sc);
        }
    }
}
