package ca.bcit.comp2522.CountryLab;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

public class CountryLab {
    private static final String INPUT_FILE = "src/ca/bcit/comp2522/CountryLab/week8countries.txt";
    private static final String OUTPUT_DIRECTORY = "src/matches";
    private static final String OUTPUT_FILE = "src/matches/data.txt";

    // Constants used in Stream and Filter operations
    private static final int COUNTRY_MAX_CHAR_LENGTH = 10;

    private static final int COUNTRY_MIN_CHAR_LENGTH = 5;

    private static final String OVERWRITE_FILE_CONTENT_EMPTY = "";

    private static final int ZERO_INDEX = 0;

    private static final int FIRST_INDEX = 1;

    private static final String LINEBREAK_LINE = "---------------------------------------------------------";

    private static final int MAX_COUNTRY_NAME_LENGTH = 3;

    private static final int TITLE_STARTING_INDEX = 1;

    public static void main(final String[] args) throws java.io.IOException
    {
        final Path inputFilePath;
        final Path outputFilePath;
        final Path outputDirectoryPath;
        
        final List<String> countries;
        List<String> filteredCountriesList;

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

        int titleNumber = TITLE_STARTING_INDEX;

        //1V. Long Country Names:
                // Write "Country names longer than 10 characters:" followed by all
                // country names with more than 10 characters (one country per line).
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.length() > COUNTRY_MAX_CHAR_LENGTH)
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "Country names longer than 10 characters:" );
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //2J. Short Country Names:
                // Write "Country names shorter than 5 characters:" followed by all
                // country names with fewer than 5 characters.
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.length() < COUNTRY_MIN_CHAR_LENGTH)
                .toList();

        addTitleAndLinebreak(outputFilePath, titleNumber, "Country names shorter than 5 characters:" );
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //3V. Starting with "A": List all country names that start with the letter "A".
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.startsWith("A"))
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "Country names starting with 'A':");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //4J. Ending with "land": List all country names that end with "land".
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.endsWith("land"))
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "All country names that end with \"Land\":");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //5V. Containing "United": List all countries containing the word "United".
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.contains("United"))
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "All countries containing the word \"United\":");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //6J. Sorted Names (Ascending): List all country names in alphabetical order.
        filteredCountriesList = filteredStream(countries)
                .sorted()
                .collect(Collectors.toList());
        addTitleAndLinebreak(outputFilePath, titleNumber, "All country names in alphabetical order:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

//        //7V. Sorted Names (Descending): List all country names in reverse alphabetical order.
//        filteredCountriesList = filteredStream(countries)
//                .sorted((Comparator<? super String>) countries.reversed())
//                .collect(Collectors.toList());
//
//        Files.writeString(outputFilePath, "List all country names in reverse alphabetical order:" + System.lineSeparator(), StandardOpenOption.APPEND);
//        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);

            //8J. Unique First Letters: List the unique first letters of all country names.
        filteredCountriesList = filteredStream(countries)
                .map(country -> country.substring(ZERO_INDEX, FIRST_INDEX))
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "List the unique first letters of all country names:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //9V. Count of Countries: Write the total count of country names.
        final int totalCountryCount;
        final String totalCountryCountString;
        filteredCountriesList = filteredStream(countries)
                .collect(Collectors.toList());
        totalCountryCount = filteredCountriesList.size();
        totalCountryCountString = "" + totalCountryCount;

        addTitleAndLinebreak(outputFilePath, titleNumber, "The total amount of country names:");
        Files.writeString(outputFilePath, totalCountryCountString, StandardOpenOption.APPEND);
        titleNumber++;

        //10J. Longest Country Name: Write the longest country name.
        final Optional<String> longestCountryName;

        longestCountryName = filteredStream(countries)
                .max(Comparator.comparing(String::length));

        addTitleAndLinebreak(outputFilePath, titleNumber, "The total amount of country names:");

        if (longestCountryName.isPresent())
        {
            Files.writeString(outputFilePath, totalCountryCountString, StandardOpenOption.APPEND);
        }
        titleNumber++;

        //11V. Shortest Country Name: Write the shortest country name.
        final Optional<String> shortestCountryName;
        shortestCountryName = Optional.of(String.valueOf(countries));

        addTitleAndLinebreak(outputFilePath, titleNumber, "The shortest country name is:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //12V. Names in Uppercase: Write all country names converted to uppercase.
        filteredCountriesList = filteredStream(countries)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "All countries in UPPERCASE:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;
        
            //13V. Countries with More Than One Word: List all country names with more than one word.
        filteredCountriesList = filteredStream(countries)
                .filter(country -> country.contains(" "))
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "All countries in UPPERCASE:");

        addTitleAndLinebreak(outputFilePath, titleNumber, "All countries that have more than one word:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

            //14J. Country Names to Character Count: Map each country name to its character count,
            //    writing each name and count as "Country: X characters".
        filteredCountriesList = filteredStream(countries)
                .map(country -> country + ": " + country.length() + " characters")
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber, "All country names to Character Count:");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

        //15V. Any Name Starts with "Z": Write "true" if any country name starts with "Z"; otherwise, "false".
        filteredCountriesList = filteredStream(countries)
                .map(country -> {
                    return country.startsWith("Z") ? "true" : "false";
                })
                .collect(Collectors.toList());

        addTitleAndLinebreak(outputFilePath, titleNumber,"Countries that start with \"Z\", and prints true, otherwise \"false\"");
        Files.write(outputFilePath, filteredCountriesList, StandardOpenOption.APPEND);
        titleNumber++;

        //16J. All Names Longer Than 3: Write "true" if all country names are longer than 3 characters;
            //    otherwise, "false".

        final boolean allLongerThanThreeChars;

        allLongerThanThreeChars = filteredStream(countries)
                .allMatch(country -> country.length() > MAX_COUNTRY_NAME_LENGTH);
//16. All Country Names Longer Than 3, prints "true" or "false"
        addTitleAndLinebreak(outputFilePath,
                            titleNumber,"All Country Names Longer Than 3 (prints \"true\" or \"false\")");
        Files.writeString(outputFilePath, "" + allLongerThanThreeChars, StandardOpenOption.APPEND);
    }

    /*
     * Helper function that adds the title to describe the output below.
     * @param outputFilePath the file / path to write to.
     * @param titleMessage the title to describe what is happening in the output.
     * @throws java.io.IOException when file cannot be written to.
     */
    private static void addTitleAndLinebreak(final Path outputFilePath, final int titleNumber, final String titleMessage) throws java.io.IOException {
        Files.writeString(outputFilePath,
            LINEBREAK_LINE +
                System.lineSeparator() +
                titleNumber +
                System.lineSeparator() +
                titleMessage +
                System.lineSeparator() +
                LINEBREAK_LINE +
                System.lineSeparator(), StandardOpenOption.APPEND);
    }

    /*
     * Helper function that removes all blank and null values from the List
     * @param countries as a List<String>
     * @return the filtered Stream<String>
     */
    private static Stream<String> filteredStream(final List<String> countries)
    {
        final Stream<String> filteredStream;
        filteredStream = countries.stream()
                                  .filter(country -> (country != null) && (!country.isBlank()));
        return filteredStream;
    }
}
