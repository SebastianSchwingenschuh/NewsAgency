import java.time.Month;

public class NovemberSubscriber extends ArticleReader implements NewsPushObserver {
    @Override
    public void update(Article article) {
        if(article.publicationTime.getMonth() == Month.NOVEMBER){
            this.articlesReadCount++;
        }
    }
}
