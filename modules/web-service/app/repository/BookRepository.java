package repository;

import models.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository {
    public List<Book> allBooks();

    public Book findById(Integer id);

    public void addBook(Book book);

    public void removeBook(Book book);
}
