import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WebArticleTest {
    @Test
    void testInheritance() {
        WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        assertEquals(true, webArticle instanceof Article);
    }

    @Test
    void testConstructorValidValues() {
        WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        assertEquals("OOENachrichten", webArticle.getAuthor());
        assertEquals("Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", webArticle.getTitle());
        assertEquals(LocalDateTime.of(2023, 11, 14, 12, 2), webArticle.getPublicationTime());
        assertEquals(NewsCategory.SPORTS, webArticle.getCategory());
        assertEquals("https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023", webArticle.getLink());
    }

    @Test
    void testConstructorWithNullAuthor() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle(null, "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Author must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithBlankAuthor() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle("    ", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Author must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithNullTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle("OOENachrichten", null, LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Title must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithBlankTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle("OOENachrichten", "", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Title must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithNullLink() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, null);
        });

        assertEquals("Link must not be null and start with http:// or https://!", ex.getMessage());
    }

    @Test
    void testConstructorWithWrongLink() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "htps://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Link must not be null and start with http:// or https://!", ex.getMessage());
    }

    @Test
    void testSetLinkWithValidLink() {
        WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        webArticle.setLink("http://blauweiss-linz.at/");

        assertEquals("http://blauweiss-linz.at/", webArticle.getLink());
    }

    @Test
    void testSetLinkWithNullLink() {
        WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            webArticle.setLink(null);
        });

        assertEquals("Link must not be null and start with http:// or https://!", ex.getMessage());
    }

    @Test
    void testSetLinkWithWrongLink() {
        WebArticle webArticle = new WebArticle("OOENachrichten", "Derby-Versprechen eingehalten: Bei Blau-Weiss Linz regiert die Leberkaessemmel", LocalDateTime.of(2023, 11, 14, 12, 2), NewsCategory.SPORTS, "https://www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            webArticle.setLink("www.nachrichten.at/sport/fussball/blauweiss-linz/derby-versprechen-eingehalten-bei-blau-weiss-linz-regiert-die-leberkaessemmel;art102094,3897023");
        });

        assertEquals("Link must not be null and start with http:// or https://!", ex.getMessage());
    }

    @Test
    void testGetTruncatedTitle() {
        WebArticle webArticle = new WebArticle("Der Standard", "Berlin, Paris und Rom einig ueber Prinzipien von KI-Regulierung in EU", LocalDateTime.of(2023, 11, 18, 20, 27), NewsCategory.SCIENCE, "https://www.derstandard.at/story/3000000195820/berlin-paris-und-rom-einig-ueber-prinzipien-von-ki-regulierung-in-eu");

        assertEquals("Berlin, Paris und Rom einig...", webArticle.getTitleTruncated());
    }

    @Test
    void testGetTruncatedShortTitle() {
        WebArticle webArticle = new WebArticle("HTL Leonding News", "Schoene Herbstferien!", LocalDateTime.of(2023, 10, 25, 13, 35), NewsCategory.LIFESTYLE, "https://www.htl-leonding.at/2023/10/25/schoene-herbstferien-2/");

        assertEquals("Schoene Herbstferien!", webArticle.getTitleTruncated());
    }

    @Test
    void testGetTruncatedTitleWithExactTruncationLength() {
        WebArticle webArticle = new WebArticle("HTL Leonding News", "Kennenlerntage 1AHITM & 1BHITM", LocalDateTime.of(2023, 10, 8, 8, 00), NewsCategory.CULTURE, "https://www.htl-leonding.at/2023/10/08/kennenlerntage-1ahitm-und-1bhitm/");

        assertEquals("Kennenlerntage 1AHITM & 1BHITM", webArticle.getTitleTruncated());
    }

    @Test
    void testGetPrettyPublicationTime() {
        WebArticle webArticle = new WebArticle("Die Presse", "Wie Kiews Bewohner mit den Angriffen leben", LocalDateTime.of(2023, 6, 3, 14, 19), NewsCategory.POLITICS, "https://www.diepresse.com/6295422/wie-kiews-bewohner-mit-den-angriffen-leben");

        assertEquals("2023-06-03 14:19", webArticle.getPublicationTimePretty());
    }

    @Test
    void testToString() {
        WebArticle webArticle = new WebArticle("Der Standard", "So nutzen Konzerne und Reiche die EU-Freiheiten aus", LocalDateTime.of(2023, 11, 17, 17, 33), NewsCategory.POLITICS, "https://www.derstandard.at/story/3000000195751/ein-europa-der-schlupfl246cher");

        assertEquals("2023-11-17 17:33 Der Standard - So nutzen Konzerne und Reic... (POLITICS)", webArticle.toString());
    }
}