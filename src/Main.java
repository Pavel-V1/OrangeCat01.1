import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File horror_movies = new File("C:\\Users\\User\\IdeaProjects\\GitHub\\OrangeCat01.1", "horror_movies.csv");
        Scanner scanner = new Scanner(horror_movies);
        String title = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            StringBuilder stringBuilder = new StringBuilder();
            int counter = 0;
            for (int i = 0; i < str.length(); i++) {

                if ("\"".equals(String.valueOf(str.charAt(i))) && counter == 0) {
                    //
                    counter++;
                } else {
                    //
                    counter--;
                }
            }
        }
//        System.out.println(list);
    }
}