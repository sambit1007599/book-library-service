package org.echocat.kata.java.part1.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

/**
 * The type Book and magazine.
 */
@Data
public class BookAndMagazine {

  private String title;
  private String isbn;
  private List<Author> authors;
  private String description;
  private LocalDate publishedAt;
}
