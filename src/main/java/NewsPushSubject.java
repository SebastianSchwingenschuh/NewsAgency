import java.util.ArrayList;
import java.util.List;

public class NewsPushSubject extends NewsAgency{
    List<NewsPushObserver> observers = new ArrayList<NewsPushObserver>();
    public void registerObserver(NewsPushObserver observer) {
        if(observer != null &&  !observers.contains(observer))
            observers.add(observer);
    }

    public void unregisterObserver(NewsPushObserver observer) {
        if(observer != null)
            observers.remove(observer);
    }

    @Override
    public void releaseArticle() {
        super.releaseArticle();
        for (NewsPushObserver observer : observers){
            observer.update(this.latestReleasedArticle);
        }
    }
}
