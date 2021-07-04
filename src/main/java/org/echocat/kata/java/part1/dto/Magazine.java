package org.echocat.kata.java.part1.dto;

import java.time.LocalDate;

import lombok.Data;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

/**
 * The type Magazine.
 */
@Data
public class Magazine {

  @CsvBindByPosition(position = 0)
  private String title;

  @CsvBindByPosition(position = 1)
  private String isbn;

  @CsvBindByPosition(position = 2)
  private String authorEmails;

  @CsvDate(value = "dd.MM.yyyy")
  @CsvBindByPosition(position = 3)
  private LocalDate publishedAt;
}
