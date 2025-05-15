package ea.lamda_app;

@FunctionalInterface
public interface SongFilter
{
    boolean filter(Song song);
}
