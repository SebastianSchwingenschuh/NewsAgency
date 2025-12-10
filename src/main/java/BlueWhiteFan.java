public class BlueWhiteFan extends ArticleReader implements NewsPushObserver {
    @Override
    public void update(Article article) {
        if(article.title.toUpperCase().contains(("Blau").toUpperCase()) || article.title.toUpperCase().contains(("Weiss").toUpperCase())){
            this.articlesReadCount++;            
        }
    }
}
