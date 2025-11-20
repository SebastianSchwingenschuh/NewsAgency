import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WebArticleFactory implements ArticleFactory {
    @Override
    public Article createFromString(String s) {
        //"2023-11-16 16:11;CULTURE;Die Presse;The Crown-Spoiler: Prinzessin Diana stirbt!;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt"        
        String[] fields = s.split(";");
        
        for(int i = 0; i < fields.length; i++){
            fields[i] = fields[i].trim();
        }
        
        if(fields.length != 5 || fields[2].isEmpty() || fields[3].isEmpty() || fields[4].isEmpty() || !fields[4].contains("https")){
            return null;
        }
        
        return new WebArticle(fields[2], fields[3], LocalDateTime.parse(fields[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), NewsCategory.valueOf(fields[1]), fields[4]);
    }
}
