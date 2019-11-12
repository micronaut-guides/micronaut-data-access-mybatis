package example.micronaut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class Genre {

    public Genre() {
    }

    public Genre(@NotNull String name) {
        this.name = name;
    }

    private Long id;

    @NotNull
    private String name;

    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
