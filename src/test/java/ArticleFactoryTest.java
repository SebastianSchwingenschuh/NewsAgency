import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ArticleFactoryTest {
    @Test
    void testInterface() {
        assertEquals(true, Modifier.isInterface(ArticleFactory.class.getModifiers()));
    }

    @Test
    void testInheritance() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();
        PodcastFactory podcastFactory = new PodcastFactory();

        assertEquals(true, webArticleFactory instanceof ArticleFactory);
        assertEquals(true, podcastFactory instanceof ArticleFactory);
    }

    @Test
    void testCreatePodcast() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10 12:00;SPORTS;ballesterer;Sportwetten: Endstation Gefaengnis;59");
        Podcast podcast = (Podcast)article;

        assertEquals("ballesterer", podcast.getAuthor());
        assertEquals("Sportwetten: Endstation Gefaengnis", podcast.getTitle());
        assertEquals(LocalDateTime.of(2023, 11, 10, 12, 0), podcast.getPublicationTime());
        assertEquals(NewsCategory.SPORTS, podcast.getCategory());
        assertEquals(59, podcast.getDuration());
    }

    @Test
    void testCreatePodcastWithWhitespaces() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("      2023-10-20 10:00    ; LIFESTYLE  ;   Der Standard    ; Fleisch sollte uns wieder mehr wert sein   ;                      25             ");
        Podcast podcast = (Podcast)article;

        assertEquals("Der Standard", podcast.getAuthor());
        assertEquals("Fleisch sollte uns wieder mehr wert sein", podcast.getTitle());
        assertEquals(LocalDateTime.of(2023, 10, 20, 10, 0), podcast.getPublicationTime());
        assertEquals(NewsCategory.LIFESTYLE, podcast.getCategory());
        assertEquals(25, podcast.getDuration());
    }

    @Test
    void testCreatePodcastWithTooShortLine() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10 12:00;SPORTS;Sportwetten: Endstation Gefaengnis;59");

        assertEquals(null, article);
    }

    @Test
    void testCreatePodcastWithTooLongLine() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10;12:00;SPORTS;ballesterer;Sportwetten: Endstation Gefaengnis;59");

        assertEquals(null, article);
    }

    @Test
    void testCreatePodcastWithInvalidDuration() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10 12:00;SPORTS;ballesterer;Sportwetten: Endstation Gefaengnis;0");

        assertEquals(null, article);
    }

    @Test
    void testCreatePodcastWithInvalidAuthor() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10 12:00;SPORTS;   ;Sportwetten: Endstation Gefaengnis;59");

        assertEquals(null, article);
    }

    @Test
    void testCreatePodcastWithInvalidTitle() {
        PodcastFactory podcastFactory = new PodcastFactory();

        Article article = podcastFactory.createFromString("2023-11-10 12:00;SPORTS;ballesterer;;59");

        assertEquals(null, article);
    }

    @Test
    void testCreateWebArticle() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;CULTURE;Die Presse;The Crown-Spoiler: Prinzessin Diana stirbt!;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");
        WebArticle webArticle = (WebArticle) article;

        assertEquals("Die Presse", webArticle.getAuthor());
        assertEquals("The Crown-Spoiler: Prinzessin Diana stirbt!", webArticle.getTitle());
        assertEquals(LocalDateTime.of(2023, 11, 16, 16, 11), webArticle.getPublicationTime());
        assertEquals(NewsCategory.CULTURE, webArticle.getCategory());
        assertEquals("https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt", webArticle.getLink());
    }

    @Test
    void testCreateWebArticleWithWhitespaces() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("    2023-11-14 15:48  ;   SPORTS ;        OOENachrichten              ; LASK-Kicker bedrohte Blau-Weiss-Fan: Werden dich durch ganz Linz jagen  ;            https://www.nachrichten.at/sport/fussball/lask/lask-kicker-bedrohte-blau-weiss-fan-werden-dich-durch-ganz-linz-jagen   ");
        WebArticle webArticle = (WebArticle) article;

        assertEquals("OOENachrichten", webArticle.getAuthor());
        assertEquals("LASK-Kicker bedrohte Blau-Weiss-Fan: Werden dich durch ganz Linz jagen", webArticle.getTitle());
        assertEquals(LocalDateTime.of(2023, 11, 14, 15, 48), webArticle.getPublicationTime());
        assertEquals(NewsCategory.SPORTS, webArticle.getCategory());
        assertEquals("https://www.nachrichten.at/sport/fussball/lask/lask-kicker-bedrohte-blau-weiss-fan-werden-dich-durch-ganz-linz-jagen", webArticle.getLink());
    }

    @Test
    void testCreateWebArticleWithTooShortLine() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;CULTURE;The Crown-Spoiler: Prinzessin Diana stirbt!;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");

        assertEquals(null, article);
    }

    @Test
    void testCreateWebArticleWithTooLongLine() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;Eva Dinnewitzer;CULTURE;Die Presse;The Crown-Spoiler: Prinzessin Diana stirbt!;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");

        assertEquals(null, article);
    }

    @Test
    void testCreateWebArticleWithInvalidLink() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;CULTURE;Die Presse;The Crown-Spoiler: Prinzessin Diana stirbt!;www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");

        assertEquals(null, article);
    }

    @Test
    void testCreateWebArticleWithInvalidAuthor() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;CULTURE;;The Crown-Spoiler: Prinzessin Diana stirbt!;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");

        assertEquals(null, article);
    }

    @Test
    void testCreateWebArticleWithInvalidTitle() {
        WebArticleFactory webArticleFactory = new WebArticleFactory();

        Article article = webArticleFactory.createFromString("2023-11-16 16:11;CULTURE;Die Presse;      ;https://www.diepresse.com/17828909/the-crown-spoiler-prinzessin-diana-stirbt");

        assertEquals(null, article);
    }
}