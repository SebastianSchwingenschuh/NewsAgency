import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.PriorityQueue;

public class NewsAgency {
    PriorityQueue<Article> articles = new PriorityQueue<>(
        (a, b) -> a.getPublicationTime().compareTo(b.getPublicationTime())
    );
    Article latestReleasedArticle = null;

    public Article getLatestReleasedArticle() {
        return latestReleasedArticle;
    }
    
    public void addArticle(Article article) {
        if(article != null) {
            articles.add(article);
        }
    }

    public boolean hasMoreArticles() {
        return !articles.isEmpty();
    }

    private Article getOldestArticle() {
        return articles.peek(); // O(1), returns head
    }

    public void releaseArticle() {
        Article article = articles.poll(); // O(log n), removes and returns head
        if (article == null) {
            throw new NewsException("No articles to release!");
        }
        latestReleasedArticle = article;
    }

    public void addArticlesFromFile(String s, ArticleFactory articleFactory) {
        try{
            List<String> lines = Files.readAllLines(Path.of(s));
            // Parse lines using factory and add articles
            for (String line : lines) {
                Article article = articleFactory.createFromString(line);
                if (article != null) addArticle(article);
            }
        }
        catch(IOException e){
            throw new NewsException("Oh no, something happened while loading from file!", e);
        }
    }
}
