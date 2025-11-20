import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSV_File_Reader_Class {
    public HashMap<Integer, ArrayList<String>> CSVFileReaded() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("horror_movies.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader = new CSVReaderBuilder(fileReader)
                .withCSVParser(parser)
                .build();

        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();
        try {
            int c = 0;
            for (String[] row : csvReader.readAll()) {
                c++;
                int counter = 0;
                int idCol = 0;
                for (String col : row) {
                    counter++;
                    if (c != 1 && counter == 2) {
                        if (col.isEmpty()) {
                            continue;
                        }
                        idCol = Integer.parseInt(col);
                        hashMap.put(idCol, new ArrayList<>());
                    } else if (c != 1) {
                        if (counter == 4 || counter == 8 || counter == 10 || counter == 11 ||
                            counter == 12 || counter == 16 || counter == 17 || counter == 19) {

                            hashMap.get(idCol).add(col);
                        }
                    }
                }
            }
            return hashMap;
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
