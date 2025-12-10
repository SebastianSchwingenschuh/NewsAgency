public class Vegetarian extends ArticleReader implements NewsPullObserver{
    @Override
    public void update(NewsPullSubject subject) {
        if(subject.latestReleasedArticle.title.toUpperCase().contains(("Fleisch").toUpperCase())){
            this.articlesReadCount++;
        }
    }
}
