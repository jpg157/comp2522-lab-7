import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessCountries {
    private static final String INPUT_FILE = "src/week8countries.txt";
    private static final String OUTPUT_DIRECTORY = "src/matches";
    private static final String OUTPUT_FILE = "src/matches/data.txt";

    // Constants used in Stream and Filter operations
    private static final int COUNTRY_MAX_CHAR_LENGTH = 10;

    private static final int COUNTRY_MIN_CHAR_LENGTH = 5;

    private static final String OVERWRITE_FILE_CONTENT_EMPTY = "";

    public static void main(final String[] args) throws java.io.IOException
    {
        final Path inputFilePath;
        final Path outputFilePath;
        final Path outputDirectoryPath;

        final List<String> countries;

        countries = new ArrayList<>();

        // Read a text file named "week8countries.txt", where each line contains the name of a
        // country (the file was supplied along with this lab writeup document).

        inputFilePath = Paths.get(INPUT_FILE);

        if (Files.exists(inputFilePath)) {

            // Using try-with-resources (large file) - to be auto-closed, resource needs to be declared inside try block
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

        outputDirectoryPath = Paths.get(OUTPUT_DIRECTORY);
        outputFilePath = Paths.get(OUTPUT_FILE);

        if (Files.notExists(outputDirectoryPath))
        {
            Files.createDirectory(outputDirectoryPath);
        }
        else
        {
            System.out.println("Output directory already exists.");
        }

        if (Files.notExists(outputFilePath))
        {
            Files.createFile(outputFilePath);
        }
        else {
            System.out.println("Output file already exists.");
        }

        // Overwrite the file contents from the previous main method run output
        Files.writeString(outputFilePath, OVERWRITE_FILE_CONTENT_EMPTY, StandardOpenOption.TRUNCATE_EXISTING);

        // Use "Stream" and "filter" operations on the list of countries to meet specific criteria:

        // For each of the 16 stream/filter operations listed below:
        // - use stream processing to filter, map, or reduce the list of countries based on conditions
        // - collect filtered stream into data collection (arraylist)
        // - write to file OUTPUT_FILE with no overwrite

            //1. Long Country Names:
                // Write "Country names longer than 10 characters:" followed by all
                // country names with more than 10 characters (one country per line).
            writeFileLongCountryNames(countries, outputFilePath);

            Files.writeString(outputFilePath, System.lineSeparator(), StandardOpenOption.APPEND);

            //2. Short Country Names:
                // Write "Country names shorter than 5 characters:" followed by all
                // country names with fewer than 5 characters.
            writeFileShortCountryNames(countries, outputFilePath);

    //TODO: below functions

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

    private static void writeFileLongCountryNames(final List<String> countries, final Path outputFilePath)
            throws IOException
    {
        // For storing filteredCountriesList
        // Not to be changed
        final List<String> filteredCountriesList;

        //1. Long Country Names:
        // Write "Country names longer than 10 characters:" followed by all
        // country names with more than 10 characters (always one country per line).

        // write "Country names longer than 10 characters:" to file with path outputFilePath

        Files.writeString(outputFilePath, "Country names longer than 10 characters:" + System.lineSeparator(), StandardOpenOption.APPEND);

        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.length() > COUNTRY_MAX_CHAR_LENGTH)
                .collect(Collectors.toList());

        // Output filtered country list to file
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);

        // Clear ArrayList
        filteredCountriesList.clear();
    }

    private static void writeFileShortCountryNames(final List<String> countries, Path outputFilePath)
    {
        //TODO
        //2. Short Country Names:
        // Write "Country names shorter than 5 characters:" followed by all
        // country names with fewer than 5 characters.

        //        Files.writeString(outputFilePath, "Country names shorter than 5 characters:" + System.lineSeparator(), StandardOpenOption.APPEND);
        //
        //        filteredCountriesList = filteredStream(countries)
        //                .filter(country -> country.length() < COUNTRY_MIN_CHAR_LENGTH)
        //                .toList();
        //
        //        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
    }

    private static Stream<String> filteredStream(final List<String> countries)
    {
        final Stream<String> filteredStream;
        filteredStream = countries.stream()
                                  .filter(country -> (country != null) && (!country.isBlank()));

        return filteredStream;
    }
}
