import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PodcastFactory implements ArticleFactory {
    @Override
    public Article createFromString(String s) {
        // "2023-11-10 12:00;SPORTS;ballesterer;Sportwetten: Endstation Gefaengnis;59"
        String[] fields =  s.split(";");
        
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i].trim();
        }

        if (fields.length != 5 || fields[2].isEmpty() || fields[3].isEmpty()) {
            return null;
        }
        int duration;
        try {
            duration = Integer.parseInt(fields[4]);
        } catch (NumberFormatException e) {
            return null;
        }
        if (duration <= 0) {
            return null;
        }

        return new Podcast(fields[2], fields[3], LocalDateTime.parse(fields[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), NewsCategory.valueOf(fields[1]), Integer.parseInt(fields[4]));
    }
}
