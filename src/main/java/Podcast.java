import java.time.LocalDateTime;

public class Podcast extends Article {
    int duration;

    public int getDuration() {
        return duration;
    }

    public Podcast(String author, String title, LocalDateTime publicationTime, NewsCategory category, int duration) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank!");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be at least 1!");
        }
        if(author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author must not be null or blank!");
        }
        this.author = author;
        this.title = title;
        this.publicationTime = publicationTime;
        this.category = category;
        this.duration = duration;
    }

    @Override
    public String getTitleTruncated() {
        //  27 + ...
        int maxLength = 30;
        if (super.title.length() <= maxLength
        )
            return super.title;
        else
            return super.title.substring(0, maxLength - 3) + "...";

    }

    @Override
    public String toString() {
        // "2023-09-18 13:00 ballesterer - Superjuden: Juedische Ident... (SPORTS) - 0:50"
        return getPublicationTimePretty() + " " + getAuthor() + " - " + getTitleTruncated() + " (" + getCategory() + ") - " + getDurationPretty();
    }

    private String getDurationPretty() {
        // minuten in HH:MM format
        int duration = getDuration();
        int hours = 0;
        String durationPretty = "0";
        while (duration >= 60) {  //solange im stunden bereich
            hours++;
            duration -= 60;
        }
        if (hours < 10 && hours > 0) {
            durationPretty += hours;
        }
        else {
            durationPretty = String.valueOf(hours);
        }
        durationPretty += ":";
        if(duration < 10) {
            durationPretty += "0" + duration;
        }
        else {
            durationPretty += duration;
        }
        return durationPretty;
    }
}