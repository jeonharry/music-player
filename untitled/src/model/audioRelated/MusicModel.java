package model.audioRelated;

public class MusicModel extends AudioModel
{
    private String lyric;
    public MusicModel(String audioName, String nameOfArtist, Genre genre, String link, String cover, String lyric)
    {
        super(audioName,nameOfArtist,genre,link,cover);
        this.lyric=lyric;
    }
    public String getLyric()
    {
        return this.lyric;
    }
    public void setLyric(String lyric)
    {
        this.lyric=lyric;
    }
    public String toString()
    {
        return super.toString()+"\nLyric: "+this.lyric;
    }
}
