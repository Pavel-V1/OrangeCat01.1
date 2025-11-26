import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы...");
        CSV_File_Reader_Class csvFileReaderClass = new CSV_File_Reader_Class();
        HashMap<Integer, ArrayList<String>> hashMap = csvFileReaderClass.CSVFileReaded();
        int t = 5;

        BPlusTree dateBPTree = new BPlusTree(t);
        BPlusTree voteBPTree = new BPlusTree(t);

        for (Integer id : hashMap.keySet()) {
            ArrayList<String> arrayList = hashMap.get(id);
            String dateString = arrayList.get(1);
            Integer date = stringToIntDate(dateString);
            Float avgVote = Float.valueOf(arrayList.get(4));

            dateBPTree.add(date.floatValue(), id);
            voteBPTree.add(avgVote, id);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа готова, шаблоны ввода: {date/vote/date-vote} {ГГГГ-ММ-ДД} {x/x.x}");
//        while (!scanner.nextLine().equalsIgnoreCase("stop")) {
//            String[] s = scanner.nextLine().split(" ");
//            ArrayList<Integer> movies = null;
//            if (s[0].equals("date")) {
//                System.out.println("Обработка...");
//                if (s.length < 2) {
//                    System.out.println("Некорректный ввод");
//                    continue;
//                }
//                String a = s[1];
//                if (s.length >= 3) {
//                    String b = s[2];
//                    movies = dateBPTree.search(Float.valueOf(stringToIntDate(a)), Float.valueOf(stringToIntDate(b)));
//                } else {
//                    movies = dateBPTree.search(Float.valueOf(stringToIntDate(a)));
//                }
//            } else if (s[0].equals("vote")) {
//                System.out.println("Обработка...");
//                if (s.length < 2) {
//                    System.out.println("Некорректный ввод");
//                    continue;
//                }
//                String a = s[1];
//                if (s.length >= 3) {
//                    String b = s[2];
//                    movies = voteBPTree.search(Float.valueOf(a), Float.valueOf(b));
//                } else {
//                    movies = voteBPTree.search(Float.valueOf(a));
//                }
//            } else if (s[0].equals("date-vote")) {
//                System.out.println("Обработка...");
//                if (s.length < 3) {
//                    System.out.println("Некорректный ввод");
//                    continue;
//                }
//                int count = 2;
//                String a = s[1];
//                String b = s[2];
//                String c = null;
//                String d = null;
//                if (s.length >= 4) {
//                    c = s[3];
//                    count++;
//                }
//                if (s.length == 5) {
//                    d = s[4];
//                    count++;
//                }
//
//                if (count == 2) {
//                    c = b;
//                    d = b;
//                    a = String.valueOf(stringToIntDate(a));
//                    b = a; //aabb
//                } else if (count == 3) {
//                    if (String.valueOf(b).length() < 8) {
//                        d = c;
//                        c = b;
//                        a = String.valueOf(stringToIntDate(a));
//                        b = a; //aabc
//                    } else {
//                        d = c; //abcc
//                        a = String.valueOf(stringToIntDate(a));
//                        b = String.valueOf(stringToIntDate(b));
//                    }
//                }
//
//                movies = getMoviesFromBothCols(Float.valueOf(a), Float.valueOf(b),
//                        Float.valueOf(c), Float.valueOf(d), dateBPTree, voteBPTree);
//            }
//            if (movies != null && movies.isEmpty()) {
//                System.out.println("Фильмов не найдено");
//            } else if (movies != null) {
//                System.out.println(movies);
//            }
//        }
    }

    private static Integer stringToIntDate(String dateString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : dateString.toCharArray()) {
            boolean ok = true;
            try {
                Integer tryToGetCharValue_WhyNotMeow_Sleeping___ = Integer.valueOf(String.valueOf(c));
            } catch (Exception e) {
                ok = false;
            }
            if (ok) {
                stringBuilder.append(c);
            }
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    public static ArrayList<Integer> getMoviesFromBothCols(Float lowestDate, Float highestDate,
                                                                 Float lowestAvgVote, Float highestAvgVote,
                                                                 BPlusTree dateBPTree, BPlusTree voteBPTree) {

        ArrayList<Integer> movieArrayDate = dateBPTree.search(lowestDate, highestDate);
        ArrayList<Integer> movieArrayVote = voteBPTree.search(lowestAvgVote, highestAvgVote);
        ArrayList<Integer> movieIDs = new ArrayList<>();
        for (Integer id : movieArrayDate) {
            if (movieArrayVote.contains(id)) {
                movieIDs.add(id);
            }
        }
        return movieIDs;
    }
}
