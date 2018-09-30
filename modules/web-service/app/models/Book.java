package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Book extends Model implements Serializable {

    @Id
    @Constraints.Required
    public Integer id;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Constraints.MinLength(5)
    public String title;

    @Constraints.Required
    @Constraints.Max(100)
    @Constraints.Min(5)
    public Integer price;

    @Constraints.Required
    public String author;

    public static final Finder<Integer, Book> find = new Finder<>(Book.class);

    public Book() { }

    public Book(Integer id, String title, Integer price, String author) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book that = (Book) o;

        return Objects.equals(this.author, that.author) &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.price, that.price) &&
                Objects.equals(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, id, price, title);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("author = " + author)
                .add("id = " + id)
                .add("price = " + price)
                .add("title = " + title)
                .toString();
    }
}