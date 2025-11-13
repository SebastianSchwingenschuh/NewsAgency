import java.time.LocalDateTime;

public abstract class Article {
    String author;
    String title;
    LocalDateTime publicationTime;
    NewsCategory category;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getPublicationTime() {
        return publicationTime;
    }

    public NewsCategory getCategory() {
        return category;
    }
}
