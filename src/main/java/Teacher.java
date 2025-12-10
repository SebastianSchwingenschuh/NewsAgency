public class Teacher extends ArticleReader implements NewsPushObserver {
    @Override
    public void update(Article article) {
        if(article.category.equals(NewsCategory.CULTURE) || article.category.equals(NewsCategory.POLITICS)){
            this.articlesReadCount++;
        }
    }
}
