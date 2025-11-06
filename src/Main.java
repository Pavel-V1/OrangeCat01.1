import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File horror_movies = new File("C:\\Users\\User\\IdeaProjects\\GitHub\\OrangeCat01.1", "horror_movies.csv");
        File horror_movies = new File("C:\\Users\\voronov_p_a\\IdeaProjects\\OrangeCat01.1", "horror_movies.csv");
        Scanner scanner = new Scanner(horror_movies);
//        String title = scanner.nextLine();
//        while (scanner.hasNextLine()) {
//            String str = scanner.nextLine();
//            StringBuilder str_build = new StringBuilder();
//            int counter = 0;
//            for (int i = 0; i < str.length(); i++) {
//                boolean equals = "\"".equals(String.valueOf(str.charAt(i)));
//                if (equals && counter == 0) {
//                    //
//                    counter++;
//                } else if (equals && counter == 1) {
//                    //
//                    counter--;
//                } else {
//
//                }
//            }
//        }
        List list = new List();
        while (scanner.hasNextLine()) {
        }
        System.out.println(list);
    }
}
