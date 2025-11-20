import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CSV_File_Reader_Class csvFileReaderClass = new CSV_File_Reader_Class();
        HashMap<Integer, ArrayList<String>> hashMap = csvFileReaderClass.CSVFileReader();

        BPlusTree<Integer> dateBPTree = new BPlusTree<>();
        BPlusTree<Float> voteBPTree = new BPlusTree<>();

        for (ArrayList<String> arrayList : hashMap.values()) {
            String dateString = arrayList.getFirst();
            Integer date = stringToIntDate(dateString);
            Float avgVote = Float.valueOf(arrayList.get(1));

            dateBPTree.add(date);
            voteBPTree.add(avgVote);
        }

        //
    }

    private static Integer stringToIntDate(String dateString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : dateString.toCharArray()) {
            boolean ok = true;
            try {
                Integer tryingToGetValueFromChar = Integer.valueOf(String.valueOf(c));
            } catch (Exception e) {
                ok = false;
            }
            if (ok) {
                stringBuilder.append(c);
            }
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    public static ArrayList<Integer> getMoviesFromRealiseDate(Integer lowestDate, Integer highestDate, BPlusTree<Integer> bpTree) {
        if (lowestDate > highestDate) {
            Integer a = lowestDate;
            lowestDate = highestDate;
            highestDate = a;
        }
        return bpTree.search(lowestDate, highestDate);
    }

    public static ArrayList<Integer> getMoviesFromVoteAverage(Float lowestAvgVote, Float highestAvgVote, BPlusTree<Float> bpTree) {
        if (lowestAvgVote > highestAvgVote) {
            Float f = lowestAvgVote;
            lowestAvgVote = highestAvgVote;
            highestAvgVote = f;
        }
        return bpTree.search(lowestAvgVote, highestAvgVote);
    }

    public static ArrayList<Integer> getMoviesFromBothParameters(Integer lowestDate, Integer highestDate,
                                   Float lowestAvgVote, Float highestAvgVote, BPlusTree<Integer> dateBPTree,
                                   BPlusTree<Float> voteBPTree, HashMap<Integer, ArrayList<String>> hashMap) {

        ArrayList<Integer> movie_array_date = getMoviesFromRealiseDate(lowestDate, highestDate, dateBPTree);
        ArrayList<Integer> movie_array_vote = getMoviesFromVoteAverage(lowestAvgVote, highestAvgVote, voteBPTree);
        ArrayList<Integer> movieIDs = new ArrayList<>();
        for (Integer id : movie_array_date) {
            if (movie_array_vote.contains(id)) {
                movieIDs.add(id);
            }
        }
        return movieIDs;
    }
}
