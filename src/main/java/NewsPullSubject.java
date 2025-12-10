import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class NewsPullSubject extends NewsAgency {
    List<NewsPullObserver> observers = new ArrayList<NewsPullObserver>();
    public void registerObserver(NewsPullObserver observer) {
        if(observer != null) {
            this.observers.add(observer);
        }
    }

    public void unregisterObserver(NewsPullObserver observer) {
        if(observer != null) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void releaseArticle() {
        super.releaseArticle();
        for(NewsPullObserver observer : observers) {
            observer.update(this);
        }
    }
}
