package ea.lamda;

@FunctionalInterface
public interface SongFilter
{
    boolean filter(Song song);
}
