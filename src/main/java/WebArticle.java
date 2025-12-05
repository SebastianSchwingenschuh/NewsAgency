import java.time.LocalDateTime;

public class WebArticle extends Article {
    String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if ( link == null || !(link.contains("http://") || link.contains("https://"))) {
            throw new IllegalArgumentException("Link must not be null and start with http:// or https://!");
        }
        this.link = link;
    }

    public WebArticle(String author, String title, LocalDateTime publicationTime, NewsCategory category, String link) {
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author must not be null or blank!");
        }
        if (link == null || !(link.contains("http://") || link.contains("https://"))) {
            throw new IllegalArgumentException("Link must not be null and start with http:// or https://!");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or blank!");
        }
        
        this.author = author;
        this.title = title;
        this.publicationTime = publicationTime;
        this.category = category;
        this.link = link;
    }

    public String getTitleTruncated() {
        // 27 zeichen + ...
        int maxLength = 30;
        if (title.length() <= maxLength)
            return title;


        return title.substring(0, maxLength - 3) + "...";
    }

    @Override
    public String toString() {
        // "2023-11-17 17:33 Der Standard - So nutzen Konzerne und Reic... (POLITICS)"
        return getPublicationTimePretty() + " " + getAuthor() + " - " + getTitleTruncated() + " (" + getCategory() + ")";
    }
}
