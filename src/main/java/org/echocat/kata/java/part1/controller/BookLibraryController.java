package org.echocat.kata.java.part1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.echocat.kata.java.part1.model.BookAndMagazine;
import org.echocat.kata.java.part1.service.BookLibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Book library controller.
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Slf4j
public class BookLibraryController {

  private final BookLibraryService bookLibraryService;

  /**
   * Gets all book and magazine details.
   *
   * @return the all book and magazine details
   */
  @GetMapping("/all")
  public ResponseEntity<List<BookAndMagazine>> getAllBookAndMagazineDetails() {
    return new ResponseEntity<>(bookLibraryService.retrieveAllBookAndMagazineList(), HttpStatus.OK);
  }

  /**
   * Search book or magazine by isbn response entity.
   *
   * @param isbn the isbn
   * @return the response entity
   */
  @GetMapping("/book-magazine/{isbn}")
  public ResponseEntity<Optional<BookAndMagazine>> searchBookOrMagazineByIsbn(
      @PathVariable("isbn") @NotBlank String isbn) {
    return new ResponseEntity<>(bookLibraryService.searchBookOrMagazineByIsbn(isbn), HttpStatus.OK);
  }

  /**
   * Search book or magazine by author email id response entity.
   *
   * @param emailId the email id
   * @return the response entity
   */
  @GetMapping("/book-magazine/author/{emailId}")
  public ResponseEntity<List<BookAndMagazine>> searchBookOrMagazineByAuthorEmailId(
      @PathVariable("emailId") @NotBlank String emailId) {
    return new ResponseEntity<>(bookLibraryService.searchBookOrMagazineByAuthorEmailId(emailId), HttpStatus.OK);
  }

}
