package ea.lamda_app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MusicApp
{
    static
    {
        final Predicate<BigDecimal> p = MusicApp::verifica;
        final Predicate<BigDecimal> p2 = bd -> bd.longValue() > 100;
        //search(null, p.and(p2).or(n -> n.intValue() - 1 > 2));
    }

    static boolean verifica(final BigDecimal bd)
    {
        return bd.longValue() % 2 == 0;
    }

    public static void change(final Song song)
    {
        final Consumer<Song> consumer = s -> s.setDuration(100);
        song.modifica(consumer);
        //equivale a
        song.modifica(s -> s.setDuration(100));
    }

    public static void main(final String[] args)
    {
        final Album album = new Album("My Album");
        album.addSong(new Song("Hey you", 250));
        album.addSong(new Song("Wish you were here", 270));
        album.addSong(new Song("Echoes", 600));

        final List<Song> search = album.search(x -> x.getDuration() < 300);
        System.out.println("Duration < 300:");
        search.stream().forEach(System.out::println);
        System.out.println();

        album.search((final Song song) -> song.getTitle().split("\\s").length >= 3);
        methodRef(album);

        final BiPredicate<String, String> eqstr = (s1, s2) -> s1.equals(s2);
        final BiPredicate<String, String> eqstr2 = String::equals;
    }

    public static void methodRef(final Album album)
    {
        //statici
        final List<Song> search1 = album.search(x -> x.getDuration() > 250);
        final List<Song> search2 = album.search(Song::isLongSong);

        //metodi istanza di uno specifico oggetto
        final List<Song> s = album.search(album::isFirst);
        System.out.println("First Song:");
        s.forEach(System.out::println);
        System.out.println();

        // metodi istanza legati al tipo dell oggetto fornito in seguito
        final List<String> l = new ArrayList<>();
        for (final Song song : search1)
        {
            l.add(song.getTitle());
        }
        //equivale a
        System.out.println("Only title");
        search1.stream().map(x -> x.getTitle()).forEach(System.out::println);
        System.out.println();

        Collections.sort(l, String::compareToIgnoreCase);
        System.out.println("Sorted by Title: " + l);

        // Costruttori
        final Supplier<Song> ss = Song::new;
        final BiFunction<String, Integer, Song> sss = Song::new;
        // Crea un oggetto Song
        Song song = sss.apply("Imagine", 183);
    }

    public static void search1(final Album album)
    {
        final Predicate<Song> predicate = s -> s.getTitle().startsWith("W");
        album.search2(predicate.and(s -> s.getDuration() < 300));
    }

    public static List<BigDecimal> search2(final List<BigDecimal> numbers, final Predicate<BigDecimal> filter)
    {
        final List<BigDecimal> toRet = new ArrayList<>();
        for (final BigDecimal n : numbers)
        {
            if (filter.test(n))
            {
                toRet.add(n);
            }
        }
        return toRet;
    }
}
