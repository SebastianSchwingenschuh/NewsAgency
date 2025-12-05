
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsPullTest {
    @Test
    void testInheritance() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        assertEquals(true, newsAgency instanceof NewsAgency);

        Nerd nerd = new Nerd();
        StandardSubscriber standardSubscriber = new StandardSubscriber();
        Vegetarian vegetarian = new Vegetarian();

        assertEquals(true, nerd instanceof NewsPullObserver);
        assertEquals(true, nerd instanceof ArticleReader);
        assertEquals(true, standardSubscriber instanceof NewsPullObserver);
        assertEquals(true, standardSubscriber instanceof ArticleReader);
        assertEquals(true, vegetarian instanceof NewsPullObserver);
        assertEquals(true, vegetarian instanceof ArticleReader);
    }

    @Test
    void testOneObserverReceivesRelevantArticles() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");

        Vegetarian vegetarian = new Vegetarian();

        newsAgency.registerObserver(vegetarian);

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleNormalOderFleisch);

        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(1, vegetarian.getArticlesReadCount());
    }

    @Test
    void testOneObserverDoesntReceiveArticlesAfterUnregistering() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");

        StandardSubscriber standardSubscriber = new StandardSubscriber();

        newsAgency.registerObserver(standardSubscriber);

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKonzerne);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(webArticleNormalOderFleisch);

        assertEquals(0, standardSubscriber.getArticlesReadCount());

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(2, standardSubscriber.getArticlesReadCount());

        newsAgency.unregisterObserver(standardSubscriber);

        newsAgency.releaseArticle();
        newsAgency.releaseArticle();

        assertEquals(2, standardSubscriber.getArticlesReadCount());
    }

    @Test
    void testMultipleObservers() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        Nerd nerd = new Nerd();
        StandardSubscriber standardSubscriber = new StandardSubscriber();
        Vegetarian vegetarian = new Vegetarian();

        newsAgency.registerObserver(nerd);
        newsAgency.registerObserver(standardSubscriber);
        newsAgency.registerObserver(vegetarian);

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        newsAgency.addArticle(podcastStaatskuenstler);
        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(podcastSuperjuden);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleNormalOderFleisch);
        newsAgency.addArticle(webArticleKonzerne);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleKennenlerntage);

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(1, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(1, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(1, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(1, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(2, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(3, standardSubscriber.getArticlesReadCount());
        assertEquals(1, vegetarian.getArticlesReadCount());

        newsAgency.releaseArticle();

        assertEquals(1, nerd.getArticlesReadCount());
        assertEquals(4, standardSubscriber.getArticlesReadCount());
        assertEquals(1, vegetarian.getArticlesReadCount());
    }

    @Test
    void testAddArticlesFromFileWithMultipleObservers() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        Nerd nerd = new Nerd();
        StandardSubscriber standardSubscriber = new StandardSubscriber();
        Vegetarian vegetarian = new Vegetarian();

        newsAgency.registerObserver(nerd);
        newsAgency.registerObserver(standardSubscriber);
        newsAgency.registerObserver(vegetarian);

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastStaatskuenstler);
        newsAgency.addArticle(webArticleNormalOderFleisch);

        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(webArticleKonzerne);

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());

        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(podcastSuperjuden);

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        while(newsAgency.hasMoreArticles()) {
            newsAgency.releaseArticle();
        }

        assertEquals(9, nerd.getArticlesReadCount());
        assertEquals(19, standardSubscriber.getArticlesReadCount());
        assertEquals(4, vegetarian.getArticlesReadCount());
    }

    @Test
    void testMultipleObserversRegisterUnregister() {
        NewsPullSubject newsAgency = new NewsPullSubject();

        Nerd nerd = new Nerd();
        StandardSubscriber standardSubscriber = new StandardSubscriber();
        Vegetarian vegetarian = new Vegetarian();

        newsAgency.registerObserver(nerd);
        //standard subscriber not registered yet
        newsAgency.registerObserver(vegetarian);

        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        WebArticle webArticleNormalOderFleisch = new WebArticle("Der Standard", "Normal oder mit Fleisch? Platin-Effie für werbewirksamste Kampagne an Burger King", LocalDateTime.of(2023, 11, 17, 22, 0), NewsCategory.LIFESTYLE, "https://www.derstandard.at/story/3000000195480/normal-oder-mit-fleisch-platin-effie-fuer-werbewirksamste-kampagne-an-burger-king");
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");


        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(webArticleKiew);
        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastZuSpaet);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(podcastSuperjuden);
        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(podcastStaatskuenstler);
        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleHerbstferien);

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());

        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(webArticleNormalOderFleisch);
        newsAgency.addArticle(webArticleKonzerne);

        assertEquals(0, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(0, vegetarian.getArticlesReadCount());

        for(int i = 0; i < 30; i++) {
            newsAgency.releaseArticle();
        }

        assertEquals(5, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(2, vegetarian.getArticlesReadCount());

        newsAgency.unregisterObserver(nerd);
        newsAgency.registerObserver(standardSubscriber);

        assertEquals(5, nerd.getArticlesReadCount());
        assertEquals(0, standardSubscriber.getArticlesReadCount());
        assertEquals(2, vegetarian.getArticlesReadCount());

        while(newsAgency.hasMoreArticles()) {
            newsAgency.releaseArticle();
        }

        assertEquals(5, nerd.getArticlesReadCount());
        assertEquals(8, standardSubscriber.getArticlesReadCount());
        assertEquals(4, vegetarian.getArticlesReadCount());
    }
}