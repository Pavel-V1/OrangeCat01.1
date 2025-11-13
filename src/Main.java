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

        for (ArrayList<String> arr : hashMap.values()) {
            System.out.println(arr.get(0));
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
