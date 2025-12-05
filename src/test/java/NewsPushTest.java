
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsPushTest {
    @Test
    void testInheritance() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        assertEquals(true, newsAgency instanceof NewsAgency);

        BlueWhiteFan blueWhiteFan = new BlueWhiteFan();
        NovemberSubscriber novemberSubscriber = new NovemberSubscriber();
        Teacher teacher = new Teacher();

        assertEquals(true, blueWhiteFan instanceof NewsPushObserver);
        assertEquals(true, blueWhiteFan instanceof ArticleReader);
        assertEquals(true, novemberSubscriber instanceof NewsPushObserver);
        assertEquals(true, novemberSubscriber instanceof ArticleReader);
        assertEquals(true, teacher instanceof NewsPushObserver);
        assertEquals(true, teacher instanceof ArticleReader);
    }

    @Test
    void testOneObserverReceivesRelevantArticles() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        BlueWhiteFan blueWhiteFan = new BlueWhiteFan();

        newsAgency.registerObserver(blueWhiteFan);

        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleLeberkaese);

        assertEquals(0, blueWhiteFan.getArticlesReadCount());

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(1, blueWhiteFan.getArticlesReadCount());
    }

    @Test
    void testOneObserverDoesntReceiveArticlesAfterUnregistering() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        NovemberSubscriber novemberSubscriber = new NovemberSubscriber();

        newsAgency.registerObserver(novemberSubscriber);

        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(webArticleKonzerne);
        newsAgency.addArticle(webArticleKiRegulierung);

        assertEquals(0, novemberSubscriber.getArticlesReadCount());

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(2, novemberSubscriber.getArticlesReadCount());

        newsAgency.unregisterObserver(novemberSubscriber);

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(2, novemberSubscriber.getArticlesReadCount());
    }

    @Test
    void testMultipleObservers() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        BlueWhiteFan blueWhiteFan = new BlueWhiteFan();
        NovemberSubscriber novemberSubscriber = new NovemberSubscriber();
        Teacher teacher = new Teacher();

        newsAgency.registerObserver(blueWhiteFan);
        newsAgency.registerObserver(novemberSubscriber);
        newsAgency.registerObserver(teacher);

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        newsAgency.addArticle(webArticleNormalOderFleisch);
        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(podcastSuperjuden);
        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(podcastStaatskuenstler);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(webArticleKonzerne);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKiRegulierung);

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(1, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(2, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(2, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(3, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(4, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(5, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(5, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(1, novemberSubscriber.getArticlesReadCount());
        assertEquals(5, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(1, blueWhiteFan.getArticlesReadCount());
        assertEquals(2, novemberSubscriber.getArticlesReadCount());
        assertEquals(5, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(1, blueWhiteFan.getArticlesReadCount());
        assertEquals(3, novemberSubscriber.getArticlesReadCount());
        assertEquals(6, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(1, blueWhiteFan.getArticlesReadCount());
        assertEquals(4, novemberSubscriber.getArticlesReadCount());
        assertEquals(6, teacher.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(1, blueWhiteFan.getArticlesReadCount());
        assertEquals(5, novemberSubscriber.getArticlesReadCount());
        assertEquals(6, teacher.getArticlesReadCount());
    }

    @Test
    void testAddArticlesFromFileWithMultipleObservers() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        BlueWhiteFan blueWhiteFan = new BlueWhiteFan();
        NovemberSubscriber novemberSubscriber = new NovemberSubscriber();
        Teacher teacher = new Teacher();

        newsAgency.registerObserver(blueWhiteFan);
        newsAgency.registerObserver(novemberSubscriber);
        newsAgency.registerObserver(teacher);

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastStaatskuenstler);

        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        newsAgency.addArticle(webArticleNormalOderFleisch);
        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(webArticleKonzerne);

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());

        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(podcastSuperjuden);

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        while(newsAgency.hasMoreArticles()) {
            newsAgency.releaseArticle();
        }

        assertEquals(8, blueWhiteFan.getArticlesReadCount());
        assertEquals(26, novemberSubscriber.getArticlesReadCount());
        assertEquals(18, teacher.getArticlesReadCount());
    }

    @Test
    void testMultipleObserversRegisterUnregister() {
        NewsPushSubject newsAgency = new NewsPushSubject();

        BlueWhiteFan blueWhiteFan = new BlueWhiteFan();
        NovemberSubscriber novemberSubscriber = new NovemberSubscriber();
        Teacher teacher = new Teacher();

        newsAgency.registerObserver(blueWhiteFan);
        newsAgency.registerObserver(novemberSubscriber);
        //teacher not registered yet

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastStaatskuenstler);

        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(webArticleKonzerne);

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());

        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(webArticleNormalOderFleisch);
        newsAgency.addArticle(podcastSuperjuden);

        assertEquals(0, blueWhiteFan.getArticlesReadCount());
        assertEquals(0, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        for(int i = 0; i < 30; i++) {
            newsAgency.releaseArticle();
        }

        assertEquals(3, blueWhiteFan.getArticlesReadCount());
        assertEquals(8, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        newsAgency.unregisterObserver(novemberSubscriber);
        newsAgency.registerObserver(teacher);

        assertEquals(3, blueWhiteFan.getArticlesReadCount());
        assertEquals(8, novemberSubscriber.getArticlesReadCount());
        assertEquals(0, teacher.getArticlesReadCount());

        while(newsAgency.hasMoreArticles()) {
            newsAgency.releaseArticle();
        }

        assertEquals(8, blueWhiteFan.getArticlesReadCount());
        assertEquals(8, novemberSubscriber.getArticlesReadCount());
        assertEquals(6, teacher.getArticlesReadCount());
    }
}