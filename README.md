# Book Library Service

This service provide a simple library system.

## Resources

Given resources:

* [`authors.csv`](src/main/resources/org/echocat/kata/java/part1/data/authors.csv): Contains authors with its `email`
  , `firstName` and `lastName`.
* [`books.csv`](src/main/resources/org/echocat/kata/java/part1/data/books.csv): Contains books with its `title`
  , `description`, one or more `authors` and an `isbn`.
* [`magazines.csv`](src/main/resources/org/echocat/kata/java/part1/data/magazines.csv): Contains magazines with
  its `title`, one or more `authors`, a `publishedAt` and an `isbn`.

### Main tasks

1. Your software should read all data from the given CSV files in a meaningful structure.

2. Print out all books and magazines (could be a GUI, console, …) with all their details (with a meaningful output
   format).
3. Find a book or magazine by its `isbn`.

4. Find all books and magazines by their `authors`’ email.

5. Print out all books and magazines with all their details sorted by `title`. This sort should be done for books and
   magazines together.
   
