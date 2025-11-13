import java.time.LocalDateTime;

public class WebArticle extends Article {
    String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public WebArticle(String author, String title, LocalDateTime publicationTime, NewsCategory category, String link) {
        this.author = author;
        this.title = title;
        this.publicationTime = publicationTime;
        this.category = category;
        this.link = link;
    }
}
