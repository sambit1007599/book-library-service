package org.echocat.kata.java.part1.dto;

import lombok.Data;

import com.opencsv.bean.CsvBindByPosition;

/**
 * The type Book.
 */
@Data
public class Book {

  @CsvBindByPosition(position = 0)
  private String title;

  @CsvBindByPosition(position = 1)
  private String isbn;

  @CsvBindByPosition(position = 2)
  private String authorEmails;

  @CsvBindByPosition(position = 3)
  private String description;

}
