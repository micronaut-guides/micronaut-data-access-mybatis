package example.micronaut.domain;

import javax.validation.constraints.NotNull;

public class Book {

    public Book() {
    }

    public Book(@NotNull String isbn, @NotNull String name, Genre genre) {
        this.isbn = isbn;
        this.name = name;
        this.genre = genre;
    }

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String isbn;

    private Genre genre;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("id=");
        sb.append(id);
        sb.append(", name='");
        sb.append(name);
        sb.append("', isbn='");
        sb.append(isbn);
        sb.append("', genre='");
        sb.append(genre);
        sb.append("'}");
        return sb.toString();
    }
}
