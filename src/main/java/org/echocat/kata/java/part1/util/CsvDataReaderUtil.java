package org.echocat.kata.java.part1.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import lombok.experimental.UtilityClass;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * The type CsvDataReaderUtil.
 */
@UtilityClass
public class CsvDataReaderUtil {

  /**
   * To read csv  data from file given as input.
   *
   * @param <T>       the type for casting row of csv data file
   * @param filePath  the file path
   * @param classType the class type
   * @param separator the separator
   * @param skipLine  the skip line
   * @return the list of rows of csv file
   * @throws FileNotFoundException the FileNotFoundException
   */
  public <T> List<T> readCsvData(String filePath, Class<T> classType, char separator, int skipLine)
      throws FileNotFoundException {

    CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();

    CSVReader csvReader =
        new CSVReaderBuilder(new FileReader(CsvDataReaderUtil.class.getClassLoader().getResource(filePath).getFile()))
            .withCSVParser(csvParser).withSkipLines(skipLine)
            .build();

    return new CsvToBeanBuilder<T>(csvReader)
        .withType(classType)
        .build()
        .parse();

  }
}
