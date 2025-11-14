import java.util.PriorityQueue;

public class NewsAgency {
    PriorityQueue<Article> articles = new PriorityQueue<>();
    Article latestReleasedArticle;

    public Article getLatestReleasedArticle() {
        return latestReleasedArticle;
    }
    
    public void addArticle(Article article) {
        articles.add(article);
    }
    
    public void addArticlesFromFile(Article article) {
        articles.add(article);
    }
    
    public boolean hasMoreArticles() {
        return !articles.isEmpty();
    }
    
    public void releaseArticle(){
        articles.remove(latestReleasedArticle); // was ist latestReleasedArticle?
    }
}
