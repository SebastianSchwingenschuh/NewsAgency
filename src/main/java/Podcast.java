import java.time.LocalDateTime;

public class Podcast extends Article {
    int duration;

    public int getDuration() {
        return duration;
    }

    public Podcast(String author, String title, LocalDateTime publicationTime, NewsCategory category, int duration) {
        this.author = author;
        this.title = title;
        this.publicationTime = publicationTime;
        this.category = category;
        this.duration = duration;
    }

    @Override
    public String getTitleTruncated() {
        //  27 + ...
        if (super.title.length() <= 27)
            return super.title.substring(0, 27);
        else
            return super.title.substring(0, 27) + "...";

    }
}
