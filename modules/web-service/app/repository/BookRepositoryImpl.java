package repository;

import models.Book;

import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public List<Book> allBooks() {
        return Book.find.all();
    }

    @Override
    public Book findById(Integer id) {
        return Book.find.byId(id);
    }

    @Override
    public void addBook(Book book) {
        book.save();
    }

    @Override
    public void removeBook(Book book) {
        book.delete();
    }
}
