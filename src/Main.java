import java.util.ArrayList;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        CSV_File_Reader_Class csvFileReaderClass = new CSV_File_Reader_Class();
        HashMap<Integer, ArrayList<String>> hashMap = csvFileReaderClass.CSVFileReader();

        // Надо реализовать чтение из файла с помощью CSV-читателя (найти),
        // в словарь ключами определить "id", сделать В+_дерево,
        // создание дерева и ключей-маршрутизаторов в оперативной памяти,
        // в долговременной хранятся файлы с настоящими ключами - "id",
        // найти настоящие ключи ("id") по маршрутизаторам (индексам);
        // по "id" лезть в словарь и доставать данные.

        BPlusTree bpTree = new BPlusTree();
        for (int id : hashMap.keySet()) {
            bpTree.add(id);
        }

    }

    public ArrayList<String> getMovieFromRealiseDate(String smallestDate, String largestDate, BPlusTree bpTree) {
        for (int i = 0; i < smallestDate.length(); i++) {
            char j = smallestDate.charAt(i);
            char k = largestDate.charAt(i);
            if (j > k) {
                String str = smallestDate;
                smallestDate = largestDate;
                largestDate = str;
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

    public ArrayList<String> getMovieFromBothParameters(String smallestDate, String largestDate,
                                                        String lowestAvgVote, String highestAvgVote, BPlusTree bpTree) {
        ArrayList<String> movie_array = getMovieFromRealiseDate(smallestDate, largestDate, bpTree);
        // взять фильмы подходящие по дате
        ArrayList<String> movies = new ArrayList<>();
        // сюда передать из movie_array фильмы подходящие по оценке
        return movies;
    }
}
