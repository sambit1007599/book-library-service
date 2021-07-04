package org.echocat.kata.java.part1.service;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.echocat.kata.java.part1.config.BookLibraryConstants;
import org.echocat.kata.java.part1.dto.Book;
import org.echocat.kata.java.part1.dto.Magazine;
import org.echocat.kata.java.part1.error.exception.CsvFileNotFoundException;
import org.echocat.kata.java.part1.model.Author;
import org.echocat.kata.java.part1.model.BookAndMagazine;
import org.echocat.kata.java.part1.util.CsvDataReaderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Book library service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookLibraryService {

  private final BookLibraryConstants bookLibraryConstants;

  /**
   * Retrieve all book and magazine list list.
   *
   * @return the list
   */
  public List<BookAndMagazine> retrieveAllBookAndMagazineList() {
    try {

      List<Book> bookList = CsvDataReaderUtil
          .readCsvData(bookLibraryConstants.getCsvFileRepoPath().concat(bookLibraryConstants.getBookCsvFileName()),
              Book.class, bookLibraryConstants.getCsvFileColumnSeparator(), bookLibraryConstants.getCsvFilesSkipLine());

      List<Magazine> magazineList = CsvDataReaderUtil
          .readCsvData(bookLibraryConstants.getCsvFileRepoPath().concat(bookLibraryConstants.getMagazineCsvFileName()),
              Magazine.class, bookLibraryConstants.getCsvFileColumnSeparator(),
              bookLibraryConstants.getCsvFilesSkipLine());

      return getAllBookAndMagazineList(bookList, magazineList);

    } catch (FileNotFoundException e) {
      throw new CsvFileNotFoundException(bookLibraryConstants.getBookCsvFileName());
    }
  }

  private List<BookAndMagazine> getAllBookAndMagazineList(List<Book> bookList, List<Magazine> magazineList) {
    Map<String, Author> emailAuthorMap = getEmailAuthorMap();
    return Stream.concat(bookList.stream().map(book -> {
      BookAndMagazine bookAndMagazine = new BookAndMagazine();
      BeanUtils.copyProperties(book, bookAndMagazine);
      if (StringUtils.isNotBlank(book.getAuthorEmails())) {
        bookAndMagazine
            .setAuthors(Arrays.stream(book.getAuthorEmails().split(",")).map(emailId -> emailAuthorMap.get(emailId))
                .collect(Collectors.toList()));
      }
      return bookAndMagazine;
    }), magazineList.stream().map(magazine -> {
      BookAndMagazine bookAndMagazine = new BookAndMagazine();
      BeanUtils.copyProperties(magazine, bookAndMagazine);
      if (StringUtils.isNotBlank(magazine.getAuthorEmails())) {
        bookAndMagazine
            .setAuthors(Arrays.stream(magazine.getAuthorEmails().split(",")).map(emailId -> emailAuthorMap.get(emailId))
                .collect(Collectors.toList()));
      }
      return bookAndMagazine;
    }))
        .sorted(Comparator.comparing(BookAndMagazine::getTitle))
        .collect(Collectors.toList());

  }

  private Map<String, Author> getEmailAuthorMap() {
    try {
      String nameSeparator = " ";
      List<org.echocat.kata.java.part1.dto.Author> authors = CsvDataReaderUtil
          .readCsvData(bookLibraryConstants.getCsvFileRepoPath().concat(bookLibraryConstants.getAuthorCsvFileName()),
              org.echocat.kata.java.part1.dto.Author.class, bookLibraryConstants.getCsvFileColumnSeparator(),
              bookLibraryConstants.getCsvFilesSkipLine());
      return authors.stream().map(author -> {
        Author authorDetails = new Author();
        authorDetails.setEmail(author.getEmail());
        authorDetails.setName(author.getFirstname().concat(nameSeparator).concat(author.getLastname()));
        return authorDetails;
      }).collect(Collectors.toMap(Author::getEmail, author -> author, (key, value) -> key));
    } catch (FileNotFoundException e) {
      throw new CsvFileNotFoundException(bookLibraryConstants.getBookCsvFileName());
    }
  }

  /**
   * Search book or magazine by isbn optional.
   *
   * @param isbn the isbn
   * @return the optional
   */
  public Optional<BookAndMagazine> searchBookOrMagazineByIsbn(String isbn) {

    return retrieveAllBookAndMagazineList().parallelStream().filter(bm -> bm.getIsbn().equalsIgnoreCase(isbn))
        .findFirst();

  }

  /**
   * Search book or magazine by author email id list.
   *
   * @param emailId the email id
   * @return the list
   */
  public List<BookAndMagazine> searchBookOrMagazineByAuthorEmailId(String emailId) {

    return retrieveAllBookAndMagazineList().parallelStream()
        .filter(bm -> bm.getAuthors().stream().anyMatch(author -> emailId.equalsIgnoreCase(author.getEmail())))
        .collect(Collectors.toList());
  }
}
