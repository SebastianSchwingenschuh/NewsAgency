import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;

public class NewsAgency {
    PriorityQueue<Article> articles = new PriorityQueue<>();
    Article latestReleasedArticle = null;

    public Article getLatestReleasedArticle() {
        return latestReleasedArticle;
    }
    
    public void addArticle(Article article) {
        if(article != null) {
            articles.add(article);
        }
    }
    
    public void addArticlesFromFile(Article article) {
        articles.add(article);
    }
    
    public boolean hasMoreArticles() {
        return !articles.isEmpty();
    }
    
    public void releaseArticle(){
        latestReleasedArticle = getOldestArticle();
        if(latestReleasedArticle == null){
            throw new NewsException("No articles to release!");
        }
        articles.remove(latestReleasedArticle);
    }

    private Article getOldestArticle() {
        Article oldestArticle = articles.peek();
        for (Article article : articles) {
            if (article.getPublicationTime().isBefore(oldestArticle.getPublicationTime()))
                oldestArticle = article;
        }
        return oldestArticle;
    }

    public void addArticlesFromFile(String s, ArticleFactory articleFactory) {
        if(!Files.exists(Path.of(s))){
            IOException ioException = new IOException("Oh no, something happened while loading from file!");
            throw new NewsException("Oh no, something happened while loading from file!",  ioException);
        }
        File
    }
}
