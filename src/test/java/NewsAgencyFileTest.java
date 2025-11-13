import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewsAgencyFileTest {
    @Test
    void testLoadFromNonExistingFile() {
        NewsAgency newsAgency = new NewsAgency();

        NewsException exception = assertThrows(NewsException.class, () -> {
            newsAgency.addArticlesFromFile("data/haxi.csv", new PodcastFactory());
        });

        assertEquals("Oh no, something happened while loading from file!", exception.getMessage());
        assertEquals(true, exception.getCause() instanceof IOException);
    }

    @Test
    void testAddManuallyAndFromFileReleasesCorrectAmount() {
        NewsAgency newsAgency = new NewsAgency();

        WebArticle webArticleLeberkaese = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        Podcast podcastStaatskuenstler = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);
        Podcast podcastOesterreichsRolle = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);
        Podcast podcastSpendenaktion = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);
        WebArticle webArticleKiew = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");
        Podcast podcastLiebesgschichten = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);
        WebArticle webArticleKennenlerntage = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");
        WebArticle webArticleHerbstferien = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");
        Podcast podcastZuSpaet = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);
        WebArticle webArticleKiRegulierung = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");
        Podcast podcastSuperjuden = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        WebArticle webArticleKonzerne = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");

        newsAgency.addArticle(webArticleLeberkaese);
        newsAgency.addArticle(podcastStaatskuenstler);
        newsAgency.addArticle(podcastOesterreichsRolle);
        newsAgency.addArticle(podcastSpendenaktion);
        newsAgency.addArticle(webArticleKiew);

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());

        newsAgency.addArticle(podcastLiebesgschichten);
        newsAgency.addArticle(webArticleKennenlerntage);
        newsAgency.addArticle(webArticleHerbstferien);
        newsAgency.addArticle(podcastZuSpaet);

        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        newsAgency.addArticle(webArticleKiRegulierung);
        newsAgency.addArticle(podcastSuperjuden);
        newsAgency.addArticle(webArticleKonzerne);

        int counter = 0;
        while(newsAgency.hasMoreArticles()) {
            newsAgency.releaseArticle();
            counter++;
        }

        assertEquals(47, counter);
    }

    @Test
    void testLoadFromFilesReleaseInCorrectOrder() {
        NewsAgency newsAgency = new NewsAgency();

        newsAgency.addArticlesFromFile("data/podcasts.csv", new PodcastFactory());
        newsAgency.addArticlesFromFile("data/webarticles.csv", new WebArticleFactory());

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals(null, newsAgency.getLatestReleasedArticle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Wiedergeborene Blaue Nachzuegler", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Warum sich fuers Klima an Bilderrahmen festkleben?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Vienna: Besuch der alten Dame", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Vorerst haelt die blaue Festung trotz zahlreicher Skandale noch", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("ChatGPT wuerde laut aktueller Studie wohl linke Parteien waehlen", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Maurer ueber Koalition: Keine Garantien, Ziel ist Fortbestand", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Paszek macht gegen sie gerichtete Todesdrohung publik", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Fleischkonsum: Eine Schweinehalterin rechnet ab", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Geschichte des Alkohols - Kultur und Exzess", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("In der Mixed Zone: Dominik Thaller, Filmemacher", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("FC Blau Weiss Linz: Der Aufstieg vom Amateur zum Profiklub!", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Eine Anwaeltin predigt die Unschuldsvermutung - bis sie selber anklagt", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Fleisch sollte uns wieder mehr wert sein", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Welche Staedte die Superreichen bevorzugen", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("KI-Pionier vergleicht Risiken der kuenstlichen Intelligenz mit Klimakrise", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Doch kein Verbot fuer Gasheizungen - was jetzt?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Kuenstliche Intelligenz entschluesselt antike Schriftrollen und aerztliche Befunde", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Der importierte Nahostkonflikt", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Sportwetten: Endstation Gefaengnis", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Was, wenn Kickl Kanzler wird?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Intervallfasten: Selten essen und uralt werden", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Blau-Weiss zwingt LASK im Linzer Derby in die Knie", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("2:0-Sensation! Blau-Weiss schiesst LASK im Linz-Derby ab", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("LASK-Kicker bedrohte Blau-Weiss-Fan: Werden dich durch ganz Linz jagen", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Was bei einem Angriff auf 450 US-Atomwaffenlager passieren wuerde", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Die anstandsfreie Republik", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Moskaus treuer Kunde: Oesterreich kauft wieder mehr Gas aus Russland", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("The Crown-Spoiler: Prinzessin Diana stirbt!", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Essen Sie noch Fleisch?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Pipelines, Kuehe, Deponien: Wie loesen wir das Methan-Problem?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Premier League zog Everton zehn Punkte ab", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Jedermann-Regisseur Carsen: Ob es ein gutes Stueck ist, weiss ich noch nicht", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Schallenberg: Die Hamas muss verschwinden", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(true, newsAgency.hasMoreArticles());
        assertEquals("Macht das Internet keinen Spass mehr?", newsAgency.getLatestReleasedArticle().getTitle());

        newsAgency.releaseArticle();

        assertEquals(false, newsAgency.hasMoreArticles());
        assertEquals("So sieht die Fast-Fashion-Kollektion von McDonald's und Crocs aus", newsAgency.getLatestReleasedArticle().getTitle());
    }
}