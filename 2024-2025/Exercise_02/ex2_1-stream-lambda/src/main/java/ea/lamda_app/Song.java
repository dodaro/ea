package ea.lamda_app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song
{
    public static boolean isLongSong(final Song song)
    {
        return song.getDuration() > 300;
    }

    private String title;

    private int duration;

    public void modifica(final Consumer<Song> consumer)
    {
        consumer.accept(this);
    }

    @Override
    public String toString()
    {
        return title;
    }
}
