import java.util.ArrayList;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        CSV_File_Reader_Class csvFileReaderClass = new CSV_File_Reader_Class();
        HashMap<Integer, ArrayList<String>> hashMap = csvFileReaderClass.CSVFileReader();

        BPlusTree dateBPTree = new BPlusTree();
        BPlusTree voteBPTree = new BPlusTree();

        for (ArrayList<String> arrayList : hashMap.values()) {
            String dateString = arrayList.getFirst();
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : dateString.toCharArray()) {
                boolean ok = true;
                try {
                    int tryingToGetValueFromChar = Integer.parseInt(String.valueOf(c));
                } catch (Exception e) {
                    ok = false;
                }
                if (ok) {
                    stringBuilder.append(c);
                }
            }
            int date = Integer.parseInt(stringBuilder.toString());
            float avgVote = Float.parseFloat(arrayList.get(1));

            dateBPTree.add(date);
            voteBPTree.add(avgVote);
        }
    }

    public ArrayList<String> getMovieFromRealiseDate(String lowestDate, String highestDate, BPlusTree bpTree) {
        for (int i = 0; i < lowestDate.length(); i++) {
            char j = lowestDate.charAt(i);
            char k = highestDate.charAt(i);
            if (j > k) {
                String str = lowestDate;
                lowestDate = highestDate;
                highestDate = str;
                break;
            }
        }
        ArrayList<String> movies = new ArrayList<>();
        //
        return movies;
    }

    public ArrayList<String> getMovieFromVoteAverage(String lowestAvgVote, String highestAvgVote, BPlusTree bpTree) {
        //
        ArrayList<String> movies = new ArrayList<>();
        //
        return movies;
    }

    public ArrayList<String> getMovieFromBothParameters(String lowestDate, String highestDate,
                                                        String lowestAvgVote, String highestAvgVote, BPlusTree bpTree) {
        ArrayList<String> movie_array = getMovieFromRealiseDate(lowestDate, highestDate, bpTree);
        // взять фильмы подходящие по дате
        ArrayList<String> movies = new ArrayList<>();
        // сюда передать из movie_array фильмы подходящие по оценке
        return movies;
    }
}
