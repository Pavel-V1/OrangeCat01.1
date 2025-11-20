import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CSV_File_Reader_Class csvFileReaderClass = new CSV_File_Reader_Class();
        HashMap<Integer, ArrayList<String>> hashMap = csvFileReaderClass.CSVFileReaded();

        BPlusTree<Integer> dateBPTree = new BPlusTree<>();
        BPlusTree<Float> voteBPTree = new BPlusTree<>();
        HashMap<Integer, Integer> dateMap = new HashMap<>();
        HashMap<Float, Integer> voteMap = new HashMap<>();

        for (Integer id : hashMap.keySet()) {
            ArrayList<String> arrayList = hashMap.get(id);
            String dateString = arrayList.get(1);
            Integer date = stringToIntDate(dateString);
            Float avgVote = Float.valueOf(arrayList.get(4));

            dateBPTree.add(date);
            voteBPTree.add(avgVote);
            dateMap.put(date, id);
            voteMap.put(avgVote, id);
        }

        //
    }

    private static Integer stringToIntDate(String dateString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : dateString.toCharArray()) {
            boolean ok = true;
            try {
                Integer tryToGetCharValue = Integer.valueOf(String.valueOf(c));
            } catch (Exception e) {
                ok = false;
            }
            if (ok) {
                stringBuilder.append(c);
            }
        }
        return Integer.valueOf(stringBuilder.toString());
    }

    public static ArrayList<Integer> getMoviesFromRealiseDate(Integer lowestDate, Integer highestDate,
                                                              BPlusTree<Integer> bpTree, HashMap<Integer, Integer> dateMap) {
        if (lowestDate > highestDate) {
            Integer a = lowestDate;
            lowestDate = highestDate;
            highestDate = a;
        }
        ArrayList<Integer> arrayList = new ArrayList<>(bpTree.search(lowestDate, highestDate));
        ArrayList<Integer> movieIDs = new ArrayList<>();
        for (int i : arrayList) {
            movieIDs.add(dateMap.get(i));
        }
        return movieIDs;
    }

    public static ArrayList<Integer> getMoviesFromVoteAverage(Float lowestAvgVote, Float highestAvgVote,
                                                              BPlusTree<Float> bpTree, HashMap<Float, Integer> voteMap) {
        if (lowestAvgVote > highestAvgVote) {
            Float f = lowestAvgVote;
            lowestAvgVote = highestAvgVote;
            highestAvgVote = f;
        }
        ArrayList<Float> arrayList = new ArrayList<>(bpTree.search(lowestAvgVote, highestAvgVote));
        ArrayList<Integer> movieIDs = new ArrayList<>();
        for (float i : arrayList) {
            movieIDs.add(voteMap.get(i));
        }
        return movieIDs;
    }

    public static ArrayList<Integer> getMoviesFromBothParameters(Integer lowestDate, Integer highestDate,
                                   Float lowestAvgVote, Float highestAvgVote, BPlusTree<Integer> dateBPTree,
                                   BPlusTree<Float> voteBPTree, HashMap<Integer, Integer> dateMap, HashMap<Float, Integer> voteMap) {

        ArrayList<Integer> movie_array_date = getMoviesFromRealiseDate(lowestDate, highestDate, dateBPTree, dateMap);
        ArrayList<Integer> movie_array_vote = getMoviesFromVoteAverage(lowestAvgVote, highestAvgVote, voteBPTree, voteMap);
        ArrayList<Integer> movieIDs = new ArrayList<>();
        for (Integer id : movie_array_date) {
            if (movie_array_vote.contains(id)) {
                movieIDs.add(id);
            }
        }
        return movieIDs;
    }
}
