import jdk.jfr.Category;

public class Nerd extends ArticleReader implements NewsPullObserver{
    @Override
    public void update(NewsPullSubject subject) {
        if(subject.latestReleasedArticle.category.equals(NewsCategory.SCIENCE)){
            this.articlesReadCount++;   // count erhöhen im nerd
        }
    }
}
