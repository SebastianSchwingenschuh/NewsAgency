import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Test
    void testIsAbstract() {
        assertEquals(true, Modifier.isAbstract(Article.class.getModifiers()));
    }

    @Test
    void testCompareTo() {
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        LinkedList<Article> articles = new LinkedList<>();
        articles.add(podcastLiebesgschichten);
        articles.add(podcastSpendenaktion);
        articles.add(webArticleKiew);
        articles.add(podcastSuperjuden);
        articles.add(webArticleKennenlerntage);
        articles.add(podcastStaatskuenstler);
        articles.add(podcastOesterreichsRolle);
        articles.add(webArticleHerbstferien);
        articles.add(podcastZuSpaet);
        articles.add(webArticleLeberkaese);
        articles.add(webArticleKonzerne);
        articles.add(webArticleKiRegulierung);

        Collections.shuffle(articles);
        Collections.sort(articles);

        assertEquals(podcastLiebesgschichten, articles.get(0));
        assertEquals(podcastSpendenaktion, articles.get(1));
        assertEquals(webArticleKiew, articles.get(2));
        assertEquals(podcastSuperjuden, articles.get(3));
        assertEquals(webArticleKennenlerntage, articles.get(4));
        assertEquals(podcastStaatskuenstler, articles.get(5));
        assertEquals(podcastOesterreichsRolle, articles.get(6));
        assertEquals(webArticleHerbstferien, articles.get(7));
        assertEquals(podcastZuSpaet, articles.get(8));
        assertEquals(webArticleLeberkaese, articles.get(9));
        assertEquals(webArticleKonzerne, articles.get(10));
        assertEquals(webArticleKiRegulierung, articles.get(11));

    }
}