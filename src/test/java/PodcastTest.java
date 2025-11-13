import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PodcastTest {
    @Test
    void testInheritance() {
        Podcast podcast = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);

        assertEquals(true, podcast instanceof Article);
    }

    @Test
    void testConstructorValidValues() {
        Podcast podcast = new Podcast("Der Standard", "Oesterreichs Rolle im Nahost-Konflikt", LocalDateTime.of(2023, 10, 21, 18, 30), NewsCategory.POLITICS, 50);

        assertEquals("Der Standard", podcast.getAuthor());
        assertEquals("Oesterreichs Rolle im Nahost-Konflikt", podcast.getTitle());
        assertEquals(LocalDateTime.of(2023, 10, 21, 18, 30), podcast.getPublicationTime());
        assertEquals(NewsCategory.POLITICS, podcast.getCategory());
        assertEquals(50, podcast.getDuration());
    }

    @Test
    void testConstructorWithNullAuthor() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast(null, "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        });

        assertEquals("Author must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithBlankAuthor() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast("", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        });

        assertEquals("Author must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithNullTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast("ballesterer", null, LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        });

        assertEquals("Title must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithBlankTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast("ballesterer", "     ", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);
        });

        assertEquals("Title must not be null or blank!", ex.getMessage());
    }

    @Test
    void testConstructorWithNullDuration() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 0);
        });

        assertEquals("Duration must be at least 1!", ex.getMessage());
    }

    @Test
    void testConstructorWithWrongDuration() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Podcast podcast = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, -50);
        });

        assertEquals("Duration must be at least 1!", ex.getMessage());
    }

    @Test
    void testGetTruncatedTitle() {
        Podcast podcast = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);

        assertEquals("Wir Staatskuenstler: Alte H...", podcast.getTitleTruncated());
    }

    @Test
    void testGetTruncatedShortTitle() {
        Podcast podcast = new Podcast("Schwammerltalk", "Spendenaktion Ukraine", LocalDateTime.of(2022, 4, 10, 14, 30), NewsCategory.POLITICS, 27);

        assertEquals("Spendenaktion Ukraine", podcast.getTitleTruncated());
    }

    @Test
    void testGetTruncatedTitleWithExactTruncationLength() {
        Podcast podcast = new Podcast("Schwammerltalk", "Liebesgschichten und HTLsachen", LocalDateTime.of(2022, 2, 15, 12, 40), NewsCategory.LIFESTYLE, 14);

        assertEquals("Liebesgschichten und HTLsachen", podcast.getTitleTruncated());
    }

    @Test
    void testGetPrettyPublicationTime() {
        Podcast podcast = new Podcast("OE1", "Wir Staatskuenstler: Alte Hunde - neue Tricks", LocalDateTime.of(2023, 10, 15, 17, 05), NewsCategory.CULTURE, 32);

        assertEquals("2023-10-15 17:05", podcast.getPublicationTimePretty());
    }

    @Test
    void testToString() {
        Podcast podcast = new Podcast("ballesterer", "Superjuden: Juedische Identitaeten im Stadion", LocalDateTime.of(2023, 9, 18, 13, 0), NewsCategory.SPORTS, 50);

        assertEquals("2023-09-18 13:00 ballesterer - Superjuden: Juedische Ident... (SPORTS) - 0:50", podcast.toString());
    }

    @Test
    void testToStringWithShortDuration() {
        Podcast podcast = new Podcast("Die Welt", "Deshalb kommen manche Menschen immer zu spaet", LocalDateTime.of(2023, 11, 8, 8, 5), NewsCategory.LIFESTYLE, 9);

        assertEquals("2023-11-08 08:05 Die Welt - Deshalb kommen manche Mensc... (LIFESTYLE) - 0:09", podcast.toString());
    }
}