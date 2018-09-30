package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BookRepository;
import views.html.book.create;
import views.html.book.edit;
import views.html.book.index;
import views.html.book.show;
import views.html.errors.*;
import views.html.errors._404;

import javax.inject.Inject;

public class BookController extends Controller {

    private final BookRepository bookRepository;
    private final FormFactory formFactory;

    @Inject
    public BookController(BookRepository bookRepository, FormFactory formFactory) {
        this.bookRepository = bookRepository;
        this.formFactory = formFactory;
    }

    public Result index() {
        return ok(index.render(bookRepository.allBooks()));
    }

    public Result create() {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(create.render(bookForm));
    }

    public Result show(Integer id) {
        Book book = bookRepository.findById(id);
        if(book == null)
            return notFound("Book not found");

        return ok(show.render(book));
    }

    public Result edit(Integer id) {
        Book book = bookRepository.findById(id);
        if(book == null)
            return notFound(_404.render());

        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        return ok(edit.render(bookForm));
    }

    public Result update() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        if(bookForm.hasErrors()) {
            flash("danger", "Please correct the below form");
            return badRequest(create.render(bookForm));
        }

        Book book = bookForm.get();
        Book oldBook = bookRepository.findById(book.id);

        if(oldBook == null) {
            flash("danger", "Book not found");
            return notFound();
        } else {
            oldBook.setTitle(book.getTitle());
            oldBook.setPrice(book.getPrice());
            oldBook.setAuthor(book.getAuthor());
        }

        flash("success", "Book has been updated successfully");

        return ok();
    }

    public Result save() {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        if(bookForm.hasErrors()) {
            flash("danger", "Please correct the below form");
            return badRequest(create.render(bookForm));
        }

        Book book = bookForm.get();
        bookRepository.addBook(book);
        flash("success", "Book has been created successfully");
        return redirect(routes.BookController.index());
    }

    public Result destroy(Integer id) {
        Book book = bookRepository.findById(id);
        if(book == null) {
            flash("danger", "Book not found.");
            return notFound();
        }

        bookRepository.removeBook(book);
        flash("success", "Book deleted successfully.");
        return ok();
    }
}
