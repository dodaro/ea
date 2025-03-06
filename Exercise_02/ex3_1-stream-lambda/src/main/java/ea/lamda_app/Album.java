package ea.lamda_app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Album
{
    private final String name;
    private final List<Song> songs;
    public Album(final String name)
    {
        this.name = name;
        songs = new ArrayList<>();
    }

    public void addSong(final Song song)
    {
        songs.add(song);
    }

    public String getName()
    {
        return name;
    }

    public boolean isFirst(final Song song)
    {
        return songs.get(0).getTitle().equals(song.getTitle());
    }

    public List<Song> search(final SongFilter songFilter)
    {
        final List<Song> toRet = new ArrayList<>();
        for (final Song song : songs)
        {
            if (songFilter.filter(song))
            {
                toRet.add(song);
            }
        }
        return toRet;
    }
    public List<Song> search2(final Predicate<Song> songFilter)
    {
        final List<Song> toRet = new ArrayList<>();
        for (final Song song : songs)
        {
            if (songFilter.test(song))
            {
                toRet.add(song);
            }
        }
        return toRet;
    }
}
