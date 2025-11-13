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
    public HashMap<Integer, ArrayList<String>> CSVFileReader() {
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
            for (String[] row : csvReader.readAll()) {
                int counter = 0;
                int int_col = 0;
                for (String col : row) {
                    counter++;
                    if (counter == 2 && !col.equals("id")) {
                        if (col.equals("")) {
                            continue;
                        }
                        int_col = Integer.parseInt(col);
                        hashMap.put(int_col, new ArrayList<>());
                    } else if (int_col != 0) {
                        if (counter == 8 && !col.equals("realise_date")) {
                            hashMap.get(int_col).add(col);
                        } else if (counter == 12 && !col.equals("vote_average")) {
                            hashMap.get(int_col).add(col);
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
