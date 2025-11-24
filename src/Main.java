import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        while (!scanner.nextLine().equalsIgnoreCase("stop")) {
            if (scanner.next().equals("date")) {
                String a = scanner.next();
                if (scanner.hasNext()) {
                    String b = scanner.next();
                    System.out.println(dateBPTree.search(Float.valueOf(stringToIntDate(a)), Float.valueOf(stringToIntDate(b))));
                } else {
                    System.out.println(dateBPTree.search(Float.valueOf(stringToIntDate(a))));
                }
            } else if (scanner.next().equals("vote")) {
                String a = scanner.next();
                if (scanner.hasNext()) {
                    String b = scanner.next();
                    System.out.println(voteBPTree.search(Float.valueOf(a), Float.valueOf(b)));
                } else {
                    System.out.println(voteBPTree.search(Float.valueOf(a)));
                }
            } else {
                int count = 2;
                String a = scanner.next();
                String b = scanner.next();
                String c = null;
                String d = null;
                if (scanner.hasNext()) {
                    c = scanner.next();
                    count++;
                }
                if (scanner.hasNext()) {
                    d = scanner.next();
                    count++;
                }

                if (count == 2) {
                    c = b;
                    d = b;
                    a = String.valueOf(stringToIntDate(a));
                    b = a; //aabb
                } else if (count == 3) {
                    if (String.valueOf(b).length() < 8) {
                        d = c;
                        c = b;
                        a = String.valueOf(stringToIntDate(a));
                        b = a; //aabc
                    } else {
                        d = c; //abcc
                        a = String.valueOf(stringToIntDate(a));
                        b = String.valueOf(stringToIntDate(b));
                    }
                }

                System.out.println(getMoviesFromBothCols(Float.valueOf(a), Float.valueOf(b),
                        Float.valueOf(c), Float.valueOf(d), dateBPTree, voteBPTree));
            }
        }
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
