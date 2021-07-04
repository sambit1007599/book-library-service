package org.echocat.kata.java.part1.dto;

import lombok.Data;

import com.opencsv.bean.CsvBindByPosition;

/**
 * The type Author.
 */
@Data
public class Author {

  @CsvBindByPosition(position = 0)
  private String email;

  @CsvBindByPosition(position = 1)
  private String firstname;

  @CsvBindByPosition(position = 2)
  private String lastname;
}
