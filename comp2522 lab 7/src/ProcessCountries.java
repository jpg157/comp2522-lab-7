import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProcessCountries {
    private static final String INPUT_FILE = "src/week8countries.txt";
    private static final String OUTPUT_DIRECTORY = "src/matches";
    private static final String OUTPUT_FILE = "src/matches/data.txt";

    // Constants used in Stream and Filter operations
    private static final int COUNTRY_MAX_CHAR_LENGTH = 10;

    private static final int COUNTRY_MIN_CHAR_LENGTH = 5;

    public static void main(final String[] args) throws java.io.IOException
    {
        final Path inputFilePath;

        final Path outputFile;
        final Path outputDirectory;
        final List<String> countries;

        final Path subDirPath;

        countries = new ArrayList<>();

        // Read a text file named "week8countries.txt", where each line contains the name of a
        // country (the file was supplied along with this lab writeup document).

        inputFilePath = Paths.get(INPUT_FILE);

        if (Files.exists(inputFilePath)) {

            // Using try-with-resources - to be auto-closed, resource needs to be declared inside try block
            try (final BufferedReader br = Files.newBufferedReader(inputFilePath))
            {
                String currentLine;

                currentLine = br.readLine();

                // Check if the first and subsequent lines are not null.
                while (currentLine != null)
                {
                    countries.add(currentLine);

                    currentLine = br.readLine();
                }
            }
            catch (final IOException e)
            {
                e.printStackTrace();
            }
        }

        outputDirectory = Paths.get(OUTPUT_DIRECTORY);
        outputFile = Paths.get(OUTPUT_FILE);

        if (Files.notExists(outputDirectory))
        {
            Files.createDirectory(outputDirectory);
        }
        else
        {
            System.out.println("Output directory already exists.");
        }

        if (Files.notExists(outputFile))
        {
            Files.createFile(outputFile);
        }
        else {
            System.out.println("Output file already exists.");
        }

        //TODO:
        // - Write results into file "data.txt" within the "matches" directory.
        // - Write to "data.txt": For each of the 16 requirements below, use stream processing to
        // - filter, map, or reduce the list of countries as needed, and write the results to "data.txt" in the
        // "matches" directory.

        //TODO:
        // Req step 1: need to overwrite the file contents from the previous output - see slide 10 Files.write method

        // Use "Stream" and "filter" operations on the list of countries to meet specific criteria.

            //1. Long Country Names: Write "Country names longer than 10 characters:" followed by all
            //   country names with more than 10 characters (always one country per line).

        //TODO:
        // write "Country names longer than 10 characters:" to  file "data.txt"
        filteredStream(countries)
                .filter(country -> country.length() > COUNTRY_MAX_CHAR_LENGTH);
        //TODO: ^
        // Req step 2 (for each of the 16 stream/filter operations listed below):
        // - collect filtered stream into data collection (arraylist)
            // List<String> filteredCountries;
            // filteredCountries = new ArrayList<>();
            // filteredCountries = ... (see collect method)
        // - write to file file "data.txt" - slide 10 - with NO OVERWRITE

        System.out.println("------------------------------------------------------------------------");

            //2. Short Country Names: Write "Country names shorter than 5 characters:" followed by all
            //   country names with fewer than 5 characters.

        //TODO:
        // write "Country names shorter than 5 characters:" to  file "data.txt"
        filteredStream(countries)
                .filter(country -> country.length() < COUNTRY_MIN_CHAR_LENGTH);
        //TODO: ^
        // same as req step 2

        System.out.println("------------------------------------------------------------------------");
            //3. Starting with "A": List all country names that start with the letter "A".
        System.out.println("------------------------------------------------------------------------");
            //4. Ending with "land": List all country names that end with "land".
        System.out.println("------------------------------------------------------------------------");
            //5. Containing "United": List all countries containing the word "United".
        System.out.println("------------------------------------------------------------------------");
            //6. Sorted Names (Ascending): List all country names in alphabetical order.
        System.out.println("------------------------------------------------------------------------");
            //7. Sorted Names (Descending): List all country names in reverse alphabetical order.
            //8. Unique First Letters: List the unique first letters of all country names.
        System.out.println("------------------------------------------------------------------------");
            //9. Count of Countries: Write the total count of country names.
        System.out.println("------------------------------------------------------------------------");
            //10. Longest Country Name: Write the longest country name.
        System.out.println("------------------------------------------------------------------------");
            //11. Shortest Country Name: Write the shortest country name.
        System.out.println("------------------------------------------------------------------------");
            //12. Names in Uppercase: Write all country names converted to uppercase.
        System.out.println("------------------------------------------------------------------------");
            //13. Countries with More Than One Word: List all country names with more than one word.
        System.out.println("------------------------------------------------------------------------");
            //14. Country Names to Character Count: Map each country name to its character count,
            //    writing each name and count as "Country: X characters".
        System.out.println("------------------------------------------------------------------------");
            //15. Any Name Starts with "Z": Write "true" if any country name starts with "Z"; otherwise,
            //  "false".
        System.out.println("------------------------------------------------------------------------");
            //16. All Names Longer Than 3: Write "true" if all country names are longer than 3 characters;
            //    otherwise, "false".
        System.out.println("------------------------------------------------------------------------");

    }

    private static Stream<String> filteredStream(final List<String> countries)
    {
        final Stream<String> filteredStream;
        filteredStream = countries.stream()
                                  .filter(country -> (country != null) && (!country.isBlank()));

        return filteredStream;
    }
}
