package it.unical.inf.ea.reflection.ex2.bean;

import java.io.Serializable;

public class MusicCd extends Cd implements Serializable
{
    public static final int CONST = 0;

    private static final long serialVersionUID = 1L;

    @WithSpace
    @NotNull
    public String author;

    @NotNull
    public String title;

    @Digits
    @NotNull
    private String codeSiae;

    @NotNull
    private Genre genre;

    @AtLeast(6)
    @NotNull
    private String[] tracks;

    public MusicCd()
    {
        initializeTracks(0);
    }

    public MusicCd(final String author, final String title, final String codeSiae, final Genre genre, final int numTracks)
    {
        this(author, title, numTracks);
        this.codeSiae = codeSiae;
        this.genre = genre;
    }

    private MusicCd(final String author, final String title, final int numTracks)
    {
        this.author = author;
        this.title = title;
        initializeTracks(numTracks);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final MusicCd other = (MusicCd) obj;
        if (codeSiae == null)
        {
            if (other.codeSiae != null)
            {
                return false;
            }
        }
        else if (!codeSiae.equals(other.codeSiae))
        {
            return false;
        }
        return true;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getCodeSiae()
    {
        return codeSiae;
    }

    public Genre getGenre()
    {
        return genre;
    }

    public int getNumTracks()
    {
        return tracks.length;
    }

    public String getSong(final int numTrack)
    {
        if (numTrack - 1 > tracks.length || numTrack < 1)
        {
            return null;
        }
        return tracks[numTrack - 1];
    }

    public String getTitle()
    {
        return title;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (codeSiae == null ? 0 : codeSiae.hashCode());
        return result;
    }

    public boolean record(final int numTrack, final String song)
    {
        if (numTrack - 1 > tracks.length || numTrack < 1)
        {
            return false;
        }
        tracks[numTrack - 1] = song;
        return true;
    }

    public void setAuthor(final String author)
    {
        this.author = author;
    }

    public void setCodeSiae(final String codeSiae)
    {
        this.codeSiae = codeSiae;
    }

    public void setGenre(final Genre genre)
    {
        this.genre = genre;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "MusicCd [author=" + author + ", title=" + title + ", codeSiae=" + codeSiae + ", genre=" + genre + ", numTracks="
                + tracks.length + "]";
    }

    private void initializeTracks(final int numTracks)
    {
        tracks = new String[numTracks];
    }
}
