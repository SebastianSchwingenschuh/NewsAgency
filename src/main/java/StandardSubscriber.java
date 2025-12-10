public class StandardSubscriber extends ArticleReader implements NewsPullObserver{
    @Override
    public void update(NewsPullSubject subject) {
        if(subject.latestReleasedArticle.author.equalsIgnoreCase(("Der Standard"))){
            this.articlesReadCount++;
        }
    }
}
