package org.echocat.kata.java.part1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Book library constants.
 */
@Component
@ConfigurationProperties("constants.library")
@Getter
@Setter
public class BookLibraryConstants {

  private String bookCsvFileName;
  private String authorCsvFileName;
  private String magazineCsvFileName;
  private char csvFileColumnSeparator;
  private int csvFilesSkipLine;
  private String csvFileRepoPath;
}
