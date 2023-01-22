package filesaver;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVfileSaver {

    private static final String[] HEADERS = {"PORADI", "CAS"};
    private static final String URL = "./results.txt";
    private List<String> results;

    public CSVfileSaver(List<String> results) {
        this.results = results;
    }

    public void createCSVFile() throws IOException {
        FileWriter out = new FileWriter(URL);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {
            for (int i = 0; i < results.size(); i++) {
                printer.printRecord(i + 1, results.get(i));
            }
        }
    }
}
