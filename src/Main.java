import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Main {
    public static void main(String[] args) throws IOException, CsvException {
        FileReader fileReader = new FileReader("horror_movies.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(parser)
                .build();

        HashMap<Integer, String[]> hashMap = new HashMap<>();
        for (String[] row : csvReader.readAll()) {
            int counter = 0;
            for (String col : row) {
                counter++;
                if (counter == 2 && !col.equals("id")) {
                    int int_col = Integer.parseInt(col);
                    hashMap.put(int_col, null);
                } else if (counter == 8 && !col.equals("realise_date")) {
                    //
                } else if (counter == 12 && !col.equals("vote_average")) {
                    //
                }
            }
        }
        System.out.println(hashMap);

        // Надо реализовать чтение из файла с помощью CSV-читателя (найти),
        // в словарь ключами определить "id", сделать В+_дерево,
        // создание дерева и ключей-маршрутизаторов в оперативной памяти,
        // в долговременной хранятся файлы с настоящими ключами - "id",
        // найти настоящие ключи ("id") по маршрутизаторам (индексам);
        // по "id" лезть в словарь и доставать данные.
    }
}
