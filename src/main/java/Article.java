import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Article implements Comparable<Article>{
    @Override
    public int compareTo(Article o) {
        return 0;
    }

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

    public String getTitleTruncated() {
        return title;
    }

    public String getPublicationTimePretty() {
        // "2023-06-03 14:19"
        String publicationTimePretty = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        publicationTimePretty = publicationTime.format(dateTimeFormatter);
        return publicationTimePretty;
    }
}
