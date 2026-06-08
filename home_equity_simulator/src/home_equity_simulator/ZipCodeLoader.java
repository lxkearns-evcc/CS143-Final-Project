package home_equity_simulator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ZipCodeLoader {
	private static final String DATA_DIRECTORY_PATH = "data_files";
	
	public static ArrayList<Path> detectAvailableDatasets() {
		ArrayList<Path> csvFiles = new ArrayList<>();
		File dataDir = new File(DATA_DIRECTORY_PATH);
        
        // Filter the directory contents for files ending with .csv
        File[] foundFiles = dataDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        
        if (foundFiles != null) {
            for (File file : foundFiles) {
            	Path thisFilePath = Path.of(DATA_DIRECTORY_PATH,file.getName());
                csvFiles.add(thisFilePath);
            }
        }
        System.out.println(csvFiles);
        return csvFiles;
    }
    /**
     * Reads a CSV file containing regional housing data and converts rows into ZipCode objects.
     * Expects schema: zip_code,county_name,state,avg_home_price,avg_annual_appreciation
     *
     * @param filePath Path to the target CSV file.
     * @return A list of successfully parsed ZipCode objects.
     */
    public static ArrayList<ZipCode> loadMarketData(Path filePath) {
    	ArrayList<ZipCode> zipRegistry = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
            // Read and discard the header row
            String header = br.readLine();
            if (header == null) {
                System.err.println("Warning: The market data file is empty.");
                return zipRegistry;
            }

            // Parse data rows sequentially
            while ((line = br.readLine()) != null) {
                // Handle blank lines safely
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split columns by comma
                String[] columns = line.split(",");

                // Guard clause: ensure data row matches expected length
                if (columns.length < 5) {
                    System.err.println("Skipping malformed data row: " + line);
                    continue;
                }

                try {
                    String zipCode = columns[0].trim();
                    String countyName = columns[1].trim();
                    String state = columns[2].trim();
                    double avgHomePrice = Double.parseDouble(columns[3].trim());
                    double avgAnnualAppreciation = Double.parseDouble(columns[4].trim());

                    ZipCode zip = new ZipCode(zipCode, countyName, state, avgHomePrice, avgAnnualAppreciation);
                    zipRegistry.add(zip);

                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping row due to numeric parsing failure on line: " + line);
                }
            }
            zipRegistry.sort(Comparator.comparingDouble(ZipCode::getAvgHomePrice));
        } catch (IOException e) {
            System.err.println("Critical Error: Unable to read file at " + filePath + ". Message: " + e.getMessage());
        }

        return zipRegistry;
    }
}
